package com.itheima.filter;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter( urlPatterns= "/*")
public class AjaxFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        //向下转型
        HttpServletRequest request = (HttpServletRequest)req;
        HttpServletResponse response =(HttpServletResponse)resp;

        //允许AJAX跨域访问
        response.setHeader("Access-Control-Allow-Origin", "http://www.itheima351.com:8020");
        //AJAX访问允许客户端保存cookie
        response.setHeader("Access-Control-Allow-Credentials","true");
        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
