package com.ab.actionbook.dao;


import com.ab.actionbook.domain.User;

import java.util.List;

public interface UserDAO {

    public void addUser(User user);

    public List<User> findUserByLogin(String login);

    public List<User> getAllUsers();

    public List<User> getAllFriends();

    public User getCurrentUser();

    public int getCountOfPropositionToFriends();

    public void addToFriend(Integer id);

    public List<User> getAllPropositions();

    public void confirmPropose(Integer id);

    public void turnDownPropose(Integer id);

    public void setUserInformation(User user);

    public List<User> findUserById(Integer id);

    public User getUserById(Integer id);

    public User getUserInformation(Integer id);

    public void setAP(String path);

}
