package com.itheima.Servlet;

import com.itheima.bean.Result;
import com.itheima.common.BaseServlet;
import com.itheima.service.OrderService;
import com.itheima.utils.BeanFactory;
import com.itheima.utils.WXPayUtils;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@WebServlet(urlPatterns = "/pay")
public class PayServlet extends BaseServlet {

    private OrderService orderService = BeanFactory.getBean(OrderService.class);

    public void createNative(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String oid = request.getParameter("oid");

        Map map = WXPayUtils.createNative(oid, "1");


        Result r = new Result(1,"",map);
        response.getWriter().print(JSONObject.fromObject(r));
    }

    public void queryPayStatus(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oid = request.getParameter("oid");

        boolean flag = WXPayUtils.getFlag(oid);
        if(flag){

            //修改订单状态
            orderService.updateStateByOid(oid);


            Result r = new Result();
            r.setStatus(1);
            response.getWriter().print(JSONObject.fromObject(r));
        }
    }

}
