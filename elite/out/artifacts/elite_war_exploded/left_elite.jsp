<%--
  Created by IntelliJ IDEA.
  User: 人才招聘
  Date: 2022/12/21
  Time: 11:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html >
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="keywords"  content = " 人才招聘 java jsp"/>
    <meta http-equiv="author" content="Bronya"/>
    <script src="./Js/prototype.lite.js" type="text/javascript"></script>
    <script src="./Js/moo.fx.js" type="text/javascript"></script>
    <script src="./Js/moo.fx.pack.js" type="text/javascript"></script>
    <link rel="stylesheet" type="text/css" href="./Style/skin.css" />
    <script type="text/javascript">
        window.onload = function () {
            var contents = document.getElementsByClassName('content');
            var toggles = document.getElementsByClassName('type');

            var myAccordion = new fx.Accordion(
            toggles, contents, {opacity: true, duration: 400}
            );
            myAccordion.showThisHideOpen(contents[0]);
            for(var i=0; i<document .getElementsByTagName("a").length; i++){
                var dl_a = document.getElementsByTagName("a")[i];
                    dl_a.onfocus=function(){
                        this.blur();
                    }
            }
        }
    </script>
</head>

<body>
    <table width="100%" height="200" border="0" cellpadding="0" cellspacing="0" class="le">
        <tr>
            <td width="182" valign="top">
                <div id="container">
 <!-- *********** -->
                    <h1 class="type"><a href="javascript:void(0)">个人中心</a></h1>
                    <div class="content">
                        <ul class="RM">
                            <li><a href="./set_pwd_new.jsp" target="main">修改密码</a></li>
                            <li><a href="./elite.let?type=details" target="main">个人信息</a></li>
                        </ul>
                    </div>
                    <!-- *********** -->
                    <h1 class="type"><a href="javascript:void(0)">求职中心</a></h1>
                    <div class="content">
                        <ul class="RM">
                            <li><a href="jobs_search.jsp" target="main">工作查找</a></li>
                            <li><a href="./comp.let?type=query&pageIndex=1" target="main">公司查看</a></li>
                            <li><a href="./erecord.let?type=queryelite&pageIndex=1" target="main">简历投递情况</a></li>
                        </ul>
                    </div>

                    <!-- *********** --
                    <div class="content">
                        <table width="100%" border="0" cellspacing="0" cellpadding="0">
                            <tr>
                                <td><img src="./Images/menu_top_line.gif" width="182" height="5" /></td>
                            </tr>
                        </table>
                        <ul class="RM">
                            <li><a href="javascript:void(0)" target="main">友情连接</a></li>
                            <li><a href="javascript:void(0)" target="main">在线留言</a></li>
                            <li><a href="javascript:void(0)" target="main">网站投票</a></li>
                            <li><a href="javascript:void(0)" target="main">邮箱设置</a></li>
                            <li><a href="javascript:void(0)" target="main">图片上传</a></li>
                        </ul>
                    </div>
                    <!-- *********** -->
                </div>
            </td>
        </tr>
    </table>
</body>
</html>
<style>
.le{
    background-color: rgb(255, 255, 255);
}
.type{
margin-top: 15px;
margin-bottom: 15px;
font-weight:normal;
}
.content{
    margin-top: 15px;
    margin-bottom: 15px;
    font-size: 15px;
    width:210px;
    height:30px;
    background-color: #ececec;
}
.content{width:182px;height:26px;}
.RM ul {list-style-type:none;display:block; margin-top: 15px;
margin-bottom: 15px;}
.RM li {
    line-height:26px;
    color:#333333;
    height:30px;
    width:200px;
    margin-top: 20px;
    margin-bottom: 15px;
}
.RM {width:182px;left:0px;top:0px;clip:rect(0px,0px,0px,0px);}
.RM a:link,.RM a:visited {line-height:26px;color:#333333;background:url(../images/menu_bg1.gif) no-repeat;height:26px;width:182px;display:block;text-align:center;overflow:hidden;}
.RM a:hover {font-weight:bold;color:#409EFF; }

</style>