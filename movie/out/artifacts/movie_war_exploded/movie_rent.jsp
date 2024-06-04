<%--
  Created by IntelliJ IDEA.
  User: 电影
  Date: 2022/12/21
  Time: 11:58
  To change this template use File | Settings | File Templates.
--%><%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html >
<html >
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta http-equiv="keywords"  content = " 电影 java jsp"/>
    <meta http-equiv="author" content="Bronya"/>
    <link rel="stylesheet" type="text/css" href="./Style/skin.css" />
    <script src="Js/jquery-3.3.1.min.js"></script>
    <script>
        /**
         * 获取系统的当前时间
         *  2021-02-12
         **/


        $(function(){


            $("#btnQueryMovie").prop("disabled","disabled");
            $("#btnSubmit").prop("disabled","disabled");
            var member = null;
            $("#btnQuery").click(function(){
                //1.获取用户输入的身份证号
                var content = $("#memberId").val();
                //判断为null ,""
                if(!content){
                   alert("请输入用户证件号");
                   return;
                }
                //2.调用js-ajax()/post()/get
                var url="member.let?type=doajax&idn="+content;
                $.get(url,function(data,status){
                    //json字符串
                    console.log(data);
                    //1.json字符串--》json对象
                    member = JSON.parse(data);
                    console.log(member.balance+","+member.type.name+","+member.type.amount+","+member.name);
                    //2.给组件赋值
                    $("#name").val(member.name);
                    $("#type").val(member.type.name);
                    $("#amount").val(member.type.amount);
                    $("#balance").val(member.balance);

                });
                 //查询用户的功能关闭
                $(this).prop("disabled","disabled");
                //开启了查询按钮的功能
                $("#btnQueryMovie").removeAttr("disabled");
            });

            //保存所有添加过的书名
            var movieNameList = new Array();
            $("#btnQueryMovie").click(function(){
                var name=$("#movieContent").val();
                var url = "movie.let?type=doajax&name="+name;
                $.get(url,function(data,status){
                    //{}/一本书的json
                    console.log(data);
                    //特殊情况 {}
                    if(data==="{}"){
                        alert("当前电影不存在，查找失败");
                        $("#movieContent").val("");
                        return;
                    }
                    //添加书名到数组
                    movieNameList.push(name);
                    var movie = JSON.parse(data);
                    /**
                     *<tr align="center" class="d">
                     <td><input type="checkbox" value="1" checked  class="ck" /></td>
                     <td>罗小黑战记</td>
                     <td>2010-10-01</td>
                     <td>2010-10-31</td>
                     <td>北京联合导演</td>
                     <td>东区-01-02</td>
                     <td>39.9</td>
                     </tr>
                     */
                    //1.创建行
                    var tr = $("<tr align=\"center\" class=\"d\">");
                    //2.创建多个列
                    var tdCheck = $("<td><input type=\"checkbox\" value=\""+movie.id+"\" class=\"ck\" checked /></td>");
                    var tdName = $("<td>"+movie.name+"</td>");
                    var tdBuyDate=new Date(movie.date).toLocaleString();
                    var tdPublish = $("<td>"+movie.publish+"</td>");
                    var tdAddress = $("<td>"+movie.address+"</td>");
                    var tdPrice = $("<td>"+movie.price+"</td>");

                    //3.行加列
                    tr.append(tdCheck);
                    tr.append(tdName);
                    tr.append(tdBuyDate);
                    tr.append(tdPublish);
                    tr.append(tdAddress);
                    tr.append(tdPrice);
                    //4.表加行
                    $("#tdMovie").append(tr);
                    $("#movieContent").val("");
                    $("#btnSubmit").removeAttr("disabled");
                });

            });

            //全选功能
            $("#ckAll").click(function(){
                $(".ck").prop("checked",$(this).prop("checked"));
            });
            //完成购买功能
            $("#btnSubmit").click(function(){

                //1.获取用户选择的电影上映日期(多本 1,2,3  1_2_3)
                var ids =  new Array();
                var count = 0;
                $(".ck").each(function () {
                   if($(this).prop("checked")){
                       ids.push($(this).val());
                       count++;
                   }
                });
                if(count===0){
                    alert("请选择电影");
                    return;
                }
                if(count>member.type.amount){
                    alert("购买数量超出范围");
                    return;
                }
                var seat=$("#seat").val();
               //请求servlet http://localhost:8888/mymovie_war_exploded/record.let?type=add&mid=1&ids=5_4_9_10
                location.href="record.let?type=add&seat="+seat+"&mid="+member.id+"&ids="+ids.join("_");
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
                                        <td valign="bottom"><h3 style="letter-spacing:1px;">常用功能 >  电影购票 </h3></td>
                                    </tr>
                                </table>
                            </td>
                        </tr>
                        <!-- 一条线 -->
                        <tr>
                            <td height="20" colspan="4">
                                <table width="100%" height="1" border="0" cellpadding="0" cellspacing="0" bgcolor="#CCCCCC">
                                    <tr><td></td></tr>
                                </table>
                            </td>
                        </tr>
                        <!-- 会员信息开始 -->
                        <tr>
                            <td width="2%">&nbsp;</td>
                            <td width="96%">
                                <fieldset>
                                    <legend>查询用户</legend>
                                    <table width="100%" border="0" class="cont"  >
                                        <tr>
                                            <td width="8%" class="run-right"> 身份证号</td>
                                            <td colspan="7"><input class="text" type="text" id="memberId" name="memberId"/> 
                                                 <input type="button" id="btnQuery" value="确定" style="width: 80px;"/></td>
                           
                                            </td>
                                         
                                        </tr>
                                        <tr>
                                            <td width="8%" class="run-right">用户名称</td>
                                            <td width="17%"><input class="text" type="text" id="name" disabled/></td>
                                            <td width="8%" class="run-right">用户类型:</td>
                                            <td width="17%"><input class="text" type="text" id="type" disabled/></td>
                                            <td width="8%" class="run-right">可购数量</td>
                                            <td width="17%"><input class="text" type="text" id="amount"  disabled/></td>
                                            <td width="8%" class="run-right">账户余额</td>
                                            <td width="17%"><input class="text" type="text" id="balance"  disabled/></td>
                                        </tr>
                                    </table>
                                </fieldset>
                            </td>
                            <td width="2%">&nbsp;</td>
                        </tr>
                      
                        <!--空行-->
                        <tr>
                            <td height="40" colspan="3">
                            </td>
                        </tr>
                        
                        <!-- 电影搜索条-->
                        <tr>
                            <td width="2%">&nbsp;</td>
                            <td width="96%">
                                <fieldset>
                                    <legend>查询电影</legend>
                                    <table width="100%" border="1" class="cont"  >
                                        <tr>
                                            <td colspan="8">
                                               
                                                请输入:&nbsp;&nbsp;<input class="text" type="text" id="movieContent" name="movieContent" placeholder="请输入电影名"/>

                                            </td>
                                            <td>座位号:</td>
                                            <td width="95%"><input class="text"  type="number" id="seat"name="seat"/></td>
                                            <td width="0%">&nbsp;</td>
                                            <input type="button" id="btnQueryMovie" value="确定" style="width: 80px;"/>
                                            <input type="button" id="btnSubmit" value="完成购买" style="width: 80px;"/>
                                            <td width="0%">&nbsp;</td>
                                        </tr>
                                    </table>
                                </fieldset>
                            </td>
                            <td width="2%">&nbsp;</td>
                        </tr>
                        <tr><td height="20" colspan="5"></td></tr>
                        <tr>
                            <td width="2%">&nbsp;</td>
                            <td width="96%">
                                <table width="100%">
                                    <tr>
                                        <td colspan="2">
                                            <form action="" method="">
                                                <table width="100%"  class="cont tr_color" id="tdMovie">
                                                    <tr>
                                                        <th><input type="checkbox" id="ckAll" checked/>全选/全不选</th>
                                                        <th>电影名</th>
                                                        <th>上映时间</th>
                                                        <th>导演</th>
                                                        <th>影厅</th>
                                                        <th>定价(元)</th>
                                                    </tr>


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