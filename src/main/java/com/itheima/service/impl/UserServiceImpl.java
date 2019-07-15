package com.itheima.service.impl;

import com.itheima.bean.User;
import com.itheima.dao.UserDao;
import com.itheima.service.UserService;
import com.itheima.utils.BeanFactory;

import java.sql.SQLException;

public class UserServiceImpl implements UserService {

    private  UserDao userDao = BeanFactory.getBean(UserDao.class);

    public User queryUser(User user) {
        try {
            return userDao.selectUserByCondition(user);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    public boolean saveUser(User user) {

        long count = 0;
        try {
            count = userDao.insertUser(user);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        if (count == 1) return true;

        return false;
    }
}
