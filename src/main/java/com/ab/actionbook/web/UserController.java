package com.ab.actionbook.web;

import com.ab.actionbook.domain.User;
import com.ab.actionbook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

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

    @RequestMapping("/user/{userID}")
    public String userPage(@PathVariable("userID") Integer userID,
                           @ModelAttribute("user")User user, Map<String, Object> map){
        int cid = userService.getCurrentUser().getId();
        user = userService.getUserInformation(userID);
        map.put("cid", cid);
        map.put("id", userID);
        map.put("name", user.getFirstname());
        map.put("sname", user.getLastname());
        map.put("mail", user.getEmail());
        map.put("birthday", user.getDateOfBirthday());
        map.put("avatarPath", user.getAvatarPath());
        return "user";
    }

}
