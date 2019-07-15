package com.itheima.service;

import com.itheima.bean.User;

public interface UserService {
    User queryUser(User user);

    boolean saveUser(User user);
}
