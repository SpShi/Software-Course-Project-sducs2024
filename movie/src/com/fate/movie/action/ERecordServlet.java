package com.fate.movie.action;

import com.alibaba.fastjson.JSON;
import com.fate.movie.bean.Comp;
import com.fate.movie.bean.ERecord;
import com.fate.movie.bean.User;
import com.fate.movie.biz.CompBiz;
import com.fate.movie.biz.ERecordBiz;
import com.fate.movie.biz.EliteBiz;
import com.fate.movie.biz.JobsBiz;

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
            case "reply":
                //1.购买的会员编号
                long idr = user.getId();
                //2.购买的电影编号
                Long eid = Long.parseLong(req.getParameter("ids"));
                String commentr=req.getParameter("comment");
                int sr=Integer.parseInt(req.getParameter("state"));
                //4.调用biz
                int countr=eRecordBiz.modify(eid,idr,commentr,sr);
                if(countr>0){

                    out.println("<script>alert('简历回复成功');location.href='main_new.jsp';</script>");
                }else{
                    out.println("<script>alert('简历回复失败');location.href='main_new.jsp';</script>");
                }
                break;
            case "queryelite":
                queryelite(req,resp,out);
                break;
            case "queryjob":
                //1.获取岗位信息
//                String s=req.getParameter("jobid");
//                if(s==null)  {
//                    out.println("<script>alert('简历查询失败');location.href='main_new.jsp';</script>");
//                }
//                long jobid=Long.parseLong(s);
//                boolean desc=Boolean.parseBoolean(req.getParameter("desc"));
//                int statej=Integer.parseInt(req.getParameter("statej"));
                //2.获取会员对象和所有的未还的记录

                List<ERecord> jRecords = eRecordBiz.getRecordsByJobId(user.getId(),false,2);
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
     * movie.let?type=query&pageIndex=1
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
        int statee = Integer.parseInt(req.getParameter("statee"));


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
