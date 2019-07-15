package com.itheima.service.impl;

import com.itheima.bean.Category;
import com.itheima.dao.CategoryDao;
import com.itheima.service.CategoryService;
import com.itheima.utils.BeanFactory;

import java.sql.SQLException;
import java.util.List;

public class CategoryServiceImpl implements CategoryService {

    private CategoryDao categoryDao = BeanFactory.getBean(CategoryDao.class);

    public List<Category> queryAll() {

        try {
            return categoryDao.selectAll();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}
