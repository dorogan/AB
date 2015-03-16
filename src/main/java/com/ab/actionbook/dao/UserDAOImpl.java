package com.ab.actionbook.dao;

import com.ab.actionbook.domain.User;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.JDBC4Connection;
import org.hibernate.Criteria;
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
    public List<User> findUserByLogin(String login) {
        return sessionFactory.getCurrentSession().createQuery("from User where login='"+login+"'").list();

    }

    @SuppressWarnings("unchecked")
    public List<User> getAllUsers() {
        Criteria criteria = sessionFactory.getCurrentSession().createCriteria(User.class);
        criteria.setMaxResults(25);
        criteria.add(Restrictions.not(Restrictions.in("id", new Integer[] {getCurrentUser().getId()})));
        List users = criteria.list();
        //return sessionFactory.getCurrentSession().createQuery("from User where id not in ("
        //        + getCurrentUser().getId() + ")").list();
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
    public void setUserInformation(User user) {
        int uid = getCurrentUser().getId();
        Date birthday = user.getDateOfBirthday();
        String interests = user.getInterests();
        String profession = user.getProfession();
        String phones = user.getPhone();
        String address = user.getAddress();
        String skype = user.getSkype();
        sessionFactory.getCurrentSession().createSQLQuery("UPDATE " +
                "users_information SET " +
                "`birthday`=" + "'" + birthday  + "', " +
                "`interests`=" + "'" + interests + "'" + ", " +
                "`profession`=" + "'" + profession + "'" + ", " +
                "`phones`=" + "'" + phones + "'" + ", " +
                "`address`=" + "'" + address + "'" + ", " +
                "`skype`=" + "'" + skype + "'" +
                " WHERE `uid`=" + uid).executeUpdate();
    }

    @Override
    public List<User> findUserById(Integer id) {
        return sessionFactory.getCurrentSession().createQuery("from User where id=" + id).list();
    }

    @Override
    public User getUserById(Integer id) {
        User user = new User(
                findUserById(id).get(0).getLogin(),
                findUserById(id).get(0).getFirstname(),
                findUserById(id).get(0).getLastname(),
                findUserById(id).get(0).getEmail(),
                findUserById(id).get(0).getPassword()
        );
        user.setId(id);
        return user;
    }

    @Override
    public User getUserInformation(Integer id) {
        User user = getUserById(id);
        user.setAvatarPath(sessionFactory.getCurrentSession()
                .createQuery("select avatarPath from User where id=" + id).list().get(0).toString());
        user.setDateOfBirthday((Date)sessionFactory.getCurrentSession()
                .createQuery("select dateOfBirthday from User where id=" + id).list().get(0));
        user.setInterests(sessionFactory.getCurrentSession()
                .createQuery("select interests from User where id=" + id).list().get(0).toString());
        user.setProfession(sessionFactory.getCurrentSession()
                .createQuery("select profession from User where id=" + id).list().get(0).toString());
        user.setPhone(sessionFactory.getCurrentSession()
                .createQuery("select phone from User where id=" + id).list().get(0).toString());
        user.setAddress(sessionFactory.getCurrentSession()
                .createQuery("select address from User where id=" + id).list().get(0).toString());
        user.setSkype(sessionFactory.getCurrentSession()
                .createQuery("select skype from User where id=" + id).list().get(0).toString());
        return user;
    }

    @Override
    public void setAP(String path) {
        sessionFactory.getCurrentSession().createSQLQuery("UPDATE users_information SET `avatar`='" + path
                + "' WHERE uid = " + getCurrentUser().getId()).executeUpdate();
    }

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
