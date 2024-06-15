<%--
  Created by IntelliJ IDEA.
  User: Altair
  Date: 2024/6/13
  Time: 20:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html >
<html >
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="keywords"  content = " 人才招聘 java jsp"/>
    <meta http-equiv="author" content="Bronya"/>
    <link rel="stylesheet" type="text/css" href="./Style/skin.css" />
    <script src="Js/jquery-3.3.1.min.js"></script>
    <script>

        $(function(){
            $("#btnQuery").click(function(){
                //0.清理
                $("#tbRecord").find("tbody").html("");
                //1.获取id
                // 获取年龄区间
                var ageh = $('input[name="ageh"]').val();
                var agel = $('input[name="agel"]').val();
                var salary = $('input[name="salary"]').val();

                // 获取学历、性别和介绍的选择
                var education = $('#education').val();
                var gender = $('#gender').val();
                var desc = $('#desc').val();

                // 获取搜索关键字
                var keyword = $('#intro').val();

                // 组装 URL
                var url = "jobs.let?type=search&ageh="+ageh+"&agel="+agel+"&degrees="+education+"&gender="+gender+
                    "&desc="+desc+"&keyword="+keyword+"&salary="+salary;

                // 发送请求并处理数据
                $.get(url, function(data){
                    console.log(data);
                    //将 data 数据转化为 json 对象
                    var jobsList2 = JSON.parse(data);
                    //如果 jobsList2 为空，那么告知没有信息展示并停止运行
                    if(jobsList2.length === 0){
                        alert("没有信息展示");
                        return;
                    }
                    //遍历 jobsList2
                    for(var i = 0; i < jobsList2.length; i++){
                        var job = jobsList2[i];
                        //创建并填充各个 td 元素
                        var tr = $("<tr align=\"center\" class=\"d\">");
                        var id= $("<td>" + job.id + "</td>");
                        var na= $("<td>" + job.name + "</td>");
                        var compna= $("<td>" + job.comp.ename + "</td>");
                        var ag= $("<td>" + job.age + "</td>");
                        if(job.gender==1) var ge= $("<td>限女</td>");
                        else if(job.gender==2) var ge= $("<td>限男</td>");
                        else var ge= $("<td>不限</td>");


                        if(job.degrees==0) var de= $("<td>无学历</td>");
                        else if(job.degrees===1) var de= $("<td>小学毕业</td>");
                        else if(job.degrees===2) var de= $("<td>初中毕业</td>");
                        else if(job.degrees===3) var de= $("<td>高中毕业</td>");
                        else if(job.degrees===4) var de= $("<td>专科</td>");
                        else if(job.degrees===5) var de= $("<td>学士学位</td>");
                        else if(job.degrees===6) var de= $("<td>硕士学位</td>");
                        else if(job.degrees===7) var de= $("<td>博士学位</td>");
                        else var de= $("<td>保密</td>");
                        var ma= $("<td>" + job.major + "</td>");
                        var sa= $("<td>" + job.salary + "</td>");
                        var em= $("<td>" + job.email + "</td>");
                        var int= $("<td>" + job.intro + "</td>");
                        var butt=$("<td colspan=\"3\"><input class=\"btn\" type=\"button\" value=\"投递简历\" " +
                            "onclick=\"window.location='erecord.let?type=addpre&jobid="+job.id +"';\" /></td>" )
                        //将所有 td 元素添加到tr中
                        tr.append(id);
                        tr.append(na);
                        tr.append(compna);
                        tr.append(ag);
                        tr.append(ge);
                        tr.append(de);
                        tr.append(ma);
                        tr.append(sa);
                        tr.append(em);
                        tr.append(int);
                        tr.append(butt);
                        //将 tr 添加到 ID 为 "tbRecord" 的表格的 tbody 中
                        $("#tbRecord").find("tbody").append(tr);
                    }
                });
            });
        });
    </script>
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
    <!-- 头部开始 -->
    <tr>
        <td width="17" valign="top" background="./Images/mail_left_bg.gif">
            <img src="./Images/image.png" width="0" height="39" />
        </td>
        <td valign="top" background="./Images/image.png">

        </td>
        <td  width="17" valign="top" background="./Images/mail_right_bg.gif">
            <img src="./Images/image.png" width="5" height="39" /></td>
    </tr>
    <!-- 中间部分开始 -->
    <tr>
        <!--第一行左边框-->
        <td valign="middle" background="./Images/mail_left_bg.gif">&nbsp;</td>
        <!--第一行中间内容-->
        <td valign="top" bgcolor="#F7F8F9">
            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                <!-- 空白行-->
                <tr><td colspan="2" valign="top">&nbsp;</td><td>&nbsp;</td><td valign="top">&nbsp;</td></tr>
                <tr>
                    <td colspan="4">
                        <table>
                            <tr>
                                <!-- <td width="100" align="center"><img src="./Images/mime.gif" /></td> -->
                                <td valign="bottom"><h3 style="letter-spacing:1px; color:#409EFF;font-size: 15px;"> &nbsp;&nbsp;&nbsp;&nbsp; 人才管理 > 人才查找</h3></td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <!-- 一条线 -->
                <tr>
                    <td height="40" colspan="4">
                        <table width="100%" height="1" border="0" cellpadding="0" cellspacing="0" bgcolor="#CCCCCC">
                            <tr><td></td></tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td width="2%">&nbsp;</td>
                    <td width="96%">
                        <fieldset>
                            <legend>查询条件</legend>
                            <table class="cont"  >
                                <tr width="100%">
                                    <td colspan="8" >
                                        &nbsp;&nbsp;&nbsp;最大年龄:&nbsp;&nbsp;<input type="text" name="ageh" value="60" />
                                        &nbsp;&nbsp;&nbsp;最小年龄:&nbsp;&nbsp;<input type="text" name="agel" value="18" />
                                        &nbsp;&nbsp;&nbsp;最低薪资:&nbsp;&nbsp;<input type="text" name="salary" value="1220" />
                                        &nbsp;&nbsp;&nbsp;所属公司:&nbsp;&nbsp;<input type="text" placeholder="不填为无要求" name="salary" value="" />
                                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;最高学历范围:&nbsp;&nbsp;<select class="input" id="education" name="degrees" width="auto" >
                                        <option value="0">无学历</option>
                                        <option value="1">小学毕业</option>
                                        <option value="2">初中毕业</option>
                                        <option value="3">高中毕业</option>
                                        <option value="4">专科</option>
                                        <option value="5">学士学位</option>
                                        <option value="6">硕士学位</option>
                                        <option value="7">博士学位</option>
                                    </select></td> </tr>
                                <tr width="100%"> <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;性&nbsp;&nbsp;别:&nbsp;&nbsp;</td><td><select class="input" id="gender" name="gender" width="auto">
                                    <option value="0">不限</option>
                                    <option value="1">女</option>
                                    <option value="2">男</option>
                                </select></td>
                                    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;排序方式:&nbsp;&nbsp;</td><td><select class="input" id="desc" name="desc" width="auto">
                                        <option value="true">薪资从低到高</option>
                                        <option value="false">薪资从高到低</option>
                                    </select></td>
                                    <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;请输入关键字:&nbsp;&nbsp;<input class="text" type="text" id="intro" name="keyword" width="auto"/>
                                        <input type="button" id="btnQuery" value="搜索" style="width: 80px;"/>
                                    </td>
                                </tr>
                            </table>
                        </fieldset>
                    </td>
                    <td width="2%">&nbsp;</td>
                </tr>

                <!--空行-->
                <tr>
                    <td height="40" colspan="3">
                    </td>
                </tr>
                <tr>
                    <td width="2%">&nbsp;</td>
                    <td width="96%">
                        <table width="100%">
                            <tr>
                                <td colspan="2">
                                    <form action="" method="">
                                        <table width="100%"  class="cont tr_color" id="tbRecord">
                                            <thead>
                                            <tr>
                                                <th>id</th>
                                                <th>用户名</th>
                                                <th>公司名</th>
                                                <th>年龄要求</th>
                                                <th>性别要求</th>
                                                <th>最低学历</th>
                                                <th>专业要求</th>
                                                <th>基础薪资</th>
                                                <th>邮箱</th>
                                                <th>求职要求</th>
                                                <th>求职</th>
                                            </tr>
                                            </thead>
                                            <tbody>

                                            </tbody>
                                        </table>
                                    </form>
                                </td>
                            </tr>
                        </table>
                    </td>
                    <td width="2%">&nbsp;</td>
                </tr>
                <tr>
                    <td height="40" colspan="4">
                        <table width="100%" height="1" border="0" cellpadding="0" cellspacing="0" bgcolor="#CCCCCC">
                            <tr><td></td></tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td width="2%">&nbsp;</td>
                    <td width="51%" class="left_txt">
                        <img src="./Images/icon_mail.gif" width="16" height="11"> 客户服务邮箱：356303328@qq.com<br />
                    </td>
                    <td>&nbsp;</td><td>&nbsp;</td>
                </tr>
            </table>
        </td>
        <td background="./Images/mail_right_bg.gif">&nbsp;</td>
    </tr>
    <!-- 底部部分 -->
    <tr>
        <td valign="bottom" background="./Images/mail_left_bg.gif">
            <img src="./Images/buttom_left.gif" width="17" height="17" />
        </td>
        <td background="./Images/buttom_bgs.gif">
            <img src="./Images/buttom_bgs.gif" width="17" height="17">
        </td>
        <td valign="bottom" background="./Images/mail_right_bg.gif">
            <img src="./Images/buttom_right.gif" width="16" height="17" />
        </td>
    </tr>
</table>
</body>
</html>
<style>

    .lab{
        margin-top: 100px;
        color: dimgray;
        font-size: 14px;
        width: 40%;
        height: 50px;
        margin-left: 30px;
        /* background-color: aqua; */
    }
    .input{
        width: 200px;
        height: 30px;
        border: none;
        /* border-color: skyblue; */
        padding-left: 20px;
        outline: none;
        /* background-color: pink; */
    }
    .con{
        /* height: 400px; */
        /* background-color: aquamarine; */
    }
    .btlg{
        float:right;
        /* margin-right: 100px; */
        /* margin-top: 20px; */
        width:75px;
        height: 40px;
        background-color: #409EFF;
        border: none;
        color:white;
        font-size:17px;
        border-radius: 5px;
        text-align:center;
        vertical-align: middle;
        line-height: 40px;
    }
</style>
