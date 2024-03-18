package com.fate.movie.action;

import com.alibaba.fastjson.JSON;
import com.fate.movie.bean.Member;
import com.fate.movie.bean.Minfo;
import com.fate.movie.bean.Movie;
import com.fate.movie.biz.MinfoBiz;
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

@WebServlet("/minfo.let")
public class MinfoServlet extends HttpServlet {
    MinfoBiz  minfoBiz = new MinfoBiz();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

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

        HttpSession session2 = req.getSession();
        String mvin= (String) session2.getAttribute("mvin");
        String type = req.getParameter("type");
        switch (type){
            case "add":
                try {
                    add(req,resp,out);
                } catch (Exception e) {
                    e.printStackTrace();
                    resp.sendError(500,e.getMessage());
                }
                break;
            case "remove":
                //1.获取删除的minfoid
                long removeId = Long.parseLong(req.getParameter("id"));

                //2. 调用biz删除方法
                try {
                    int count =  minfoBiz.remove(removeId,mvin);
                    if(count>0){
                        out.println("<script>alert('评论删除成功'); location.href='minfo.let?type=query&mvin="+mvin+"&pageIndex=1';</script>");
                    }else{
                        out.println("<script>alert('评论删除失败'); location.href='minfo.let?type=query&mvin="+mvin+"&pageIndex=1';</script>");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    out.println("<script>alert('"+e.getMessage()+"'); location.href='minfo.let?type=query&mvin="+mvin+"&pageIndex=1';</script>");
                }
                //3. 提示+跳转=>out (查询的servlet)
                break;
            case "query":
                query(req,resp,out);
                break;
            default:
                resp.sendError(404);
        }
    }

    /**
     * 查询
     * minfo.let?type=query&pageIndex=1
     * 页数: biz
     * 当前页码:pageIndex = 1
     * 存:request,转发
     * @param req
     * @param resp
     * @param out
     */
    private void query(HttpServletRequest req, HttpServletResponse resp, PrintWriter out) throws ServletException, IOException {
        //1.获取信息(页数，页码,信息)
        int pageSize = 6;
        HttpSession session = req.getSession();
        String mvin= (String) session.getAttribute("mvin");
        int pageCount = minfoBiz.getPageCount( pageSize,mvin);
        int pageIndex = Integer.parseInt(req.getParameter("pageIndex"));
        if(pageIndex<1){
            pageIndex = 1;
        }
        if(pageIndex>pageCount){
            pageIndex = pageCount;
        }
        List<Minfo> minfos = minfoBiz.getByPage(pageIndex,pageSize,mvin);

        //2.存
        req.setAttribute("pageCount",pageCount);
        req.setAttribute("minfos",minfos);
        HttpSession session1 = req.getSession();
        Long state=(Long) session1.getAttribute("state");
        if(state==0){
            req.getRequestDispatcher("minfo_list_beta.jsp?mvin="+mvin+"&pageIndex="+pageIndex).forward(req,resp);
        }
        else
        {
            req.getRequestDispatcher("minfo_list.jsp?mvin="+mvin+"&pageIndex="+pageIndex).forward(req,resp);
        }
        //3. 转发到jsp页面

    }
    /**
     * 添加电影
     *  1.enctype="multipart/form-data":和以前不同
     *  获取表单元素  req.getParameter("name") : error
     *  2.文件上传 ：图片文件从浏览器端保存到服务器端 (第三方 FileUpload+io)
     *  3.路径
     *    图片:
     *    D:\电影管理系统-JavaWeb\01-数据库脚本\cover\city.png  :实际路径
     *    http://localhost:8888/myminfo_explored_war/Images/cover/文城.png:虚拟路径(服务器)
     *
     *
     * @param req
     * @param resp
     */
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
        ServletFileUpload fileUpload = new ServletFileUpload(factory);

        //3.将请求解析成一个个FileItem(文件+表单元素)
        List<FileItem> fileItems = fileUpload.parseRequest(req);

        //4.遍历FileItem
        Minfo minfo  =new Minfo();
        for(FileItem item: fileItems){
            //4.1 元素名称和用户填写的值  name: 文城
            String  name = item.getFieldName();
            String value = item.getString("utf-8");//防止乱码
            switch(name){
                case "desc":
                    minfo.setDesc(value);
                    break;
            }

            
        }
        HttpSession session = req.getSession();
        Member member=(Member) session.getAttribute("user");
        minfo.setMname(member.getName());
        HttpSession session1 = req.getSession();
        String mvin= (String) session1.getAttribute("mvin");
        //5.将信息保存到数据库
        int count = minfoBiz.add(minfo,mvin);
        if(count>0){
            out.println("<script>alert('评论成功');location.href='minfo.let?type=query&mvin="+mvin+"&pageIndex=1';</script>");
        }else{
            out.println("<script>alert('评论失败');location.href='minfo.let?type=query&mvin=\"+mvin+\"&pageIndex=1';</script>");
        }
    }
}
