package com.itheima.dao;

import com.itheima.bean.Category;

import java.sql.SQLException;
import java.util.List;

public interface CategoryDao {
    List<Category> selectAll() throws SQLException;
}
