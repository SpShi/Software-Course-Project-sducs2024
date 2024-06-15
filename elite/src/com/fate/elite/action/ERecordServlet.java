package com.fate.elite.action;

import com.alibaba.fastjson.JSON;
import com.fate.elite.bean.Comp;
import com.fate.elite.bean.ERecord;
import com.fate.elite.bean.Jobs;
import com.fate.elite.bean.User;
import com.fate.elite.biz.CompBiz;
import com.fate.elite.biz.ERecordBiz;
import com.fate.elite.biz.EliteBiz;
import com.fate.elite.biz.JobsBiz;

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
    JobsBiz jobsBiz=new JobsBiz();
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
        User user = (User)session.getAttribute("user_now");

        if(user==null){
            out.println("<script>alert('请登录');parent.window.location.href='login.html';</script>");
            return;
        }

        switch(type){
            case "addpre":
                long jobid_a=Long.parseLong(req.getParameter("jobid"));
                Jobs jobs=jobsBiz.getById(jobid_a);
                req.setAttribute("job",jobs);
                req.getRequestDispatcher("resume_add.jsp").forward(req,resp);
                break;
            case "add":
                //1.购买的会员编号
                long userId = user.getId();
                //2.购买的人才编号
                int count=0;
                long jobid=Long.parseLong(req.getParameter("jobid"));
                String comment=req.getParameter("comment");
                if(user.getType()==0){
                    //4.调用biz
                    count =  eRecordBiz.add(user.getId(),jobid,comment);
                }
                else{
                    out.println("<script>alert('简历投递失败');location.href='jobs_search.jsp';</script>");
                }
                if(count>0){

                    out.println("<script>alert('简历投递成功');location.href='./erecord.let?type=queryelite&pageIndex=1';</script>");
                }else if(count==-1){
                    out.println("<script>alert('已投递过简历');location.href='jobs_search.jsp';</script>");
                }else{
                out.println("<script>alert('简历投递失败');location.href='jobs_search.jsp';</script>");
            }
                break;
            case "modifypre":
                long id_m=Long.parseLong(req.getParameter("id"));
                ERecord eRecord=eRecordBiz.getById(id_m);
                req.setAttribute("eRecord",eRecord);
                req.getRequestDispatcher("resume_reply.jsp").forward(req,resp);
                break;
            case "reply":
                //2.购买的人才编号
                Long eid = Long.parseLong(req.getParameter("id"));
                String commentr=req.getParameter("comment");
                //4.调用biz
                int countr=eRecordBiz.modify(eid,commentr,1);
                if(countr>0){

                    out.println("<script>alert('简历回复成功');location.href='./erecord.let?type=queryjob';</script>");
                }else{
                    out.println("<script>alert('简历回复失败');location.href='./erecord.let?type=queryjob';</script>");
                }
                break;
            case "queryelite":
                queryelite(req,resp,out);
                break;
            case "queryjob":
                //1.获取岗位信息
                //2.获取会员对象和所有的未还的记录

                List<ERecord> jRecords = eRecordBiz.getRecordsByJobId(user.getId(),false,2);
                //3.存request
                req.setAttribute("jRecords",jRecords);

                //4.转发
                req.getRequestDispatcher("resume_list.jsp").forward(req,resp);
                //out.println(jRecords);
                break;
            case "doajax":

                //没有输入: keyword: 空串""  null:没有找到 keyword
                String keyword = req.getParameter("keyword");
                keyword = keyword.isEmpty()?null:keyword;
                if(user.getType()==0){
                    //获取数据
                    List<Map<String,Object>> rows  = eRecordBiz.query_0(user.getId(),keyword,2);
                    //转成json
                    out.print(JSON.toJSONString(rows));
                }
                else if(user.getType()==1){
                    CompBiz compBiz=new CompBiz();
                    Comp comp=compBiz.getById(user.getId());
                    //获取数据
                    List<Map<String,Object>> rows  = eRecordBiz.query_1(comp.getlicense(),keyword,2);
                    //转成json
                    out.print(JSON.toJSONString(rows));
                }
                else {

                    //获取数据
                    List<Map<String,Object>> rows  = eRecordBiz.query(keyword,2);
                    //转成json
                    out.print(JSON.toJSONString(rows));
                }

                break;
            default:
                resp.sendError(404,"请求的地址不存在");
        }

    }
    /**
     * 查询
     * elite.let?type=query&pageIndex=1
     * 页数: biz
     * 当前页码:pageIndex = 1
     * 存:request,转发
     * @param req
     * @param resp
     * @param out
     */
    private void queryelite(HttpServletRequest req, HttpServletResponse resp, PrintWriter out) throws ServletException, IOException {
        HttpSession session1 = req.getSession();
        User user = (User)session1.getAttribute("user_now");
        int statee = 2;


        //1.获取信息(页数，页码,信息)
        int pageSize = 5;
        int pageCount = eRecordBiz.getPageCount(pageSize,user.getId(),statee);
        int pageIndex = Integer.parseInt(req.getParameter("pageIndex"));
        if(pageIndex<1){
            pageIndex = 1;
        }
        if(pageIndex>pageCount){
            pageIndex = pageCount;
        }
        List<ERecord> eRecords = eRecordBiz.getByPage(pageIndex,pageSize,user.getId(),statee);

        //2.存
        req.setAttribute("pageCount",pageCount);
        req.setAttribute("eRecords",eRecords);

        //3. 转发到jsp页面
        req.getRequestDispatcher("myjobs_list.jsp?pageIndex="+pageIndex).forward(req,resp);

    }
}
