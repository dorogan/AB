package com.ab.actionbook.web;

import java.sql.Date;
import java.sql.Time;
import java.util.Map;

import com.ab.actionbook.dao.UserDAO;
import com.ab.actionbook.domain.Action;
import com.ab.actionbook.domain.User;
import com.ab.actionbook.service.ActionService;

import com.ab.actionbook.service.UserService;
import com.fasterxml.jackson.databind.util.JSONPObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import sun.org.mozilla.javascript.json.JsonParser;

@Controller
public class ActionController {

	@Autowired
	private UserService userService;

	@Autowired
	private ActionService actionService;

	@RequestMapping("/action")
	public String listActions(Map<String, Object> map) {

		map.put("action", new Action());
		map.put("actionList", actionService.listAction(userService.getCurrentUser().getId()));

		return "action";
	}

	@RequestMapping("/")
	public String home() {
		return "redirect:/index";
	}

	@RequestMapping("/delete/{actionId}")
	public String deleteAction(@PathVariable("actionId") Integer actionId) {

		actionService.removeAction(actionId);

		return "redirect:/index";
	}

    @RequestMapping(value = "/details/{actionId}", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public Action detailsAction(@PathVariable("actionId") Integer actionId, Map<String, Object> map){
        Action action = actionService.getActionById(actionId);
        map.put("action", action);
        return action;
    }

    @RequestMapping(value = "/update/{actionId}", method = RequestMethod.POST)
    @ResponseBody
    public void updateActionDetails(@PathVariable("actionId") Integer actionId,
                                    @RequestParam(value = "act-description") String description,
                                    @RequestParam(value = "act-date") Date date,
                                    @RequestParam(value = "act-deadline") Date deadline,
                                    @RequestParam(value = "act-time") String time,
                                    @RequestParam(value = "act-permission") Integer permission,
                                    @RequestParam(value = "act-priority") Integer priority){
        Action action = actionService.getActionById(actionId);
        action.setDescription(description);
        action.setDate(date);
        action.setDeadline(deadline);
        if (time.equals("00:00")){
            action.setTime(null);
        }
        else {
            action.setTime(Time.valueOf(time));
        }
        action.setPermission(permission);
        action.setPriority(priority);
        actionService.updateAction(actionId, action);
    }
}