package com.college.management.controller;

import com.college.management.command.StudentCommand;
import com.college.management.command.UserCommand;
import com.college.management.model.AdminInformation;
import com.college.management.model.Student;
import com.college.management.model.User;
import com.college.management.model.UserPhoto;
import com.college.management.services.AdminService;
import com.college.management.services.PhotoService;
import com.college.management.services.StudentService;
import com.college.management.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.List;

@Controller
@PreAuthorize("hasRole('ROLE_ADMIN')")
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private AdminService adminService;

    @Autowired
    private PhotoService photoService;



    @InitBinder//This method will be called when every web request comes in
    public void initBinder(WebDataBinder dataBinder) {
        //StringTrimmerEditor removes leading and trailing white space

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }




    @RequestMapping("/home")
    public String setAdminHome(Model model) throws IOException{

        //Obtain Current Admin Details from Security Context Holder
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String principalName = authentication.getName();

        //Find Current Admin Details
        UserCommand user = userService.findByEmail(principalName);

        //find Admin Information
        AdminInformation admin = adminService.selectFirstNameByEmail(principalName);

        //find admin photo
        String userPhoto = photoService.findImageByUserId(user.getId());

        if(userPhoto != null) {
            //add base64 image to model
            model.addAttribute("userPhoto", userPhoto);
        }

        Long count = adminService.counNewlyRegisteredStudents();
        user.setCountNewStudents(count);

        model.addAttribute("user", user);
        model.addAttribute("admin", admin);

        return "admin/admin-home";
    }

    @RequestMapping("/upload/{userId}")
    public String uploadPhoto(@PathVariable Long userId, Model model){

        User user = new User();
        user.setId(userId);

        model.addAttribute("user", user);
        return "admin/upload-photo";
    }

    @PostMapping("/uploadPhoto")
    public String uploadAdminPhoto(@ModelAttribute("user") User user,
                       @RequestParam("photo")MultipartFile file) throws IOException{

        UserPhoto userPhoto = new UserPhoto(file.getOriginalFilename(), file.getContentType(),
                                file.getBytes());

        photoService.saveUserPhoto(userPhoto, user.getId());

        return "redirect:/admin/home";

    }

    @RequestMapping("/addStudent")
    public String addStudentAdmin(Model model){
        //Obtain Current Admin Details from Security Context Holder
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String principalName = authentication.getName();

        //Find Current Admin Details
        UserCommand user = userService.findByEmail(principalName);

        //find Admin Information
        AdminInformation admin = adminService.selectFirstNameByEmail(principalName);

        //find admin photo
        try {
            String userPhoto = photoService.findImageByUserId(user.getId());
            if(userPhoto != null) {
                //add base64 image to model
                model.addAttribute("userPhoto", userPhoto);
            }
        }catch (IOException e){

        }
        Long count = adminService.counNewlyRegisteredStudents();
        user.setCountNewStudents(count);


        model.addAttribute("user", user);
        model.addAttribute("admin", admin);



        model.addAttribute("studentCommand", new StudentCommand());

        return "admin/addStudent";
    }



    @PostMapping("/addStudent")
    public String addStudent(@Valid @ModelAttribute("studentCommand") StudentCommand studentCommand,
            BindingResult bindingResult,Model model){


        if(bindingResult.hasErrors()){

            System.out.println(bindingResult);
            return "redirect:/admin/addStudent?error=true";
        }

        //check if student already exists
        boolean UsersExists = adminService.checkIfUserExists(studentCommand.getUserCommand().getEmail());


        if(UsersExists){
            return "redirect:/admin/addStudent?studentexists=true";
        }else {
            adminService.saveStudent(studentCommand);
        }

        return  "redirect:/admin/showAllStudents";
    }



    @RequestMapping("/addStudentBasic")
    public String addBasicStudentBasic(Model model) throws IOException{

        //Obtain Current Admin Details from Security Context Holder
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String principalName = authentication.getName();

        //Find Current Admin Details
        UserCommand user = userService.findByEmail(principalName);

        //find Admin Information
        AdminInformation admin = adminService.selectFirstNameByEmail(principalName);

        //find admin photo
        String userPhoto = photoService.findImageByUserId(user.getId());

        if(userPhoto != null) {
            //add base64 image to model
            model.addAttribute("userPhoto", userPhoto);
        }
        Long count = adminService.counNewlyRegisteredStudents();
        user.setCountNewStudents(count);


        model.addAttribute("user", user);
        model.addAttribute("admin", admin);
        model.addAttribute("userCommand", new UserCommand());

        return "admin/addStudentBasic";
    }

    @PostMapping("/addStudentBasicAdmin")
    public String AddBasicStudentToDatabase(@Valid @ModelAttribute("userCommand")UserCommand userCommand,
                                            BindingResult bindingResult){

        System.out.println("hello");
        if(bindingResult.hasErrors()){

            return "admin/addStudentBasic";
        }

        adminService.saveBasicStudentInUser(userCommand);


        return "redirect:/admin/home";
    }


    @RequestMapping("/showAllStudents")
    public String showAllStudents(Model model) throws IOException{


        //Obtain Current Admin Details from Security Context Holder
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String principalName = authentication.getName();

        //Find Current Admin Details
        UserCommand user = userService.findByEmail(principalName);

        //find Admin Information
        AdminInformation admin = adminService.selectFirstNameByEmail(principalName);

        //find admin photo
        String userPhoto = photoService.findImageByUserId(user.getId());

        if(userPhoto != null) {
            //add base64 image to model
            model.addAttribute("userPhoto", userPhoto);
        }

        model.addAttribute("user", user);
        model.addAttribute("admin", admin);

        Long count = adminService.counNewlyRegisteredStudents();
        user.setCountNewStudents(count);



        List<User> users = adminService.findAllStudent();

        model.addAttribute("users", users);

        return "admin/showAllStudents";

    }


    @RequestMapping("/editStudent/{studentId}")
    public String editStudent(@PathVariable("studentId") Long studentId,Model model){

        //Obtain Current Admin Details from Security Context Holder
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String principalName = authentication.getName();

        //Find Current Admin Details
        UserCommand user = userService.findByEmail(principalName);

        //find Admin Information
        AdminInformation admin = adminService.selectFirstNameByEmail(principalName);

        //find admin photo
        String userPhoto = null;
        try {
            userPhoto = photoService.findImageByUserId(user.getId());
        }catch (IOException e){

        }


        if(userPhoto != null) {
            //add base64 image to model
            model.addAttribute("userPhoto", userPhoto);
        }

        Long count = adminService.counNewlyRegisteredStudents();
        user.setCountNewStudents(count);

        model.addAttribute("user", user);
        model.addAttribute("admin", admin);






        StudentCommand studentCommand = adminService.findStudentById(studentId);

        model.addAttribute("studentCommand", studentCommand);

        return "admin/editStudent";
    }


    @PostMapping("/editProfile")
    public String updateStudent(@Valid @ModelAttribute("studentCommand")StudentCommand studentCommand,
                                BindingResult bindingResult, Model model){


        if(bindingResult.hasErrors()){

            return "redirect:/student/editStudent/" + studentCommand.getId() + "?error=true";
        }


        boolean checkStudentIfExists = adminService.checkIfStudentExists(studentCommand.getId());

        if(checkStudentIfExists){

            adminService.updateStudent(studentCommand);

        }else{

        }


        return "redirect:/admin/showAllStudents";
    }

    @RequestMapping("/deleteStudent/{studentId}")
    public String deleteStudent(@PathVariable("studentId") Long id){

        adminService.deleteStudent(id);

        return "redirect:/admin/showAllStudents";
    }


    @RequestMapping("/newRegistrations")
    public String showNewRegistrations(Model model){

        //Obtain Current Admin Details from Security Context Holder
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String principalName = authentication.getName();

        //Find Current Admin Details
        UserCommand user = userService.findByEmail(principalName);

        //find Admin Information
        AdminInformation admin = adminService.selectFirstNameByEmail(principalName);

        //find admin photo
        String userPhoto ;

        try{
            userPhoto = photoService.findImageByUserId(user.getId());
            if(userPhoto != null) {
                //add base64 image to model
                model.addAttribute("userPhoto", userPhoto);
            }
        }catch (IOException e){
            e.printStackTrace();
        }

        Long count = adminService.counNewlyRegisteredStudents();
        user.setCountNewStudents(count);

        model.addAttribute("user", user);
        model.addAttribute("admin", admin);



        //get new Registrations
        List<UserCommand> newStudents = adminService.selectNewlyRegisteredStudents();
        model.addAttribute("newStudents", newStudents);


        return  "admin/newRegistrations";
    }


    @RequestMapping("/approveStudent/{userId}")
    public String approveStudent(@PathVariable("userId") Long userId){

        adminService.approveStudent(userId);

        return "redirect:/admin/newRegistrations";
    }

    @RequestMapping("/deleteNewStudent/{userId}")
        public String deleteNewStudent(@PathVariable("userId") Long userId){

            adminService.deleteNewStudent(userId);

            return "redirect:/admin/newRegistrations";
        }

}
