package com.college.management.controller;

import com.college.management.command.StudentCommand;
import com.college.management.command.UserCommand;
import com.college.management.model.Student;
import com.college.management.model.User;
import com.college.management.model.UserPhoto;
import com.college.management.services.PhotoService;
import com.college.management.services.StudentService;
import com.college.management.services.UserService;
import com.college.management.utility.ErrorMessage;
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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.IOException;

@Controller
@PreAuthorize("hasRole('ROLE_STUDENT')")
@RequestMapping("/student")
public class StudentController {


    @Autowired
    private UserService userService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private PhotoService photoService;

    @InitBinder//This method will be called when every web request comes in
    public void initBinder(WebDataBinder dataBinder) {
        //StringTrimmerEditor removes leading and trailing white space

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }

    @RequestMapping(value = {"/","/home"})
    public String setStudentHome(Model model) throws IOException{

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        //get Student email from principal
        String studentEmail = authentication.getName();

        //find currently login student
        UserCommand userCommand = userService.findByEmail(studentEmail);

        //find Student information
        StudentCommand studentCommand = studentService.findByEmail(studentEmail);



        String userPhoto = photoService.findImageByUserId(userCommand.getId());

        //find user photo
        if(userPhoto != null) {
            //add base64 image to model
            model.addAttribute("userPhoto", userPhoto);
        }

        model.addAttribute("user", userCommand);
        model.addAttribute("studentCommand", studentCommand);


        return "student/student-home";
    }

    @RequestMapping("/upload/{userId}")
    public String uploadPhoto(@PathVariable("userId") Long userId, Model model){



        model.addAttribute("userCommand", new UserCommand(userId));

        return "student/upload-photo";

    }

    @PostMapping("/uploadPhoto")
    public String uploadStudentPhoto(@ModelAttribute("userCommand") UserCommand userCommand,
                                     @RequestParam("photo")MultipartFile file, Model model,
                                     HttpServletRequest request)
                                        throws IOException {

        if(file.getContentType().equalsIgnoreCase("image/jpeg")  || file.getContentType().equalsIgnoreCase("image/png")){
            System.out.println("saving jpg");
            UserPhoto userPhoto = new UserPhoto(file.getOriginalFilename(),
                    file.getContentType(), file.getBytes());

            photoService.saveUserPhoto(userPhoto, userCommand.getId());

            return "redirect:/student/home";



        }
        else{
            System.out.println(file.getContentType());
            System.out.println("returning error");

            model.addAttribute("userCommand", new UserCommand(userCommand.getId()));



            return "redirect:/student/upload/" + userCommand.getId() + "?error=true";
        }


    }


    @RequestMapping("/editProfile/{userId}")
    public String sendStudentInfoForm(@PathVariable("userId")Long userId, Model model) throws IOException{

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        //get Student email from principal
        String studentEmail = authentication.getName();

        //find currently login student
        UserCommand userCommand = userService.findByEmail(studentEmail);

        //find Student information
        StudentCommand studentCommand = studentService.findByEmail(studentEmail);

        System.out.println("\n\n\n\n\n"+studentCommand+"\n\n\n\n\n");

        String userPhoto = photoService.findImageByUserId(userCommand.getId());

        //find user photo
        if(userPhoto != null) {
            //add base64 image to model
            model.addAttribute("userPhoto", userPhoto);
        }

        model.addAttribute("user", userCommand);

        if(studentCommand == null){

            model.addAttribute("studentCommand", new StudentCommand());
        }else{
            model.addAttribute("studentCommand", studentCommand);
        }




        return "student/editStudent-form";
    }


    @PostMapping("/editProfile")
    public String studentCommand(@Valid @ModelAttribute("studentCommand") StudentCommand studentCommand,
                                     BindingResult bindingResult){


        if(bindingResult.hasErrors()){

            System.out.println(bindingResult);
            return "redirect:/student/editProfile/" + studentCommand.getUserCommand().getId() +"?error=true";
        }
        System.out.println(studentCommand.getUserCommand().getId());


        if(studentCommand.getId() != null) {
            StudentCommand checkStudent = studentService.findById(studentCommand.getId());

            if (checkStudent == null) {

                studentService.saveStudentInfo(studentCommand);
            } else {
                studentService.updateStudent(studentCommand, studentCommand.getId());
            }
        }else{


            studentService.saveStudentInfo(studentCommand);

        }

        return "redirect:/student/home";

    }



}
