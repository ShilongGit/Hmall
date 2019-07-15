package com.itheima.dao.impl;

import com.itheima.bean.User;
import com.itheima.dao.UserDao;
import com.itheima.utils.BeanFactory;
import com.itheima.utils.C3P0Utils;
import com.itheima.utils.UUIDUtils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class UserDaoImpl implements UserDao{

    private QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());

    public User selectUserByCondition(User user) throws SQLException {

        String sql = "select * from user where username = ? and password = ?";
        Object[] params ={user.getUsername(),user.getPassword()};
        return queryRunner.query(sql,new BeanHandler<User>(User.class),params);
    }

    public long insertUser(User u) throws SQLException {

        String sql = "insert into user values(?,?,?,?,?,?,?,?,?,?)";
        Object[] params = { u.getUid(),u.getUsername(),u.getPassword(),u.getName(),
                u.getEmail(),u.getBirthday(),u.getGender(),u.getState(),
                u.getCode(),u.getRemark()};

        return queryRunner.update(sql,params);
    }
}
