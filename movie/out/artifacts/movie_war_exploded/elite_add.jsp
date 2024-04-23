<%--
  Created by IntelliJ IDEA.
  User: Spshi
  Date: 2024/4/22
  Time: 17:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="keywords" content=" 电影 java jsp"/>
    <meta http-equiv="author" content="Bronya"/>
    <link rel="stylesheet" type="text/css" href="./Style/skin.css"/>
    <script src="Js/jquery-3.3.1.min.js"></script>
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

    </script>
</head>
<body>
<table width="100%" border="0" cellpadding="0" cellspacing="0">
    <!-- 头部开始 -->
    <tr>
        <td width="17" valign="top" background="./Images/mail_left_bg.gif">
            <img src="./Images/left_top_right.gif" width="17" height="29"/>
        </td>
        <td valign="top" background="./Images/content_bg.gif">
        </td>
        <td width="16" valign="top" background="./Images/mail_right_bg.gif"><img src="./Images/nav_right_bg.gif"
                                                                                 width="16" height="29"/></td>
    </tr>
    <!-- 中间部分开始 -->
    <tr>
        <!--第一行左边框-->
        <td valign="middle" background="./Images/mail_left_bg.gif">&nbsp;</td>
        <!--第一行中间内容-->
        <td valign="top" bgcolor="#F7F8F9">
            <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
                <!-- 空白行-->
                <tr>
                    <td colspan="2" valign="top">&nbsp;</td>
                    <td>&nbsp;</td>
                    <td valign="top">&nbsp;</td>
                </tr>
                <tr>
                    <td colspan="4">
                        <table>
                            <tr>
                                <td width="100" align="center"><img src="./Images/mime.gif"/></td>
                                <td valign="bottom"><h3 style="letter-spacing:1px;">个人用户注册 </h3></td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <!-- 一条线 -->
                <tr>
                    <td height="40" colspan="4">
                        <table width="100%" height="1" border="0" cellpadding="0" cellspacing="0" bgcolor="#CCCCCC">
                            <tr>
                                <td></td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <!-- 添加栏目开始 -->
                <tr>
                    <td width="2%">&nbsp;</td>
                    <td width="96%">
                        <table width="100%">
                            <tr>
                                <td colspan="2">
                                    <form action="elite.let?type=add" method="post">
                                        <table width="100%" class="cont">
                                            <tr>
                                                <td width="2%">&nbsp;</td>
                                                <td width="10%">用户名：</td>
                                                <td width="20%"><input class="text" type="text" name="name" value=""
                                                                       required/></td>
                                                <td class="err">长度6~15个字符</td>
                                                <td width="2%">&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td width="2%">&nbsp;</td>
                                                <td>密码：</td>
                                                <td><input class="text" type="password" name="pwd" value="" required/>
                                                </td>
                                                <td class="err">必须包含字母和数字，长度6~15位</td>
                                                <td width="2%">&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td width="2%">&nbsp;</td>
                                                <td>确认密码：</td>
                                                <td><input class="text" type="password" name="pwd2" value="" required/>
                                                </td>
                                                <td class="err">两次密码必须一致</td>
                                                <td width="2%">&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td width="2%">&nbsp;</td>
                                                <td width="10%">身份证号：</td>
                                                <td width="20%"><input class="text" type="text" name="idnum" value=""
                                                                       required/></td>
                                                <td class="err">长度17个字符</td>
                                                <td width="2%">&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td width="2%">&nbsp;</td>
                                                <td>性别</td>
                                                <td>
                                                    <c:forEach items="${genders}" var="mt">
                                                        <c:if test="${mt.id==1}">
                                                            <input type="radio" name="gender" value="${mt.id}"
                                                                   checked/> ${mt.name}男&nbsp;&nbsp;
                                                            <input type="hidden" value="${mt.recharge}"/>
                                                        </c:if>
                                                        <c:if test="${mt.id!=1}">
                                                            <input type="radio" name="gender"
                                                                   value="${mt.id}"/> ${mt.name}女&nbsp;&nbsp;
                                                            <input type="hidden" value="${mt.recharge}"/>
                                                        </c:if>
                                                    </c:forEach>
                                                </td>
                                                <td></td>
                                                <td width="2%">&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td width="2%">&nbsp;</td>
                                                <td>年龄</td>
                                                <td>
                                                    <input class="text" id="balance" type="number" name="age"
                                                           value="" readonly/>
                                                </td>
                                                <td class="err">您的年龄应在18岁到60岁之间</td>
                                                <td></td>
                                                <td width="2%">&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td width="2%">&nbsp;</td>
                                                <td>学历</td>
                                                <td>
                                                    <select id="education" name="degrees">
                                                        <option value="0">无学历</option>
                                                        <option value="1">小学毕业</option>
                                                        <option value="2">初中毕业</option>
                                                        <option value="3">高中毕业</option>
                                                        <option value="4">学士学位</option>
                                                        <option value="5">硕士学位</option>
                                                        <option value="6">博士学位</option>
                                                    </select>
                                                </td>
                                                <td></td>
                                                <td width="2%">&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td width="2%">&nbsp;</td>
                                                <td>电话号码</td>
                                                <td>
                                                    <input class="text" type="tel" name="tel" required/>
                                                </td>
                                                <td class="err">请输入13位电话号码</td>
                                                <td></td>
                                                <td width="2%">&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td width="2%">&nbsp;</td>
                                                <td>简历</td>
                                                <td>
                                                    <input class="text" type="text" name="resume" required/>
                                                </td>
                                                <td></td>
                                                <td width="2%">&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td width="2%">&nbsp;</td>
                                                <td>专业</td>
                                                <td>
                                                    <input class="text" type="text" name="major" required/>
                                                </td>
                                                <td></td>
                                                <td width="2%">&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td width="2%">&nbsp;</td>
                                                <td>邮箱</td>
                                                <td>
                                                    <input class="text" type="text" name="email" required/>
                                                </td>
                                                <td></td>
                                                <td width="2%">&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td width="2%">&nbsp;</td>
                                                <td>证书</td>
                                                <td>
                                                    <input class="text" type="text" name="ctfct" required/>
                                                </td>
                                                <td></td>
                                                <td width="2%">&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td width="2%">&nbsp;</td>
                                                <td>求职意向</td>
                                                <td>
                                                    <input class="text" type="text" name="intt" required/>
                                                </td>
                                                <td></td>
                                                <td width="2%">&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td width="2%">&nbsp;</td>
                                                <td>自我评估</td>
                                                <td>
                                                    <input class="text" type="text" name="slfe" required/>
                                                </td>
                                                <td></td>
                                                <td width="2%">&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td width="2%">&nbsp;</td>
                                                <td>工作经验</td>
                                                <td>
                                                    <input class="text" type="text" name="expe" required/>
                                                </td>
                                                <td></td>
                                                <td width="2%">&nbsp;</td>
                                            </tr>
                                            <tr>
                                                <td>&nbsp;</td>
                                                <td></td>
                                                <td><input class="btn" type="submit" value="提交"/></td>
                                                <td></td>
                                                <td>&nbsp;</td>
                                            </tr>
                                        </table>
                                    </form>
                                </td>
                            </tr>
                        </table>
                    </td>
                    <td width="2%">&nbsp;</td>
                </tr>
                <!-- 添加栏目结束 -->
                <tr>
                    <td height="40" colspan="4">
                        <table width="100%" height="1" border="0" cellpadding="0" cellspacing="0" bgcolor="#CCCCCC">
                            <tr>
                                <td></td>
                            </tr>
                        </table>
                    </td>
                </tr>
                <tr>
                    <td width="2%">&nbsp;</td>
                    <td width="51%" class="left_txt">
                        <img src="./Images/icon_mail.gif" width="16" height="11"> 客户服务邮箱：356303328@qq.com<br/>
                    </td>
                    <td>&nbsp;</td>
                    <td>&nbsp;</td>
                </tr>
            </table>
        </td>
        <td background="./Images/mail_right_bg.gif">&nbsp;</td>
    </tr>
    <!-- 底部部分 -->
    <tr>
        <td valign="bottom" background="./Images/mail_left_bg.gif">
            <img src="./Images/buttom_left.gif" width="17" height="17"/>
        </td>
        <td background="./Images/buttom_bgs.gif">
            <img src="./Images/buttom_bgs.gif" width="17" height="17">
        </td>
        <td valign="bottom" background="./Images/mail_right_bg.gif">
            <img src="./Images/buttom_right.gif" width="16" height="17"/>
        </td>
    </tr>
</table>
</body>
</html>