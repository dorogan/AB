package com.ab.actionbook.web;

import com.ab.actionbook.domain.Action;
import com.ab.actionbook.service.ActionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class IndexController {

    @Autowired
    private ActionService actionService;

    @RequestMapping("/index")
    public String listActions(Map<String, Object> map) {

        map.put("action", new Action());
        map.put("actionList", actionService.listAction());

        return "index";
    }

}