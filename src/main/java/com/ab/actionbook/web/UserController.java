package com.ab.actionbook.web;

import com.ab.actionbook.domain.User;
import com.ab.actionbook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping("/registration")
    public String registration(){
        return "registration";
    }

    @RequestMapping(value = "/registration/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user")User user, BindingResult result){
        userService.addUser(user);
        return "redirect:/index";
    }

    @RequestMapping("/user")
    public String userPage(){
        return "user";
    }

}
