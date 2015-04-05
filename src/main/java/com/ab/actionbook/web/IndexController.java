package com.ab.actionbook.web;

import com.ab.actionbook.domain.Action;
import com.ab.actionbook.domain.User;
import com.ab.actionbook.service.ActionService;
import com.ab.actionbook.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@SessionAttributes("user")
public class IndexController {

    @Autowired
    private UserService userService;

    @Autowired
    private ActionService actionService;

    @RequestMapping("/index")
    public String listActions(HttpSession httpSession, Map<String, Object> map) {
        User user = userService.getCurrentUser();
        httpSession.setAttribute("user", user);
        map.put("action", new Action());
        List<Action> actions = actionService.listAction(user.getId());
        actionService.sortByDate(actions);
        map.put("actionList", actions);

        String userLink = user.getFirstname() + " " + user.getLastname();
        int id = user.getId();
        map.put("userPhoto", user.getAvatarPath());
        map.put("userPageLink", userLink);
        map.put("userID", id);
        map.put("countOfProp", userService.getCountOfPropositionToFriends());

        return "index";
    }

    //add new action
    @RequestMapping(value = "/add", method = RequestMethod.POST, produces = "application/json; charset=UTF-8")
    public String addAction(@RequestParam(value = "value") String name,
                            @ModelAttribute("action") Action action,
                            @ModelAttribute("user") User user, BindingResult result) {
        action.setName(name);
        actionService.addAction(action, user.getId());
        return "redirect:/index";
    }

    @RequestMapping(value = "/updateName/{actionId}", method = RequestMethod.POST, produces = {"application/json; charset=UTF-8"})
    @ResponseBody
    public void updateActionName(@PathVariable("actionId") Integer actionId,
                                   @RequestParam(value = "value") String name){

        actionService.updateActionName(actionId, name);
        //return "redirect:/index";
    }

    @RequestMapping(value = "/done/{actionId}")
    public String setActionStatusDon(@PathVariable("actionId") Integer id){
        actionService.setActionStatusDone(id);
        return "redirect:/index";
    }
}