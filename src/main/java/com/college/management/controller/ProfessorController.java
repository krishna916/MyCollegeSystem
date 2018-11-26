package com.college.management.controller;

import com.college.management.command.ProfessorCommand;
import com.college.management.command.UserCommand;

import com.college.management.model.Professor;
import com.college.management.model.UserPhoto;
import com.college.management.services.PhotoService;
import com.college.management.services.ProfessorService;
import com.college.management.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
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

@Controller
@RequestMapping("/professor")
public class ProfessorController {


    @Autowired
    private UserService userService;

    @Autowired
    private PhotoService photoService;

    @Autowired
    private ProfessorService professorService;



    @InitBinder//This method will be called when every web request comes in
    public void initBinder(WebDataBinder dataBinder) {
        //StringTrimmerEditor removes leading and trailing white space

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }


    public Model bootstrapAdmin(String principalName, Model model) throws IOException {
        //Find Current Admin Details
        UserCommand user = userService.findByEmail(principalName);

        //Find Professor information
        ProfessorCommand professorCommand = professorService.findProfessorByEmail(principalName);



        //find admin photo
        String userPhoto = photoService.findImageByUserId(user.getId());

        if(userPhoto != null) {
            //add base64 image to model
            model.addAttribute("userPhoto", userPhoto);
        }

        model.addAttribute("professorCommand", professorCommand);

        model.addAttribute("user", user);


        return model;
    }



    @GetMapping("/home")
    public String setProfessorHome(Model model) throws IOException{


        //Obtain Current Admin Details from Security Context Holder
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String principalName = authentication.getName();


            model = bootstrapAdmin(principalName, model);



        return "professor/professor-home";
    }

    @GetMapping("/upload/{userId}")
    public String uploadPhotoForm(@PathVariable("userId")Long userId, Model model){

        model.addAttribute("userCommand", new UserCommand(userId));

        return "professor/upload-photo";

    }

    @PostMapping("/uploadPhoto")
    public String uploadPhoto(@ModelAttribute("userCommand") UserCommand userCommand,
                              @RequestParam("photo")MultipartFile file, Model model)
                                throws IOException{

        if(file.getContentType().equalsIgnoreCase("image/jpeg")  || file.getContentType().equalsIgnoreCase("image/png")) {
            System.out.println("saving jpg");
            UserPhoto userPhoto = new UserPhoto(file.getOriginalFilename(),
                    file.getContentType(), file.getBytes());

            photoService.saveUserPhoto(userPhoto, userCommand.getId());

            return "redirect:/professor/home";
        }else{
            System.out.println(file.getContentType());
            System.out.println("returning error");

            model.addAttribute("userCommand", new UserCommand(userCommand.getId()));



            return "redirect:/student/upload/" + userCommand.getId() + "?error=true";
        }

    }

    @GetMapping("/editProfile/{userId}")
    public String showEditProfileForm(@PathVariable("userId") Long userId, Model model) throws IOException{

        //Obtain Current Admin Details from Security Context Holder
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String principalName = authentication.getName();



        model = bootstrapAdmin(principalName, model);

        ProfessorCommand professorCommand = professorService.findProfessorByUserId(userId);

        model.addAttribute("professorCommand", professorCommand);

        return "professor/editProfessor";
    }


    @PostMapping("/editProfile")
    public String editProfessorProfile(@Valid @ModelAttribute("professorCommand") ProfessorCommand professorCommand,
                                       BindingResult bindingResult){

        if(bindingResult.hasErrors()){

            System.out.println(bindingResult);
            return "redirect:/professor/editProfile/" + professorCommand.getUserCommand().getId() +"?error=true";
        }

        professorService.updateProfessor(professorCommand);

        return "redirect:/professor/home";
    }



}
