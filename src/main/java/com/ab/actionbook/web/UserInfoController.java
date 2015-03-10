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
import java.util.Map;

@Controller
public class UserInfoController {

    @Autowired
    private UserService userService;

    @RequestMapping("/userinfo")
    public String setInfoPage(@ModelAttribute("user")User user, Map<String, Object> map){
        int cid = userService.getCurrentUser().getId();
        user = userService.getUserInformation(cid);
        map.put("cid", cid);
        map.put("mail", user.getEmail());
        map.put("birthday", user.getDateOfBirthday());
        map.put("avatarPath", user.getAvatarPath());
        map.put("interests", user.getInterests());
        return "userinfo";
    }

    @RequestMapping(value = "/userinfo/save", method = RequestMethod.POST)
    public String saveUserInfo(@RequestParam("dateOfBirthday") Date date,
                               @ModelAttribute("user")User user, BindingResult result){
        user.setDateOfBirthday(date);
        userService.setUserInformation(user);
        return "redirect:/index";
    }

    @RequestMapping(value = "/userinfo/avatar")
    public String setAvatar(@RequestParam("avatarPath") MultipartFile file){
        return "userinfo";
    }

}
