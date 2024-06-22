<%--
  Created by IntelliJ IDEA.
  User: Altair
  Date: 2024/5/20
  Time: 17:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.fate.elite.bean.ERecord" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html >
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="keywords"  content = " 岗位 java jsp"/>
    <meta http-equiv="author" content="Bronya"/>
    <link rel="stylesheet" type="text/css" href="./Style/skin.css" />
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
                                <td valign="bottom"><h3 style="letter-spacing:1px; color:#409EFF;font-size: 15px;"> &nbsp;&nbsp;&nbsp;&nbsp; 岗位管理 > 在招岗位</h3></td>
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
                <!-- 产品列表开始 -->
                <tr>
                    <td width="2%">&nbsp;</td>
                    <td width="96%">
                        <table width="100%">
                            <tr>
                                <td colspan="2">

                                    <table width="100%"  class="cont tr_color">
                                        <tr>
                                            <th>编号</th>
                                            <th>名称</th>
                                            <th>所属公司</th>
                                            <th>年龄要求</th>
                                            <th>性别限制</th>
                                            <th>学历要求</th>
                                            <th>专业要求</th>
                                            <th>证书</th>
                                            <th>工资</th>
                                            <th>邮箱</th>
                                            <th>简介</th>
                                            <th>操作</th>
                                        </tr>
                                        <c:forEach items="${jobs}" var="b">
                                            <tr align="center" class="d">
                                                <td><a href="jobs.let?type=details&id=${b.id}">${b.id}</a></td>
                                                <td>${b.name}</td>
                                                <td>${b.comp.name}</td>
                                                <td>${b.age}</td>
                                                <td>${b.gender}</td>
                                                <td>${b.degrees}</td>
                                                <td>${b.major}</td>
                                                <td>${b.certificates}</td>
                                                <td>${b.salary}</td>
                                                <td>${b.email}</td>
                                                <td>${b.intro}</td>
                                                <td>
                                                    <a href="jobs.let?type=details&id=${b.id}">详情</a>
                                                    <a onclick="return confirm('确认修改');" href="jobs.let?type=modifypre&id=${b.id}">修改</a>&nbsp;&nbsp;
                                                    <a onclick="return confirm('确认删除');" href="jobs.let?type=remove&id=${b.id}">删除</a>
                                                </td>
                                            </tr>
                                        </c:forEach>

                                        <tr><td colspan="9" align="center">
                                            <div class="pager">
                                                <ul class="clearfix">
                                                    <li><a href="jobs.let?type=query&pageIndex=${param.pageIndex-1}">上一页</a></li>
                                                    <c:forEach var="i" begin="1" end="${pageCount}" step="1">
                                                        <c:if test="${i==param.pageIndex}">
                                                            <li class="current"><a href="jobs.let?type=query&pageIndex=${i}">${i}</a></li>
                                                        </c:if>
                                                        <c:if test="${i!=param.pageIndex}">
                                                            <li><a href="jobs.let?type=query&pageIndex=${i}">${i}</a></li>
                                                        </c:if>
                                                    </c:forEach>
                                                    <li><a href="jobs.let?type=query&pageIndex=${param.pageIndex+1}">下一页</a></li>
                                                </ul>
                                            </div>
                                        </td></tr>
                                    </table>

                                </td>
                            </tr>
                        </table>
                    </td>
                    <td width="2%">&nbsp;</td>
                </tr>
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
    .select{
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
    .btn{
        float:right;
        margin-top: 5px;
        margin-bottom:5px ;
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