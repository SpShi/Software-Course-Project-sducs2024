<%--
  Created by IntelliJ IDEA.
  User: Altair
  Date: 2024/6/15
  Time: 18:32
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
                                    <form action="erecord.let?type=reply&id=${eRecord.id}" method="post">
                                        <table valign="top" width="100%" style="margin-left: 15px;">
                                            <tr class="lab">
                                                <th>
                                                <td width="80px">姓名：</td>
                                                <td>${eRecord.elite.name}</td>
                                                <%--                                    <td ><input class="input" placeholder="长度6~15个字符"  type="text" name="name" value="${m.name}" required/></td>--%>
                                                <!-- <td class="err">长度1~15个字符</td> -->
                                                </th>
                                                <th>
                                                <td width="80px">身份证号：</td>
                                                <%--                                    <td><input class="input" placeholder="长度17个字符"  type="text" name="idnum" value="${m.idNumber}" required/></td>--%>
                                                <!-- <td class="err">长度17个字符</td> -->

                                                <td>${eRecord.elite.idNumber}</td>
                                                </th>
                                            </tr>
                                            <tr class="lab">
                                                <th>
                                                <td width="80px">性别：</td>

                                                <td >
                                                    <c:if test="${eRecord.elite.gender==0}">其他</c:if>
                                                    <c:if test="${eRecord.elite.gender==1}">女</c:if>
                                                    <c:if test="${eRecord.elite.gender==2}">男</c:if>
                                                </td>

                                                </th>
                                                <th>
                                                <td width="80px">年龄：</td>
                                                <%--                                    <td><input class="input" placeholder="您的年龄应在18岁到60岁之间"  id="balance" type="number" name="age" value="${m.age}" required/></td>--%>
                                                <%--                                    <td class="err">您的年龄应在18岁到60岁之间</td>--%>
                                                <td>${eRecord.elite.age}</td>

                                                </th>
                                            </tr>
                                            <tr class="lab">
                                                <th>
                                                <td width="80px">学历：</td>
                                                <td>
                                                    <c:if test="${eRecord.elite.degrees==0}">无学历</c:if>
                                                    <c:if test="${eRecord.elite.degrees==1}">小学毕业</c:if>
                                                    <c:if test="${eRecord.elite.degrees==2}">初中毕业</c:if>
                                                    <c:if test="${eRecord.elite.degrees==3}">高中毕业</c:if>
                                                    <c:if test="${eRecord.elite.degrees==4}">专科</c:if>
                                                    <c:if test="${eRecord.elite.degrees==5}">本科</c:if>
                                                    <c:if test="${eRecord.elite.degrees==6}">硕士学位</c:if>
                                                    <c:if test="${eRecord.elite.degrees==7}">博士学位</c:if>
                                                </td>

                                                </th>
                                                <th>
                                                <td width="80px">电话号码：</td>
                                                <td>${eRecord.elite.tel}</td>
                                                <%--                                    <td><input class="input" placeholder="请输入11位电话号码"  type="tel" name="tel" value="${m.tel}" required/></td>--%>
                                                <%--                                    <td class="err">请输入11位电话号码</td>--%>

                                                </th>
                                            </tr>

                                            <tr class="lab">
                                                <th>
                                                <td width="80px">专业：</td>
                                                <td>${eRecord.elite.major}</td>
                                                <%--                                    <td><input class="input"  type="text" name="major" value="${m.major}" required/></td>--%>

                                                </th>
                                                <th>
                                                <td width="80px">邮箱：</td>
                                                <td>${eRecord.elite.email}</td>
                                                <%--                                    <td><input class="input" placeholder="请输入邮箱"  type="text" name="email" value="${m.email}" required/></td>--%>

                                                </th>
                                            </tr>
                                            <tr class="lab">
                                                <th >

                                                <td width="80px">简历：</td>
                                                <%--                                    <td>${eRecord.elite.resume}</td>--%>
                                                <td><textarea class="tex"  type="text"   rows="6"  disabled>${eRecord.elite.resume}</textarea></td>
                                                </th>
                                                <th>
                                                <td width="80px">证书：</td>
                                                <%--                                    <td>${eRecord.elite.certificate}</td>--%>
                                                <td><textarea class="tex"  type="text"   rows="6"  disabled>${eRecord.elite.certificate}</textarea></td>
                                                <%--                                    <td><textarea class="tex" name="ctfct"  rows="6"required>${m.certificate}</textarea></td>--%>

                                                </th>
                                            </tr>

                                            <tr class="lab">
                                                <th>
                                                <td width="80px">求职意向：</td>
                                                <%--                                    <td>${eRecord.elite.intention}</td>--%>
                                                <td><textarea class="tex"  type="text"   rows="6"  disabled>${eRecord.elite.intention}</textarea></td>

                                                <%--                                    <td><textarea class="tex"  name="intt" rows="6" required >${m.intention}</textarea></td>--%>

                                                </th>
                                                <th>
                                                <td width="80px">自我评估：</td>
                                                <%--                                    <td>${eRecord.elite.selfevaluation}</td>--%>
                                                <td><textarea class="tex"  type="text"   rows="6"  disabled>${eRecord.elite.selfevaluation}</textarea></td>

                                                <%--                                    <td><textarea class="tex"  name="slfe" rows="6" required>${m.selfevaluation}</textarea></td>--%>

                                                </th>
                                            </tr>
                                            <tr class="lab">
                                                <th>
                                                <td width="80px">工作经验：</td>
                                                <%--                                    <td>${eRecord.elite.experience}</td>--%>
                                                <td><textarea class="tex"  type="text"   rows="6"  disabled>${eRecord.elite.experience}</textarea></td>

                                                <%--                                    <td><textarea class="tex" name="expe"rows="6" required>${m.experience}</textarea></td>--%>

                                                </th>
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
