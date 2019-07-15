package com.itheima.Servlet;

import com.itheima.bean.*;
import com.itheima.common.BaseServlet;
import com.itheima.service.OrderService;
import com.itheima.utils.BeanFactory;
import com.itheima.utils.UUIDUtils;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@WebServlet(urlPatterns = "/order")
public class OrderServlet extends BaseServlet {

    private OrderService orderService = BeanFactory.getBean(OrderService.class);

    public void updateOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oid = request.getParameter("oid");
        String telephone = request.getParameter("telephone");
        String name = request.getParameter("name");
        String address = request.getParameter("address");

        Orders orders = new Orders();
        orders.setOid(oid);
        orders.setTelephone(telephone);
        orders.setName(name);
        orders.setAddress(address);

        orderService.updateOrderByOid(orders);

        Result result = new Result();
        result.setStatus(1);

        response.getWriter().print(JSONObject.fromObject(result));
    }

    public void getOrderById(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oid = request.getParameter("oid");

        System.out.println(oid);
        User user = (User) request.getSession().getAttribute("user");

        if (user == null){
            Result result = new Result();
            result.setStatus(2);
            response.getWriter().print(JSONObject.fromObject(result));
            return;
        }

        Orders orders = orderService.queryOrdersByOid(oid);
        List<OrderView> orderViewList = orderService.queryOrderListByOid(oid);

        OrderInfoView orderInfoView = new OrderInfoView();
        orderInfoView.setOid(oid);
        orderInfoView.setState(orders.getState());
        orderInfoView.setOrdertime(orders.getOrdertime());
        orderInfoView.setOrderViewList(orderViewList);

        Result result = new Result();
        result.setData(orderInfoView);
        result.setStatus(1);
        response.getWriter().print(JSONObject.fromObject(result));
    }

    public void showOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String currentPage = request.getParameter("currentPage");

        if(currentPage==null || currentPage.equals("null")){
            currentPage = "1";
        }

        User user = (User) request.getSession().getAttribute("user");

        if (user == null){
            Result result = new Result();
            result.setStatus(2);
            response.getWriter().print(JSONObject.fromObject(result));
            return;
        }

        PageBean<OrderView> orderViewsPage = orderService.queryOrderListByUid(user.getUid(),Integer.valueOf(currentPage));


        Result result = new Result();
        result.setStatus(1);
        result.setData(orderViewsPage);
        response.getWriter().print(JSONObject.fromObject(result));


    }

    public void addOrders(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        User user = (User) request.getSession().getAttribute("user");

        if (user == null){
            Result result = new Result();
            result.setStatus(2);
            response.getWriter().print(JSONObject.fromObject(result));
            return;
        }

        Cart cart = (Cart) request.getSession().getAttribute("cart");

        if (cart.getItemMap().size() == 0){
            Result result = new Result();
            result.setStatus(0);
            result.setMessage("购物车为空");
            response.getWriter().print(JSONObject.fromObject(result));
            return;
        }


        Orders orders = new Orders();

        String oid = UUIDUtils.getId();
        Date date = new Date();
        String uid = user.getUid();
        orders.setOrdertime(date);
        orders.setOid(oid);
        orders.setTotal(cart.getTotal());
        // 订单状态 0:未付款 1:已付款 2:已发货 3.已完成
        orders.setState(0);
        orders.setUid(uid);


        List<OrderItem> orderItemList = new ArrayList<OrderItem>();
        Collection<CartItem> itemMap = cart.getItemMap();
        for (CartItem cartItem : itemMap) {
            OrderItem orderItem = new OrderItem();


            orderItem.setCount(cartItem.getCount());
            orderItem.setOid(oid);
            orderItem.setPid(cartItem.getProduct().getPid());
            orderItem.setSubTotal(cartItem.getSubTotal());

            orderItemList.add(orderItem);
        }

        orderService.saveOrders(orders,orderItemList);

        Result result = new Result();
        result.setStatus(1);
        result.setMessage(uid);
        response.getWriter().print(JSONObject.fromObject(result));


        //清空购物车
        cart.clearItem();

    }

}
