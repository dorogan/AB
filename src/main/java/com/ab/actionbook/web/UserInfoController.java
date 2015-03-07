package com.ab.actionbook.web;

import com.ab.actionbook.domain.User;
import com.ab.actionbook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.Date;

@Controller
public class UserInfoController {

    @Autowired
    private UserService userService;

    @RequestMapping("/userinfo")
    public String setInfoPage(){
        return "userinfo";
    }

    @RequestMapping(value = "/userinfo/save", method = RequestMethod.POST)
    public String saveUserInfo(@RequestParam("dateOfBirthday") Date date,
                               @ModelAttribute("user")User user, BindingResult result){
        user.setDateOfBirthday(date);
        userService.setUserInformation(user);
        return "redirect:/index";
    }

}
