<%--
  Created by IntelliJ IDEA.
  User: Altair
  Date: 2024/5/27
  Time: 15:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html >
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="keywords"  content = " 电影 java jsp"/>
    <meta http-equiv="author" content="Bronya"/>
    <link rel="stylesheet" type="text/css" href="./Style/skin.css" />

</head>
<body>
<div class="body"></div>
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
                                <td valign="bottom"><h3 style="letter-spacing:1px; color:#409EFF;font-size: 15px;"> &nbsp;&nbsp;&nbsp;&nbsp; 个人中心 > 修改密码</h3></td>
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


                <!-- 添加产品开始 -->
                <tr>
                    <td width="2%">&nbsp;</td>
                    <td width="96%">
                        <table width="100%" >
                            <tr>
                                <td colspan="2">
                                    <form action="user.let?type=modifyPwd" method="post">
                                        <table valign="top" width="90%" style="margin-left: 15px">
                                            <tr class="lab"><td width="80px">原密码：</td>
                                                <td><input class="input" placeholder="请输入原密码" type="password" name="pwd" value=""/></td>
                                                <td class="err">*原密码不匹配</td></tr>
                                            <tr class="lab"><td width="80px"> 新密码：</td>
                                                <td ><input class="input" placeholder="请输入新密码"type="password" name="newpwd" value="" /></td></tr>
                                            <tr class="lab"><td width="80px">确认密码：</td>
                                                <td ><input class="input" placeholder="确认密码"type="password" name="newpwd2" value="" /></td>
                                                <td  class="err">两次密码不相同</td>   </tr>
                                        </table>
                                <td width="2%">&nbsp;</td>
                            <tr><td><input class="btlg" type="submit" value="提交" /><td></td></tr>

                            </form>

                            </td>
                            </tr>
                            <tr class="con"><td></td></tr>
                        </table>
                    </td>
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
<style>
    .body{
        background-color: #fff;
        border: black;

    }
    .lab{
        margin-top: 50px;
        color: dimgray;
        font-size: 14px;
        width: 60%;
        height: 50px;
        margin-left: 50px;
        /* background-color: aqua; */
    }
    .input{
        width: 50%;
        height: 30px;
        border: none;
        padding-left: 8px;
        outline: none;
    }
    .con{
        /* height: 400px; */
        /* background-color: aquamarine; */
    }
    .btlg{
        float:right;
        margin-right: 100px;
        margin-top: 400px;
        width:75px;
        height: 40px;
        background-color: #409EFF;
        border: none;
        color: white;
        font-size:16px;
        border-radius: 5px;
    }
</style>
