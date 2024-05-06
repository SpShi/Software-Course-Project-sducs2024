package com.fate.movie.action;

import com.alibaba.fastjson.JSON;
import com.fate.movie.bean.Jobs;
import com.fate.movie.bean.Movie;
import com.fate.movie.bean.User;
import com.fate.movie.biz.JobsBiz;
import com.fate.movie.biz.UserBiz;
import com.fate.movie.util.DateHelper;
import org.apache.commons.fileupload.FileItem;
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
import java.util.Map;

@WebServlet("/jobs.let")
public class JobsServlet extends HttpServlet {
    UserBiz userBiz=new UserBiz();
    JobsBiz jobsBiz=new JobsBiz();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    /**
     * /jobs.let?type=add 添加个人
     * /jobs.let?type=modifypre&id=xx 修改前准备
     * /jobs.let?type=modify        修改
     * /jobs.let?type=remove&id=xx    删除
     * /jobs.let?type=query&pageIndex=1 :分页查询(request:转发)
     * /jobs.let?type=details&id=xx   展示个人详细信息
     * /jobs.let?type=doajax&name=xx  :使用ajax查询个人名对应的个人信息
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //字符编码
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        //out
        PrintWriter out  = resp.getWriter();
        HttpSession session = req.getSession();


        //请求类型
        String type = req.getParameter("type");

        //判断类型
        switch (type){
            case "addpre":
                //存request
                //转发

                HttpSession session1 = req.getSession();
                req.getRequestDispatcher("jobs_add.jsp").forward(req,resp);
                break;
            case "add":

                String namea =  req.getParameter("name");
                long placea=Long.parseLong(req.getParameter("place"));
                int agea=Integer.parseInt(req.getParameter("age"));
                int gendera=Integer.parseInt(req.getParameter("gender"));
                int salarya=Integer.parseInt(req.getParameter("salary"));
                int degreesa=Integer.parseInt(req.getParameter("degrees"));
                String certia=req.getParameter("certificates");
                String majora=req.getParameter("major");
                String emaila=req.getParameter("email");
                String introa=req.getParameter("intro");

                int count = 0;

                count = jobsBiz.add(namea,placea, agea,gendera,degreesa,majora,  certia, salarya, emaila,introa);
                if(count>0){
                    out.println("<script>alert('岗位添加成功'); location.href='jobs.let?type=query';</script>");
                }else{
                    out.println("<script>alert('岗位添加失败'); location.href='jobs.let?type=query';</script>");
                }

                break;
            case "modifypre":
                if(session.getAttribute("user")==null){
                    out.println("<script>alert('请登录');parent.window.location.href='login.html';</script>");
                    return;
                }
                //类型&会员的信息
                long id = Long.parseLong(req.getParameter("id"));
                Jobs jobs = jobsBiz.getById(id);

                req.setAttribute("jobs",jobs);
                break;
            case "modify":
                try {
                    modify(req,resp,out);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "remove":
                if(session.getAttribute("user")==null){
                    out.println("<script>alert('请登录');parent.window.location.href='login.html';</script>");
                    return;
                }
                long jobsId = Long.parseLong(req.getParameter("id"));
                try {
                    int count2 = jobsBiz.remove(jobsId);
                    if(count2>0){
                        out.println("<script>alert('岗位删除成功'); location.href='jobs.let?type=query';</script>");
                    }else{
                        out.println("<script>alert('岗位删除失败'); location.href='jobs.let?type=query';</script>");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    out.println("<script>alert('"+e.getMessage()+"'); location.href='jobs.let?type=query';</script>");
                }
                break;
            case "query":
                query(req,resp,out);
                break;
            case "details":
                details(req,resp,out);
                break;
            case "search":
                if(session.getAttribute("user")==null){
                    out.println("<script>alert('请登录');parent.window.location.href='login.html';</script>");
                    return;
                }
                long places=Long.parseLong(req.getParameter("place"));
                int agel=Integer.parseInt(req.getParameter("agel"));
                int ageh=Integer.parseInt(req.getParameter("ageh"));
                int genders=Integer.parseInt(req.getParameter("gender"));
                int degreess=Integer.parseInt(req.getParameter("degrees"));
                int salarys=Integer.parseInt(req.getParameter("salary"));
                String intros=req.getParameter("intro");
                boolean desc=Boolean.parseBoolean("desc");
                List<Jobs> jobsList2 = jobsBiz.getAllwithLimit(places,agel,ageh,genders,degreess,salarys,intros,desc);
                req.setAttribute("jobsList",jobsList2);
                req.getRequestDispatcher("jobs_list.jsp").forward(req,resp);

                break;
            case "exit":
                if(session.getAttribute("user")==null){
                    out.println("<script>alert('请登录');parent.window.location.href='login.html';</script>");
                    return;
                }
                //1.清除session
                session.invalidate();
                //2.跳转到login.html(框架中需要回去)  top.jsp->parent->index.jsp
                out.println("<script>alert('Success');parent.window.location.href='login.html';</script>");
                break;
            default:
                resp.sendError(404,"请求的地址不存在");
        }

    }

    /**
     * String namem =  req.getParameter("name");
     *         long placem=Long.parseLong(req.getParameter("place"));
     *         int agem=Integer.parseInt(req.getParameter("age"));
     *         int genderm=Integer.parseInt(req.getParameter("gender"));
     *         int salarym=Integer.parseInt(req.getParameter("salary"));
     *         int degreesm=Integer.parseInt(req.getParameter("degrees"));
     *         String certim=req.getParameter("certificates");
     *         String majorm=req.getParameter("major");
     *         String emailm=req.getParameter("email");
     *         String introm=req.getParameter("intro");
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
        Jobs job  =new Jobs();
        for(FileItem item: fileItems){
            if(item.isFormField()){
                //4.1 元素名称和用户填写的值  name: 文城
                String  name = item.getFieldName();
                String value = item.getString("utf-8");//防止乱码
                switch(name){
                    case "id":
                        job.setId(Long.parseLong(value));
                        break;
                    case "name":
                        job.setName(value);
                        break;
                    case "place":
                        job.setPlace(Long.parseLong(value));
                        break;
                    case "age":
                        job.setAge(Integer.parseInt(value));
                        break;
                    case "gender":
                        job.setGender(Integer.parseInt(value));
                        break;
                    case "degrees":
                        job.setDegrees(Integer.parseInt(value));
                        break;
                    case "major":
                        job.setMajor(value);
                        break;
                    case "certificates":
                        job.setCertificates(value);
                        break;
                    case "salary":
                        job.setSalary(Integer.parseInt(value));
                        break;
                    case "email":
                        job.setEmail(value);
                        break;
                    case "intro":
                        job.setIntro(value);
                        break;
                }
            }
        }

        //5.将信息保存到数据库
        int countm = jobsBiz.modify(job);
        if(countm>0){
            out.println("<script>alert('岗位修改成功'); location.href='jobs.let?type=query';</script>");
        }else{
            out.println("<script>alert('岗位修改失败'); location.href='jobs.let?type=query';</script>");
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
        long jobId =  Long.parseLong(req.getParameter("id"));
        //2.根据编号获取电影对象
        Jobs job1 = jobsBiz.getById(jobId);
        //3.将对象保存到req
        req.setAttribute("job",job1);
        HttpSession session = req.getSession();
        session.setAttribute("jobin",job1.getName());
        //4.转发到 jsp页面
        req.getRequestDispatcher("job_details.jsp").forward(req,resp);

    }

    /**
     * 查询
     * job.let?type=query&pageIndex=1
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
        int pageCount = jobsBiz.getPageCount( pageSize);
        int pageIndex = Integer.parseInt(req.getParameter("pageIndex"));
        if(pageIndex<1){
            pageIndex = 1;
        }
        if(pageIndex>pageCount){
            pageIndex = pageCount;
        }
        List<Jobs> jobs = jobsBiz.getByPage(pageIndex,pageSize);

        //2.存
        req.setAttribute("pageCount",pageCount);
        req.setAttribute("jobs",jobs);

        //3. 转发到jsp页面
        req.getRequestDispatcher("job_list.jsp?pageIndex="+pageIndex).forward(req,resp);
    }
}
