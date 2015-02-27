package com.ab.actionbook.dao;

import com.ab.actionbook.domain.User;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.JDBC4Connection;
import org.hibernate.SessionFactory;
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
    }

    @SuppressWarnings("unchecked")
    public List<User> findUserByLogin(String login) {
        return sessionFactory.getCurrentSession().createQuery("from User where login='"+login+"'").list();

    }

    @SuppressWarnings("unchecked")
    public List<User> getAllUsers() {
        return sessionFactory.getCurrentSession().createQuery("from User").list();
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
        User user = getUserByLogin(username);
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
    public void setUserInformation() {

    }

    /*@SuppressWarnings("deprecation")
    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException, DataAccessException {
        User user = new User(
                findUserByLogin(s).get(0).getLogin(),
                findUserByLogin(s).get(0).getFirstname(),
                findUserByLogin(s).get(0).getLastname(),
                findUserByLogin(s).get(0).getEmail(),
                findUserByLogin(s).get(0).getPassword()
        );
        user.setId(findUserByLogin(s).get(0).getId());
        List<GrantedAuthority> authorityList = AuthorityUtils.createAuthorityList("ROLE_USER");
        UserDetails userDetails = new org.springframework.security.core.userdetails.User(
                user.getLogin(),
                user.getPassword(),
                authorityList
        );
        return userDetails;
    }*/

    public User getUserByLogin(String s){
        User user = new User(
                findUserByLogin(s).get(0).getLogin(),
                findUserByLogin(s).get(0).getFirstname(),
                findUserByLogin(s).get(0).getLastname(),
                findUserByLogin(s).get(0).getEmail(),
                findUserByLogin(s).get(0).getPassword()
        );
        user.setId(findUserByLogin(s).get(0).getId());
        return user;
    }
}
