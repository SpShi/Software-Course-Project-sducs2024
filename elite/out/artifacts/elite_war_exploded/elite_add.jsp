<%--
  Created by IntelliJ IDEA.
  User: Spshi
  Date: 2024/4/22
  Time: 17:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta http-equiv="keywords" content=" 人才招聘 java jsp"/>
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
<img class="body"></img>
<div class="back">
    <div class="reg">
        <form action="elite.let?type=add" method="post">
            <table valign="top" style=" text-align: center;margin-left: 120px;width: 100%;">
                <h4 style="letter-spacing:1px;font-size:23px; color: rgb(119, 118, 118);">完善个人信息</h4>
                <tr class="lab"><td>用户名：</td>
                    <td><input class="input" placeholder="长度1~15个字符"  type="text" name="name" value="" required/></td>
                    <td class="err">长度1~15个字符</td></tr>

                <tr class="lab"><td>身份证号：</td>
                    <td><input class="input" placeholder="长度17个字符"  type="text" name="idnum" value="" required/></td>
                    <td class="err">长度17个字符</td></tr>

                <tr class="lab"><td>性别：</td>
                    <td>
                        <select class="input" id="gender" name="gender">
                            <option value="0">其他</option>
                            <option value="1">女</option>
                            <option value="2">男</option>
                        </select>
                    </td>
                </tr>
                <tr class="lab"><td>年龄：</td>
                    <td><input class="input" placeholder="您的年龄应在18岁到60岁之间"  id="balance" type="number" name="age" value="" required/></td>
                    <td class="err">您的年龄应在18岁到60岁之间</td></tr>


                <tr class="lab"><td>学历：</td>
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

                <tr class="lab"><td>电话号码：</td>
                    <td><input class="input" placeholder="请输入11位电话号码"  type="tel" name="tel"  required/></td>
                    <td class="err">请输入11位电话号码</td></tr>

                <tr class="lab"><td >专业：</td>
                    <td><input class="input"  type="text" name="major" value="" required/></td>
                </tr>

                <tr class="lab"><td width="80px">邮箱：</td>
                    <td><input class="input" placeholder="请输入邮箱"  type="text" name="email" required/></td>
                </tr>

                <tr class="lab"><td>简历：</td>
                    <td>
                        <textarea class="tex"  type="text" name="resume"  rows="5" required></textarea>

                        <!-- <input class="input"  type="text" name="age" value="" required/> -->
                    </td>
                </tr>


                <tr class="lab"><td width="80px">证书：</td>
                    <td>
                        <textarea class="tex" name="ctfct"  rows="5" required></textarea></td>
                    <!-- <input class="input" style="height:50px"  type="text" name="ctfct" value="" required/> -->
                </tr>

                <tr class="lab"><td width="80px">求职意向：</td>
                    <td>
                        <textarea class="tex"  name="intt"  rows="5" required ></textarea></td>

                    <!-- <input class="input" type="text" name="intt" value="" required/> -->
                    </td>
                </tr>

                <tr class="lab"><td width="80px">自我评估：</td>
                    <td>
                        <textarea class="tex"  name="slfe"  rows="5" required></textarea></td>
                    <!-- <input class="input"  type="text" name="slfe" value="" required/> -->
                    </td>
                </tr>

                <tr class="lab"><td width="80px">工作经验：</td>
                    <td>
                        <textarea class="tex" name="expe" rows="5" required></textarea></td>

                    <!-- <input class="input"  type="text" name="expe" value="" required/> -->
                    </td>
                </tr>

            </table>
            <div style=" background-color: #e0e8ef;">
                <td><input class="btlg" type="submit" value="提交" /></td>
            </div>

        </form>
    </div>
</div>
</body>
</html>
<style>
    .body{
        background: url("./Images/elite.jpeg");
        background-repeat: no-repeat;
        background-size: 100% 100%;
        position: fixed;
        height: 100%;
        width: 100%;
        opacity: 0.8;
        filter: blur(5px);


    }
    .back{
        text-align: center;
        float:left;
        margin-left: 450px;
        width: 700px;
        height:920px;
        /*height: 100%;*/
        background-color: rgba(255, 255, 255, 0.789);
        backdrop-filter: blur(1px);
    }
    .reg{
        /*float:left;*/
        width: 700px;
        height: 800px;
        /* background-color: rgba(222, 146, 146, 0.789); */
        /*margin-left: 500px;*/
        margin-top: 7%;
        text-align: center;
        /*border-radius: 10px;*/
        /*backdrop-filter: blur(1px);*/
    }
    .lab{
        margin-top: 100px;
        color: dimgray;
        font-size: 14px;
        width: 80%;
        height: 50px;
        margin-left: 30px;
        /* background-color: aqua; */
    }
    .input{
        width: 100%;
        height: 30px;
        border: none;
        padding-left: 8px;
        outline: none;
    }
    .tex{
        width: 100%;
        height: 64px;
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
        margin-right: 100px;
        margin-top: 10px;
        width:75px;
        height: 40px;
        background-color: #409EFF;
        border: none;
        color: white;
        font-size:16px;
        border-radius: 5px;
    }
</style>