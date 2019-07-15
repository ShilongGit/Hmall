package com.itheima.Servlet;

import com.itheima.bean.Category;
import com.itheima.bean.PageBean;
import com.itheima.bean.Product;
import com.itheima.bean.Result;
import com.itheima.common.BaseServlet;
import com.itheima.service.AdminService;
import com.itheima.service.ProductService;
import com.itheima.utils.BeanFactory;
import com.itheima.utils.UUIDUtils;
import net.sf.json.JSONObject;
import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.IOUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.*;

@WebServlet(urlPatterns = "/admin")
public class AdminServlet extends BaseServlet {

    private AdminService adminService = BeanFactory.getBean(AdminService.class);
    private ProductService productService = BeanFactory.getBean(ProductService.class);

    public void showProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String currentPage = request.getParameter("currentPage");

        if(currentPage==null || currentPage.equals("null")){
            currentPage = "1";
        }

        PageBean<Product> productPageBean = adminService.queryProductList(Integer.valueOf(currentPage));


        Result result = new Result();
        result.setData(productPageBean);
        response.getWriter().print(JSONObject.fromObject(result));
    }



    public void editCategoty(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String cid = request.getParameter("cid");
        String cname = request.getParameter("cname");
        Category categorie = new Category();
        categorie.setCid(cid);
        categorie.setCname(cname);

        adminService.updateCategoryByCid(categorie);
        Result result = new Result();

        result.setStatus(1);
        result.setMessage("恭喜修改成功");
        response.getWriter().print(JSONObject.fromObject(result));
    }

    public void delCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String cid = request.getParameter("cid");
        long count = adminService.queryProductCountByCid(cid);

        if (count > 0){
            Result result = new Result();
            result.setStatus(2);
            result.setMessage("该分类不可删除");
            response.getWriter().print(JSONObject.fromObject(result));
            return;
        }

        adminService.delCategoryByCid(cid);

        Result result = new Result();
        result.setStatus(1);
        response.getWriter().print(JSONObject.fromObject(result));
    }

    public void queryCategoryByCid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String cid = request.getParameter("cid");
        Category categorie = adminService.queryCategoryByCid(cid);
        Result result = new Result();
        result.setData(categorie);
        response.getWriter().print(JSONObject.fromObject(result));
    }

    public void saveCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String cname = request.getParameter("cname");
        List<Category> categories = adminService.queryAll();

        Set<String> key = new HashSet<>();
        for (Category category : categories) {
            key.add(category.getCid());
        }

        String cid ="";

        while (true){
            cid = (int)(Math.random()*1000)+"";
            if (cid!=""&&!key.contains(cid)) {
                break;
            }
        }

        adminService.saveCategory(cid,cname);

        Result result = new Result();
        result.setStatus(1);
        response.getWriter().print(JSONObject.fromObject(result));
    }

    public void showCategorys(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Category> categories = adminService.queryAll();

        Result result = new Result();
        result.setData(categories);
        response.getWriter().print(JSONObject.fromObject(result));
    }

    public void addProduct(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

       Map<String, String[]> map = parseRequest(request);
        Product product = new Product();
        try {
            BeanUtils.populate(product,map);
        } catch (Exception e) {
        }
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        product.setPid(UUIDUtils.getId());
        product.setPdate(sdf.format(new Date()));
        product.setPflag(0);

        productService.addProduct(product);


        Result result = new Result();
        result.setStatus(1);
        result.setMessage("恭喜添加成功");
        response.getWriter().print(JSONObject.fromObject(result));
    }

    private Map<String,String[]> parseRequest(HttpServletRequest request){
        Map<String,String[]> map = new HashMap<String, String[]>();
        //创建文件磁盘工厂
        DiskFileItemFactory diskFileItemFactory = new DiskFileItemFactory();
        //创建解析对象
        ServletFileUpload servletFileUpload = new ServletFileUpload(diskFileItemFactory);
        //解析Servlet
        try {
            //返回集合,存储文件项
            List<FileItem> list = servletFileUpload.parseRequest(request);
            for (FileItem items : list){
                //获取提交参数名
                String paramName = items.getFieldName();
                //判断是否普通项目
                if(items.isFormField()){
                    //获取提交参数
                    String paramValue = items.getString("utf-8");
                    map.put(paramName,new String[]{paramValue});
                }else {
                    //获取文件名
                    String fileName = items.getName();
                    //去掉盘符
//                    fileName = UploadUtils.getRealName(fileName);
//                    //随机文件名
//                    fileName = UploadUtils.getUUIDName(fileName);
                    //随机文件存储路径
                    String erji = UUID.randomUUID().toString().replace("-","");
                    String dir ="C:/Users/admin/Documents/HBuilderProjects/web/resources/products/";
                    File file = new File(dir);
                    if(!file.exists()){
                        file.mkdirs();
                    }
                    //文件输入流
                    InputStream in = items.getInputStream();
                    //文件输出流
                    FileOutputStream out = new FileOutputStream(dir+"/"+erji+fileName);
                    //commonsio复制文件
                    IOUtils.copy(in,out);
                    //是否资源
                    out.close();
                    in.close();
                    items.delete();
                    map.put("pimage",new String[]{"resources/products"+"/"+erji+fileName});
                }
            }
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return map;
    }
}
