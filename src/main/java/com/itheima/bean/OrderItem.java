package com.itheima.bean;
/*
    将中间表封装成类
 */
public class OrderItem {
    //商品数量
    private Integer count;
    //订单项小计
    private Double subTotal;
    private String pid;
    private String oid;

    public OrderItem() {
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(Double subTotal) {
        this.subTotal = subTotal;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getOid() {
        return oid;
    }

    public void setOid(String oid) {
        this.oid = oid;
    }

    @Override
    public String toString() {
        return "OrderItem{" +
                "count=" + count +
                ", subTotal=" + subTotal +
                ", pid='" + pid + '\'' +
                ", oid='" + oid + '\'' +
                '}';
    }
}
