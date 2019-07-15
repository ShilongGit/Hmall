package com.itheima.bean;

import java.util.Date;

public class OrderView {
    //商品的pid
    private String pid;
    //商品的个数
    private int count;
    //小计
    private double subtotal;
    //商品名称
    private String pname;
    //商品的图片路径
    private String pimage;
    //商品的购买价格
    private double shop_price;
    // 订单时间
    private Date ordertime;
    // 订单状态
    private Integer state;// 订单状态 0:未付款 1:已付款 2:已发货 3.已完成
    // 订单id
    private String oid;

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    public OrderView() {
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public double getSubtotal() {
        return subtotal;
    }

    public void setSubtotal(double subtotal) {
        this.subtotal = subtotal;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    public String getPimage() {
        return pimage;
    }

    public void setPimage(String pimage) {
        this.pimage = pimage;
    }

    public double getShop_price() {
        return shop_price;
    }

    public void setShop_price(double shop_price) {
        this.shop_price = shop_price;
    }

    public Date getOrdertime() {
        return ordertime;
    }

    public void setOrdertime(Date ordertime) {
        this.ordertime = ordertime;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }
}
