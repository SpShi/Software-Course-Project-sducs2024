package com.fate.movie.action;

import com.fate.movie.bean.Elite;
import com.fate.movie.bean.User;
import com.fate.movie.biz.EliteBiz;
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

@WebServlet("/jobs.let")
public class JobsServlet extends HttpServlet {
    UserBiz userBiz=new UserBiz();
    EliteBiz jobsBiz=new EliteBiz();
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
                String pwda =  req.getParameter("pwd");
                String idnuma=req.getParameter("idnum");
                long gendera=Long.parseLong(req.getParameter("gender"));
                long agea=Long.parseLong(req.getParameter("age"));
                long degreesa=Long.parseLong(req.getParameter("degrees"));
                String tela =  req.getParameter("tel");
                String resumea=req.getParameter("resume");
                String majora=req.getParameter("major");
                String emaila=req.getParameter("email");
                String ctfcta=req.getParameter("ctfct");
                String intta=req.getParameter("intt");
                String slfea=req.getParameter("slfe");
                String expea=req.getParameter("expe");

                if(!jobsBiz.checktel(tela))
                {
                    out.println("<script>alert('电话号码不合法'); location.href='jobs.let?type=query';</script>");
                    return;
                }
                if(!jobsBiz.checkiN(idnuma))
                {
                    out.println("<script>alert('身份证号码不合法'); location.href='jobs.let?type=query';</script>");
                    return;
                }
                long idtmp=userBiz.getidBysp();
                userBiz.modifysp(1,idtmp+1);
                int count = userBiz.add(idtmp,pwda,0);
                if(count<=0){
                    userBiz.modifysp(1,idtmp-1);
                    out.println("<script>alert('用户注册失败'); location.href='jobs.let?type=query';</script>");
                }
                count = jobsBiz.add(idtmp,namea,idnuma,resumea,gendera, agea,degreesa,Long.parseLong(tela),
                        majora, emaila, ctfcta, intta, slfea,expea);
                if(count>0){
                    out.println("<script>alert('用户注册成功'); location.href='jobs.let?type=query';</script>");
                }else{
                    userBiz.modifysp(1,idtmp-1);
                    out.println("<script>alert('用户注册失败'); location.href='jobs.let?type=query';</script>");
                }

                break;
            case "modifypre":
                if(session.getAttribute("user")==null){
                    out.println("<script>alert('请登录');parent.window.location.href='login.html';</script>");
                    return;
                }
                //类型&会员的信息
                long id = Long.parseLong(req.getParameter("id"));
                Elite jobs = jobsBiz.getById(id);

                req.setAttribute("jobs",jobs);

                HttpSession session2 = req.getSession();
                Long type1=(Long) session2.getAttribute("type");
                if(type1==0){
                    req.getRequestDispatcher("jobs_modify.jsp").forward(req,resp);
                }

                break;
            case "modify":
                if(session.getAttribute("user")==null){
                    out.println("<script>alert('请登录');parent.window.location.href='login.html';</script>");
                    return;
                }
                String namem =  req.getParameter("name");
                String idnumm=req.getParameter("idnum");
                long genderm=Long.parseLong(req.getParameter("gender"));
                long agem=Long.parseLong(req.getParameter("age"));
                long degreesm=Long.parseLong(req.getParameter("degrees"));
                String telm =  req.getParameter("tel");
                String resumem=req.getParameter("resume");
                String majorm=req.getParameter("major");
                String emailm=req.getParameter("email");
                String ctfctm=req.getParameter("ctfct");
                String inttm=req.getParameter("intt");
                String slfem=req.getParameter("slfe");
                String expem=req.getParameter("expe");

                if(!jobsBiz.checktel(telm))
                {
                    out.println("<script>alert('电话号码不合法'); location.href='jobs.let?type=query';</script>");
                    return;
                }
                if(!jobsBiz.checkiN(idnumm))
                {
                    out.println("<script>alert('身份证号码不合法'); location.href='jobs.let?type=query';</script>");
                    return;
                }
                long idm=Long.parseLong(req.getParameter("id"));
                int countm = jobsBiz.modify(idm,namem,idnumm,resumem,genderm, agem,degreesm,Long.parseLong(telm),
                        majorm, emailm, ctfctm, inttm, slfem,expem);
                if(countm>0){
                    out.println("<script>alert('用户修改成功'); location.href='jobs.let?type=query';</script>");
                }else{
                    out.println("<script>alert('用户注册失败'); location.href='jobs.let?type=query';</script>");
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
                        out.println("<script>alert('个人用户删除成功'); location.href='jobs.let?type=query';</script>");
                    }else{
                        out.println("<script>alert('个人用户删除失败'); location.href='jobs.let?type=query';</script>");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    out.println("<script>alert('"+e.getMessage()+"'); location.href='jobs.let?type=query';</script>");
                }
                break;
            case "query":
                if(session.getAttribute("user")==null){
                    out.println("<script>alert('请登录');parent.window.location.href='login.html';</script>");
                    return;
                }
                List<Elite> jobsList = jobsBiz.getAll();
                req.setAttribute("jobsList",jobsList);
                HttpSession session3 = req.getSession();
                Long type3=(Long) session3.getAttribute("type");
                if(type3==0){
                    req.getRequestDispatcher("jobs_list.jsp").forward(req,resp);
                }
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
            case "modifyPwd":
                if(session.getAttribute("user")==null){
                    out.println("<script>alert('请登录');parent.window.location.href='login.html';</script>");
                    return;
                }
                //修改密码
                //1.获取用户输入的新的密码
                String newPwd = req.getParameter("newpwd");
                //2.获取用户的编号-session
                long idmp = ((User)session.getAttribute("user")).getId();

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
