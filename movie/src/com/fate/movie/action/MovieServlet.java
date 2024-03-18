package com.fate.movie.action;

import com.alibaba.fastjson.JSON;
import com.fate.movie.bean.Movie;
import com.fate.movie.biz.MovieBiz;
import com.fate.movie.util.DateHelper;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

@WebServlet("/movie.let")
public class MovieServlet extends HttpServlet {
    MovieBiz  movieBiz = new MovieBiz();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    /**
     * /movie.let?type=add 添加电影
     * /movie.let?type=modifypre&id=xx 修改前准备
     * /movie.let?type=modify        修改
     * /movie.let?type=remove&id=xx    删除
     * /movie.let?type=query&pageIndex=1 :分页查询(request:转发)
     * /movie.let?type=details&id=xx   展示电影详细信息
     * /movie.let?type=doajax&name=xx  :使用ajax查询电影名对应的电影信息
     *
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {


        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        PrintWriter  out  = resp.getWriter();

        //验证用户是否登录
        HttpSession session = req.getSession();
        if(session.getAttribute("user")==null){
           out.println("<script>alert('请登录');parent.window.location.href='login.html';</script>");
           return;
        }


        String type = req.getParameter("type");
        switch (type){
            case "add":
                try {
                    add(req,resp,out);
                } catch (FileUploadException e) {
                    e.printStackTrace();
                    resp.sendError(500,"文件上传失败");
                } catch (Exception e) {
                    e.printStackTrace();
                    resp.sendError(500,e.getMessage());
                }
                break;
            case "modifypre":
                //movie.let?type=modifypre&id=xx
                long movieId = Long.parseLong(req.getParameter("id"));
                Movie movie = movieBiz.getById(movieId);
                req.setAttribute("movie",movie);
                req.getRequestDispatcher("movie_modify.jsp").forward(req,resp);
                break;
            case "modify":
                try {
                    modify(req,resp,out);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "remove":
                //1.获取删除的movieid
                long removeId = Long.parseLong(req.getParameter("id"));

                //2. 调用biz删除方法
                try {
                    int count =  movieBiz.remove(removeId);
                    if(count>0){
                        out.println("<script>alert('电影删除成功'); location.href='movie.let?type=query&pageIndex=1';</script>");
                    }else{
                        out.println("<script>alert('电影删除失败'); location.href='movie.let?type=query&pageIndex=1';</script>");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    out.println("<script>alert('"+e.getMessage()+"'); location.href='movie.let?type=query&pageIndex=1';</script>");
                }
                //3. 提示+跳转=>out (查询的servlet)
                break;
            case "query":
                query(req,resp,out);
                break;
            case "details":

                details(req,resp,out);
                break;
            case "doajax":
                String name = req.getParameter("name");
                Movie movie2 = movieBiz.getByName(name);
                if(movie2==null){
                    out.print("{}");//null json 对象
                }else{
                    out.print(JSON.toJSONString(movie2));
                }
                break;
            default:
                 resp.sendError(404);
        }
    }

    /**
     * 修改电影信息的方法
     * @param req
     * @param resp
     * @param out
     */
    private void modify(HttpServletRequest req, HttpServletResponse resp, PrintWriter out) throws Exception {

        //1.构建一个磁盘工厂
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //1.1 设置大小
        factory.setSizeThreshold(1024*9);
        //1.2 临时仓库
        File file = new File("c:\\temp");
        if(!file.exists()){
            file.mkdir();//创建文件夹
        }
        factory.setRepository(file);

        //2.文件上传+表单数据
        ServletFileUpload fileUpload = new ServletFileUpload(factory);

        //3.将请求解析成一个个FileItem(文件+表单元素)
        List<FileItem> fileItems = fileUpload.parseRequest(req);

        //4.遍历FileItem
        Movie movie  =new Movie();
        for(FileItem item: fileItems){
            if(item.isFormField()){
                //4.1 元素名称和用户填写的值  name: 文城
                String  name = item.getFieldName();
                String value = item.getString("utf-8");//防止乱码
                switch(name){
                    case "id":
                        movie.setId(Long.parseLong(value));
                        break;
                    case "pic":
                        movie.setPic(value);
                        break;
                    case "typeId":
                        movie.setTypeId(Long.parseLong(value));
                        break;
                    case "name":
                        movie.setName(value);
                        break;
                    case "price":
                        movie.setPrice(Double.parseDouble(value));
                        break;
                    case "desc":
                        movie.setDesc(value);
                        break;
                    case "publish":
                        movie.setPublish(value);
                        break;
                    case "author":
                        movie.setAuthor(value);
                        break;
                    case "stock":
                        movie.setStock(Long.parseLong(value));
                        break;
                    case "address":
                        movie.setAddress(Integer.parseInt(value));
                        break;
                    case "date":
                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
                        java.util.Date date = new Date(System.currentTimeMillis());
                        try {
                            date = format.parse(value);

                        }
                        catch (Exception e)
                        {
                            format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                            date = format.parse(value);
                        }
                        movie.setDate(date);
                        break;
                }

            }else {
                //4.2 文件: 图片的文件名  city.png,用户不选择图片时，fileName的数据为""
                String fileName = item.getName();
                //避免文件替换：当前的系统时间.png
                if(fileName.trim().length()>0) {
                    //4.2.1 获取后缀名 .png
                    String filterName = fileName.substring(fileName.lastIndexOf("."));
                    //4.2.2 修改文件名  20211117112512234.png
                    fileName = DateHelper.getImageName() + filterName;
                    //保存到哪里
                    //虚拟路径: Images/cover/1-1.png
                    //文件的读写:实际路径 D://xx  --> 虚拟路径: Images/cover对应的实际路径
                    String path = req.getServletContext().getRealPath("/Images/cover");
                    //d:/xxx/xx/ 20211117112512234.png
                    String filePath = path + "/" + fileName;
                    //数据库表中的路径 ：Images/cover/101-1.png：相对项目的根目录的位置
                    String dbPath = "Images/cover/" + fileName;
                    movie.setPic(dbPath);

                    //4.3 保存文件
                    item.write(new File(filePath));
                }
            }
        }
        java.util.Date datenow = new Date(System.currentTimeMillis());
        if(movie.getDate().before(datenow))
        {
            out.println("<script>alert('电影日期错误');location.href='movie_add.jsp';</script>");
            return;
        }
        //5.将信息保存到数据库
        int count = movieBiz.modify(movie);
        if(count>0){
            out.println("<script>alert('修改电影成功');location.href='movie.let?type=query&pageIndex=1';</script>");
        }else{
            out.println("<script>alert('修改电影失败');location.href='movie.let?type=query&pageIndex=1';</script>");
        }
    }

    /**
     * 查看电影详情
     * @param req
     * @param resp
     * @param out
     */
    private void details(HttpServletRequest req, HttpServletResponse resp, PrintWriter out) throws ServletException, IOException {
        //1.获取电影的编号
         long movieId =  Long.parseLong(req.getParameter("id"));
        //2.根据编号获取电影对象
         Movie movie = movieBiz.getById(movieId);
        //3.将对象保存到req
        req.setAttribute("movie",movie);
        HttpSession session = req.getSession();
        session.setAttribute("mvin",movie.getName());
        //4.转发到 jsp页面

        HttpSession session1 = req.getSession();
        Long state=(Long) session1.getAttribute("state");
        if(state==0){
            req.getRequestDispatcher("movie_details_beta.jsp").forward(req,resp);
        }
        else
        {
            req.getRequestDispatcher("movie_details.jsp").forward(req,resp);
        }

    }

    /**
     * 查询
     * movie.let?type=query&pageIndex=1
     * 页数: biz
     * 当前页码:pageIndex = 1
     * 存:request,转发
     * @param req
     * @param resp
     * @param out
     */
    private void query(HttpServletRequest req, HttpServletResponse resp, PrintWriter out) throws ServletException, IOException {
        //1.获取信息(页数，页码,信息)
        int pageSize = 5;
        int pageCount = movieBiz.getPageCount( pageSize);
        int pageIndex = Integer.parseInt(req.getParameter("pageIndex"));
        if(pageIndex<1){
            pageIndex = 1;
        }
        if(pageIndex>pageCount){
            pageIndex = pageCount;
        }
        List<Movie> movies = movieBiz.getByPage(pageIndex,pageSize);

        //2.存
        req.setAttribute("pageCount",pageCount);
        req.setAttribute("movies",movies);

        //3. 转发到jsp页面
        HttpSession session1 = req.getSession();
        Long state=(Long) session1.getAttribute("state");
        if(state==0){
            req.getRequestDispatcher("movie_list_beta.jsp?pageIndex="+pageIndex).forward(req,resp);
        }
        else
        {
            req.getRequestDispatcher("movie_list.jsp?pageIndex="+pageIndex).forward(req,resp);
        }
    }
    /**
     * 添加电影
     *  1.enctype="multipart/form-data":和以前不同
     *  获取表单元素  req.getParameter("name") : error
     *  2.文件上传 ：图片文件从浏览器端保存到服务器端 (第三方 FileUpload+io)
     *  3.路径
     *    图片:
     *    D:\电影管理系统-JavaWeb\01-数据库脚本\cover\city.png  :实际路径
     *    http://localhost:8888/mymovie_explored_war/Images/cover/文城.png:虚拟路径(服务器)
     *
     *
     * @param req
     * @param resp
     */
    private void add(HttpServletRequest req, HttpServletResponse resp,  PrintWriter  out) throws Exception {
        HttpSession session = req.getSession();

        Long state=(Long) session.getAttribute("state");
        if(state==0){
            out.println("<script>alert('无权限'); location.href='index_beta.jsp';</script>");
        }
        //1.构建一个磁盘工厂
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //1.1 设置大小
        factory.setSizeThreshold(1024*9);
        //1.2 临时仓库
        File file = new File("c:\\temp");
        if(!file.exists()){
            file.mkdir();//创建文件夹
        }
        factory.setRepository(file);

        //2.文件上传+表单数据
        ServletFileUpload fileUpload = new ServletFileUpload(factory);

        //3.将请求解析成一个个FileItem(文件+表单元素)
        List<FileItem> fileItems = fileUpload.parseRequest(req);

        //4.遍历FileItem
        Movie movie  =new Movie();
        for(FileItem item: fileItems){
              if(item.isFormField()){
                  //4.1 元素名称和用户填写的值  name: 文城
                  String  name = item.getFieldName();
                  String value = item.getString("utf-8");//防止乱码
                  switch(name){
                      case "typeId":
                           movie.setTypeId(Long.parseLong(value));
                          break;
                      case "name":
                          movie.setName(value);
                          break;
                      case "price":
                          movie.setPrice(Double.parseDouble(value));
                          break;
                      case "desc":
                          movie.setDesc(value);
                          break;
                      case "publish":
                          movie.setPublish(value);
                          break;
                      case "author":
                          movie.setAuthor(value);
                          break;
                      case "stock":
                          movie.setStock(Long.parseLong(value));
                          break;
                      case "address":
                          movie.setAddress(Integer.parseInt(value));
                          break;
                      case "date":
                          SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
                          java.util.Date date = new Date(System.currentTimeMillis());
                          try {
                              date = format.parse(value);

                          }
                          catch (Exception e)
                          {
                              format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
                              date = format.parse(value);
                          }
                          movie.setDate(date);
                          break;
                  }

              }else {
                  //4.2 文件: 图片的文件名
                  String fileName = item.getName();
                  //避免文件替换：当前的系统时间.png
                  //4.2.1 获取后缀名 .png
                  String filterName = fileName.substring(fileName.lastIndexOf("."));
                  //4.2.2 修改文件名  20211117112512234.png
                  fileName = DateHelper.getImageName()+filterName;
                  //保存到哪里
                  //虚拟路径: Images/cover/1-1.png
                  //文件的读写:实际路径 D://xx  --> 虚拟路径: Images/cover对应的实际路径
                  String path = req.getServletContext().getRealPath("/Images/cover");
                  //d:/xxx/xx/ 20211117112512234.png
                  String filePath = path+"/"+fileName;
                  //数据库表中的路径 ：Images/cover/101-1.png：相对项目的根目录的位置
                  String dbPath  = "Images/cover/"+fileName;
                  movie.setPic(dbPath);

                  //4.3 保存文件
                  item.write(new File(filePath));

              }

        }
        java.util.Date datenow = new Date(System.currentTimeMillis());
        if(movie.getDate().before(datenow))
        {
            out.println("<script>alert('电影日期错误');location.href='movie_add.jsp';</script>");
            return;
        }
        //5.将信息保存到数据库
        int count = movieBiz.add(movie);
        if(count>0){
            out.println("<script>alert('添加电影成功');location.href='movie.let?type=query&pageIndex=1';</script>");
        }else{
            out.println("<script>alert('添加电影失败');location.href='movie_add.jsp';</script>");
        }
    }
}
