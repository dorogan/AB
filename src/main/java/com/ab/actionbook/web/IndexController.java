package com.ab.actionbook.web;

import com.ab.actionbook.domain.Action;
import com.ab.actionbook.domain.User;
import com.ab.actionbook.service.ActionService;
import com.ab.actionbook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import java.util.Map;

@Controller
public class IndexController {

    @Autowired
    private UserService userService;

    @Autowired
    private ActionService actionService;

    @RequestMapping("/index")
    public String listActions(Map<String, Object> map) {

        map.put("action", new Action());
        map.put("actionList", actionService.listAction(userService.getCurrentUser().getId()));

        map.put("userPageLink", userService.getCurrentUser().getFirstname());

        return "index";
    }

}