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
<html >
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="keywords"  content = " 电影 java jsp"/>
    <meta http-equiv="author" content="Bronya"/>
    <link rel="stylesheet" type="text/css" href="./Style/skin.css" />
    <script src="Js/jquery-3.3.1.min.js"></script>
   <script language="JavaScript">
         $(function(){
             //为每个radio绑定事件
             $(":radio").each(function(index,element){
                  $(this).click(function(){
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
            <form action="user.let?type=add" method="post">
                <table valign="top" width="90%" style="margin-left: 150px">
                    <h4 style="letter-spacing:1px;font-size:23px;margin: 20px 10px; color: rgb(119, 118, 118);">注册</h4>
                    <!-- <tr class="lab"><td>用户名：</td>
                        <td><input class="input"  placeholder="请输入用户名" type="text" name="name" value="" required/></td>
                        <td class="err">长度6~15个字符</td>
                    </tr> -->

                    <tr class="lab"><td>密&nbsp;&nbsp;&nbsp;&nbsp;码：</td>
                        <td><input class="input" placeholder="请输入密码" type="password" name="pwd" value="" required/></td>
                        <td class="err">必须包含字母和数字，长度6~15位</td>
                    </tr>
                    <tr class="lab"><td>确认密码：</td>
                        <td><input class="input" placeholder="确认密码" type="password" name="pwd2" value="" required/></td>
                        <td class="err">两次密码必须一致</td>
                    </tr>

                    <tr class="lab"><td>用户类型：</td>
                        <td>
                            <label for="type"></label>
                            <select class="input" id="type">
                                <option value="0">个人</option>
                                <option value="1">企业</option>
                            </select>
                        </td>
                    </tr>

                    <!-- <tr class="lab"><td>电话号码：</td>
                        <td><input class="input" placeholder="请输入电话号码" type="tel" name="tel" required/></td>

                    </tr>
                    <tr class="lab"><td>身份证号：</td>
                        <td><input class="input" placeholder="请输入身份证号" type="text" name="idNumber" required/></td>
                    </tr>
 -->

                </table>
                <div style="margin-top: 70px; background-color: #e0e8ef;">
                    <td><input class="btlg" type="submit" value="提交" /></td>
                </div>
            </form>
        </div>
    </div>


</body>
<style>
    .body{
        background: url("./Images/movie.jpeg");
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
        height: 600px;
        /*background-color: rgba(255, 255, 255, 0.789);*/
        /*margin-left: 500px;*/
        margin-top: 15%;
        text-align: center;
        /*border-radius: 10px;*/
        /*backdrop-filter: blur(1px);*/
    }
    .lab{
        color: dimgray;
        font-size: 12px;
        width: 100%;
        height: 50px;
        margin-left: 20px;
        /* background-color: aqua; */
    }
    .input{
        width: 80%;
        height: 30px;
        border: none;
        padding-left: 8px;
        outline: none;
    }
    .btlg{
        float:right;
        margin-right: 100px;
        margin-top: 100px;
        width:82px;
        height: 40px;
        background-color: #409EFF;
        border: none;
        color: white;
        font-size:18px;
        border-radius: 5px;
    }


</style>
</html>