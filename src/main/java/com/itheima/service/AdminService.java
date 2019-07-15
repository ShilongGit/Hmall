package com.itheima.service;

import com.itheima.bean.Category;
import com.itheima.bean.PageBean;
import com.itheima.bean.Product;

import java.util.List;

public interface AdminService {
    List<Category> queryAll();

    Category queryCategoryByCid(String cid);

    void updateCategoryByCid(Category categorie);

    void saveCategory(String cid, String cname);

    long queryProductCountByCid(String cid);

    void delCategoryByCid(String cid);

    PageBean<Product> queryProductList(Integer integer);
}
