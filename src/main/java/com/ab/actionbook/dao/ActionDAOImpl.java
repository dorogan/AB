package com.ab.actionbook.dao;

import java.util.List;

import com.ab.actionbook.domain.Action;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ActionDAOImpl implements ActionDAO {

	@Autowired
	private SessionFactory sessionFactory;

	public void addAction(Action action) {
		sessionFactory.getCurrentSession().save(action);
	}

	@SuppressWarnings("unchecked")
	public List<Action> listAction() {

		return sessionFactory.getCurrentSession().createQuery("from Action")
			.list();
	}

	public void removeAction(Integer id) {
		Action action = (Action) sessionFactory.getCurrentSession().load(
				Action.class, id);
		if (null != action) {
			sessionFactory.getCurrentSession().delete(action);
		}

	}
}
