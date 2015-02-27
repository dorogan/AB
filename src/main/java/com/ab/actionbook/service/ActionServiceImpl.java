package com.ab.actionbook.service;
 
import java.util.List;

import com.ab.actionbook.dao.ActionDAO;
import com.ab.actionbook.domain.Action;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
 
@Service
public class ActionServiceImpl implements ActionService {
 
    @Autowired
    private ActionDAO actionDAO;
 
    @Transactional
    public void addAction(Action action, Integer uid) {
        actionDAO.addAction(action, uid);
    }
 
    @Transactional
    public List<Action> listAction(Integer uid) {
        return actionDAO.listAction(uid);
    }
 
    @Transactional
    public void removeAction(Integer id) {
        actionDAO.removeAction(id);
    }
}
