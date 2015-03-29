package com.ab.actionbook.dao;

import com.ab.actionbook.domain.User;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.JDBC4Connection;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Expression;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Repository
public class UserDAOImpl implements UserDAO{

    @Autowired
    private SessionFactory sessionFactory;

    public void addUser(User user) {
        sessionFactory.getCurrentSession().save(user);
        sessionFactory.getCurrentSession().createSQLQuery("INSERT INTO " +
                "user_authorization (userrole_id, user_id, role)" +
                "VALUES (" + 1 +", " + user.getId() + ", 'ROLE_USER')").executeUpdate();
        sessionFactory.getCurrentSession().createSQLQuery("INSERT INTO " +
                "users_information (uid, avatar, interests, profession, phones, address, skype)" +
                "VALUES (" + user.getId() + ", '/resources/images/no-photo.png', '', '', '', '', '')").executeUpdate();
    }

    @SuppressWarnings("unchecked")
    public User findUserByLogin(String login) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
        criteria.add(Restrictions.eq("login", login));
        User user = (User) criteria.uniqueResult();
        return user;
    }

    @SuppressWarnings("unchecked")
    public List<User> getAllUsers() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
        criteria.setMaxResults(25);
        criteria.add(Restrictions.not(Restrictions.in("id", new Integer[]{getCurrentUser().getId()})));
        List users = criteria.list();
        return users;
    }

    @SuppressWarnings("unchecked")
    public List<User> getAllFriends() {
        User user = getCurrentUser();
        int uid = user.getId();
        return sessionFactory.getCurrentSession().createQuery("from User").list();
    }

    @Override
    public User getCurrentUser() {
        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        User user = findUserByLogin(username);
        return user;
    }

    @Override
    public int getCountOfPropositionToFriends() {
        return getAllPropositions().size();
    }

    @Override
    public void addToFriend(Integer id) {
        User user = getCurrentUser();
        int uid = user.getId();

        sessionFactory.getCurrentSession().createSQLQuery("INSERT INTO users_relations (uid, fid, relation) VALUES (" +
                id +", " + uid + ", -1)").executeUpdate();
        sessionFactory.getCurrentSession().createSQLQuery("INSERT INTO users_relations (uid, fid, relation) VALUES (" +
                uid +", " + id + ", 2)").executeUpdate();
    }

    @Override
    public List<User> getAllPropositions() {
        User user = getCurrentUser();
        int uid = user.getId();
        List<Integer> userIdProp= sessionFactory.getCurrentSession().createSQLQuery("SELECT fid FROM users_relations WHERE " +
                "uid=" + uid + " AND relation=-1").list();
        List<User> uProp = new ArrayList<User>();
        for (int i = 0; i < userIdProp.size(); i++) {
            int id = userIdProp.get(i);
            List<User> usr = sessionFactory.getCurrentSession().createQuery("from User where id=" + id).list();
            uProp.add(i, usr.get(0));
        }
        return uProp;
    }

    @Override
    public void confirmPropose(Integer id) {
        User user = getCurrentUser();
        int uid = user.getId();

        sessionFactory.getCurrentSession().createSQLQuery("UPDATE `users_relations` SET `relation`=1 WHERE uid="+uid+
                " AND fid=" + id).executeUpdate();
        sessionFactory.getCurrentSession().createSQLQuery("UPDATE `users_relations` SET `relation`=1 WHERE uid="+id+
                " AND uid=" + id).executeUpdate();
    }

    @Override
    public void turnDownPropose(Integer id) {
        User user = getCurrentUser();
        int uid = user.getId();

        sessionFactory.getCurrentSession().createSQLQuery("UPDATE `users_relations` SET `relation`=0 WHERE uid="+uid+
                " AND fid=" + id).executeUpdate();
    }

    @Override
    public void setUserInformation(User user) {
        Query query = sessionFactory.getCurrentSession().createQuery("update User set " +
                "firstname = :uFirstName, " +
                "lastname = :uLastName, " +
                "dateOfBirthday = :uDateOfBirthday, " +
                "interests = :uInterests, " +
                "profession = :uProfession, " +
                "phone = :uPhone, " +
                "address = :uAddress, " +
                "skype = :uSkype " +
                "where id = :uId");
        query.setParameter("uFirstName", user.getFirstname());
        query.setParameter("uLastName", user.getLastname());
        query.setParameter("uDateOfBirthday", user.getDateOfBirthday());
        query.setParameter("uInterests", user.getInterests());
        query.setParameter("uProfession", user.getProfession());
        query.setParameter("uPhone", user.getPhone());
        query.setParameter("uAddress", user.getAddress());
        query.setParameter("uSkype", user.getSkype());
        query.setParameter("uId", user.getId());
        query.executeUpdate();
    }

    @Override
    public User findUserById(Integer id) {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
        criteria.add(Restrictions.like("id", new Integer(id)));
        User user = (User) criteria.uniqueResult();
        return user;
    }

    @Override
    public User getUserById(Integer id) {
        User user = findUserById(id);
        return user;
    }

    @Override
    public User getUserInformation(Integer id) {
        User user = findUserById(id);
        return user;
    }

    @Override
    public void setAP(String path) {
        sessionFactory.getCurrentSession().createSQLQuery("UPDATE users_information SET `avatar`='" + path
                + "' WHERE uid = " + getCurrentUser().getId()).executeUpdate();
    }

}
