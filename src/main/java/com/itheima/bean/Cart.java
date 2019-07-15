package com.itheima.bean;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
/*
    购物车
 */
public class Cart {
    /*
        总金额
     */
    private double total;
    /*
        购物车中的购物项
        键 String  pid
        值 购物项对象
     */
    private Map<String,CartItem> itemMap = new HashMap<String,CartItem>();

    /*
        添加购物项
        就是向map中添加CartItem对象
        判断Map集合中是否包含这个CartItem对象
        如果不包含 直接添加
        如果包含 获取到对应的CartItem对象 获取到数量
        新的数量 + 获取到的数量 = 修改后的数量

     */
    public void addCartItem(CartItem item){
        //获取到要添加购物项的pid
        String pid = item.getProduct().getPid();
        // 判断Map集合中是否包含这个CartItem对象
        if(itemMap.containsKey(pid)){
            //获取到map中存储的 购物项对象
            CartItem cartItem = itemMap.get(pid);
            //重新设置这个对象的数量  新的数量 + 原来的数量
            cartItem.setCount(item.getCount()+cartItem.getCount());
        }else{
            itemMap.put(pid,item);
        }

        //计算总金额
        total+=item.getSubTotal();
    }

    /*
        删除购物项
        实际上就是根据键删除对应的购物项对象
     */
    public void removeItem(String pid){
        //删除购物项
        CartItem removeItem = itemMap.remove(pid);
        //重新计算总金额
        total -= removeItem.getSubTotal();

    }

    /*
        清空购物项
        就是将map集合中的数据清空
        总价为0
     */
    public void clearItem(){
        //清空map集合
        itemMap.clear();

        //总价为0
        total = 0;

    }

    public Cart() {
    }

    public double getTotal() {
        return total;
    }

    /*
        当获取购物项集合时 不再获取map集合 而是只获取所有的购物项的集合就可以了
     */
    public Collection<CartItem> getItemMap() {
        Collection<CartItem> values = itemMap.values();
        return values;
    }

    public void setItemMap(Map<String, CartItem> itemMap) {
        this.itemMap = itemMap;
    }
}
