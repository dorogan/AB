package com.ab.actionbook.service;

import com.ab.actionbook.dao.UserDAO;
import com.ab.actionbook.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserDAO userDAO;

    @Override
    @Transactional
    public void addUser(User user) {
        userDAO.addUser(user);
    }

    @Override
    public List<User> findUserByLogin(String login) {
        return userDAO.findUserByLogin(login);
    }

    @Override
    @Transactional
    public List<User> getAllUsers() {
        return userDAO.getAllUsers();

    }

    @Override
    public List<User> getAllFriends() {
        return userDAO.getAllFriends();
    }

    @Override
    public int getCountOfPropositionToFriends() {
        return 0;
    }

    @Transactional
    public void addToFriend(Integer id){
        userDAO.addToFriend(id);
    }

    @Transactional
    @Override
    public List<User> getAllPropositions() {
        return userDAO.getAllPropositions();
    }

    @Transactional
    @Override
    public void confirmPropose(Integer id) {
        userDAO.confirmPropose(id);
    }

    @Transactional
    @Override
    public void turnDownPropose(Integer id) {
        userDAO.turnDownPropose(id);
    }

    @Transactional
    @Override
    public User getCurrentUser() {
        return userDAO.getCurrentUser();
    }

    @Transactional
    @Override
    public void setUserInformation(User user) {
        userDAO.setUserInformation(user);
    }
}
