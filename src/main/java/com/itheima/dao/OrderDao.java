package com.itheima.dao;

import com.itheima.bean.OrderItem;
import com.itheima.bean.OrderView;
import com.itheima.bean.Orders;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface OrderDao {
    void insertOrders(Connection connection, Orders orders) throws SQLException;

    void insertOrderItem(Connection connection, OrderItem orderItem) throws SQLException;

    List<OrderView> selectOrderListByUid(String uid) throws SQLException;

    long selectCountUid(String uid) throws SQLException;

    List<OrderView> selectOrderViewByLimit(int i, int pageSize, String uid) throws SQLException;

    Orders selectOrdersByOid(String oid) throws SQLException;

    List<OrderView> selectOrderListByOid(String oid) throws SQLException;

    void updateOrderByOid(Orders orders) throws SQLException;

    void updateStateByOid(String oid) throws SQLException;
}
