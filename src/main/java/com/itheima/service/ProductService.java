package com.itheima.service;

import com.itheima.bean.PageBean;
import com.itheima.bean.Product;

import java.util.List;

public interface ProductService {
    List<Product> queryProductByCondition(Integer integer);

    List<Product> queryNewProduct(Integer integer);

    Product queryProductByPid(String pid);

    PageBean<Product> queryProductByCid(String cid,String currentPage);

    void addProduct(Product product);
}
