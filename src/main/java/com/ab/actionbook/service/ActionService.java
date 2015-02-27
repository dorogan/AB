package com.ab.actionbook.service;

import java.util.List;
import com.ab.actionbook.domain.Action;

public interface ActionService {

	public void addAction(Action action, Integer uid);

	public List<Action> listAction(Integer uid);

	public void removeAction(Integer id);
}
