package com.college.management.controller;

import com.college.management.command.UserCommand;
import com.college.management.model.User;
import com.college.management.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.core.Authentication;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.Set;

@Controller
public class HomeController {


    @Autowired
    private UserService userService;



    @InitBinder//This method will be called when every web request comes in
    public void initBinder(WebDataBinder dataBinder) {
        //StringTrimmerEditor removes leading and trailing white space

        StringTrimmerEditor stringTrimmerEditor = new StringTrimmerEditor(true);
        dataBinder.registerCustomEditor(String.class, stringTrimmerEditor);
    }


    @RequestMapping("/loginPage")
    public String loginPage(Model model){

        User user = new User();
        model.addAttribute("user", user);

        return "loginPage";

    }

    @RequestMapping("/home")
    public String redirectAccordingToRoles(){

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

        if(roles.contains("ROLE_ADMIN")){
            return "redirect:/admin/home";
        }else if(roles.contains("ROLE_STUDENT")){
            return "redirect:/student/home";
        }else if(roles.contains("ROLE_MANAGEMENT-STAFF")){
            return "redirect:/staff/home";
        }else if(roles.contains("ROLE_PROFESSOR")){
            return "redirect:/professor/home";
        }
        return null;

    }

    @RequestMapping("/register")
    public String showRegisterForm(Model model){

        model.addAttribute("userCommand", new UserCommand());
        return "/register";

    }

    @PostMapping("/register")
    public String registerStudent(@Valid @ModelAttribute("userCommand") UserCommand userCommand,
                                  BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()){
            System.out.println(bindingResult);
            return "redirect:/register?error=true";
        }

        System.out.println(userCommand);
        boolean userExists = userService.checkIfUserExists(userCommand.getEmail());

        if(userExists){
            return "redirect:/register?userExists=true";
        }else{

            userService.registerStudent(userCommand);


                return "redirect:/register?registered=true";


        }



    }


}
