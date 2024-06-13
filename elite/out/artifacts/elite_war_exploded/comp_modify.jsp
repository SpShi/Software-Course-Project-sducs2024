<%--
  Created by IntelliJ IDEA.
  User: 29012
  Date: 2024/5/22
  Time: 14:57
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
                                <td valign="bottom"><h3 style="letter-spacing:1px; color:#409EFF;font-size: 15px;"> &nbsp;&nbsp;&nbsp;&nbsp; 企业中心 > 企业信息修改</h3></td>
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
                    <td width="2%">&nbsp</td>
                    <td width="96%">
                        <table width="100%">
                            <tr>
                                <td colspan="2">
                                    <form action="comp.let?type=modify&id=${compx.id}" method="post">
                                        <table valign="top" width="100%" style="margin-left: 15px;">
                                            <tr class="lab">
                                                <td width="120px">账号：</td>
                                                <td><input class="input" type="text" name="id" value="${compx.id}" readonly/></td>
                                            </tr>
                                            <tr class="lab">
                                                <td width="120px">注册人：</td>
                                                <td><input class="input" placeholder="请输入注册人"  type="text" name="ename" value="${compx.ename}" required/></td>
                                                <td class="err">长度17个字符</td>
                                            </tr>
                                            <tr class="lab">
                                                <td width="120px">企业名：</td>
                                                <td><input class="input" placeholder="请输入企业名"  type="text" name="name" value="${compx.name}" required/></td>
                                            </tr>
                                            <tr class="lab">
                                                <td width="120px">身份证号：</td>
                                                <td><input class="input" placeholder="请输入身份证号"  type="text" name="idnumber" value="${compx.idnumber}" required/></td>
                                            </tr>
                                            <tr class="lab">
                                                <td width="120px">经营许可证编号：</td>
                                                <td><input class="input" placeholder="请输入经营许可证编号"  type="text" name="license" value="${compx.license}" required/></td>
                                            </tr>
                                            <tr class="lab">
                                                <td width="120px">联系方式：</td>
                                                <td><input class="input" placeholder="请输入联系方式"  type="text" name="tel" value="${compx.tel}" required/></td>
                                            </tr>
                                            <tr class="lab">
                                                <td width="120px">地址：</td>
                                                <td><input class="input" placeholder="请输入地址"  type="text" name="addr" value="${compx.addr}" required/></td>
                                            </tr>
                                        </table>
                                        <td width="2%">&nbsp;</td>
                                        <tr><td><input class="btlg" type="submit" value="提交" /></td> </tr>
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
        margin-top: 20px;
        color: dimgray;
        font-size: 14px;
        width: 40%;
        height: 50px;
        margin-left: 80px;
        /* background-color: aqua; */
    }
    .input{
        width: 50%;
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