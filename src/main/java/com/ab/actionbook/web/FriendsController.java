package com.ab.actionbook.web;

import com.ab.actionbook.domain.User;
import com.ab.actionbook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Map;

@Controller
@SessionAttributes("user")
public class FriendsController {

    @Autowired
    private UserService userService;

    @RequestMapping("/friends")
    public String listPropositions(Map<String, Object> map){
        //map.put("user", new User());
        map.put("proposeList", userService.getAllPropositions());
        return "friends";
    }

    @RequestMapping("/confirm/{userID}")
    public String confirmPropose(@PathVariable("userID") Integer userId) {
        userService.confirmPropose(userId);
        return "redirect:/friends";
    }

    @RequestMapping("/down/{userID}")
    public String turnDownPropose(@PathVariable("userID") Integer userId) {
        userService.turnDownPropose(userId);
        return "redirect:/friends";
    }

}
