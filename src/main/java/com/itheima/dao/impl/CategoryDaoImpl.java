package com.itheima.dao.impl;

import com.itheima.bean.Category;
import com.itheima.dao.CategoryDao;
import com.itheima.utils.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import java.sql.SQLException;
import java.util.List;

public class CategoryDaoImpl implements CategoryDao {

    private QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());

    public List<Category> selectAll() throws SQLException {

        String sql = "select * from category";

        return queryRunner.query(sql,new BeanListHandler<Category>(Category.class));
    }
}
