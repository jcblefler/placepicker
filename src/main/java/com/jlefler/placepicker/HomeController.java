package com.jlefler.placepicker;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;


    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping ("/login")
    public String login() {

        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationPage(Model model) {
        model.addAttribute("user", new User());

        return "registration";
    }

    @PostMapping("/register")
    public String processRegistrationPage(@Valid @ModelAttribute("user") User user, BindingResult result, Model model, @RequestParam("role") String role, @RequestParam("password") String password){

        model.addAttribute("user",user);
        if(result.hasErrors()) {
            return "registration";
        }
        else {
            if(userService.getCurrentUser() != null){
                user.setId(userService.getCurrentUser().getId());
                user.setPassword(password);
                userService.saveUser(user, role);
            } else {
                user.setPassword(password);
                userService.saveUser(user, role);
            }
        }


        return "index";
    }


}
