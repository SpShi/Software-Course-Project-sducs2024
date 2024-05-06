package com.fate.movie.action;


import com.fate.movie.bean.*;
import com.fate.movie.biz.AdminBiz;
import com.fate.movie.biz.EliteBiz;
import com.fate.movie.biz.CompBiz;
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

@WebServlet("/user.let")
public class UserServlet extends HttpServlet {
    UserBiz userBiz=new UserBiz();
    EliteBiz eliteBiz=new EliteBiz();
    CompBiz compBiz =new CompBiz();
    AdminBiz adminBiz=new AdminBiz();

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
                // 2.从login.html中获取用户名和密码,验证码
                long id1 = Long.parseLong(req.getParameter("id"));
                String pwd1 = req.getParameter("pwd");
                String userCode = req.getParameter("valcode");

                //2.2 提取session中的验证码,进行判断
//                String code =session.getAttribute("code").toString();
                //不区分大小写
                //暂时去掉提高效率(1717)

//                if(!code.equalsIgnoreCase(userCode)){
//                    out.println("<script>alert('验证码输入错误');location.href = 'login.html';</script>");
//                    return;
//                }


                // 3.调用UserBiz的getUser方法，根据用户名和密码获取对应的用户对象
                User usernow=userBiz.getUser(id1,pwd1);

                // 4.判断用户对象是否为null: 
                if(usernow==null){
                    //  4.1 如果是null表示用户名或密码不正确 ，提示错误，回到登录页面. 
                    out.println("<script>alert('用户名或密码不存在');location.href = 'login.html';</script>");
                }else {
                    //  4.2 非空：表示登录成功, 将用户对象保存到session中,提示登录成功后,将页面跳转到index.jsp
                    session.setAttribute("user",usernow);//user-->Object
                    if(usernow.getType()==0)
                    {
                        Elite elitenow=eliteBiz.getById(id1);
                        if(elitenow==null)
                        {
                            out.println("<script>alert('请完善您的个人信息');location.href='elite_add.jsp';</script>");
                        }
                        else{
                            out.println("<script>alert('登录成功');location.href='index_elite.jsp';</script>");
                        }

                    }
                    else if(usernow.getType()==1){
                        Comp enterprisenow= compBiz.getById(id1);
                        if(enterprisenow==null)
                        {
                            out.println("<script>alert('请完善您的企业信息');location.href='comp_add.jsp';</script>");
                        }
                        else
                        {
                            out.println("<script>alert('登录成功');location.href='index_company.jsp';</script>");
                        }

                    }
                    else if(usernow.getType()==2){
                        out.println("<script>alert('登录成功');location.href='index_admin.jsp';</script>");
                    }
                    else out.println("<script>alert('未知用户类型');location.href = 'login.html';</script>");
                }
                break;
            case "addpre":
                //存request
                //转发
                req.getRequestDispatcher("user_add.jsp").forward(req,resp);
                break;
            case "add":
                String pwda =  req.getParameter("pwd");
                long typea=Long.parseLong(req.getParameter("user_type"));
                long idtmp=userBiz.getidBysp();
                userBiz.modifysp(1,idtmp+1);
                int count = userBiz.add(idtmp,pwda,typea);
                if(count>0){
                    out.println("<script>alert('用户注册成功,请牢记您的ID:"+idtmp+"后重新登录'); location.href='user.let?type=query';</script>");
                }else{
                    userBiz.modifysp(1,idtmp-1);
                    out.println("<script>alert('用户注册失败'); location.href='user.let?type=query';</script>");
                }

                break;
            case "query":
                if(session.getAttribute("user")==null){
                    out.println("<script>alert('请登录');parent.window.location.href='login.html';</script>");
                    return;
                }
                List<User> userList = userBiz.getAll();
                req.setAttribute("userList",userList);
                HttpSession session3 = req.getSession();
                req.getRequestDispatcher("user_list.jsp").forward(req,resp);
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
                long idm = ((User)session.getAttribute("user")).getId();

                //3.调用biz层方法
                int countz = userBiz.modifyPwd(idm,newPwd);
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
