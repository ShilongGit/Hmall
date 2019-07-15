package com.itheima.Servlet;

import com.itheima.bean.PageBean;
import com.itheima.bean.Product;
import com.itheima.bean.Result;
import com.itheima.common.BaseServlet;
import com.itheima.service.ProductService;
import com.itheima.utils.BeanFactory;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/product")
public class ProductServlet extends BaseServlet {

    private ProductService productService = BeanFactory.getBean(ProductService.class);
    public void queryHotProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String queryCount = request.getParameter("queryCount");

        List<Product> products = productService.queryProductByCondition(Integer.valueOf(queryCount));

        response.getWriter().print(JSONArray.fromObject(products));
    }

    public void queryNewProducts(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String queryCount = request.getParameter("queryCount");

        List<Product> products = productService.queryNewProduct(Integer.valueOf(queryCount));

        response.getWriter().print(JSONArray.fromObject(products));
    }

    public void queryProductInfo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String pid = request.getParameter("pid");

        Product product = productService.queryProductByPid(pid);

        response.getWriter().print(JSONObject.fromObject(new Result(1,pid,product)));
    }

    public void queryProductByCid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        String cid = request.getParameter("cid");
        String currentPage = request.getParameter("currentPage");

        if(currentPage==null || currentPage.equals("null")){
            currentPage = "1";
        }
        PageBean<Product> productPage = productService.queryProductByCid(cid,currentPage);

        Result r = new Result(1,"",productPage);
        response.getWriter().print(JSONObject.fromObject(r));
    }
}
