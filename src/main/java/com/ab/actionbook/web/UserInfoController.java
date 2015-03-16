package com.ab.actionbook.web;

import com.ab.actionbook.domain.User;
import com.ab.actionbook.service.UserService;
import com.sun.java.util.jar.pack.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
        map.put("profession", user.getProfession());
        map.put("phone", user.getPhone());
        map.put("address", user.getAddress());
        map.put("skype", user.getSkype());
        return "userinfo";
    }

    @RequestMapping(value = "/userinfo/save", method = RequestMethod.POST)
    public String saveUserInfo(@RequestParam("dateOfBirthday") Date date,
                               @ModelAttribute("user")User user, BindingResult result){
        user.setDateOfBirthday(date);
        userService.setUserInformation(user);
        return "redirect:/index";
    }

    @RequestMapping(value = "/userinfo/avatar", method = RequestMethod.POST)
    @ResponseBody
    public String setAvatar(@RequestParam("file") MultipartFile file, HttpServletRequest request) throws IOException{
        String name = file.getOriginalFilename();
        String ap;
        if (!file.isEmpty()) {
            try {
                byte[] bytes = file.getBytes();

                // Створити папку для аватарів
                String rootPath = System.getProperty("catalina.home");
                String pr = request.getSession().getServletContext().getRealPath("/resources");
                File dir = new File(pr + File.separator + "usrAvatarsFiles"
                        + File.separator + userService.getCurrentUser().getId());
                if (!dir.exists())
                    dir.mkdirs();

                // створити аватар
                File serverFile = new File(dir.getAbsolutePath()
                        + File.separator + name);
                BufferedOutputStream stream = new BufferedOutputStream(
                        new FileOutputStream(serverFile));
                stream.write(bytes);
                stream.close();
                ap = "/resources/usrAvatarsFiles" + File.separator + userService.getCurrentUser().getId()
                        + File.separator + name;
                userService.setAP(ap);
                return "redirect:/userinfo";
            } catch (Exception e) {
                return e.getMessage();
            }
        } else {
            return "File is empty! Try Again!";
        }
    }

}
