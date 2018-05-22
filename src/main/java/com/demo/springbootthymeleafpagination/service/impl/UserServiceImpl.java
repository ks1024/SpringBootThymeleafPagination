package com.demo.springbootthymeleafpagination.service.impl;

import com.demo.springbootthymeleafpagination.dao.UserDao;
import com.demo.springbootthymeleafpagination.domain.User;
import com.demo.springbootthymeleafpagination.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public List<User> findAllUsers() {
        return userDao.findAllUsers();
    }

    @Override
    public List<User> findAllUsers(int pageSize, int pageOffset) {
        return userDao.findAllUsers(pageSize, pageOffset);
    }

    @Override
    public int getUsersCount() {
        return userDao.getUsersCount();
    }
}
