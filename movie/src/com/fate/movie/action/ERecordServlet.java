package com.fate.movie.action;

import com.alibaba.fastjson.JSON;
import com.fate.movie.bean.*;
import com.fate.movie.biz.*;

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

@WebServlet("/erecord.let")
public class ERecordServlet extends HttpServlet {
    ERecordBiz eRecordBiz = new ERecordBiz();
    EliteBiz eliteBiz=new EliteBiz();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    /**
     * /record.let?type=add 简历投递
     * /record.let?type=queryelite 为了查找个人的简历投递情况
     * /record.let?type=queryelite 为了查找某个岗位的建立获得
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
        User user = (User)session.getAttribute("user");

        if(user==null){
            out.println("<script>alert('请登录');parent.window.location.href='login.html';</script>");
            return;
        }

        switch(type){
            case "add":
                //1.购买的会员编号
                long userId = user.getId();
                //2.购买的电影编号
                String ids = req.getParameter("ids");
                String []strs= ids.split("_");
                List<Long> movieIds = new ArrayList<Long>();
                for(String s:strs){
                    Long id = Long.parseLong(s);
                    movieIds.add(id);
                }
                String comment=null;
                //4.调用biz
                int count=  eRecordBiz.add(userId,movieIds,comment);
                if(count>0){

                    out.println("<script>alert('简历投递成功');location.href='main_new.jsp';</script>");
                }else{
                    out.println("<script>alert('简历投递失败');location.href='main_new.jsp';</script>");
                }
                break;
            case "queryelite":

                Elite eliteq=eliteBiz.getById(user.getId());
                List<ERecord> eRecords = eRecordBiz.getRecordsByEliteId(user.getId());
                req.setAttribute("eRecords",eRecords);
                //4.转发
                req.getRequestDispatcher("myjobs_list.jsp").forward(req,resp);

                break;
            case "queryjob":
                //1.获取岗位信息
                long jobid=Long.parseLong(req.getParameter("jobid"));
                boolean desc=Boolean.parseBoolean(req.getParameter("desc"));
                //2.获取会员对象和所有的未还的记录
                List<ERecord> jRecords = eRecordBiz.getRecordsByJobId(jobid,desc);
                //3.存request
                req.setAttribute("jRecords",jRecords);
                //4.转发
                req.getRequestDispatcher("resume_list.jsp").forward(req,resp);
                break;
            case "doajax":

                //没有输入: keyword: 空串""  null:没有找到 keyword
                String keyword = req.getParameter("keyword");
                keyword = keyword.isEmpty()?null:keyword;
                if(user.getType()==0){
                    //获取数据
                    List<Map<String,Object>> rows  = eRecordBiz.query_0(user.getId(),keyword);
                    //转成json
                    out.print(JSON.toJSONString(rows));
                }
                else if(user.getType()==1){
                    CompBiz compBiz=new CompBiz();
                    Comp comp=compBiz.getById(user.getId());
                    //获取数据
                    List<Map<String,Object>> rows  = eRecordBiz.query_1(comp.getlicense(),keyword);
                    //转成json
                    out.print(JSON.toJSONString(rows));
                }
                else {

                    //获取数据
                    List<Map<String,Object>> rows  = eRecordBiz.query(keyword);
                    //转成json
                    out.print(JSON.toJSONString(rows));
                }

                break;
            default:
                resp.sendError(404,"请求的地址不存在");
        }

    }
}
