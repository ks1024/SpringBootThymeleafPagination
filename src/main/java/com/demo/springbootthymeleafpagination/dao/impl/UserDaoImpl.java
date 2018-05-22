package com.demo.springbootthymeleafpagination.dao.impl;

import com.demo.springbootthymeleafpagination.dao.UserDao;
import com.demo.springbootthymeleafpagination.domain.User;
import com.demo.springbootthymeleafpagination.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserDaoImpl implements UserDao {

    private final String SQL_FIND_ALL_USERS = "select * from user";
    private final String SQL_FIND_USERS_WITH_PAGINATION = "select * from user limit %s offset %s";
    private final String SQL_USERS_COUNT = "select count(*) from user";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public List<User> findAllUsers() {
        return jdbcTemplate.query(SQL_FIND_ALL_USERS, new UserMapper());
    }

    @Override
    public List<User> findAllUsers(int pageSize, int pageOffset) {
        return jdbcTemplate.query(String.format(SQL_FIND_USERS_WITH_PAGINATION, pageSize, pageOffset), new UserMapper());
    }

    @Override
    public int getUsersCount() {
        return jdbcTemplate.queryForObject(SQL_USERS_COUNT, Integer.class);
    }
}
