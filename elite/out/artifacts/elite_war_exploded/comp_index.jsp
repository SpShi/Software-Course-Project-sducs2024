<%--
  Created by IntelliJ IDEA.
  User: 29012
  Date: 2024/5/13
  Time: 14:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html >
<html >
<head>
    <title>天命科技 - 网站人才招聘中心</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="description" content="天命科技专业的网站 " />
    <meta name="keywords" content="天命,网站人才招聘系统,企业网站" />
    <meta name="Author" content="phenix" />
    <meta name="CopyRight" content="天命科技" />
</head>
<frameset rows="64,*"  frameborder="no" border="0" framespacing="0">
    <!--头部-->
    <frame src="./top_new.jsp" name="top" noresize="noresize" frameborder="0"  scrolling="no" marginwidth="0" marginheight="0" />
    <!--主体部分-->
    <frameset cols="185,*">
        <!--主体左部分-->
        <frame src="left_comp.jsp" name="left" noresize="noresize" frameborder="0" scrolling="no" marginwidth="0" marginheight="0" />
        <!--主体右部分-->
        <frame src="./comp.let?type=details" name="main" frameborder="0" scrolling="auto" marginwidth="0" marginheight="0" />
    </frameset>
</html>
