package com.ab.actionbook.service;

import java.util.List;
import com.ab.actionbook.domain.Action;

public interface ActionService {

	public void addAction(Action action, Integer uid);

	public List<Action> listAction(Integer uid);

	public void removeAction(Integer id);

    public void updateActionName(Integer id, String name);

    public void setActionStatusDone(Integer id);

    public Action getActionById(Integer id);

    public void updateAction(Integer id, Action action);

    public void sortByDate(List<Action> actions);
}
