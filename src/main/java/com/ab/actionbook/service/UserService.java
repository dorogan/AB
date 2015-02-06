package com.ab.actionbook.service;

import com.ab.actionbook.domain.User;

import java.util.ArrayList;
import java.util.List;

public interface UserService {

    public void addUser(User user);

    public List<User> findUserByLogin(String login);

    public List<User> getAllUsers();

    public void addToFriend(Integer id);

}
