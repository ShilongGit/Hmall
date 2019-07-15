package com.itheima.service.impl;

import com.itheima.bean.PageBean;
import com.itheima.bean.Product;
import com.itheima.dao.ProductDao;
import com.itheima.service.ProductService;
import com.itheima.utils.BeanFactory;

import java.sql.SQLException;
import java.util.List;

public class ProductServiceImpl implements ProductService {

    private ProductDao productDao = BeanFactory.getBean(ProductDao.class);
    public List<Product> queryProductByCondition(Integer integer) {

        try {
            return productDao.selectProductByCondition(integer);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Product> queryNewProduct(Integer integer) {
        try {
            return productDao.selectNewProducts(integer);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Product queryProductByPid(String pid) {
        try {
            return productDao.selectProductByPid(pid);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public PageBean<Product> queryProductByCid(String cid,String currentPage) {
        PageBean<Product> pageBean = new PageBean<Product>();

        int pageSize = 12;
        long totalCount = 0;
        Integer current = Integer.valueOf(currentPage);
        try {
            totalCount = productDao.selectCountCid(cid);
            pageBean.setPageSize(pageSize);
            pageBean.setCurrentPage(current);
            pageBean.setTotalCount(totalCount);
            pageBean.setTotalPage((int)(Math.ceil((totalCount*1.0)/pageSize)));
            pageBean.setList(productDao.selectProductByLimit((current-1)*pageSize,pageSize,cid));
            return pageBean;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void addProduct(Product product) {
        try {
            productDao.insertProduct(product);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
