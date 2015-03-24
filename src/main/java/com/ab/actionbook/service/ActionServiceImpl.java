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

    @Transactional
    @Override
    public void updateActionName(Integer id, String name) {
        actionDAO.updateActionName(id, name);
    }

    @Transactional
    @Override
    public void setActionStatusDone(Integer id) {
        actionDAO.setActionStatusDone(id);
    }

    @Transactional
    @Override
    public Action getActionById(Integer id) {
        return actionDAO.getActionById(id);
    }

    @Transactional
    @Override
    public void updateAction(Integer id, Action action) {
        actionDAO.updateAction(id, action);
    }

    public void sortByDate(List<Action> actions){
        actionDAO.sortByDate(actions);
    }
}
