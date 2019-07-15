package com.itheima.dao.impl;

import com.itheima.bean.Category;
import com.itheima.bean.Product;
import com.itheima.dao.AdminDao;
import com.itheima.utils.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class AdminDaoImpl implements AdminDao {

    private QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());

    @Override
    public List<Category> selectCategorys() throws SQLException {
        String sql = "select * from category";
        return queryRunner.query(sql,new BeanListHandler<Category>(Category.class));
    }

    @Override
    public Category selectCategoryByCid(String cid) throws SQLException {
        String sql = "select * from category where cid = ?";
        return queryRunner.query(sql,new BeanHandler<Category>(Category.class),cid);
    }

    @Override
    public void updateCategoryByCid(Category categorie) throws SQLException {
        String sql = "update category set cname = ? where cid = ?";
        Object[] params ={categorie.getCname(),categorie.getCid()};
        queryRunner.update(sql,params);
    }

    @Override
    public void insertCategory(String cid, String cname) throws SQLException {
        String sql = "insert into category values(?,?)";
        queryRunner.update(sql,cid,cname);
    }

    @Override
    public long selectProductCountByCid(String cid) throws SQLException {
        String sql = "select count(*) from product where cid = ?";
        return queryRunner.query(sql,new ScalarHandler<Long>(),cid);
    }

    @Override
    public void deleteCategoryByCid(String cid) throws SQLException {
        String sql = "delete from category where cid = ?";
        queryRunner.update(sql,cid);
    }

    @Override
    public long selectProductCount() throws SQLException {
        String sql = "select count(*) from product";
        return queryRunner.query(sql,new ScalarHandler<Long>());
    }

    @Override
    public List<Product> selectProdcutByLimit(int current, int pageSize) throws SQLException {
        String sql = "select * from product limit ?,?";

        return queryRunner.query(sql,new BeanListHandler<Product>(Product.class),current,pageSize);
    }
}
