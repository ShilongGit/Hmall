package com.itheima.service;

import com.itheima.bean.OrderItem;
import com.itheima.bean.OrderView;
import com.itheima.bean.Orders;
import com.itheima.bean.PageBean;

import java.util.List;

public interface OrderService {


    void saveOrders(Orders orders, List<OrderItem> orderItemList);

    PageBean<OrderView> queryOrderListByUid(String uid, int currentPage);

    Orders queryOrdersByOid(String oid);

    List<OrderView> queryOrderListByOid(String oid);

    void updateOrderByOid(Orders orders);

    void updateStateByOid(String oid);
}
