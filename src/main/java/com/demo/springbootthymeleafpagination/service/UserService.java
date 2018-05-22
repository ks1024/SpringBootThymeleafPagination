package com.demo.springbootthymeleafpagination.service;

import com.demo.springbootthymeleafpagination.domain.User;

import java.util.List;

public interface UserService {

    List<User> findAllUsers();
    List<User> findAllUsers(int pageSize, int pageOffset);
    int getUsersCount();
}
