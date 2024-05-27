package com.fate.movie.action;

import com.alibaba.fastjson.JSON;
import com.fate.movie.bean.Member;
import com.fate.movie.bean.Record;
import com.fate.movie.biz.MemberBiz;
import com.fate.movie.biz.RecordBiz;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@WebServlet("/record.let")
public class RecordServlet extends HttpServlet {
    RecordBiz recordBiz = new RecordBiz();
    MemberBiz memberBiz = new MemberBiz();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    /**
     * /record.let?type=add&mid=1&ids=5_4_9_10 电影购买
     * /record.let?type=queryback&idn=xx:根据会员的身份证号查询会员信息及购买信息
     * /record.let?type=back&mid=1&ids_5_4 电影归还
     * /record.let?type=keep&id=x 电影续借
     * /record.let?type=doajax&typeId=x&keyword=xx ajax查询
     * @param req
     * @param resp
     * @throws ServletException
     * @throws IOException
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html;charset=utf-8");
        String type = req.getParameter("type");
        HttpSession session = req.getSession();
        PrintWriter out = resp.getWriter();
        Member member = (Member)session.getAttribute("user");

        if(member==null){
            out.println("<script>alert('请登录');parent.window.location.href='login.html';</script>");
            return;
        }

        switch(type){
            case "add":
                //1.购买的会员编号
                long memberId = Long.parseLong(req.getParameter("mid"));
                long seat=Long.parseLong(req.getParameter("seat"));
                //2.购买的电影编号
                String ids = req.getParameter("ids");
                String []strs= ids.split("_");
                List<Long> movieIds = new ArrayList<Long>();
                for(String s:strs){
                    Long id = Long.parseLong(s);
                    movieIds.add(id);
                }
                //3.当前负责处理这个购买的管理员编号
                long userId = member.getId();

                //4.调用biz
               int count=  recordBiz.add(memberId,movieIds,userId,seat);
                HttpSession session1 = req.getSession();
                Long state=(Long) session1.getAttribute("state");
                if(state==0){
                    if(count>0){

                        out.println("<script>alert('电影购买成功');location.href='main_beta.jsp';</script>");
                    }else{
                        out.println("<script>alert('电影购买失败');location.href='main_beta.jsp';</script>");
                    }
                }
                else
                {
                    if(count>0){

                        out.println("<script>alert('电影购买成功');location.href='main.jsp';</script>");
                    }else{
                        out.println("<script>alert('电影购买失败');location.href='main.jsp';</script>");
                    }
                }

                break;
            case "queryback":
                 //1.获取会员的身份证号
                 String idn = req.getParameter("idn");
                 //2.获取会员对象和所有的未还的记录
                 Member member1 = memberBiz.getByIdNumber(idn);
                 List<Record> records = recordBiz.getRecordsByMemberId(member1.getId());
                //3.存request
                req.setAttribute("member",member1);
                req.setAttribute("records",records);
                //4.转发

                HttpSession session4 = req.getSession();
                Long state4=(Long) session4.getAttribute("state");
                if(state4==0){
                    req.getRequestDispatcher("return_list_beta.jsp").forward(req,resp);
                }
                else
                {
                    req.getRequestDispatcher("return_list.jsp").forward(req,resp);
                }
                break;
            case "back":
                 //1.会员编号
                long memberId2 = Long.parseLong(req.getParameter("mid"));
                //2.获取记录编号
                String idStr = req.getParameter("ids");
                String []idStrs = idStr.split("_");
                List<Long>  recordIds = new ArrayList<Long>();
                for(String s:idStrs){
                    recordIds.add(Long.parseLong(s));
                }
                //3.获取操作的用户编号
                long userId2 =member.getId();
                //4.调用biz
                int count2 = recordBiz.modify(memberId2, recordIds,userId2);
                //5.归还
                HttpSession session7 = req.getSession();
                Long state7=(Long) session7.getAttribute("state");
                if(state7==0){
                    if(count2>0){
                        out.println("<script>alert('退票成功');location.href='main_beta.jsp';</script>");
                    }else{
                        out.println("<script>alert('退票失败');location.href='main_beta.jsp';</script>");
                    }
                }
                else
                {
                    if(count2>0){
                        out.println("<script>alert('退票成功');location.href='main.jsp';</script>");
                    }else{
                        out.println("<script>alert('退票失败');location.href='main.jsp';</script>");
                    }
                }

                break;
            case "doajax":
                int typeId = Integer.parseInt(req.getParameter("typeId"));
                //没有输入: keyword: 空串""  null:没有找到 keyword
                String keyword = req.getParameter("keyword");
                keyword = keyword.isEmpty()?null:keyword;
            if(member.getState()==0){
                //获取数据
                List<Map<String,Object>> rows  = recordBiz.query_beta(member.getName(),typeId,keyword);
                //转成json
                out.print(JSON.toJSONString(rows));
            }
            else {

                //获取数据
                List<Map<String,Object>> rows  = recordBiz.query(typeId,keyword);
                //转成json
                out.print(JSON.toJSONString(rows));
            }

                break;
            default:
                resp.sendError(404,"请求的地址不存在");
        }

    }
}
