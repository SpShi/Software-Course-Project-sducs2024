<%--
  Created by IntelliJ IDEA.
  User: 电影
  Date: 2022/12/25
  Time: 14:57
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
                                <td valign="bottom"><h3 style="letter-spacing:1px;">${mvin}>评论 </h3></td>
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
                                            <th>日期</th>
                                            <th>内容</th>
                                            <th>操作</th>
                                        </tr>
                                        <c:forEach items="${minfos}" var="b">
                                            <tr align="center" class="d">
                                                <td>${b.id}</td>
                                                <td>${b.mname}</td>
                                                <td>${b.date}</td>
                                                <td>${b.desc}</td>
                                                <td>
                                                    <a onclick="return confirm('确认删除');" href="minfo.let?type=remove&id=${b.id}">删除</a>
                                                </td>
                                            </tr>
                                        </c:forEach>

                                        <tr><td colspan="9" align="center">
                                            <div class="pager">
                                                <ul class="clearfix">
                                                    <li><a href="minfo.let?mvin=${mvin}&type=query&pageIndex=${param.pageIndex-1}">上一页</a></li>
                                                    <c:forEach var="i" begin="1" end="${pageCount}" step="1">
                                                        <c:if test="${i==param.pageIndex}">
                                                            <li class="current"><a href="minfo.let?mvin=${mvin}&type=query&pageIndex=${i}">${i}</a></li>
                                                        </c:if>
                                                        <c:if test="${i!=param.pageIndex}">
                                                            <li><a href="minfo.let?mvin=${mvin}&type=query&pageIndex=${i}">${i}</a></li>
                                                        </c:if>
                                                    </c:forEach>
                                                    <li><a href="minfo.let?mvin=${mvin}&type=query&pageIndex=${param.pageIndex+1}">下一页</a></li>
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
                    <td width="2%">&nbsp;</td>
                    <td width="96%">
                        <table width="100%">
                            <tr>
                                <td colspan="2">
                                    <form action="minfo.let?mvin=${mvin}&type=add" method="post" enctype="multipart/form-data">
                                        <table width="100%"class="cont">
                                            <tr>
                                                <td>&nbsp;</td>
                                                <td>评论：</td>
                                                <td colspan="2"><textarea cols="150" rows="20" name="desc"></textarea></td>
                                                <td></td>
                                            </tr>
                                            <tr>
                                                <td>&nbsp;</td>
                                                <td></td>
                                                <td colspan="3"><input class="btn" type="submit" value="提交" /></td>

                                            </tr>
                                        </table>
                                    </form>
                                </td>
                            </tr>
                        </table>
                    </td>
                    <td width="2%">&nbsp;</td>
                </tr>
                <!-- 添加产品结束 -->
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