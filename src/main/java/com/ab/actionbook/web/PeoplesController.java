package com.ab.actionbook.web;

import com.ab.actionbook.domain.User;
import com.ab.actionbook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class PeoplesController {

    @Autowired
    private UserService userService;

    @RequestMapping("/peoples")
    public String listActions(Map<String, Object> map) {
        map.put("user", new User());
        map.put("userList", userService.getAllUsers());
        return "peoples";
    }

    @RequestMapping("/add/{userId}")
    public String addFriend(@PathVariable("userId") Integer userId) {

        userService.addToFriend(userId);

        return "redirect:/index";
    }
}
