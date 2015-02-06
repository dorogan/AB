package com.ab.actionbook.dao;


import com.ab.actionbook.domain.User;

import java.util.List;

public interface UserDAO {

    public void addUser(User user);

    public List<User> findUserByLogin(String login);

    public List<User> getAllUsers();

    public void addToFriend(Integer id);

}
