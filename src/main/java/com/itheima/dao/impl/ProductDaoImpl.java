package com.itheima.dao.impl;

import com.itheima.bean.Product;
import com.itheima.dao.ProductDao;
import com.itheima.utils.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.SQLException;
import java.util.List;

public class ProductDaoImpl implements ProductDao {

    private QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
    public List<Product> selectProductByCondition(Integer integer) throws SQLException {

        String sql = "select * from product where is_hot = ? limit ?";
        Object[] params ={1,integer};

        return queryRunner.query(sql,new BeanListHandler<Product>(Product.class),params);
    }

    public List<Product> selectNewProducts(Integer integer) throws SQLException {
        String sql = "select * from product order by pdate desc limit ?";
        Object[] params ={integer};

        return queryRunner.query(sql,new BeanListHandler<Product>(Product.class),params);
    }

    public Product selectProductByPid(String pid) throws SQLException {
        String sql = "select * from product where pid = ?";
        return queryRunner.query(sql,new BeanHandler<Product>(Product.class),pid);
    }

    public long selectCountCid(String cid) throws SQLException {
        String sql = "select count(*) from product where cid = ?";

        return queryRunner.query(sql,new ScalarHandler<Long>(),cid);
    }

    public List<Product> selectProductByLimit(int i, int pageSize, String cid) throws SQLException {
        String sql = "select * from product where cid = ? limit ?,?";
        Object[] params = {cid,i,pageSize};
        return queryRunner.query(sql,new BeanListHandler<Product>(Product.class),params);
    }

    @Override
    public void insertProduct(Product product) throws SQLException {
        String sql = "insert into product values(?,?,?,?,?,?,?,?,?,?)";
        Object[] params = {product.getPid(),product.getPname(),product.getMarket_price(),
                product.getShop_price(),product.getPimage(),product.getPdate(),
                product.getIs_hot(),product.getPdesc(),product.getPflag(),product.getCid()};
        queryRunner.update(sql,params);
    }
}
