package com.itheima.service.impl;

import com.itheima.bean.*;
import com.itheima.dao.OrderDao;
import com.itheima.service.OrderService;
import com.itheima.utils.BeanFactory;
import com.itheima.utils.C3P0Utils;
import org.apache.commons.dbutils.DbUtils;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class OrderServiceImpl implements OrderService {

    private  OrderDao orderDao = BeanFactory.getBean(OrderDao.class);

    public void saveOrders(Orders orders, List<OrderItem> orderItemList) {

        Connection connection = null;
        try {
            connection = C3P0Utils.getConnection();
            connection.setAutoCommit(false);

            orderDao.insertOrders(connection,orders);

            for (OrderItem orderItem : orderItemList) {
                orderDao.insertOrderItem(connection,orderItem);
            }

            DbUtils.commitAndCloseQuietly(connection);
        } catch (Exception e) {
            DbUtils.rollbackAndCloseQuietly(connection);
        }


    }

    public PageBean<OrderView> queryOrderListByUid(String uid,int currentPage) {

        PageBean<OrderView> pageBean = new PageBean<OrderView>();

        int pageSize = 3;
        long totalCount = 0;
        try {
            totalCount = orderDao.selectCountUid(uid);
            pageBean.setPageSize(pageSize);
            pageBean.setCurrentPage(currentPage);
            pageBean.setTotalCount(totalCount);
            pageBean.setTotalPage((int)(Math.ceil((totalCount*1.0)/pageSize)));
            pageBean.setList(orderDao.selectOrderViewByLimit((currentPage-1)*pageSize,pageSize,uid));
            return pageBean;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Orders queryOrdersByOid(String oid) {
        try {
            return orderDao.selectOrdersByOid(oid);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<OrderView> queryOrderListByOid(String oid) {
        try {
            return orderDao.selectOrderListByOid(oid);
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void updateOrderByOid(Orders orders) {
        try {
            orderDao.updateOrderByOid(orders);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void updateStateByOid(String oid) {
        try {
            orderDao.updateStateByOid(oid);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
