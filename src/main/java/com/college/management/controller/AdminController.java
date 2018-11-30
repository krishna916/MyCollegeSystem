package com.college.management.controller;

import com.college.management.command.*;
import com.college.management.model.*;
import com.college.management.services.AdminService;
import com.college.management.services.PhotoService;

import com.college.management.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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


    public Model bootstrapAdmin(String principalName, Model model) throws IOException{
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

        return model;
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

        Map<Long, String> departments = userService.findAllDepartments();
        model.addAttribute("department", departments);


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

    @GetMapping("/addCourse")
    public String showAddCourseForm(Model model){

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



        Map<Long, String> departments = adminService.findAllDepartments();
        model.addAttribute("department", departments);
        model.addAttribute("courseCommand", new CourseCommand());


        return "admin/addCourse";
    }


    @PostMapping("/addCourse")
    public String addCourse(@Valid @ModelAttribute("courseCommand") CourseCommand courseCommand,
                            BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()){
            System.out.println(bindingResult);
            return "redirect:/admin/addCourse?error=true";
        }

        if(adminService.checkIfCourseExists(courseCommand.getCourseCode())){

            return "redirect:/admin/addCourse?courseExists=true";
        }

        adminService.saveCourse(courseCommand);


        return "redirect:/admin/showAllCourses";
    }

    @GetMapping("/showAllCourses")
    public String showAllcourses(Model model){

        //Obtain Current Admin Details from Security Context Holder
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String principalName = authentication.getName();

        try {
            model = bootstrapAdmin(principalName, model);
        }catch (IOException e){
            e.printStackTrace();
        }

        List<CourseCommand> courses = adminService.showAllCourses();

        model.addAttribute("courseCommands", courses);

        return "admin/allCourses";
    }

    @GetMapping("deleteCourse/{courseId}")
    public String deleteCourse(@PathVariable("courseId") Long id){

        adminService.deleteCourse(id);

        return "redirect:/admin/showAllCourses";
    }


   /* ####################   Professors Related #########################*/















    @GetMapping("/addProfessor")
    public String ShowAddProfessorForm(Model model){

        //Obtain Current Admin Details from Security Context Holder
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String principalName = authentication.getName();

        try {
            model = bootstrapAdmin(principalName, model);
        }catch (IOException e){
            e.printStackTrace();
        }

        Map<Long, String> departments = adminService.findAllDepartments();
        model.addAttribute("department", departments);

        model.addAttribute("professorCommand", new ProfessorCommand());


        return "admin/addProfessor";
    }


    @PostMapping("/addProfessor")
    public String addProfessor(@Valid @ModelAttribute ProfessorCommand professorCommand,
                               BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            System.out.println(bindingResult);
            return "redirect:/admin/addProfessor?error=true";
        }

        boolean checkIfAlreadyExists = adminService.checkIfProfessorExists(professorCommand.getUserCommand().getEmail());

        if(checkIfAlreadyExists){
            return "redirect:/admin/addProfessor?alreadyExists=true";
        }

        adminService.saveProfessor(professorCommand);


        return "redirect:/admin/allProfessors";
    }


    @GetMapping("/allProfessors")
    public String showAllProfessors(Model model){

        //Obtain Current Admin Details from Security Context Holder
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String principalName = authentication.getName();

        try {
            model = bootstrapAdmin(principalName, model);
        }catch (IOException e){
            e.printStackTrace();
        }


        List<UserCommand> userCommands = adminService.showAllProfessors();

        model.addAttribute("userCommands", userCommands );



        return "admin/allProfessors";
    }


    @GetMapping("/editProfessor/{professorId}")
    public String showEditProfessorForm(@PathVariable("professorId") Long professorId, Model model){

        //Obtain Current Admin Details from Security Context Holder
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String principalName = authentication.getName();

        try {
            model = bootstrapAdmin(principalName, model);
        }catch (IOException e){
            e.printStackTrace();
        }

        ProfessorCommand professorCommand = adminService.findProfessorById(professorId);

        model.addAttribute("professorCommand", professorCommand);

        return "admin/editProfessor";
    }

    @PostMapping("/editProfessor")
    public String updateProfessor(@Valid @ModelAttribute("professorCommand") ProfessorCommand professorCommand,
                                  BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            System.out.println(bindingResult);
            return "redirect:/admin/editProfessor/" + professorCommand.getId() +"?error=true";
        }

        adminService.updateProfessor(professorCommand);


        return "redirect:/admin/allProfessors";
    }

    @GetMapping("/deleteProfessor/{userId}")
    public String deleteProfessor(@PathVariable("userId") Long id){

        adminService.deleteProfessor(id);

        return "redirect:/admin/allProfessors";
    }



    //  ###################### Batches Related ##################### //


    @GetMapping("/addBatch")
    public String showAddBatchForm(Model model){

        //Obtain Current Admin Details from Security Context Holder
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String principalName = authentication.getName();

        try {
            model = bootstrapAdmin(principalName, model);
        }catch (IOException e){
            e.printStackTrace();
        }

        Map<Long, String> departments = adminService.findAllDepartments();
        model.addAttribute("department", departments);

        Map<Long, String> professors = adminService.findAllProfessors();

        Map<Long, String> courses = adminService.findAllCourse();

        model.addAttribute("professor", professors);

        model.addAttribute("course", courses);

        model.addAttribute("batchCommand", new BatchCommand());

        return "admin/addBatch";
    }


    @PostMapping("/addBatch")
    public String addBatch(@Valid @ModelAttribute("batchCommand") BatchCommand batchCommand,
                           BindingResult bindingResult){

        if(bindingResult.hasErrors()){

            System.out.println(bindingResult);
            return "redirect: /admin/addBatch?error=true";
        }


        adminService.saveBatch(batchCommand);

        return null;
    }


    @GetMapping("/showBatch")
    public String showAllBatches(Model model){

        //Obtain Current Admin Details from Security Context Holder
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String principalName = authentication.getName();

        try {
            model = bootstrapAdmin(principalName, model);
        }catch (IOException e){
            e.printStackTrace();
        }


        List<BatchCommand> batchCommands = adminService.showAllBatches();

        model.addAttribute("batchCommand", batchCommands);

        return "admin/allBatches";
    }


    @GetMapping("/addStudentsToBatch/{batchId}")
    public String addStudentsToBatch(@PathVariable("batchId") Long batchId, Model model){

        //Obtain Current Admin Details from Security Context Holder
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String principalName = authentication.getName();

        try {
            model = bootstrapAdmin(principalName, model);
        }catch (IOException e){
            e.printStackTrace();
        }


        BatchCommand batchCommand  = adminService.findBatchById(batchId);

        List<StudentCommand> studentCommands = adminService.findStudentsByDepartment(batchCommand.getDepartmentCommand().getDepartmentName(), batchId);


        model.addAttribute("batchCommand", batchCommand);
        model.addAttribute("studentCommand", studentCommands);

        return "admin/addStudentsToBatch";
    }


    @GetMapping("/addStudentToBatch/{studentId}/{batchId}")
    public String addStudentToBatch(@PathVariable("studentId") Long studentId,
                                    @PathVariable("batchId") Long batchId){

        return null;
    }




}
