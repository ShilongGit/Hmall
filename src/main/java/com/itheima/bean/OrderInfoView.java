package com.itheima.bean;

import java.util.Date;
import java.util.List;

public class OrderInfoView {
    // 订单id
    private String oid;
    // 订单状态
    private Integer state;// 订单状态 0:未付款 1:已付款 2:已发货 3.已完成
    // 订单时间
    private Date ordertime;

    private List<OrderView> orderViewList;

    public OrderInfoView() {
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public Date getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(Date ordertime) {
        this.ordertime = ordertime;
    }

    public List<OrderView> getOrderViewList() {
        return orderViewList;
    }

    public void setOrderViewList(List<OrderView> orderViewList) {
        this.orderViewList = orderViewList;
    }
}
