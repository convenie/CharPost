<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="model.AccountInfoBean" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>名刺検索画面</title>
<link rel="stylesheet" href="css/cardsearch.css">
</head>
<body>
    <div class="menu">
      <a href="/CharPost/Main?action=main"><img src="img/mainicon.png"></a>
      <a href="/CharPost/Posting" class="hover"><img src="img/homeicon.png"></a>
      <a href="/CharPost/Account" class="hover"><img src="img/humanicon.png"></a>
      <a href="/CharPost/CardSearch" class="hover"><img src="img/searchicon.png"></a>
      <a href="/CharPost/Logout" class="hover"><img src="img/exiticon.png"></a>
    </div>
<form action="/CharPost/CardSearch" method="post" class="form-wrapper cf">
    <label>
    	<input type="text" name="othername" placeholder="名前,コメント">
    </label>
    <button type="submit">検索</button>
</form>
<%
	ArrayList<AccountInfoBean> cardDatalist = (ArrayList<AccountInfoBean>)request.getAttribute("user_list");

	if (cardDatalist == null || cardDatalist.size() == 0){
%>
	<br>
	<div class="businessCard">
	<p>　検索結果<br>　　なし</p>
	</div>
	<br>
<%	} else { %>
<%		for(int i = 0; i < cardDatalist.size(); i++){ %>
	    <div class="profile">
	    <h3><%= cardDatalist.get(i).getName() %></h3>
	    <p>誕生日：<%= cardDatalist.get(i).getBirthmonth() %>月<%= cardDatalist.get(i).getBirthday() %>日</p>
	    <p>ひとこと：<%= cardDatalist.get(i).getComment() %></p>
		</div>
		<% } %>
	<% } %>

</body>
</html>