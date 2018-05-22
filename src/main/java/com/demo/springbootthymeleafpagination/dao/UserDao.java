package com.demo.springbootthymeleafpagination.dao;

import com.demo.springbootthymeleafpagination.domain.User;

import java.util.List;

public interface UserDao {

    List<User> findAllUsers();
    List<User> findAllUsers(int pageSize, int pageOffset);
    int getUsersCount();
}
