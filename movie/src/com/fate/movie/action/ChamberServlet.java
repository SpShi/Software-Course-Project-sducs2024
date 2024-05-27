package com.fate.movie.action;

import com.alibaba.fastjson.JSON;
import com.fate.movie.bean.Chamber;
import com.fate.movie.biz.ChamberBiz;
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
import java.io.*;
import java.util.List;

@WebServlet("/chamber.let")
public class ChamberServlet extends HttpServlet {
    ChamberBiz  chamberBiz = new ChamberBiz();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    /**
     * /chamber.let?type=add 添加电影
     * /chamber.let?type=modifypre&id=xx 修改前准备
     * /chamber.let?type=modify        修改
     * /chamber.let?type=remove&id=xx    删除
     * /chamber.let?type=query&pageIndex=1 :分页查询(request:转发)
     * /chamber.let?type=details&id=xx   展示电影详细信息
     * /chamber.let?type=doajax&name=xx  :使用ajax查询电影名对应的电影信息
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
                //chamber.let?type=modifypre&id=xx
                Long state=(Long) session.getAttribute("state");
                if(state==0){
                    out.println("<script>alert('无权限'); location.href='index_beta.jsp';</script>");
                }
                long chamberId = Long.parseLong(req.getParameter("id"));
                Chamber chamber = chamberBiz.getById(chamberId);
                req.setAttribute("chamber",chamber);
                req.getRequestDispatcher("chamber_modify.jsp").forward(req,resp);
                break;
            case "modify":
                try {
                    modify(req,resp,out);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "remove":
                //1.获取删除的chamberid
                long removeId = Long.parseLong(req.getParameter("id"));

                //2. 调用biz删除方法
                try {
                    int count =  chamberBiz.remove(removeId);
                    if(count>0){
                        out.println("<script>alert('影厅删除成功'); location.href='chamber.let?type=query&pageIndex=1';</script>");
                    }else{
                        out.println("<script>alert('影厅删除失败'); location.href='chamber.let?type=query&pageIndex=1';</script>");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    out.println("<script>alert('"+e.getMessage()+"'); location.href='chamber.let?type=query&pageIndex=1';</script>");
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
                Chamber chamber2 = chamberBiz.getByName(name);
                if(chamber2==null){
                    out.print("{}");//null json 对象
                }else{
                    out.print(JSON.toJSONString(chamber2));
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
        ServletFileUpload  fileUpload = new ServletFileUpload(factory);

        //3.将请求解析成一个个FileItem(文件+表单元素)
        List<FileItem> fileItems = fileUpload.parseRequest(req);

        //4.遍历FileItem
        Chamber chamber  =new Chamber();
        for(FileItem  item: fileItems){
            if(item.isFormField()){
                //4.1 元素名称和用户填写的值  name: 文城
                String  name = item.getFieldName();
                String value = item.getString("utf-8");//防止乱码
                switch(name){
                    case "id":
                        chamber.setId(Long.parseLong(value));
                        break;
                    case "pic":
                        chamber.setPic(value);
                        break;
                    case "name":
                        chamber.setName(value);
                        break;
                    case "address":
                        chamber.setAddress(value);
                        break;
                    case "volumn":
                        chamber.setVolumn(Integer.parseInt(value));
                        break;
                }

            }else {
                //4.2 文件: 图片的文件名  文城.png,用户不选择图片时，fileName的数据为""
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
                    chamber.setPic(dbPath);

                    //4.3 保存文件
                    item.write(new File(filePath));
                }
            }
        }

        //5.将信息保存到数据库
        int count = chamberBiz.modify(chamber);
        if(count>0){
            out.println("<script>alert('修改影厅成功');location.href='chamber.let?type=query&pageIndex=1';</script>");
        }else{
            out.println("<script>alert('修改影厅失败');location.href='chamber.let?type=query&pageIndex=1';</script>");
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
         long chamberId =  Long.parseLong(req.getParameter("id"));
        //2.根据编号获取电影对象
         Chamber chamber = chamberBiz.getById(chamberId);
        //3.将对象保存到req
        req.setAttribute("chamber",chamber);
        //4.转发到 jsp页面
        HttpSession session = req.getSession();
        Long state=(Long) session.getAttribute("state");
        if(state==0){
            req.getRequestDispatcher("chamber_details_beta.jsp").forward(req,resp);
        }
        else
        {
            req.getRequestDispatcher("chamber_details.jsp").forward(req,resp);
        }
    }

    /**
     * 查询
     * chamber.let?type=query&pageIndex=1
     * 页数: biz
     * 当前页码:pageIndex = 1
     * 存:request,转发
     * @param req
     * @param resp
     * @param out
     */
    private void query(HttpServletRequest req, HttpServletResponse resp, PrintWriter out) throws ServletException, IOException {
        //1.获取信息(页数，页码,信息)
        int pageSize = 3;
        int pageCount = chamberBiz.getPageCount( pageSize);
        int pageIndex = Integer.parseInt(req.getParameter("pageIndex"));
        if(pageIndex<1){
            pageIndex = 1;
        }
        if(pageIndex>pageCount){
            pageIndex = pageCount;
        }
        List<Chamber> chambers = chamberBiz.getByPage(pageIndex,pageSize);

        //2.存
        req.setAttribute("pageCount",pageCount);
        req.setAttribute("chambers",chambers);
        HttpSession session = req.getSession();
        Long state=(Long) session.getAttribute("state");
        if(state==0){
            req.getRequestDispatcher("chamber_list_beta.jsp?pageIndex="+pageIndex).forward(req,resp);
        }
        else
        {
            req.getRequestDispatcher("chamber_list.jsp?pageIndex="+pageIndex).forward(req,resp);
        }
        //3. 转发到jsp页面

    }


    private void add(HttpServletRequest req, HttpServletResponse resp,  PrintWriter  out) throws Exception {
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
        ServletFileUpload  fileUpload = new ServletFileUpload(factory);

        //3.将请求解析成一个个FileItem(文件+表单元素)
        List<FileItem> fileItems = fileUpload.parseRequest(req);

        //4.遍历FileItem
        Chamber chamber  =new Chamber();
        for(FileItem  item: fileItems){
              if(item.isFormField()){
                  //4.1 元素名称和用户填写的值  name: 文城
                  String  name = item.getFieldName();
                  String value = item.getString("utf-8");//防止乱码
                  switch(name){
                      case "id":
                          chamber.setId(Long.parseLong(value));
                          break;
                      case "pic":
                          chamber.setPic(value);
                          break;
                      case "name":
                          chamber.setName(value);
                          break;
                      case "address":
                          chamber.setAddress(value);
                          break;
                      case "volumn":
                          chamber.setVolumn(Integer.parseInt(value));
                          break;
                  }

              }else {
                  //4.2 文件: 图片的文件名  文城.png
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
                  chamber.setPic(dbPath);

                  //4.3 保存文件
                  item.write(new File(filePath));

              }

        }

        //5.将信息保存到数据库
        int count = chamberBiz.add(chamber);
        if(count>0){
            out.println("<script>alert('添加影厅成功');location.href='chamber.let?type=query&pageIndex=1';</script>");
        }else{
            HttpSession session = req.getSession();
            Long state=(Long) session.getAttribute("state");
            if(state==0){
                out.println("<script>alert('添加影厅失败');location.href='index_beta.jsp';</script>");
            }
            else
            {
                out.println("<script>alert('添加影厅失败');location.href='chamber_add.jsp';</script>");
            }

        }
    }
}
