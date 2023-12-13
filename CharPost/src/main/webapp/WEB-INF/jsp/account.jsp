<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.UserBean,model.AccountInfoBean,model.Mutter,java.util.List" %>
<%
UserBean loginUser=(UserBean)session.getAttribute("loginUser");
AccountInfoBean accountinfo = (AccountInfoBean)session.getAttribute("accountinfo");
%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title><%= loginUser.getName() %></title>
    <link rel="stylesheet" type="text/css" href="css/account.css">
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
    <p>誕生日：
    <%if(accountinfo.getBirthmonth()!=null){ %>
    <%= accountinfo.getBirthmonth() %>
    <%}else{ %>
    ?
    <%} %>
    月
    <%if(accountinfo.getBirthday()!=null){ %>
    <%= accountinfo.getBirthday() %>
    <%}else{ %>
    ?
    <%} %>
    日</p>
    <p>ひとこと：
    <%if(accountinfo.getComment()!=null){ %>
    <%= accountinfo.getComment() %></p>
    <%}else{ %>
    コメントを入力してね★
    <%} %>
    <form action="Account" method="post">
	<input class="button" type="submit" value="アカウント情報の変更"><br>
	</form>
	<br>
	</div>
  </body>
</html>
