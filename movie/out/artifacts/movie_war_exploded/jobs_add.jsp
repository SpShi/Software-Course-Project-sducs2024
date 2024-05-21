<%--
  Created by IntelliJ IDEA.
  User: Altair
  Date: 2024/5/20
  Time: 16:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="keywords"  content = " 电影 java jsp"/>
    <meta http-equiv="author" content="Bronya"/>
    <link rel="stylesheet" type="text/css" href="./Style/skin.css" />
    <script src="Js/jquery-3.3.1.min.js"></script>
    <script>
        //图片预览
        function getFullPath(obj){
            if (obj){
                //ie
                if (window.navigator.userAgent.indexOf("MSIE") >= 1){
                    obj.select();
                    return document.selection.createRange().text;
                }else if (window.navigator.userAgent.indexOf("Firefox") >= 1){
                    //firefox　
                    return window.URL.createObjectURL(obj.files.item(0));
                }else if(navigator.userAgent.indexOf("Chrome")>0){
                    //chrome
                    return window.URL.createObjectURL(obj.files.item(0));
                }
                return obj.value;
            }
        }
        $(function(){
            $("#pic").change(function(){
                var path = getFullPath($(this)[0]);
                console.log(path);
                $("#imgPic").prop("src",path);
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
                                <td valign="bottom"><h3 style="letter-spacing:1px;">岗位管理 > 添加岗位</h3></td>
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
                        <table width="100%">
                            <tr>
                                <td colspan="2">
                                    <form action="jobs.let?type=add" method="post" enctype="multipart/form-data">
                                        <table width="100%"class="cont">
                                            <tr>
                                                <td width="2%">&nbsp;</td>
                                                <td width="15%">名称：</td>
                                                <td width="25%"><input class="text" type="text" name="name" value="" /></td>
                                                <td width="2%">&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td width="2%">&nbsp;</td>
                                                <td>最大年龄要求：</td>
                                                <td width="20%"><input class="text" style="width:50px;" type="number" name="age" value="100" /></td>

                                                <td width="2%">&nbsp;</td>
                                            </tr>
                                            <tr class="lab"><td>性别要求：</td>
                                                <td>
                                                    <c:forEach items="gender" var="mt">
                                                        <c:if test="0">
                                                            <input  type="radio" name="gender" value="0"
                                                                    checked/> 全部&nbsp;&nbsp;
                                                        </c:if>
                                                        <c:if test="1">
                                                            <input type="radio" name="gender" value="1"/> 女&nbsp;&nbsp;
                                                        </c:if>
                                                        <c:if test="2">
                                                            <input type="radio" name="gender" value="2"/> 男
                                                        </c:if>
                                                    </c:forEach>
                                                </td>
                                            </tr>
                                            <tr class="lab"><td>学历要求：</td>
                                                <td>
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
                                                </td>
                                            </tr>
                                            <tr>
                                                <td width="2%">&nbsp;</td>
                                                <td>专业要求：</td>
                                                <td width="20%"><input class="text" style="width:100px;" type="text" name="major"  /></td>

                                                <td width="2%">&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td width="2%">&nbsp;</td>
                                                <td>证书要求：</td>
                                                <td width="20%"><input class="text"  type="text" name="certificates"/></td>
                                                <td></td>
                                                <td width="2%">&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td width="2%">&nbsp;</td>
                                                <td>工资：</td>
                                                <td width="20%"><input class="text"  type="text" name="salary"/></td>
                                                <td></td>
                                                <td width="2%">&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td width="2%">&nbsp;</td>
                                                <td>联系邮箱：</td>
                                                <td width="20%"><input class="text"  type="text" name="email"/></td>
                                                <td></td>
                                                <td width="2%">&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td width="2%">&nbsp;</td>
                                                <td>简介：</td>
                                                <td width="20%"><input class="text"  type="text" name="intro"/></td>
                                                <td></td>
                                                <td width="2%">&nbsp;</td>
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
