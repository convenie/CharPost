<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.UserBean,model.AccountInfoBean,model.Mutter,java.util.List" %>
<%
UserBean loginUser=(UserBean)session.getAttribute("loginUser");
AccountInfoBean accountinfo = (AccountInfoBean)session.getAttribute("accountinfo");
System.out.println(accountinfo.getName()+":aijsp");
%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title><%= loginUser.getName() %></title>
    <link rel="stylesheet" type="text/css" href="css/account.css">
    <script type="text/javascript" src="js/jquery-3.6.0.min.js"></script>
    <script type="text/javascript" src="js/accountinfochange.js"></script>
  </head>
  <body>


    <div class="menu">
      <a href="/CharPost/Main?action=main"><img src="img/mainicon.png"></a>
      <a href="/CharPost/Posting" class="hover"><img src="img/homeicon.png"></a>
      <a href="/CharPost/Account" class="hover"><img src="img/humanicon.png"></a>
      <a href="/CharPost/CardSearch" class="hover"><img src="img/searchicon.png"></a>
      <a href="/CharPost/Logout" class="hover"><img src="img/exiticon.png"></a>
    </div>
    <div class="profile">
    <h3><%= loginUser.getName() %></h3>

    <form method="post" action="AccountInfoChange">
	    <label class="Form-Item-Label">
	    誕生日：
	    <select name="birthmonth">
	    <option value="" disabled>誕生月</option>
		<option value="1">１月</option>
		<option value="2">２月</option>
		<option value="3">３月</option>
		<option value="4">４月</option>
		<option value="5">５月</option>
		<option value="6">６月</option>
		<option value="7">７月</option>
		<option value="8">８月</option>
		<option value="9">９月</option>
		<option value="10">１０月</option>
		<option value="11">１１月</option>
		<option value="12">１２月</option>
		</select>
		</label>
		<label class="Form-Item-Label2">
	    <select name="birthday">
	    <option value="" disabled>誕生日</option>
		<option value="1">１日</option>
		<option value="2">２日</option>
		<option value="3">３日</option>
		<option value="4">４日</option>
		<option value="5">５日</option>
		<option value="6">６日</option>
		<option value="7">７日</option>
		<option value="8">８日</option>
		<option value="9">９日</option>
		<option value="10">１０日</option>
		<option value="11">１１日</option>
		<option value="12">１２日</option>
		<option value="13">１３日</option>
		<option value="14">１４日</option>
		<option value="15">１５日</option>
		<option value="16">１６日</option>
		<option value="17">１７日</option>
		<option value="18">１８日</option>
		<option value="19">１９日</option>
		<option value="20">２０日</option>
		<option value="21">２１日</option>
		<option value="22">２２日</option>
		<option value="23">２３日</option>
		<option value="24">２４日</option>
		<option value="25">２５日</option>
		<option value="26">２６日</option>
		<option value="27">２７日</option>
		<option value="28">２８日</option>
		<option value="29">２９日</option>
		<option value="30">３０日</option>
		<option value="31">３１日</option>
		</select>
		</label>
		<label class="Form-Item">
		<input class="in" name=comment type="text" class="Form-Item-Input" placeholder="Comment" value="
<%if(accountinfo.getComment()!=null){ %>
<%= accountinfo.getComment() %>
<%}else{ %>
よろしくお願いします。
<%} %>">
		</label>
		<div>
		<input class="button" type="submit" value="変更"><br>
		</div>
	</form>
	</div>
  </body>
</html>
