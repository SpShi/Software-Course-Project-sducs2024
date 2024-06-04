
<%--
  Created by IntelliJ IDEA.
  User: 电影
  Date: 2022/12/21
  Time: 11:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<script language="JavaScript">
    let list =[[${eliteList}]];
    for (let i = 0; i < list.length; i++) {
        console.log(list[i]);
}

</script>
<html >
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="keywords"  content = " 电影 java jsp"/>
    <meta http-equiv="author" content="Bronya"/>
    <link rel="stylesheet" type="text/css" href="./Style/skin.css" />
    <script language="JavaScript">
        $(function () {
            //为每个radio绑定事件
            $(":radio").each(function (index, element) {
                $(this).click(function () {
                    //radio的下一个元素(hidden)的 value
                    var value = $(this).next().val();
                    //赋值给balance的text
                    $("#balance").val(value);
                    console.log(value);
                });
            });

        });
        <%--$("#education").val(${eliteList}.degrees);--%>
		// $("#education option[value='3']").prop("selected",true);

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
                                <td valign="bottom"><h3 style="letter-spacing:1px; color:#409EFF;font-size: 15px;"> &nbsp;&nbsp;&nbsp;&nbsp; 用户管理 > 人才管理</h3></td>
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
                                    <form action="elite.let?type=query" method="GET">
                                        <table width="100%"  class="cont tr_color"  >
                                            <tr>
                                                <th>账号</th>
                                                <th>姓名</th>
                                                <th>身份证号</th>
<%--                                                <th>4</th>--%>
                                                <th  >性别</th>
                                                <th >年龄</th>
                                                <th >学历</th>
                                                <th >电话</th>
                                                <th >电子邮件</th>
                                                <th>专业</th>
                                                <th  colspan="10"  style="word-break: break-word">证书</th>
                                                <th  colspan="10"  style="word-break: break-word">简历</th>
                                                <th  colspan="10"  style="word-break: break-word">求职倾向</th>
                                                <th  colspan="10"  style="word-break: break-word">自我评估</th>
                                                <th colspan="10"  style="word-break: break-word">经验</th>
                                                <th>操作</th>
<%--                                                <th>16</th>--%>
                                            </tr>
                                            <c:forEach items="${eliteList}" var="m">
                                                <tr align="center" class="d">
                                                    <td>${m.id}</td>
                                                    <td>${m.name}</td>
                                                    <td>${m.idNumber}</td>
<%--                                                    <td>${m.state}</td>--%>

                                                    <td>
                                                        <c:if test="${m.gender==0}">其他</c:if>
                                                        <c:if test="${m.gender==1}">女</c:if>
                                                        <c:if test="${m.gender==2}">男</c:if>
                                                    </td>
                                                    <td>${m.age}</td>
                                                    <td> <c:if test="${m.degrees==0}">无学历</c:if>
                                                        <c:if test="${m.degrees==1}">小学毕业</c:if>
                                                        <c:if test="${m.degrees==2}">初中毕业</c:if>
                                                        <c:if test="${m.degrees==3}">高中毕业</c:if>
                                                        <c:if test="${m.degrees==4}">专科</c:if>
                                                        <c:if test="${m.degrees==5}">本科</c:if>
                                                        <c:if test="${m.degrees==6}">硕士学位</c:if>
                                                        <c:if test="${m.degrees==7}">博士学位</c:if>
                                                    </td>
                                                    <td >${m.tel}</td>
                                                   
                                                    <td>${m.email}</td>
                                                    <td>${m.major}</td>
                                                    <td colspan="10"  style="word-break: break-word">${m.certificate}</td>
                                                    <td colspan="10"  style="word-break: break-word">${m.resume}</td>
                                                    <td colspan="10"  style="word-break: break-word">${m.intention}</td>
                                                    <td colspan="10"  style="word-break: break-word">${m.selfevaluation}</td>
                                                    <td colspan="10"  style="word-break: break-word">${m.experience}</td>
                                                    <td >
                                                        <a onclick="return confirm('确认修改');" href="elite.let?type=modifypre&id=${m.id}">修改</a> &nbsp;&nbsp;
                                                        <a onclick="return confirm('确认删除');" href="elite.let?type=remove&id=${m.id}">删除</a>
                                                    </td>
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
/* .t{
    width: 30px;
    height: auto;
} */
</style>