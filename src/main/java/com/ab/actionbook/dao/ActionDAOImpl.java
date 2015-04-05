package com.ab.actionbook.dao;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import com.ab.actionbook.domain.Action;

import com.ab.actionbook.domain.User;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
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
        action.setDate(new Date(Calendar.getInstance().getTimeInMillis()));
        action.setDeadline(new Date(Calendar.getInstance().getTimeInMillis()));
        action.setDescription("");
        action.setPermission(0);
        action.setStatus(0);
        action.setPriority(0);
		sessionFactory.getCurrentSession().save(action);
	}

	@SuppressWarnings("unchecked")
	public List<Action> listAction(Integer uid) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Action.class);
        criteria.setMaxResults(25);
        criteria.add(Restrictions.not(Restrictions.in("status", new Integer[] {1})));
        criteria.add(Restrictions.eq("uid",uid));
        List actions = criteria.list();
		/*return sessionFactory.getCurrentSession().createQuery("from Action where uid=" + uid)
			.list();*/
        return actions;
	}

	public void removeAction(Integer id) {
		Action action = (Action) sessionFactory.getCurrentSession().load(
				Action.class, id);
		if (null != action) {
			sessionFactory.getCurrentSession().delete(action);
		}

	}

    @Override
    public void updateActionName(Integer id, String name) {
        Query query = sessionFactory.getCurrentSession().createQuery("update Action set name = :actionName" +
                                    " where id = :actionID");
        query.setParameter("actionName", name);
        query.setParameter("actionID", id);
        query.executeUpdate();
    }

    @Override
    public void setActionStatusDone(Integer id) {
        Query query = sessionFactory.getCurrentSession().createQuery("update Action set status = :actionStatus" +
                                    " where id = :actionID");
        query.setParameter("actionStatus", 1);
        query.setParameter("actionID", id);
        query.executeUpdate();
    }

    @Override
    public Action getActionById(Integer id) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(Action.class);
        criteria.add(Restrictions.like("id", new Integer(id)));
        Action action =(Action) criteria.uniqueResult();
        return action;
    }

    @Override
    public void updateAction(Integer id, Action action) {
        Query query = sessionFactory.getCurrentSession().createQuery("update Action set" +
                " description = :actionDescription" +
                ", date = :actionDate" +
                ", deadline = :actionDeadline" +
                ", time = :actionTime" +
                ", permission = :actionPermission" +
                ", priority = :actionPriority" +
                " where id = :actionID");
        query.setParameter("actionDescription", action.getDescription());
        query.setParameter("actionDate", action.getDate());
        query.setParameter("actionDeadline", action.getDeadline());
        query.setParameter("actionTime", action.getTime());
        query.setParameter("actionPermission", action.getPermission());
        query.setParameter("actionPriority", action.getPriority());
        query.setParameter("actionID", id);
        query.executeUpdate();
    }

    public void sortByDate(List<Action> actions){
        for (int i = 0; i < actions.size(); i++) {
            for (int j = 0; j < actions.size()-1; j++) {
                if (actions.get(j).getDate().getTime() > actions.get(j + 1).getDate().getTime()){
                    Action a = actions.get(j);
                    actions.set(j, actions.get(j + 1));
                    actions.set(j + 1, a);
                }
            }
        }
    }
}
