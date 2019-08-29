package com.jlefler.placepicker;

import org.hibernate.validator.constraints.pl.REGON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;

@Controller
public class HomeController {


    @Autowired
    private UserService userService;

    @Autowired
    private ListRepository listRepository;

    @Autowired
    private YelpService yelpService;


    @RequestMapping("/")
    public String index(Model model) {

        User user = userService.getCurrentUser();

        model.addAttribute("user", user);
        return "index";
    }

    @GetMapping("/register")
    public String showRegistrationPage(Model model) {
        model.addAttribute("user", new User());
        return "registration";
    }

    @PostMapping("/register")
    public String processRegistrationPage(@Valid
                                          @ModelAttribute("user") User user,
                                          BindingResult result,
                                          Model model) {
        model.addAttribute("user", user);
        if (result.hasErrors()) {
            return "registration";
        } else {
            userService.saveUser(user);
            model.addAttribute("message", "User Account Created");
        }
        return "redirect:/login";
    }

    @RequestMapping("/login")
    public String login() {
        return "login";
    }


    //List
    @GetMapping("/newlist")
    public String showNewList(Model model){
        model.addAttribute("list", new List());
        model.addAttribute("user", userService.getCurrentUser());

        return "newlist";
    }

    @PostMapping("/newlist")
    public String processNewList(Model model,
                                 @RequestParam("zip") String location,
                                 @RequestParam(required = false, value = "radius") String radius,
                                 @RequestParam(required = false, value = "$") String s,
                                 @RequestParam(required = false, value = "$$") String ss,
                                 @RequestParam(required = false, value = "$$$") String sss,
                                 @RequestParam(required = false, value = "$$$$") String ssss,
                                 @RequestParam(required = false, value = "open") String open,
                                 @RequestParam(required = false, value = "limit") String limit
                                 ) {

        HashMap<String, String> search = new HashMap<>();
        search.put("location", location);
        if (!radius.isEmpty()){
            search.put("radius", radius);
        }
        if (!s.isEmpty()){
            search.put("1", s);
        }
        if (!ss.isEmpty()){
            search.put("2", ss);
        }
        if (!sss.isEmpty()){
            search.put("3", sss);
        }
        if (!ssss.isEmpty()){
            search.put("4", ssss);
        }
        if (!open.isEmpty()){
            search.put("open", open);
        }
        if (!limit.isEmpty()){
            search.put("limit", limit);
        }

        yelpService.search(search);

        User user = userService.getCurrentUser();

        model.addAttribute("user", user);


        return "redirect://";
    }

}
