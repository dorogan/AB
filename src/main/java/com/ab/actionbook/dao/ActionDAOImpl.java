package com.ab.actionbook.dao;

import java.util.List;

import com.ab.actionbook.domain.Action;

import com.ab.actionbook.domain.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class ActionDAOImpl implements ActionDAO {

	@Autowired
	private UserDAOImpl userDAO;

	@Autowired
	private SessionFactory sessionFactory;

	public void addAction(Action action, Integer uid) {
		action.setUid(uid);
		sessionFactory.getCurrentSession().save(action);
	}

	@SuppressWarnings("unchecked")
	public List<Action> listAction(Integer uid) {

		return sessionFactory.getCurrentSession().createQuery("from Action where uid=" + uid)
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
