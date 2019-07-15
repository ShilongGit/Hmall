package com.itheima.service.impl;

import com.itheima.bean.Category;
import com.itheima.bean.PageBean;
import com.itheima.bean.Product;
import com.itheima.dao.AdminDao;
import com.itheima.service.AdminService;
import com.itheima.utils.BeanFactory;

import java.sql.SQLException;
import java.util.List;

public class AdminServiceImpl implements AdminService {

    private AdminDao adminDao = BeanFactory.getBean(AdminDao.class);

    @Override
    public List<Category> queryAll() {
        try {
            return adminDao.selectCategorys();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Category queryCategoryByCid(String cid) {
        try {
            return adminDao.selectCategoryByCid(cid);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void updateCategoryByCid(Category categorie) {
        try {
            adminDao.updateCategoryByCid(categorie);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveCategory(String cid, String cname) {
        try {
            adminDao.insertCategory(cid,cname);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public long queryProductCountByCid(String cid) {
        try {
            return adminDao.selectProductCountByCid(cid);
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        }
    }

    @Override
    public void delCategoryByCid(String cid) {
        try {
            adminDao.deleteCategoryByCid(cid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public PageBean<Product> queryProductList(Integer currentPage) {
        PageBean<Product> pageBean = new PageBean<Product>();

        int pageSize = 10;
        long totalCount = 0;
        try {
            totalCount = adminDao.selectProductCount();
            pageBean.setPageSize(pageSize);
            pageBean.setCurrentPage(currentPage);
            pageBean.setTotalCount(totalCount);
            pageBean.setTotalPage((int)(Math.ceil((totalCount*1.0)/pageSize)));
            pageBean.setList(adminDao.selectProdcutByLimit((currentPage-1)*pageSize,pageSize));
            return pageBean;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
