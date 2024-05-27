
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
    let list =[[ ${eliteList} ]];
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
        // var num = "${eliteList}";
        // console.log(num.degrees);
       
          
        $("#education").val(${eliteList}.degrees);
        // console.log((${eliteList.degrees});
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
                                <td valign="bottom"><h3 style="letter-spacing:1px; color:#409EFF;font-size: 15px;"> &nbsp;&nbsp;&nbsp;&nbsp; 个人中心 > 个人信息</h3></td>
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
                <c:forEach items="${eliteList}" var="m">
                <tr>
                <td width="2%">&nbsp;</td>
                    <td width="96%">
                <table width="100%" style="margin-left: 30px;">
                    
                    <tr class="lab">
                        <th>
                            <td width="80px">用户名：</td>
                            <!-- <td>${m.name}</td> -->
                            <td ><input class="input" placeholder="长度6~15个字符"  type="text" name="name" value="${m.name}" required/></td>
                            <!-- <td class="err">长度1~15个字符</td> -->
                        </th>
                        <th>
                            <td width="80px">身份证号：</td>
                            <td><input class="input" placeholder="长度17个字符"  type="text" name="idnum" value="${m.idNumber}" required/></td>
                            <!-- <td class="err">长度17个字符</td> -->
                            
                            <!-- <td>${m.idNumber}</td> -->
                        </th>
                    </tr>
                    <tr class="lab">
                        <th>
                            <td width="80px">性别：</td>
                            <td>
                              
                                    <c:if test="0">
                                        <input  type="radio" name="gender" value="0"checked/> 其他&nbsp;&nbsp;
                                    </c:if>
                                    <c:if test="1">
                                        <input type="radio" name="gender" value="1"/> 女&nbsp;&nbsp;
                                    </c:if>
                                    <c:if test="2">
                                        <input type="radio" name="gender" value="2"/> 男
                                    </c:if>
                              
                            </td>
                            
                            <!-- <td>${m.gender}</td> -->
                          
                        </th>
                        <th>
                            <td width="80px">年龄：</td>
                            <td><input class="input" placeholder="您的年龄应在18岁到60岁之间"  id="balance" type="number" name="age" value="${m.age}" required/></td>
                            <td class="err">您的年龄应在18岁到60岁之间</td>
                            <!-- <td>${m.age}</td> -->
                          
                        </th>
                    </tr>
                    <tr class="lab">
                        <th>
                            <td width="80px">学历：</td>
                            <!-- <td>${m.degrees}</td> -->
                            <td>
                                <select class="input" id="education" name="degrees" >
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
                            <!-- <td>${m.tel}</td> -->
                            <td><input class="input" placeholder="请输入11位电话号码"  type="tel" name="tel" value="${m.tel}" required/></td>
                            <td class="err">请输入11位电话号码</td>
                           
                        </th>
                    </tr>

                    <tr class="lab">
                        <th>
                            <td width="80px">专业：</td>
                            <!-- <td>${m.major}</td> -->
                            <td><input class="input"  type="text" name="major" value="${m.major}" required/></td>
                           
                        </th>
                        <th>
                            <td width="80px">邮箱：</td>
                            <!-- <td>${m.email}</td> -->
                            <td><input class="input" placeholder="请输入邮箱"  type="text" name="email" value="${m.email}" required/></td>
                            
                        </th>
                    </tr>
                    <tr class="lab">
                        <th>
                             <!-- <td>${m.resume}</td> -->
                            <td width="80px">简历：</td>
                            <td><textarea class="tex"  type="text" name="resume"  rows="6"  required>${m.resume}</textarea></td>
                        </th>
                       <th>
                        <td width="80px">证书：</td>
                        <!-- <td>${m.certificate}</td> -->
                        <td><textarea class="tex" name="ctfct"  rows="6"required>${m.certificate}</textarea></td>

                       </th>
                    </tr>

                    <tr class="lab">
                        <th>
                            <td width="80px">求职意向：</td>
                        <!-- <td>${m.intention}</td> -->
                        <td><textarea class="tex"  name="intt" rows="6" required >${m.intention}</textarea></td>

                        </th>
                        <th>
                            <td width="80px">自我评估：</td>
                            <!-- <td>${m.selfevaluation}</td> -->
                            <td><textarea class="tex"  name="slfe" rows="6" required>${m.selfevaluation}</textarea></td>

                        </th>
                    </tr>
                    <tr class="lab">
                        <th>
                            <td width="80px">工作经验：</td>
                            <!-- <td>${m.experience}</td> -->
                            <td><textarea class="tex" name="expe"rows="6" required>${m.experience}</textarea></td>

                        </th>
                    </tr>
               
                </table>
            </td>
            <td width="2%">&nbsp;</td>
            </tr>
            <td width="2%">&nbsp;</td>
            <td ><a class="btlg"  onclick="return confirm('确认修改');" href="elite.let?type=modifypre&id=${m.id}">修改</a></td>
            </c:forEach>
            
                <!-- <tr>
                    <td width="2%">&nbsp;</td>
                    <td width="96%">
                        <table width="100%">
                            <tr>
                                <td colspan="2">
                                    <form action="elite.let?type=query" method="POST">
                                        <table width="100%"  class="cont tr_color">
                                            <tr>
                                                <th>1</th>
                                                <th>2</th>
                                                <th>3</th>
                                                <th>4</th>
                                                <th>5</th>
                                                <th>6</th>
                                                <th>7</th>
                                                <th>8</th>
                                                <th>9</th>
                                                <th>10</th>
                                                <th>11</th>
                                                <th>12</th>
                                                <th>13</th>
                                                <th>14</th>
                                                <th>15</th>
                                                <th>16</th>
                                            </tr>
                                            <c:forEach items="${eliteList}" var="m">
                                                <tr align="center" class="d">
                                                    <td>${m.id}</td>
                                                    <td>${m.name}</td>
                                                    <td>${m.idNumber}</td>
                                                    <td>${m.state}</td>
                                                    <td>${m.resume}</td>
                                                    <td>${m.gender}</td>
                                                    <td>${m.age}</td>
                                                    <td>${m.degrees}</td>
                                                    <td>${m.tel}</td>
                                                    <td>${m.major}</td>
                                                    <td>${m.email}</td>
                                                    <td>${m.certificate}</td>
                                                    <td>${m.intention}</td>
                                                    <td>${m.selfevaluation}</td>
                                                    <td>${m.experience}</td>

                                                    <td>
                                                        <a onclick="return confirm('确认修改');" href="member.let?type=modifypre&id=${m.id}">修改</a>&nbsp;&nbsp;
                                                        <a onclick="return confirm('确认删除');" href="member.let?type=remove&id=${m.id}">删除</a>
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
                </tr> -->
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