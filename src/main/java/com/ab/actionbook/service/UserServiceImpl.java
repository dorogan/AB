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

    @Transactional
    public void addToFriend(Integer id){
        userDAO.addToFriend(id);
    }
}
