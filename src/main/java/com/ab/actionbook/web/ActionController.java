package com.ab.actionbook.web;

import java.util.Map;

import com.ab.actionbook.domain.Action;
import com.ab.actionbook.service.ActionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ActionController {

	@Autowired
	private ActionService actionService;

	@RequestMapping("/index")
	public String listActions(Map<String, Object> map) {

		map.put("action", new Action());
		map.put("actionList", actionService.listAction());

		return "action";
	}
	
	@RequestMapping("/")
	public String home() {
		return "redirect:/index";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addAction(@ModelAttribute("action") Action action, BindingResult result) {
		actionService.addAction(action);
		return "redirect:/index";
	}

	@RequestMapping("/delete/{actionId}")
	public String deleteAction(@PathVariable("actionId") Integer actionId) {

		actionService.removeAction(actionId);

		return "redirect:/index";
	}
}
