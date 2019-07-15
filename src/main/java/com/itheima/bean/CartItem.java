package com.itheima.bean;
/*
    购物项
 */
public class CartItem {
    //商品对象
    private Product product;
    //商品数量
    private int count;
    //小计
    private double subTotal;

    public CartItem() {
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
    /*
        小计只能获取 不能设置
        因为设置就可以随便设置
        小计其实就是商品的购买价格 * 数量
     */
    public double getSubTotal() {
        return subTotal = product.getShop_price()*count;
    }

//    public void setSubTotal(double subTotal) {
//        this.subTotal = subTotal;
//    }
}
