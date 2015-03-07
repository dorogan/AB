package com.ab.actionbook.service;

import com.ab.actionbook.domain.User;

import java.util.ArrayList;
import java.util.List;

public interface UserService {

    public void addUser(User user);

    public List<User> findUserByLogin(String login);

    public List<User> getAllUsers();

    public List<User> getAllFriends();

    public int getCountOfPropositionToFriends();

    public void addToFriend(Integer id);

    public List<User> getAllPropositions();

    public void confirmPropose(Integer id);

    public void turnDownPropose(Integer id);

    public User getCurrentUser();

    public void setUserInformation(User user);

}
