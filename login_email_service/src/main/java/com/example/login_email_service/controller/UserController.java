package com.example.login_email_service.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.login_email_service.entity.User;
import com.example.login_email_service.service.UserService;
import com.example.login_email_service.utility.UserUtility;

@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String loadLoginFOrm(Model model) {
        model.addAttribute("user", new User());
        return "login";
    }

    @GetMapping("/registerform")
    public String loadRegisterForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }
    @PostMapping("/register")
    public String register(@ModelAttribute User user){
        userService.saveUpdateUser(user);
        return "redirect:/login";
    }
    @GetMapping("/logincheck")
    public String loginCheck(@ModelAttribute User user, Model model){
        if(userService.loginStatus(user.getUsername(), user.getPassword())){
            User obj=userService.findByUsernameAndPassword(user.getUsername(), user.getPassword());
            UserUtility.sendMail(obj.getMail());
            
            model.addAttribute("user", obj);
            return "/home";
        }else{
            return "error";
        }
    }
    @GetMapping("/update/{id}")
    public String updateProfileForm(@PathVariable long id, Model model){
        User user=userService.getUserById(id).orElseThrow(()->new IllegalArgumentException("User ID: "+id+" does not exist!!!"));
        model.addAttribute("user", user);
        return "update";
    }
    @PostMapping("/updateprofile")
    public String updateProfile(@ModelAttribute User user){
        User usr=userService.getUserById(user.getId()).orElseThrow(()->new IllegalArgumentException("User Id: "+user.getId()+ " does not exist!!!"));
        user.setPassword(usr.getPassword());
        userService.saveUpdateUser(user);
        return "/home";
    }
    @GetMapping("/error")
    public String errorPage(Model model){
        model.addAttribute("message", "Sign-in failed!!! New User? Sign-up...");
        return "error";
    }
}
