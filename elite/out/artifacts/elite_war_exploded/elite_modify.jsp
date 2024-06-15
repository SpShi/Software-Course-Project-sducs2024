
<%--
  Created by IntelliJ IDEA.
  User: 人才招聘
  Date: 2022/12/21
  Time: 11:58
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
                                    <td valign="bottom"><h3 style="letter-spacing:1px; color:#409EFF;font-size: 15px;"> &nbsp;&nbsp;&nbsp;&nbsp; 个人中心 > 个人信息修改</h3></td>
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
                        <form action="elite.let?type=modify&id=${elite.id}" method="post"  enctype="multipart/form-data">
                            <table width="100%" style="margin-left: 30px;">
                                <tr class="lab">
                                    <th>
                                        <td width="80px">姓名：</td>
                                        <td ><input class="input" placeholder="长度6~15个字符"  type="text" name="name" value="${elite.name}" required/></td>
                                    </th>
                                    <th>
                                        <td width="80px">身份证号：</td>
                                        <td><input class="input" placeholder="长度17个字符"  type="text" name="idnum" value="${elite.idNumber}" required/></td>
                                    </th>
                                </tr>
                                <tr class="lab">
                                    <th>
                                    <td width="80px">性别：</td>

                                    <td >
                                        <select class="input" id="gender" name="gender">
                                            <option value="0">其他</option>
                                            <option value="1">女</option>
                                            <option value="2">男</option>
                                        </select>
                                    </td>

                                    </th>
                                    <th>
                                    <td width="80px">年龄：</td>
                                   <td><input class="input" placeholder="您的年龄应在18岁到60岁之间"  id="balance" type="number" name="age" value="${elite.age}" required/></td>
                                    </th>
                                </tr>
                                <tr class="lab">
                                    <th>
                                    <td width="80px">学历：</td>
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

                                    </th>
                                    <th>
                                    <td width="80px">电话号码：</td>
                                     <td><input class="input" placeholder="请输入11位电话号码"  type="tel" name="tel" value="${elite.tel}" required/></td>

                                    </th>
                                </tr>

                                <tr class="lab">
                                    <th>
                                    <td width="80px">专业：</td>
                                   <td><input class="input"  type="text" name="major" value="${elite.major}" required/></td>
                                    </th>
                                    <th>
                                    <td width="80px">邮箱：</td>
                                    <td><input class="input" placeholder="请输入邮箱"  type="text" name="email" value="${elite.email}" required/></td>--%>

                                    </th>
                                </tr>

                                <tr class="lab"><td>简历：</td>
                                    <td>
                                        <input  type="file" id="pic" name="resume"  rows="5" required/></td>
                                    </td>
                                </tr>
                                <tr class="lab">
                                    <th>
                                    <td width="80px">证书：</td>
                                     <td><textarea class="tex" name="ctfct"  rows="6"required>${elite.certificate}</textarea></td>--%>

                                    </th>
                                </tr>

                                <tr class="lab">
                                    <th>
                                    <td width="80px">求职意向：</td>
                                     <td><textarea class="tex"  name="intt" rows="6" required >${elite.intention}</textarea></td>

                                    </th>
                                    <th>
                                    <td width="80px">自我评估：</td>

                                 <td><textarea class="tex"  name="slfe" rows="6" required>${elite.selfevaluation}</textarea></td>

                                    </th>
                                </tr>
                                <tr class="lab">
                                    <th>
                                    <td width="80px">工作经验：</td>
                                     <td><textarea class="tex" name="expe"rows="6" required>${elite.experience}</textarea></td>

                                    </th>
                                </tr>

                            </table>
                            <td width="2%">&nbsp;</td>
                            </tr>
                        <td width="2%">&nbsp;</td>
                    <td><input class="btlg" type="submit" value="提交" /></td>
                </form>
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