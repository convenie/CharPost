<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.UserBean,model.PostBean,model.Mutter,java.util.List,java.util.ArrayList" %>
<%
UserBean loginUser=(UserBean)session.getAttribute("loginUser");
PostBean postBean = (PostBean)session.getAttribute("postList");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
	ArrayList<PostBean> cardDatalist = (ArrayList<PostBean>)request.getAttribute("postList");

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
	    <p><%= cardDatalist.get(i).getSyoku() %></p>
	    <p><%= cardDatalist.get(i).getNiku() %></p>
	    <p><%= cardDatalist.get(i).getSanku() %></p>
	    <p><%= cardDatalist.get(i).getName() %>魂の一句</p>
	    <br>
		</div>
	<% } %>
<% } %>
</body>
</html>