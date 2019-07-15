package com.itheima.Servlet;

import com.itheima.bean.Result;
import com.itheima.bean.User;
import com.itheima.common.BaseServlet;
import com.itheima.service.UserService;
import com.itheima.utils.BeanFactory;
import com.itheima.utils.UUIDUtils;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

@WebServlet(urlPatterns = "/user")
public class UserServlet extends BaseServlet {

    private UserService userService = BeanFactory.getBean(UserService.class);

    public void register(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        Map<String, String[]> parameterMap = request.getParameterMap();
        User user = new User();
        user.setUid(UUIDUtils.getId());
        try {
            BeanUtils.populate(user,parameterMap);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Result result = new Result();
        boolean flag = userService.saveUser(user);

        if (flag){
            result.setStatus(1);
        }else {
            result.setStatus(0);
        }

        System.out.println("234");
        response.getWriter().print(JSONObject.fromObject(result));
    }


    public void login(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        Result result = new Result();
        Map<String, String[]> parameterMap = request.getParameterMap();
        User user = new User();
        try {
            BeanUtils.populate(user,parameterMap);
        } catch (Exception e) {
            e.printStackTrace();
        }

        User dbuser = userService.queryUser(user);


//        public static final int SUCCESS = 1;//成功
//        public static final int FAILS = 0;//失败
//        public static final int NOLOGIN = 2;//没有登录
        if (dbuser == null){
            result.setStatus(0);
            result.setMessage("用户名或密码错误");
            response.getWriter().print(JSONObject.fromObject(result));
        }else {
            result.setStatus(1);
            result.setMessage("登陆成功");

            request.getSession().setAttribute("user",dbuser);
            request.getSession().setAttribute("username",dbuser.getUsername());

            Cookie c = new Cookie("username",dbuser.getUsername());
            c.setPath(request.getContextPath());
            c.setMaxAge(1800);

            c.setDomain("itheima351.com");
            //写回
            response.addCookie(c);

            response.getWriter().print(JSONObject.fromObject(result));
        }

    }


    public void loginOut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{

        request.getSession().invalidate();

        Cookie c = new Cookie("username",null);
        c.setPath(request.getContextPath());
        c.setMaxAge(0);
        c.setDomain("itheima351.com");
        response.addCookie(c);

        Result result = new Result();
        result.setStatus(1);
        result.setMessage("退出登陆成功");

        response.getWriter().print(JSONObject.fromObject(result));

    }
}
