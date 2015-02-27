package com.ab.actionbook.web;

import com.ab.actionbook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UserinfoController {

    @Autowired
    private UserService userService;

}
