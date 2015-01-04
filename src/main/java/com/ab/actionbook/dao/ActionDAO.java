package com.ab.actionbook.dao;

import java.util.List;
import com.ab.actionbook.domain.Action;

public interface ActionDAO {

	public void addAction(Action action);

	public List<Action> listAction();

	public void removeAction(Integer id);
}