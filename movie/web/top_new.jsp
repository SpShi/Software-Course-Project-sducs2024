<%--
  Created by IntelliJ IDEA.
  User: 电影
  Date: 2022/12/21
  Time: 11:58
  To change this template use File | Settings | File Templates.
--%><%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="keywords"  content = " 电影 java jsp"/>
    <meta http-equiv="author" content="Bronya"/>
    <!-- <link rel="stylesheet" type="text/css" href="./Style/skin.css" /> -->
    <script type="text/javascript">
        function logout() {
            if(window.confirm('您确定要退出吗？')) {
                top.location = 'login.html';
            }
        }       
    </script>
</head>
    <body>
        <div class="top" cellpadding="0">
                <div class="usr"> 当前用户:&nbsp;&nbsp;
                    <b>${user_now.id}</b>&nbsp;
                </div>
                <div class="outbox">
                    <a class="out"
                     onclick="return confirm('确认退出');" href="user.let?type=exit">安全退出</a>
                </div>      
        </div>
    </body>
</html>
<style>

.top{
    width:100%; 
    height:64px;
    background-color: #409EFF;
    /* color: #409EFF; */
}
.usr{
    float: left;
    padding-top:20px;
    padding-left:  20px;
    font:20px Arial,SimSun,sans-serif;
    color:#FFF
}
.out{
   
    float: right;
    /* padding-top:-5px; */
    padding-right: 10px;
    padding-left: 15px;
    font-size: 20px;
    text-decoration-line: none;
    color: #FFF;
    padding-top:20px;
}
.outbox{
    float: right;
    padding-right: 20px;
    width: 110px;
    height: 100%;
    /* background-color: palegoldenrod; */
    text-align: center;
}
.outbox:hover{
  opacity: 0.2;
  background-color:#6b6c6d;
  /* color: black; */
}

</style>