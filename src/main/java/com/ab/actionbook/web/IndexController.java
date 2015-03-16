package com.ab.actionbook.web;

import com.ab.actionbook.domain.Action;
import com.ab.actionbook.domain.User;
import com.ab.actionbook.service.ActionService;
import com.ab.actionbook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

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

        String userLink = userService.getCurrentUser().getFirstname() + " " + userService.getCurrentUser().getLastname();
        int id = userService.getCurrentUser().getId();
        map.put("userPageLink", userLink);
        map.put("userID", id);

        return "index";
    }

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    @ResponseBody
    public String addAction(@RequestParam(value = "name") String name,
                            @ModelAttribute("action") Action action, BindingResult result) {
        action.setName(name);
        actionService.addAction(action, userService.getCurrentUser().getId());
        return "redirect:/index";
    }

}