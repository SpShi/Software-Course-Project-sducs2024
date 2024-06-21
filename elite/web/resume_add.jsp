<%--
  Created by IntelliJ IDEA.
  User: Altair
  Date: 2024/6/15
  Time: 15:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="keywords"  content = " 岗位 java jsp"/>
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
                                <td valign="bottom"><h3 style="letter-spacing:1px; color:#409EFF;font-size: 15px;"> &nbsp;&nbsp;&nbsp;&nbsp; 求职 > 简历投递</h3></td>
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
                                    <form action="erecord.let?type=add&jobid=${job.id}" method="post">
                                        <table width="100%"class="cont">
                                            <tr>
                                                <td width="2%">&nbsp;</td>
                                                <td>编号：</td>
                                                <td width="20%"><input class="text" type="text" name="movieId"  value="${job.id}" readonly/></td>
                                                <td width="2%">&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td width="2%">&nbsp;</td>
                                                <td width="15%">岗位名称：</td>
                                                <td width="25%"><input class="text" type="text" name="name" value="${job.name}" readonly/></td>

                                                <td width="2%">&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td width="2%">&nbsp;</td>
                                                <td>所属公司：</td>
                                                <td width="20%">
                                                    <input class="text" type="text" name="type" value="${job.comp.name}" readonly/>
                                                </td>
                                                <td width="2%">&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td width="2%">&nbsp;</td>
                                                <td>年龄限制：</td>
                                                <td width="20%"><input class="text" style="width:50px;" type="number" name="age" value="${job.age}"  readonly /></td>

                                                <td width="2%">&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td width="2%">&nbsp;</td>
                                                <td>性别限制：</td>
                                                <td width="20%"><input class="text" style="width:100px;" type="text" name="gender" value="${job.gender}" readonly /></td>

                                                <td width="2%">&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td width="2%">&nbsp;</td>
                                                <td>学历限制：</td>
                                                <td width="20%"><input class="text"  type="text" name="degrees" value="${job.degrees}" readonly/></td>
                                                <td></td>
                                                <td width="2%">&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td width="2%">&nbsp;</td>
                                                <td>专业:</td>
                                                <td width="20%"><input class="text"  type="text" name="major" value="${job.major}" readonly/></td>
                                                <td></td>
                                                <td width="2%">&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td width="2%">&nbsp;</td>
                                                <td>证书:</td>
                                                <td width="20%"><input class="text"  type="text" name="certificates" value="${job.certificates}" readonly/></td>
                                                <td></td>
                                                <td width="2%">&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td width="2%">&nbsp;</td>
                                                <td>工资:</td>
                                                <td width="20%"><input class="text"  type="text" name="salary" value="${job.salary}"readonly/></td>
                                                <td></td>
                                                <td width="2%">&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td width="2%">&nbsp;</td>
                                                <td>邮箱:</td>
                                                <td width="20%"><input class="text"  type="text" name="email" value="${job.email}"readonly/></td>
                                                <td></td>
                                                <td width="2%">&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td>&nbsp;</td>
                                                <td>简介：</td>
                                                <td colspan="2"><textarea cols="150" rows="20" readonly>${job.intro}</textarea></td>
                                                <td></td>
                                            </tr>
                                            <tr>
                                                <td width="2%">&nbsp;</td>
                                                <td>留言：</td>
                                                <td width="20%"><input class="input"  type="text" name="comment"/></td>
                                                <td></td>
                                                <td width="2%">&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td>&nbsp;</td>
                                                <td width="2%"></td>
                                                <td><input class="btn" type="submit" value="提交" /></td>
                                            </tr>
                                            <tr>
                                                <td>&nbsp;</td>
                                                <td></td>
                                                <td colspan="3"><input class="btn" type="button" value="返回" onclick="window.location='jobs_search.jsp';" /></td>
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
        color: black;
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
