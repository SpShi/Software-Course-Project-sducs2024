package com.fate.movie.action;

import com.alibaba.fastjson.JSON;
import com.fate.movie.bean.Member;
import com.fate.movie.bean.MemberType;
import com.fate.movie.bean.Record;
import com.fate.movie.biz.MemberBiz;
import com.fate.movie.biz.MemberTypeBiz;
import com.fate.movie.biz.RecordBiz;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/member.let")
public class MemberServlet extends HttpServlet {
    MemberTypeBiz  memberTypeBiz = new MemberTypeBiz();
    MemberBiz  memberBiz = new MemberBiz();
    RecordBiz recordBiz = new RecordBiz();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    /**
     * /member.let?type=addpre 添加准备(MemberTypes)
     * /member.let?type=add
     * /member.let?type=modifypre&id=xx 修改准备(MemberTypes ,Member)
     * /member.let?type=modify 修改
     * /member.let?type=remove&id=xx 删除
     * /member.let?type=query
     * /member.let?type=modifyrecharge 充值
     * /member.let?type=doajax&idn=xx 通过ajax请求会员信息
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
        PrintWriter  out  = resp.getWriter();
        HttpSession session = req.getSession();


        //请求类型
        String type = req.getParameter("type");

        //判断类型
        switch (type){
            case"login":
                // 2.从login.html中获取用户名和密码,验证码
                String name1 = req.getParameter("name");
                String pwd1 = req.getParameter("pwd");
                String memberCode = req.getParameter("valcode");

                //2.2 提取session中的验证码,进行判断
                String code =session.getAttribute("code").toString();
                //不区分大小写

                //暂时去掉提高效率(1717)
//                if(!code.equalsIgnoreCase(memberCode)){
//                    out.println("<script>alert('验证码输入错误');location.href = 'login.html';</script>");
//                    return;
//                }


                // 3.调用UserBiz的getUser方法，根据用户名和密码获取对应的用户对象
                Member member2 = memberBiz.getMember(name1,pwd1);

                // 4.判断用户对象是否为null: 
                if(member2==null){
                    //  4.1 如果是null表示用户名或密码不正确 ，提示错误，回到登录页面. 
                    out.println("<script>alert('用户名或密码不存在');location.href = 'login.html';</script>");
                }else {
                    //  4.2 非空：表示登录成功, 将用户对象保存到session中,提示登录成功后,将页面跳转到index.jsp
                    session.setAttribute("user",member2);//user-->Object
                    session.setAttribute("state",member2.getState());
                    if(member2.getState()==0)
                    {
                        out.println("<script>alert('登录成功');location.href='index_beta.jsp';</script>");
                    }
                    else{
                        out.println("<script>alert('登录成功');location.href='index.jsp';</script>");
                    }

                }
                break;
            case "addpre":
                if(session.getAttribute("user")==null){
                    out.println("<script>alert('请登录');parent.window.location.href='login.html';</script>");
                    return;
                }
                //获取所有的会员类型
                List<MemberType> memberTypes = memberTypeBiz.getAll();
                //存request
                req.setAttribute("memberTypes",memberTypes);
                //转发
                HttpSession session1 = req.getSession();
                Long state=(Long) session1.getAttribute("state");
                if(state==0){
                    req.getRequestDispatcher("mem_add_beta.jsp").forward(req,resp);
                }
                else
                {
                    req.getRequestDispatcher("mem_add.jsp").forward(req,resp);
                }

                break;
            case "add":
                if(session.getAttribute("user")==null){
                    out.println("<script>alert('请登录');parent.window.location.href='login.html';</script>");
                    return;
                }
                String name =  req.getParameter("name");
                String pwd =  req.getParameter("pwd");
                long memberTypeId =  Long.parseLong(req.getParameter("memberType"));
                double  balance = Double.parseDouble(req.getParameter("balance"));
                String tel =  req.getParameter("tel");
                if(!memberBiz.checktel(tel))
                {
                    out.println("<script>alert('电话号码不合法'); location.href='member.let?type=query';</script>");
                    return;
                }
                String idNumber =  req.getParameter("idNumber");
                if(!memberBiz.checkiN(idNumber))
                {
                    out.println("<script>alert('身份证号码不合法'); location.href='member.let?type=query';</script>");
                    return;
                }
                int count = memberBiz.add(name,pwd,memberTypeId,balance,tel,idNumber);
                if(count>0){
                    out.println("<script>alert('用户注册成功'); location.href='member.let?type=query';</script>");
                }else{
                    out.println("<script>alert('用户注册失败'); location.href='member.let?type=query';</script>");
                }

                break;
            case "modifypre":
                if(session.getAttribute("user")==null){
                    out.println("<script>alert('请登录');parent.window.location.href='login.html';</script>");
                    return;
                }
                //类型&会员的信息
                long id = Long.parseLong(req.getParameter("id"));
                Member member = memberBiz.getById(id);

                //获取所有的会员类型
                List<MemberType> memberTypes2 = memberTypeBiz.getAll();

                req.setAttribute("member",member);
                req.setAttribute("memberTypes",memberTypes2);


                HttpSession session2 = req.getSession();
                Long state1=(Long) session2.getAttribute("state");
                if(state1==0){
                    req.getRequestDispatcher("mem_modify_beta.jsp").forward(req,resp);
                }
                else
                {
                    req.getRequestDispatcher("mem_modify.jsp").forward(req,resp);
                }
                break;
            case "modify":
                if(session.getAttribute("user")==null){
                    out.println("<script>alert('请登录');parent.window.location.href='login.html';</script>");
                    return;
                }
                long memberId = Long.parseLong( req.getParameter("id"));
                String name2 =  req.getParameter("name");
                String pwd2 =  req.getParameter("pwd");
                long memberTypeId2 =  Long.parseLong(req.getParameter("memberType"));
                double  balance2 = Double.parseDouble(req.getParameter("balance"));
                String tel2 =  req.getParameter("tel");
                String idNumber2 =  req.getParameter("idNumber");
                if(!memberBiz.checktel(tel2))
                {
                    out.println("<script>alert('电话号码不合法'); location.href='member.let?type=query';</script>");
                    return;
                }
                if(!memberBiz.checkiN(idNumber2))
                {
                    out.println("<script>alert('身份证号码不合法'); location.href='member.let?type=query';</script>");
                    return;
                }
                int count3 = memberBiz.modify(memberId,name2,pwd2,memberTypeId2,balance2,tel2,idNumber2);
                 if(count3>0){
                     out.println("<script>alert('会员修改成功'); location.href='member.let?type=query';</script>");
                 }else{
                     out.println("<script>alert('会员修改失败'); location.href='member.let?type=query';</script>");
                 }

                break;
            case "remove":
                if(session.getAttribute("user")==null){
                    out.println("<script>alert('请登录');parent.window.location.href='login.html';</script>");
                    return;
                }
                 long memId = Long.parseLong(req.getParameter("id"));
                try {
                    int count2 = memberBiz.remove(memId);
                    if(count2>0){
                        out.println("<script>alert('会员删除成功'); location.href='member.let?type=query';</script>");
                    }else{
                        out.println("<script>alert('会员删除失败'); location.href='member.let?type=query';</script>");
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    out.println("<script>alert('"+e.getMessage()+"'); location.href='member.let?type=query';</script>");
                }
                break;
            case "query":
                if(session.getAttribute("user")==null){
                    out.println("<script>alert('请登录');parent.window.location.href='login.html';</script>");
                    return;
                }
                List<Member> memberList = memberBiz.getAll();
                req.setAttribute("memberList",memberList);
                HttpSession session3 = req.getSession();
                Long state3=(Long) session3.getAttribute("state");
                if(state3==0){
                    req.getRequestDispatcher("mem_list_beta.jsp").forward(req,resp);
                }
                else
                {
                    req.getRequestDispatcher("mem_list.jsp").forward(req,resp);
                }
                break;
            case "modifyrecharge":
                if(session.getAttribute("user")==null){
                    out.println("<script>alert('请登录');parent.window.location.href='login.html';</script>");
                    return;
                }
                //获取身份证号和金额
                String idNumber3 = req.getParameter("idNumber");
                double amount = Double.parseDouble(req.getParameter("amount"));
                int count4 = memberBiz.modifyBalance(idNumber3,amount);
                if(count4>0){
                    out.println("<script>alert('会员充值成功'); location.href='member.let?type=query';</script>");
                }else{
                    out.println("<script>alert('会员充值失败'); location.href='member.let?type=query';</script>");
                }
                break;
            case "doajax":
                if(session.getAttribute("user")==null){
                    out.println("<script>alert('请登录');parent.window.location.href='login.html';</script>");
                    return;
                }
                //1.获取身份证号
                String idNum = req.getParameter("idn");
                //2.获取 member对象
                Member member1 = memberBiz.getByIdNumber(idNum);
                //2.2 修改member借电影数量
                List<Record> records = recordBiz.getRecordsByMemberId(member1.getId());
                if(records.size()>0){
                    long size = member1.getType().getAmount()-records.size();
                    member1.getType().setAmount(size);
                }
                //3. member1 --> json字符串
                String memberJson = JSON.toJSONString(member1);
                //4.响应客户端 注意：out.打印不能换行*****
                out.print(memberJson);
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
                long id1 = ((Member)session.getAttribute("user")).getId();

                //3.调用biz层方法
                int countz = memberBiz.modifyPwd(id1,newPwd);
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
