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

@WebServlet("/elite.let")
public class EliteServlet extends HttpServlet{
    UserBiz userBiz=new UserBiz();
    EliteBiz eliteBiz=new EliteBiz();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    /**
     * /elite.let?type=add 添加个人
     * /elite.let?type=modifypre&id=xx 修改前准备
     * /elite.let?type=modify        修改
     * /elite.let?type=remove&id=xx    删除
     * /elite.let?type=query&pageIndex=1 :分页查询(request:转发)
     * /elite.let?type=details&id=xx   展示个人详细信息
     * /elite.let?type=doajax&name=xx  :使用ajax查询个人名对应的个人信息
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
                String valCode = req.getParameter("valcode");

                //2.2 提取session中的验证码,进行判断
//                String code =session.getAttribute("code").toString();
                //不区分大小写
                //暂时去掉提高效率(1717)

//                if(!code.equalsIgnoreCase(valCode)){
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
                    session.setAttribute("user_type",usernow.getType());
                    if(usernow.getType()==0)
                    {
                        out.println("<script>alert('登录成功');location.href='index_elite.jsp';</script>");
                    }
                    else out.println("<script>alert('未知用户类型');location.href = 'login.html';</script>");
                }
                break;
            case "addpre":
                //存request
                //转发

                HttpSession session1 = req.getSession();
                req.getRequestDispatcher("elite_add.jsp").forward(req,resp);
                break;
            case "add":
                User usera=(User)session.getAttribute("user");
                String namea =  req.getParameter("name");
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

                if(!eliteBiz.checktel(tela))
                {
                    out.println("<script>alert('电话号码不合法'); location.href='elite.let?type=query';</script>");
                    return;
                }
                if(!eliteBiz.checkiN(idnuma))
                {
                    out.println("<script>alert('身份证号码不合法'); location.href='elite.let?type=query';</script>");
                    return;
                }
                long telnum=Long.parseLong(tela);
                int count = eliteBiz.add(usera.getId(),namea,idnuma,resumea,gendera, agea,degreesa,telnum,
                        majora, emaila, ctfcta, intta, slfea,expea);
                if(count>0){
                    out.println("<script>alert('用户信息添加成功'); location.href='elite.let?type=query';</script>");
                }else{
                    out.println("<script>alert('用户信息添加失败'); location.href='elite.let?type=query';</script>");
                }

                break;
            case "modifypre":
                if(session.getAttribute("user")==null){
                    out.println("<script>alert('请登录');parent.window.location.href='login.html';</script>");
                    return;
                }
                //类型&会员的信息
                long id = Long.parseLong(req.getParameter("id"));
                Elite elite = eliteBiz.getById(id);

                req.setAttribute("elite",elite);

                HttpSession session2 = req.getSession();
                Long type1=(Long) session2.getAttribute("user_type");
                if(type1==0){
                    req.getRequestDispatcher("elite_modify.jsp").forward(req,resp);
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

                if(!eliteBiz.checktel(telm))
                {
                    out.println("<script>alert('电话号码不合法'); location.href='elite.let?type=query';</script>");
                    return;
                }
                if(!eliteBiz.checkiN(idnumm))
                {
                    out.println("<script>alert('身份证号码不合法'); location.href='elite.let?type=query';</script>");
                    return;
                }
                User userm=(User)session.getAttribute("id");
                int countm = eliteBiz.modify(userm.getId(),namem,idnumm,resumem,genderm, agem,degreesm,Long.parseLong(telm),
                        majorm, emailm, ctfctm, inttm, slfem,expem);
                if(countm>0){
                    out.println("<script>alert('用户信息修改成功'); location.href='elite.let?type=query';</script>");
                }else{
                    out.println("<script>alert('用户信息修改失败'); location.href='elite.let?type=query';</script>");
                }

                break;
            case "remove":
                if(session.getAttribute("user")==null){
                    out.println("<script>alert('请登录');parent.window.location.href='login.html';</script>");
                    return;
                }
                long eliteId = Long.parseLong(req.getParameter("id"));
                try {
                    int count2 = eliteBiz.remove(eliteId);
                    if(count2>0){
                        out.println("<script>alert('个人用户删除成功'); location.href='elite.let?type=query';</script>");
                    }else{
                        out.println("<script>alert('个人用户删除失败'); location.href='elite.let?type=query';</script>");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    out.println("<script>alert('"+e.getMessage()+"'); location.href='elite.let?type=query';</script>");
                }
                break;
            case "query":
                if(session.getAttribute("user")==null){
                    out.println("<script>alert('请登录');parent.window.location.href='login.html';</script>");
                    return;
                }
                List<Elite> eliteList = eliteBiz.getAll();
                req.setAttribute("eliteList",eliteList);
                HttpSession session3 = req.getSession();
                Long type3=(Long) session3.getAttribute("user_type");
                if(type3==0){
                    req.getRequestDispatcher("elite_list.jsp").forward(req,resp);
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
