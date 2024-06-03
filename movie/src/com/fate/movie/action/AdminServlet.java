package com.fate.movie.action;


import com.fate.movie.bean.Admin;
import com.fate.movie.bean.User;
import com.fate.movie.biz.AdminBiz;
import com.fate.movie.biz.UserBiz;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/admin.let")
public class AdminServlet extends HttpServlet{
    UserBiz userBiz=new UserBiz();
    AdminBiz adminBiz=new AdminBiz();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    /**
     * /admin.let?type=add 添加管理员
     * /admin.let?type=modifypre&id=xx 修改前准备
     * /admin.let?type=modify        修改
     * /admin.let?type=remove&id=xx    删除
     * /admin.let?type=query&pageIndex=1 :分页查询(request:转发)
     * /admin.let?type=details&id=xx   展示管理员详细信息
     * /admin.let?type=doajax&name=xx  :使用ajax查询管理员名对应的管理员信息
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
            case"login":
                // 2.从login.html中获取管理员名和密码,验证码
                long id1 = Long.parseLong(req.getParameter("id"));
                String pwd1 = req.getParameter("pwd");
                String valCode = req.getParameter("valcode");

                //2.2 提取session中的验证码,进行判断
//                String code =session.getAttribute("code").toString();
                //不区分大小写
                //暂时去掉提高效率(1717)

//                if(!code.equalsIgnoreCase(valCode)){
//                    out.println("<script>alert('验证码输入错误');location.href = 'login.html';</script>");
//                    return;
//                }


                // 3.调用UserBiz的getUser方法，根据管理员名和密码获取对应的管理员对象
                User usernow=userBiz.getUser(id1,pwd1);

                // 4.判断管理员对象是否为null: 
                if(usernow==null){
                    //  4.1 如果是null表示管理员名或密码不正确 ，提示错误，回到登录页面. 
                    out.println("<script>alert('管理员名或密码不存在');location.href = 'login.html';</script>");
                }else {
                    //  4.2 非空：表示登录成功, 将管理员对象保存到session中,提示登录成功后,将页面跳转到index.jsp
                    session.setAttribute("user_now",usernow);//user-->Object
                    session.setAttribute("user_type",usernow.getType());
                    if(usernow.getType()==2)
                    {
                        out.println("<script>alert('登录成功');location.href='index_admin.jsp';</script>");
                    }
                    else out.println("<script>alert('未知管理员类型');location.href = 'login.html';</script>");
                }
                break;
            case "addpre":
                //存request
                //转发

                HttpSession session1 = req.getSession();
                req.getRequestDispatcher("admin_add.jsp").forward(req,resp);
                break;
            case "add":
                String namea =  req.getParameter("name");
                String pwda =  req.getParameter("pwd");
                String tela =  req.getParameter("tel");

                if(!adminBiz.checktel(tela))
                {
                    out.println("<script>alert('电话号码不合法'); location.href='admin.let?type=query';</script>");
                    return;
                }
                long idtmp=userBiz.getidBysp();
                userBiz.modifysp(1,idtmp+1);
                int count = userBiz.add(idtmp,pwda,0);
                if(count<=0){
                    userBiz.modifysp(1,idtmp-1);
                    out.println("<script>alert('管理员注册失败'); location.href='admin.let?type=query';</script>");
                }
                count = adminBiz.add(idtmp,namea,Long.parseLong(tela));
                if(count>0){
                    out.println("<script>alert('管理员注册成功'); location.href='admin.let?type=query';</script>");
                }else{
                    userBiz.modifysp(1,idtmp-1);
                    out.println("<script>alert('管理员注册失败'); location.href='admin.let?type=query';</script>");
                }

                break;
            case "modifypre":
                if(session.getAttribute("user_now")==null){
                    out.println("<script>alert('请登录');parent.window.location.href='login.html';</script>");
                    return;
                }
                //类型&会员的信息
                long id = Long.parseLong(req.getParameter("id"));
                Admin admin = adminBiz.getById(id);

                req.setAttribute("admin",admin);

                HttpSession session2 = req.getSession();
                Long type1=(Long) session2.getAttribute("user_type");
                if(type1==2){
                    req.getRequestDispatcher("admin_modify.jsp").forward(req,resp);
                }

                break;
            case "modify":
                if(session.getAttribute("user_now")==null){
                    out.println("<script>alert('请登录');parent.window.location.href='login.html';</script>");
                    return;
                }
                String namem =  req.getParameter("name");
                String telm =  req.getParameter("tel");
                if(!adminBiz.checktel(telm))
                {
                    out.println("<script>alert('电话号码不合法'); location.href='admin.let?type=query';</script>");
                    return;
                }
//                long idm=Long.parseLong(req.getParameter("id"));
                User userm=(User)session.getAttribute("user_now");
                long telmm=Long.parseLong(telm);

                int countm = adminBiz.modify(userm.getId(),namem,telmm);
                if(countm>0){
                    out.println("<script>alert('管理员修改成功'); location.href='admin.let?type=details';</script>");
                }else{
                    out.println("<script>alert('管理员注册失败'); location.href='admin.let?type=details';</script>");
                }

                break;
            case "remove":
                if(session.getAttribute("user_now")==null){
                    out.println("<script>alert('请登录');parent.window.location.href='login.html';</script>");
                    return;
                }
                long adminId = Long.parseLong(req.getParameter("id"));
                try {
                    int count2 = adminBiz.remove(adminId);
                    if(count2>0){
                        out.println("<script>alert('管理员删除成功'); location.href='admin.let?type=query';</script>");
                    }else{
                        out.println("<script>alert('管理员删除失败'); location.href='admin.let?type=query';</script>");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    out.println("<script>alert('"+e.getMessage()+"'); location.href='admin.let?type=query';</script>");
                }
                break;
            case "query":
                if(session.getAttribute("user_now")==null){
                    out.println("<script>alert('请登录');parent.window.location.href='login.html';</script>");
                    return;
                }
                List<Admin> adminList = adminBiz.getAll();
                req.setAttribute("adminList",adminList);
                HttpSession session3 = req.getSession();
                Long type3=(Long) session3.getAttribute("user_type");
                if(type3==2){
                    req.getRequestDispatcher("admin_list.jsp").forward(req,resp);
                }
                break;
            case "details":
                User userd=(User)session.getAttribute("user_now");
                if(userd==null){
                    out.println("<script>alert('请登录');parent.window.location.href='login_new.html';</script>");
                    return;
                }
                Admin admind = adminBiz.getById(userd.getId());
                req.setAttribute("admind",admind);
                HttpSession session4 = req.getSession();
                Long type4=(Long) session4.getAttribute("user_type");
                //out.println(eliteList);
                if(type4==2){
                    req.getRequestDispatcher("admin_details.jsp").forward(req,resp);
//                    req.getRequestDispatcher("elite_modify.jsp").forward(req,resp);
                }
                break;
            case "exit":
                if(session.getAttribute("user_now")==null){
                    out.println("<script>alert('请登录');parent.window.location.href='login.html';</script>");
                    return;
                }
                //1.清除session
                session.invalidate();
                //2.跳转到login.html(框架中需要回去)  top.jsp->parent->index.jsp
                out.println("<script>alert('Success');parent.window.location.href='login.html';</script>");
                break;
            case "modifyPwd":
                if(session.getAttribute("user_now")==null){
                    out.println("<script>alert('请登录');parent.window.location.href='login.html';</script>");
                    return;
                }
                //修改密码
                //1.获取管理员输入的新的密码
                String newPwd = req.getParameter("newpwd");
                //2.获取管理员的编号-session
                long idmp = ((User)session.getAttribute("user_now")).getId();

                //3.调用biz层方法
                int countz = userBiz.modifyPwd(idmp,newPwd);
                //4.响应-参考exit
                if(countz>0){
                    out.println("<script>alert('密码修改成功');parent.window.location.href='login.html';</script>");
                }else{
                    out.println("<script>alert('密码修改失败');parent.window.location.href='login.html';</script>");
                }
                break;
            default:
                resp.sendError(404,"请求的地址不存在");
        }

    }
}
