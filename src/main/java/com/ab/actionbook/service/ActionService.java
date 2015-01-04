package com.ab.actionbook.service;

import java.util.List;
import com.ab.actionbook.domain.Action;

public interface ActionService {

	public void addAction(Action action);

	public List<Action> listAction();

	public void removeAction(Integer id);
}
