package com.itheima.Servlet;

import com.itheima.bean.Cart;
import com.itheima.bean.CartItem;
import com.itheima.bean.Product;
import com.itheima.bean.Result;
import com.itheima.common.BaseServlet;
import com.itheima.service.CartService;
import com.itheima.service.ProductService;
import com.itheima.utils.BeanFactory;
import net.sf.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;

@WebServlet(urlPatterns = "/cart")
public class CartServlet extends BaseServlet {

    private CartService cartService = BeanFactory.getBean(CartService.class);

    private ProductService productService = BeanFactory.getBean(ProductService.class);

    public void clearCartItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Cart cart = getCart(request);

        cart.clearItem();

        Result result = new Result();
        result.setStatus(1);
        result.setMessage("清空失败!!");
        result.setData(cart);


        response.getWriter().print(JSONObject.fromObject(result));
    }


    public void removeCartItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pid = request.getParameter("pid");
        Cart cart = getCart(request);

        cart.removeItem(pid);

        Result result = new Result();
        result.setStatus(1);
        result.setMessage("删除失败!!");
        result.setData(cart);



        response.getWriter().print(JSONObject.fromObject(result));
    }

    public void showCartItems(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Cart cart = getCart(request);

        Result result = new Result();

        result.setData(cart.getItemMap());

        response.getWriter().print(JSONObject.fromObject(result));
    }

    public void addCartItem(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String pid = request.getParameter("pid");
        String count = request.getParameter("count");
        Product product = productService.queryProductByPid(pid);


        CartItem cartItem = new CartItem();

        cartItem.setProduct(product);
        cartItem.setCount(Integer.valueOf(count));



        Cart cart = getCart(request);
        cart.addCartItem(cartItem);

        Result result = new Result();
        result.setStatus(1);

        response.getWriter().print(JSONObject.fromObject(result));


    }

    private Cart getCart(HttpServletRequest request) {
        Cart cart = (Cart) request.getSession().getAttribute("cart");

        if (cart == null){
            Cart c = new Cart();
            request.getSession().setAttribute("cart",c);
            return c;
        }else {
            return cart;
        }
    }
}
