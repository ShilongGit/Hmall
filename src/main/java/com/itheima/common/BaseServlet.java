package com.itheima.common;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Method;

public abstract  class BaseServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        response.setContentType("text/html;charset=utf-8");
        String methodName = request.getParameter("method");
        /*
            使用反射 获取到要执行的方法
         */
        //字节码文件对象
        Class c= this.getClass();

        try{
            //获取到指定的方法
            Method method = c.getMethod(methodName, HttpServletRequest.class, HttpServletResponse.class);
            //执行指定的方法
            method.invoke(this,request,response);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setCharacterEncoding("utf-8");
        doGet(request, response);
    }
}
