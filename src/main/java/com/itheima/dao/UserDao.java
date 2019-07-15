package com.itheima.dao;

import com.itheima.bean.User;

import java.sql.SQLException;

public interface UserDao {
    User selectUserByCondition(User user) throws SQLException;

    long insertUser(User user) throws SQLException;
}
