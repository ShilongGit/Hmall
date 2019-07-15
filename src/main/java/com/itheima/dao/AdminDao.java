package com.itheima.dao;

import com.itheima.bean.Category;
import com.itheima.bean.Product;

import java.sql.SQLException;
import java.util.List;

public interface AdminDao {
    List<Category> selectCategorys() throws SQLException;

    Category selectCategoryByCid(String cid) throws SQLException;

    void updateCategoryByCid(Category categorie) throws SQLException;

    void insertCategory(String cid, String cname) throws SQLException;

    long selectProductCountByCid(String cid) throws SQLException;

    void deleteCategoryByCid(String cid) throws SQLException;

    long selectProductCount() throws SQLException;

    List<Product> selectProdcutByLimit(int i, int pageSize) throws SQLException;
}
