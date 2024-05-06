<%--
  Created by IntelliJ IDEA.
  User: Altair
  Date: 2024/4/30
  Time: 10:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                                        <td valign="bottom"><h3 style="letter-spacing:1px; color:#409EFF;font-size: 15px;"> &nbsp;&nbsp;&nbsp;&nbsp; 个人中心 > 账号信息</h3></td>
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
                                    <form action="user.let?type=query" method="POST">
                                        <table width="100%"  class="cont tr_color">
                                            <tr>
                                                <th>用户ID</th>
                                                <th>用户密码</th>
                                            </tr>
                                            <c:forEach items="${userList}" var="m">
                                                <tr align="center" class="d">
                                                    <td>${m.id}</td>
                                                    <td>${m.pwd}</td>
                                                </tr>
                                            </c:forEach>


                                        </table>
                                    </form>
                                </td>
                            </tr>
                        </table>
                    </td>
                    <td width="2%">&nbsp;</td>
                </tr>
                <tr style="height: 550px;"></tr>
                <!-- 产品列表结束 -->
                <tr>
                    <td height="40" colspan="4">
                        <table width="100%" height="1" border="0" cellpadding="0" cellspacing="0" bgcolor="#CCCCCC">
                            <tr><td></td></tr>
                        </table>
                    </td>
                </tr>
                <tr style="font-size: 12px;">
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
            <!-- 底部部分 -->
            <!-- <tr>
                <td valign="bottom" background="./Images/mail_left_bg.gif">
                    <img src="./Images/buttom_left.gif" width="17" height="17" />
                </td>
                <td background="./Images/buttom_bgs.gif">
                    <img src="./Images/buttom_bgs.gif" width="17" height="17">
                </td>
                <td valign="bottom" background="./Images/mail_right_bg.gif">
                    <img src="./Images/buttom_right.gif" width="16" height="17" />
                </td>           
            </tr> -->
            <tr>
                <td width="17" valign="bottom" background="./Images/mail_left_bg.gif">
                    <img src="./Images/image.png" width="0" height="39" />
                </td>
                <td valign="top" background="./Images/image.png">
                    
                </td>
                <td  width="17" valign="bottom" background="./Images/mail_right_bg.gif">
                    <img src="./Images/image.png" width="5" height="39" /></td>
            </tr>
</table>
</body>
</html>
