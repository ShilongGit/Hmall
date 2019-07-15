package com.itheima.Servlet;

import com.itheima.bean.Category;
import com.itheima.common.BaseServlet;
import com.itheima.service.CategoryService;
import com.itheima.utils.BeanFactory;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/category")
public class CategoryServlet extends BaseServlet {

    private CategoryService categoryService = BeanFactory.getBean(CategoryService.class);
    public void loadMenu(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        List<Category> categorys = categoryService.queryAll();

        response.getWriter().print(JSONArray.fromObject(categorys));

    }

}
