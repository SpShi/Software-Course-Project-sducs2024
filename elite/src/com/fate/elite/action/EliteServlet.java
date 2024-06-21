package com.fate.elite.action;

import com.alibaba.fastjson.JSON;
import com.fate.elite.bean.Elite;
import com.fate.elite.bean.User;
import com.fate.elite.biz.EliteBiz;
import com.fate.elite.biz.UserBiz;
import com.fate.elite.util.DateHelper;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
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
                    session.setAttribute("user_now",usernow);//user-->Object
                    session.setAttribute("user_type",usernow.getType());
                    if(usernow.getType()==0)
                    {
                        out.println("<script>alert('登录成功');location.href='elite.let?type=add';</script>");
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
                try {
                    add(req,resp,out);
                } catch (FileUploadException e) {
                    e.printStackTrace();
                    resp.sendError(500,"文件上传失败");
                } catch (Exception e) {
                    e.printStackTrace();
                    resp.sendError(500,e.getMessage());
                }
                break;
            case "modifypre":
                if(session.getAttribute("user_now")==null){
                    out.println("<script>alert('请登录');parent.window.location.href='login.html';</script>");
                    return;
                }
                //类型&会员的信息
                long id = Long.parseLong(req.getParameter("id"));
                Elite elite = eliteBiz.getById(id);

                req.setAttribute("elite",elite);

                HttpSession session2 = req.getSession();
                Long type1=(Long) session2.getAttribute("user_type");
                if(type1==0||type1==2){
                    req.getRequestDispatcher("elite_modify.jsp").forward(req,resp);
                }
                else out.println("<script>alert('无权限'); location.href='login.html';</script>");

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
                if(session.getAttribute("user_now")==null){
                    out.println("<script>alert('请登录');parent.window.location.href='login.html';</script>");
                    return;
                }
                List<Elite> eliteList = eliteBiz.getAll();
                req.setAttribute("eliteList",eliteList);
                HttpSession session3 = req.getSession();
                Long type3=(Long) session3.getAttribute("user_type");
                //out.println(eliteList);
                if(type3==2){
                    req.getRequestDispatcher("elite_list.jsp").forward(req,resp);
                }
                break;
            case "query_r":
                query_r(req,resp,out);
                break;
            case "details":
                User userd=(User)session.getAttribute("user_now");
                if(userd==null){
                    out.println("<script>alert('请登录');parent.window.location.href='login.html';</script>");
                    return;
                }
                Elite eliteinfo = eliteBiz.getById(userd.getId());
                req.setAttribute("eliteinfo",eliteinfo);
                HttpSession session4 = req.getSession();
                Long type4=(Long) session4.getAttribute("user_type");
                //out.println(eliteList);
                if(type4==0){
                    req.getRequestDispatcher("elite_details.jsp").forward(req,resp);
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
                //1.获取用户输入的新的密码
                String newPwd = req.getParameter("newpwd");
                //2.获取用户的编号-session
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
    private void query_r(HttpServletRequest req, HttpServletResponse resp, PrintWriter out) throws ServletException, IOException {
        //1.获取信息(页数，页码,信息)
        String keyword=req.getParameter("keyword");
        int agel=Integer.parseInt(req.getParameter("agel"));
        int ageh=Integer.parseInt(req.getParameter("ageh"));
        int gender=Integer.parseInt(req.getParameter("gender"));
        int degrees=Integer.parseInt(req.getParameter("degrees"));
        Boolean desc=Boolean.parseBoolean(req.getParameter("desc"));
        keyword = keyword.isEmpty()?null:keyword;
        List<Elite> elites = eliteBiz.getAllwithLimit(agel,ageh,gender,degrees,keyword,desc);
        out.print(JSON.toJSONString(elites));
    }
    private void add(HttpServletRequest req, HttpServletResponse resp,  PrintWriter  out) throws Exception {
        HttpSession session = req.getSession();
        User user=(User)session.getAttribute("user_now");
        if(user==null){
            out.println("<script>alert('请登录');parent.window.location.href='login.html';</script>");
            return;
        }
        //1.构建一个磁盘工厂
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //1.1 设置大小
        factory.setSizeThreshold(1024*9);
        //1.2 临时仓库
        File file = new File("c:\\temp");
        if(!file.exists()){
            file.mkdir();//创建文件夹
        }
        factory.setRepository(file);

        //2.文件上传+表单数据
        ServletFileUpload fileUpload = new ServletFileUpload(factory);

        //3.将请求解析成一个个FileItem(文件+表单元素)
        List<FileItem> fileItems = fileUpload.parseRequest(req);

        //4.遍历FileItem
        Elite elite=new Elite();
        for(FileItem item: fileItems){
            if(item.isFormField()){
                //4.1 元素名称和用户填写的值  name: 文城
                String  name = item.getFieldName();
                String value = item.getString("utf-8");//防止乱码
                switch(name){
                    case "id":
                        elite.setId(Long.parseLong(value));
                        break;
                    case "name":
                        elite.setName(value);
                        break;
                    case "idnum": {
                        if(!eliteBiz.checkiN(value))
                        {
                            out.println("<script>alert('身份证号码不合法'); location.href='elite.let?type=details';</script>");
                            return;
                        }
                        elite.setIdNumber(value);
                        break;
                    }
                    case "gender":
                        elite.setGender(Long.parseLong(value));
                        break;
                    case "age":
                        elite.setAge(Long.parseLong(value));
                        break;
                    case "degrees":
                        elite.setDegrees(Long.parseLong(value));
                        break;
                    case "tel":
                    {
                        if(!eliteBiz.checktel(value))
                        {
                            out.println("<script>alert('电话号码不合法'); location.href='elite.let?type=details';</script>");
                            return;
                        }
                        elite.setTel(Long.parseLong(value));
                        break;
                    }
                    case "major":
                        elite.setMajor(value);
                        break;
                    case "email":
                        elite.setEmail(value);
                        break;
                    case "ctfct":
                        elite.setCertificate(value);
                        break;
                    case "intt":
                        elite.setIntention(value);
                        break;
                    case "slfe":
                        elite.setSelfevaluation(value);
                        break;
                    case "expe":
                        elite.setExperience(value);
                        break;

                }
            }else {
                //4.2 文件: 图片的文件名  city.png,用户不选择图片时，fileName的数据为""
                String fileName = item.getName();
                //避免文件替换：当前的系统时间.png
                if(fileName.trim().length()>0) {
                    //4.2.1 获取后缀名 .png
                    String filterName = fileName.substring(fileName.lastIndexOf("."));
                    //4.2.2 修改文件名  20211117112512234.png
                    fileName = DateHelper.getImageName() + filterName;
                    //保存到哪里
                    //虚拟路径: Images/cover/1-1.png
                    //文件的读写:实际路径 D://xx  --> 虚拟路径: Images/cover对应的实际路径
                    String path = req.getServletContext().getRealPath("/Images/cover");
                    //d:/xxx/xx/ 20211117112512234.png
                    String filePath = path + "/" + fileName;
                    //数据库表中的路径 ：Images/cover/101-1.png：相对项目的根目录的位置
                    String dbPath = "Images/cover/" + fileName;
                    elite.setResume(dbPath);
                    //4.3 保存文件
                    item.write(new File(filePath));
                }
            }
        }
        long idm=Long.parseLong(req.getParameter("id"));
        //out.println(elite);
        int count = eliteBiz.add(user.getId(),elite.getName(),elite.getIdNumber(),elite.getResume(),
                elite.getGender(),elite.getAge(),elite.getDegrees(),elite.getTel(),elite.getMajor(),elite.getEmail(),
                elite.getIdNumber(),elite.getIntention(),elite.getSelfevaluation(),elite.getExperience());
        if(count>0){
            out.println("<script>alert('用户信息添加成功'); location.href='elite.let?type=query';</script>");
        }else{
            out.println("<script>alert('用户信息添加失败'); location.href='elite.let?type=add';</script>");
        }
    }
    private void modify(HttpServletRequest req, HttpServletResponse resp, PrintWriter out) throws Exception
    {
        HttpSession session = req.getSession();
        if(session.getAttribute("user_now")==null){
            out.println("<script>alert('请登录');parent.window.location.href='login.html';</script>");
            return;
        }
        //1.构建一个磁盘工厂
        DiskFileItemFactory factory = new DiskFileItemFactory();
        //1.1 设置大小
        factory.setSizeThreshold(1024*9);
        //1.2 临时仓库
        File file = new File("c:\\temp");
        if(!file.exists()){
            file.mkdir();//创建文件夹
        }
        factory.setRepository(file);

        //2.文件上传+表单数据
        ServletFileUpload fileUpload = new ServletFileUpload(factory);

        //3.将请求解析成一个个FileItem(文件+表单元素)
        List<FileItem> fileItems = fileUpload.parseRequest(req);

        //4.遍历FileItem
        Elite elite=new Elite();
        for(FileItem item: fileItems){
            if(item.isFormField()){
                //4.1 元素名称和用户填写的值  name: 文城
                String  name = item.getFieldName();
                String value = item.getString("utf-8");//防止乱码
                switch(name){
                    case "id":
                        elite.setId(Long.parseLong(value));
                        break;
                    case "name":
                        elite.setName(value);
                        break;
                    case "idnum": {
                        if(!eliteBiz.checkiN(value))
                        {
                            out.println("<script>alert('身份证号码不合法'); location.href='elite.let?type=details';</script>");
                            return;
                        }
                        elite.setIdNumber(value);
                        break;
                    }
                    case "gender":
                        elite.setGender(Long.parseLong(value));
                        break;
                    case "age":
                        elite.setAge(Long.parseLong(value));
                        break;
                    case "degrees":
                        elite.setDegrees(Long.parseLong(value));
                        break;
                    case "tel":
                    {
                        if(!eliteBiz.checktel(value))
                        {
                            out.println("<script>alert('电话号码不合法'); location.href='elite.let?type=details';</script>");
                            return;
                        }
                        elite.setTel(Long.parseLong(value));
                        break;
                    }
                    case "major":
                        elite.setMajor(value);
                        break;
                    case "email":
                        elite.setEmail(value);
                        break;
                    case "ctfct":
                        elite.setCertificate(value);
                        break;
                    case "intt":
                        elite.setIntention(value);
                        break;
                    case "slfe":
                        elite.setSelfevaluation(value);
                        break;
                    case "expe":
                        elite.setExperience(value);
                        break;

                }
            }else {
                //4.2 文件: 图片的文件名  city.png,用户不选择图片时，fileName的数据为""
                String fileName = item.getName();
                //避免文件替换：当前的系统时间.png
                if(fileName.trim().length()>0) {
                    //4.2.1 获取后缀名 .png
                    String filterName = fileName.substring(fileName.lastIndexOf("."));
                    //4.2.2 修改文件名  20211117112512234.png
                    fileName = DateHelper.getImageName() + filterName;
                    //保存到哪里
                    //虚拟路径: Images/cover/1-1.png
                    //文件的读写:实际路径 D://xx  --> 虚拟路径: Images/cover对应的实际路径
                    String path = req.getServletContext().getRealPath("/Images/cover");
                    //d:/xxx/xx/ 20211117112512234.png
                    String filePath = path + "/" + fileName;
                    //数据库表中的路径 ：Images/cover/101-1.png：相对项目的根目录的位置
                    String dbPath = "Images/cover/" + fileName;
                    elite.setResume(dbPath);
                    //4.3 保存文件
                    item.write(new File(filePath));
                }
            }
        }
        long idm=Long.parseLong(req.getParameter("id"));
        //out.println(elite);
        int countm = eliteBiz.modify(idm,elite.getName(),elite.getIdNumber(),elite.getResume(),
                elite.getGender(),elite.getAge(),elite.getDegrees(),elite.getTel(),elite.getMajor(),elite.getEmail(),
                elite.getIdNumber(),elite.getIntention(),elite.getSelfevaluation(),elite.getExperience());
        if(countm>0){
            out.println("<script>alert('用户信息修改成功'); location.href='elite.let?type=details';</script>");
        }
        else{
            out.println("<script>alert('用户信息修改失败'); location.href='elite.let?type=details';</script>");
        }
    }
}
