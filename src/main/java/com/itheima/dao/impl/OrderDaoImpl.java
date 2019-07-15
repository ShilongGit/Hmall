package com.itheima.dao.impl;

import com.itheima.bean.OrderItem;
import com.itheima.bean.OrderView;
import com.itheima.bean.Orders;
import com.itheima.dao.OrderDao;
import com.itheima.utils.C3P0Utils;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OrderDaoImpl implements OrderDao {

    public void insertOrders(Connection connection, Orders orders) throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        String sql = "insert into orders values(?,?,?,?,?,?,?,?)";
        Object[] params = {orders.getOid(),orders.getOrdertime(),orders.getTotal(),orders.getState(),orders.getAddress(),orders.getName(),orders.getTelephone(),orders.getUid()};
        queryRunner.update(connection,sql,params);
    }

    public void insertOrderItem(Connection connection, OrderItem orderItem) throws SQLException {
        QueryRunner queryRunner = new QueryRunner();
        String sql = "insert into orderitem values(?,?,?,?)";

        Object[] params = {orderItem.getCount(),orderItem.getSubTotal(),orderItem.getPid(),orderItem.getOid()};
        queryRunner.update(connection,sql,params);
    }

    public List<OrderView> selectOrderListByUid(String uid) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "SELECT \n" +
                "\t\t\toit.pid,\n" +
                "\t\t\toit.count,\n" +
                "\t\t\toit.subtotal,\n" +
                "\t\t\tp.pname,\n" +
                "\t\t\tp.pimage,\n" +
                "\t\t\tp.shop_price,\n" +
                "\t\t\to.ordertime,\n" +
                "\t\t\to.state,\n" +
                "\t\t\to.oid\n" +
                "FROM \n" +
                "     orders o\n" +
                "LEFT JOIN \n" +
                "      orderitem oit\n" +
                "ON \n" +
                "      o.oid = oit.oid\n" +
                "LEFT JOIN \n" +
                "      product p\n" +
                "ON \n" +
                "     oit.pid = p.pid\n" +
                "WHERE \n" +
                "    o.uid = ?";

        return queryRunner.query(sql,new BeanListHandler<OrderView>(OrderView.class),uid);
    }

    public long selectCountUid(String uid) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "SELECT \n" +
                "\t\t\tcount(*)\n" +
                "FROM \n" +
                "     orders o\n" +
                "LEFT JOIN \n" +
                "      orderitem oit\n" +
                "ON \n" +
                "      o.oid = oit.oid\n" +
                "LEFT JOIN \n" +
                "      product p\n" +
                "ON \n" +
                "     oit.pid = p.pid\n" +
                "WHERE \n" +
                "    o.uid = ?";

        return queryRunner.query(sql,new ScalarHandler<Long>(),uid);
    }

    public List<OrderView> selectOrderViewByLimit(int currentPage, int pageSize, String uid) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "SELECT \n" +
                "\t\t\toit.pid,\n" +
                "\t\t\toit.count,\n" +
                "\t\t\toit.subtotal,\n" +
                "\t\t\tp.pname,\n" +
                "\t\t\tp.pimage,\n" +
                "\t\t\tp.shop_price,\n" +
                "\t\t\to.ordertime,\n" +
                "\t\t\to.state,\n" +
                "\t\t\to.oid\n" +
                "FROM \n" +
                "     orders o\n" +
                "LEFT JOIN \n" +
                "      orderitem oit\n" +
                "ON \n" +
                "      o.oid = oit.oid\n" +
                "LEFT JOIN \n" +
                "      product p\n" +
                "ON \n" +
                "     oit.pid = p.pid\n" +
                "WHERE \n" +
                "    o.uid = ?" +
                " limit ?,?";
        Object[] params = {uid,currentPage,pageSize};
        return queryRunner.query(sql,new BeanListHandler<OrderView>(OrderView.class),params);
    }

    public Orders selectOrdersByOid(String oid) throws SQLException {

        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "select * from orders where oid =?";
        return queryRunner.query(sql,new BeanHandler<Orders>(Orders.class),oid);
    }

    public List<OrderView> selectOrderListByOid(String oid) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "SELECT \n" +
                "\t\t\toit.pid,\n" +
                "\t\t\toit.count,\n" +
                "\t\t\toit.subtotal,\n" +
                "\t\t\tp.pname,\n" +
                "\t\t\tp.pimage,\n" +
                "\t\t\tp.shop_price,\n" +
                "\t\t\to.ordertime,\n" +
                "\t\t\to.state,\n" +
                "\t\t\to.oid\n" +
                "FROM \n" +
                "     orders o\n" +
                "LEFT JOIN \n" +
                "      orderitem oit\n" +
                "ON \n" +
                "      o.oid = oit.oid\n" +
                "LEFT JOIN \n" +
                "      product p\n" +
                "ON \n" +
                "     oit.pid = p.pid\n" +
                "WHERE \n" +
                "    o.oid = ?";

        return queryRunner.query(sql,new BeanListHandler<OrderView>(OrderView.class),oid);
    }

    @Override
    public void updateOrderByOid(Orders orders) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "update orders set name=?,address=?,telephone=? where oid =?";
        Object[] params = {orders.getName(),orders.getAddress(),orders.getTelephone(),orders.getOid()};
        queryRunner.update(sql,params);
    }

    @Override
    public void updateStateByOid(String oid) throws SQLException {
        QueryRunner queryRunner = new QueryRunner(C3P0Utils.getDataSource());
        String sql = "update orders set state=1 where oid =?";
        queryRunner.update(sql,oid);
    }
}
