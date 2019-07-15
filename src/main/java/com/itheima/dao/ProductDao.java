package com.itheima.dao;

import com.itheima.bean.Product;

import java.sql.SQLException;
import java.util.List;

public interface ProductDao {
    List<Product> selectProductByCondition(Integer integer) throws SQLException;

    List<Product> selectNewProducts(Integer integer) throws SQLException;

    Product selectProductByPid(String pid) throws SQLException;

    long selectCountCid(String cid) throws SQLException;

    List<Product> selectProductByLimit(int i, int pageSize, String cid) throws SQLException;

    void insertProduct(Product product) throws SQLException;
}
