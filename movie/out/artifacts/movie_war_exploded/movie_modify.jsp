<%--
  Created by IntelliJ IDEA.
  User: 电影
  Date: 2022/12/21
  Time: 11:58
  To change this template use File | Settings | File Templates.
--%><%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html >
<html >
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="keywords"  content = " 电影 java jsp"/>
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
            $("#filePic").change(function(){
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
                    <img src="./Images/left_top_right.gif" width="17" height="29" />
                </td>
                <td valign="top" background="./Images/content_bg.gif">
                    
                </td>
                <td width="16" valign="top" background="./Images/mail_right_bg.gif"><img src="./Images/nav_right_bg.gif" width="16" height="29" /></td>
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
                                        <td width="100" align="center"><img src="./Images/mime.gif" /></td>
                                        <td valign="bottom"><h3 style="letter-spacing:1px;">电影管理 > 修改电影</h3></td>
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
                                            <form action="movie.let?type=modify" method="post" enctype="multipart/form-data">
                                                <table width="100%" class="cont">
                                                    <tr>
                                                        <td width="2%">&nbsp;</td>
                                                        <td>编号：</td>
                                                        <td width="20%"><input class="text" type="text" name="id" value="${movie.id}" readonly/></td>
                                                        <td rowspan="8" valign="top" >
                                                            <fieldset style="width: 210px; height: 370px;">
                                                             <legend>图片预览</legend>
                                                             <img id="imgPic" src="${movie.pic}" width="200px" height="350px"/>
                                                            </fieldset>
                                                        </td>
                                                        <td width="2%">&nbsp;</td>
                                                    </tr>
                                                    <tr>
                                                        <td width="2%">&nbsp;</td>
                                                        <td width="15%">名称：</td>
                                                        <td width="25%"><input class="text" type="text" name="name" value="${movie.name}" /></td>
                                                       
                                                        <td width="2%">&nbsp;</td>
                                                    </tr>
                                                    <tr>
                                                        <td width="2%">&nbsp;</td>
                                                        <td>类型：</td>
                                                        <td width="20%">
                                                            <select id="movie_type"  name="typeId">
                                                               <option value="0">---请选择---</option>
                                                                <c:forEach items="${types}" var="t">
                                                                    <c:if test="${movie.typeId== t.id}">
                                                                        <option value="${t.id}" selected>${t.name}</option>
                                                                    </c:if>
                                                                    <c:if test="${movie.typeId!=t.id}">
                                                                        <option value="${t.id}" >${t.name}</option>
                                                                    </c:if>

                                                                </c:forEach>
                                                            </select>
                                                        </td>
                                                       
                                                        <td width="2%">&nbsp;</td>
                                                    </tr>
                                                    <tr>
                                                        <td width="2%">&nbsp;</td>
                                                        <td>图片：</td>
                                                        <!--
                                                          1.用户不需要修改封面(不会选择图片) filePic.value==>后台 文件名:"" 数据库中应该保存之前的图片路径
                                                          2.用户需要修改封面，文件上传(保存文件)
                                                        -->
                                                        <td width="20%">
                                                            <!--保存之前的图片路径  movie.pic -->
                                                          <input type="hidden" name="pic"  value="${movie.pic}">
                                                            <input type="file" id="filePic" name="filePic"  value="${movie.pic}"/>
                                                        </td>
                                                        <td width="2%">&nbsp;</td>
                                                    </tr>
                                                    <tr>
                                                        <td width="2%">&nbsp;</td>
                                                        <td>余票量：</td>
                                                        <td width="20%"><input class="text" style="width:50px;" type="number" name="stock" value="${movie.stock}" /></td>
                                                        
                                                        <td width="2%">&nbsp;</td>
                                                    </tr>
                                                    <tr>
                                                        <td width="2%">&nbsp;</td>
                                                        <td>单价：</td>
                                                        <td width="20%"><input class="text" style="width:100px;" type="text" name="price" value="${movie.price}" /></td>
                                                        
                                                        <td width="2%">&nbsp;</td>
                                                    </tr>
                                                    <tr>
                                                        <td width="2%">&nbsp;</td>
                                                        <td>导演：</td>
                                                        <td width="20%"><input class="text"  type="text" name="publish" value="${movie.publish}"/></td>
                                                        <td></td>
                                                        <td width="2%">&nbsp;</td>
                                                    </tr>
                                                    <tr>
                                                        <td width="2%">&nbsp;</td>
                                                        <td>主演:</td>
                                                        <td width="20%"><input class="text"  type="text" name="author" value="${movie.author}"/></td>
                                                        <td></td>
                                                        <td width="2%">&nbsp;</td>
                                                    </tr>
                                                    <tr>
                                                        <td width="2%">&nbsp;</td>
                                                        <td>影厅号:</td>
                                                        <td width="20%"><input class="text"  type="text" name="address"  value="${movie.address}"/></td>
                                                        <td></td>
                                                        <td width="2%">&nbsp;</td>
                                                    </tr>
                                                    <tr>
                                                        <td width="2%">&nbsp;</td>
                                                        <td>上映日期:</td>
                                                        <td width="20%"><input class="text"  type="text" name="date" value="${movie.date}"/></td>
                                                        <td></td>
                                                        <td width="2%">&nbsp;</td>
                                                    </tr>
                                                    <tr>
                                                        <td>&nbsp;</td>
                                                        <td>简介：</td>
                                                        <td colspan="2"><textarea cols="150" rows="20" name="desc">${movie.desc}</textarea></td>
                                                        <td></td>
                                                    </tr>
                                                    <tr>
                                                        <td>&nbsp;</td>
                                                        <td></td>
                                                        <td colspan="3"><input class="btn" type="submit" value="提交" /></td>
                                                    </tr>
                                                </table>
                                            </form>
                                        </td>
                                    </tr>
                                </table>
                            </td>
                            <td width="2%">&nbsp;</td>
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