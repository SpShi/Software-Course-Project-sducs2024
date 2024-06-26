package com.fate.elite.action;


import com.fate.elite.bean.Comp;
import com.fate.elite.bean.User;
import com.fate.elite.biz.CompBiz;
import com.fate.elite.biz.UserBiz;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/comp.let")
public class CompServlet extends HttpServlet{
    UserBiz userBiz=new UserBiz();
    CompBiz compBiz =new CompBiz();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    /**
     * /comp.let?type=add 添加企业
     * /comp.let?type=modifypre&id=xx 修改前准备
     * /comp.let?type=modify        修改
     * /comp.let?type=remove&id=xx    删除
     * /comp.let?type=query&pageIndex=1 :分页查询(request:转发)
     * /comp.let?type=details&id=xx   展示企业详细信息
     * /comp.let?type=doajax&name=xx  :使用ajax查询企业名对应的企业信息
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
                // 2.从login.html中获取企业名和密码,验证码
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


                // 3.调用UserBiz的getUser方法，根据企业名和密码获取对应的企业对象
                User usernow=userBiz.getUser(id1,pwd1);

                // 4.判断企业对象是否为null: 
                if(usernow==null){
                    //  4.1 如果是null表示企业名或密码不正确 ，提示错误，回到登录页面. 
                    out.println("<script>alert('企业名或密码不存在');location.href = 'login.html';</script>");
                }else {
                    //  4.2 非空：表示登录成功, 将企业对象保存到session中,提示登录成功后,将页面跳转到index.jsp
                    session.setAttribute("user_now",usernow);//user-->Object
                    session.setAttribute("user_type",usernow.getType());
                    if(usernow.getType()==1)
                    {
                        out.println("<script>alert('登录成功');location.href='index_comp.jsp';</script>");
                    }
                    else out.println("<script>alert('未知企业类型');location.href = 'login.html';</script>");
                }
                break;
            case "addpre":
                //存request
                //转发

                HttpSession session1 = req.getSession();
                req.getRequestDispatcher("comp_add.jsp").forward(req,resp);
                break;
            case "add":

                String namea =  req.getParameter("name");
                String idnuma=req.getParameter("idnum");
                String tela =  req.getParameter("tel");
                Long licensea=Long.parseLong(req.getParameter("license"));
                String enamea=req.getParameter("ename");
                String addra=req.getParameter("addr");

                if(!compBiz.checktel(tela))
                {
                    out.println("<script>alert('电话号码不合法'); location.href='comp_add.jsp';</script>");
                    return;
                }
                if(!compBiz.checkiN(idnuma))
                {
                    out.println("<script>alert('身份证号码不合法'); location.href='comp_add.jsp';</script>");
                    return;
                }
                User usera=(User)session.getAttribute("user_now");
                long idtmp=usera.getId();
                 int count = compBiz.add(idtmp,namea,idnuma,licensea,Long.parseLong(tela),enamea,addra);
                if(count>0){
                    out.println("<script>alert('企业信息初始化成功'); location.href='index_comp.jsp';</script>");
                }else{
                    out.println("<script>alert('企业信息初始化失败'); location.href='comp_add.jsp';</script>");
                }

                break;
            case "modifypre":

                //类型&会员的信息
                User user=(User) session.getAttribute("user_now");
                if(user==null){
                    out.println("<script>alert('请登录');parent.window.location.href='login.html';</script>");
                    return;
                }
                //类型&会员的信息
                long id = Long.parseLong(req.getParameter("id"));
                Comp comp = compBiz.getById(id);

                req.setAttribute("compx",comp);
                HttpSession session2 = req.getSession();
                Long type1=(Long) session2.getAttribute("user_type");
                if(type1==1||type1==2){
                    req.getRequestDispatcher("comp_modify.jsp").forward(req,resp);
                }

                break;
            case "modify":
                try {
                    modify(req,resp,out);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                break;
            case "remove":
                if(session.getAttribute("user_now")==null){
                    out.println("<script>alert('请登录');parent.window.location.href='login.html';</script>");
                    return;
                }
                long memId = Long.parseLong(req.getParameter("id"));
                try {
                    int count2 = compBiz.remove(memId);
                    if(count2>0){
                        out.println("<script>alert('企业删除成功'); location.href='comp.let?type=query';</script>");
                    }else{
                        out.println("<script>alert('企业删除失败'); location.href='comp.let?type=query';</script>");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    out.println("<script>alert('"+e.getMessage()+"'); location.href='comp.let?type=query';</script>");
                }
                break;
            case "query":
                if(session.getAttribute("user_now")==null){
                    out.println("<script>alert('请登录');parent.window.location.href='login.html';</script>");
                    return;
                }
                List<Comp> compList = compBiz.getAll();
                req.setAttribute("compList",compList);
                HttpSession session3 = req.getSession();
                Long type3=(Long) session3.getAttribute("user_type");
                if(type3==2){
                    req.getRequestDispatcher("comp_list.jsp").forward(req,resp);
                }
                else if(type3==0||type3==0)
                {
                    req.getRequestDispatcher("comp_list_elite.jsp").forward(req,resp);
                }
                break;
            case "details":
                long compId;
                User userd=(User)session.getAttribute("user_now");
                if(userd.getType()==1)
                {
                    compId=userd.getId();
                }
                else compId=Long.parseLong(req.getParameter("id"));
                Comp compx= compBiz.getById(compId);
                if(userd==null){
                    out.println("<script>alert('请登录');parent.window.location.href='login.html';</script>");
                    return;
                }
                req.setAttribute("compx",compx);
                String ff=req.getParameter("flag");
                req.getRequestDispatcher("comp_details.jsp").forward(req,resp);
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
                //1.获取企业输入的新的密码
                String newPwd = req.getParameter("newpwd");
                //2.获取企业的编号-session
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
    private void modify(HttpServletRequest req, HttpServletResponse resp, PrintWriter out) throws Exception
    {
        String namem =  req.getParameter("name");
        //out.println(namem);
        String idnumm=req.getParameter("idnumber");
        String telm =  req.getParameter("tel");
        Long licensem=Long.parseLong(req.getParameter("license"));
        String enamem=req.getParameter("ename");
        String addrm=req.getParameter("addr");

        if(!compBiz.checktel(telm))
        {
            out.println("<script>alert('电话号码不合法'); location.href='comp.let?type=details';</script>");
            return;
        }
        if(!compBiz.checkiN(idnumm))
        {
            out.println("<script>alert('身份证号码不合法'); location.href='comp.let?type=details';</script>");
            return;
        }
        HttpSession session = req.getSession();
        long idm=Long.parseLong(req.getParameter("id"));
        int countm = compBiz.modify(idm,namem,idnumm,licensem,Long.parseLong(telm),enamem,addrm);
        if(countm>0){
            out.println("<script>alert('信息修改成功'); location.href='comp.let?type=details';</script>");
        }else{
            out.println("<script>alert('信息修改失败');location.href='comp.let?type=details';</script>");
        }


    }
}
