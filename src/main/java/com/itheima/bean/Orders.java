package com.itheima.bean;

import java.util.Date;

/*
    订单的实体类
 */
public class Orders {
    // 订单id
    private String oid;
    // 订单时间
    private Date ordertime;
    // 订单金额
    private Double total;
    // 订单状态
    private Integer state;// 订单状态 0:未付款 1:已付款 2:已发货 3.已完成
    // 收获地址
    private String address;
    // 收货人姓名
    private String name;
    // 收获人电话
    private String telephone;
    /*
        用户id
     */
    private String uid;

    public Orders() {
    }



    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public Date getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(Date ordertime) {
        this.ordertime = ordertime;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    @Override
    public String toString() {
        return "Orders{" +
                "oid='" + oid + '\'' +
                ", ordertime=" + ordertime +
                ", total=" + total +
                ", state=" + state +
                ", address='" + address + '\'' +
                ", name='" + name + '\'' +
                ", telephone='" + telephone + '\'' +
                ", uid='" + uid + '\'' +
                '}';
    }
}
