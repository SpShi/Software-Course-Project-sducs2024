<%--
  Created by IntelliJ IDEA.
  User: Altair
  Date: 2024/6/1
  Time: 15:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html >
<html >
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="keywords"  content = " 电影 java jsp"/>
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

                // 获取学历、性别和介绍的选择
                var education = $('#education').val();
                var gender = $('#gender').val();
                var desc = $('#desc').val();

                // 获取搜索关键字
                var keyword = $('#intro').val();

                // 组装 URL
                var url = "elite.let?type=query_r&ageh="+ageh+"&agel="+agel+"&degrees="+education+"&gender="+gender+"&desc="+desc+"&keyword="+keyword;

                // 发送请求并处理数据
                $.get(url, function(data){
                    console.log(data);
                    //将 data 数据转化为 json 对象
                    var elites = JSON.parse(data);
                    //如果 elites 为空，那么告知没有信息展示并停止运行
                    if(elites.length === 0){
                        alert("没有信息展示");
                        return;
                    }
                    //遍历 elites
                    for(var i = 0; i < elites.length; i++){
                        var elite = elites[i];
                        //创建并填充各个 td 元素
                        var tr = $("<tr align=\"center\" class=\"d\">");
                         var id= $("<td>" + elite.id + "</td>");
                         var na= $("<td>" + elite.name + "</td>");
                         var idn= $("<td>" + elite.idNumber + "</td>");
                         var st= $("<td>" + elite.state + "</td>");
                         var re= $("<td>" + elite.resume + "</td>");
                         var ge= $("<td>" + elite.gender + "</td>");
                         var ag= $("<td>" + elite.age + "</td>");
                         var de= $("<td>" + elite.degrees + "</td>");
                         var te= $("<td>" + elite.tel + "</td>");
                         var ma= $("<td>" + elite.major + "</td>");
                         var em= $("<td>" + elite.email + "</td>");
                         var ce= $("<td>" + elite.certificate + "</td>");
                         var int= $("<td>" + elite.intention + "</td>");
                         var se= $("<td>" + elite.selfevaluation + "</td>");
                         var ex= $("<td>" + elite.experience + "</td>");

                        //将所有 td 元素添加到tr中
                        tr.append(id);
                        tr.append(na);
                        tr.append(idn);
                        tr.append(re);
                        tr.append(ge);
                        tr.append(ag);
                        tr.append(de);
                        tr.append(te);
                        tr.append(ma);
                        tr.append(em);
                        tr.append(ce);
                        tr.append(int);
                        tr.append(se);
                        tr.append(ex);
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
            <img src="./Images/left_top_right.gif" width="17" height="29" />
        </td>
        <td valign="top" background="./Images/content_bg.gif">

        </td>
        <td width="16" valign="top" background="./Images/mail_right_bg.gif"><img src="./Images/nav_right_bg.gif" width="16" height="29" /></td>
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
                                <td width="100" align="center"><img src="./Images/mime.gif" /></td>
                                <td valign="bottom"><h3 style="letter-spacing:1px;">电影添加 > 人才列表 </h3></td>
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
                            <table width="100%" class="cont"  >
                                <tr>
                                    <td colspan="8" align="center">
                                        最大年龄:<input type="text" name="ageh" value="60"/> &nbsp;&nbsp;
                                        最小年龄:<input type="text" name="agel" value="18"/> &nbsp;&nbsp;
                                        <select class="input" id="education" name="degrees">
                                            <option value="0">无学历</option>
                                            <option value="1">小学毕业</option>
                                            <option value="2">初中毕业</option>
                                            <option value="3">高中毕业</option>
                                            <option value="4">专科</option>
                                            <option value="5">学士学位</option>
                                            <option value="6">硕士学位</option>
                                            <option value="7">博士学位</option>
                                        </select>
                                        <select class="input" id="gender" name="gender">
                                            <option value="0">其他</option>
                                            <option value="1">女</option>
                                            <option value="2">男</option>
                                        </select>
                                        <select class="input" id="desc" name="desc">
                                            <option value="true">学历从低到高</option>
                                            <option value="false">学历从高到低</option>
                                        </select>
                                        请输入关键字:&nbsp;&nbsp;<input class="text" type="text" id="intro" name="keyword"/>
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
                                                <th>身份证号</th>
                                                <th>4</th>
                                                <th>5</th>
                                                <th>性别</th>
                                                <th>年龄</th>
                                                <th>学历</th>
                                                <th>电话号码</th>
                                                <th>专业</th>
                                                <th>邮箱</th>
                                                <th>简历</th>
                                                <th>证书</th>
                                                <th>求职意向</th>
                                                <th>自我评估</th>
                                                <th>工作经验</th>
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
<%--                                <tr>--%>
<%--                                    <td width="2%">&nbsp;</td>--%>
<%--                                    <td width="96%">--%>
<%--                                        <table width="100%">--%>
<%--                                            <tr>--%>
<%--                                                <td colspan="2">--%>
<%--                                                        <table width="100%"  class="cont tr_color">--%>
<%--                                                            <tr>--%>
<%--                                                                <th>id</th>--%>
<%--                                                                <th>用户名</th>--%>
<%--                                                                <th>身份证号</th>--%>
<%--                                                                <th>4</th>--%>
<%--                                                                <th>5</th>--%>
<%--                                                                <th>性别</th>--%>
<%--                                                                <th>年龄</th>--%>
<%--                                                                <th>学历</th>--%>
<%--                                                                <th>电话号码</th>--%>
<%--                                                                <th>专业</th>--%>
<%--                                                                <th>邮箱</th>--%>
<%--                                                                <th>简历</th>--%>
<%--                                                                <th>证书</th>--%>
<%--                                                                <th>求职意向</th>--%>
<%--                                                                <th>自我评估</th>--%>
<%--                                                                <th>工作经验</th>--%>
<%--                                                            </tr>--%>
<%--                                                            <c:forEach items="${elite_List}" var="m">--%>
<%--                                                                <tr align="center" class="d">--%>
<%--                                                                    <td>${m.id}</td>--%>
<%--                                                                    <td>${m.name}</td>--%>
<%--                                                                    <td>${m.idNumber}</td>--%>
<%--                                                                    <td>${m.state}</td>--%>
<%--                                                                    <td>${m.resume}</td>--%>
<%--                                                                    <td>${m.gender}</td>--%>
<%--                                                                    <td>${m.age}</td>--%>
<%--                                                                    <td>${m.degrees}</td>--%>
<%--                                                                    <td>${m.tel}</td>--%>
<%--                                                                    <td>${m.major}</td>--%>
<%--                                                                    <td>${m.email}</td>--%>
<%--                                                                    <td>${m.certificate}</td>--%>
<%--                                                                    <td>${m.intention}</td>--%>
<%--                                                                    <td>${m.selfevaluation}</td>--%>
<%--                                                                    <td>${m.experience}</td>--%>
<%--                                                                </tr>--%>
<%--                                                            </c:forEach>--%>

<%--                                                            <tr><td colspan="9" align="center">--%>
<%--                                                                <div class="pager">--%>
<%--                                                                    <ul class="clearfix">--%>
<%--                                                                        <li><a href="elite.let?type=query_r&pageIndex=${param.pageIndex-1}">上一页</a></li>--%>
<%--                                                                        <c:forEach var="i" begin="1" end="${pageCount}" step="1">--%>
<%--                                                                            <c:if test="${i==param.pageIndex}">--%>
<%--                                                                                <li class="current"><a href="elite.let?type=query_r&pageIndex=${i}">${i}</a></li>--%>
<%--                                                                            </c:if>--%>
<%--                                                                            <c:if test="${i!=param.pageIndex}">--%>
<%--                                                                                <li><a href="elite.let?type=query_r&pageIndex=${i}">${i}</a></li>--%>
<%--                                                                            </c:if>--%>
<%--                                                                        </c:forEach>--%>
<%--                                                                        <li><a href="elite.let?type=query_r&pageIndex=${param.pageIndex+1}">下一页</a></li>--%>
<%--                                                                    </ul>--%>
<%--                                                                </div>--%>
<%--                                                            </td></tr>--%>
<%--                                                        </table>--%>

<%--                                                </td>--%>
<%--                                            </tr>--%>
<%--                                        </table>--%>
<%--                                    </td>--%>
<%--                                    <td width="2%">&nbsp;</td>--%>
<%--                                </tr>--%>
                <!-- 产品列表结束 -->
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
        width: 70%;
        height: 30px;
        border: none;
        /* border-color: skyblue; */
        padding-left: 8px;
        outline: none;
        /* background-color: pink; */
    }
    .tex{
        width: 90%;
        height: 90px;
        border: none;
        padding-left: 8px;
        padding-top: 5px;
        margin-top: 10px;
        outline: none;
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
