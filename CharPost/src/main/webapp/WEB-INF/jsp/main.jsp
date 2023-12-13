<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.UserBean,model.AccountInfoBean,model.PostBean,model.Mutter,java.util.List,java.util.ArrayList" %>
<%
UserBean loginUser=(UserBean)session.getAttribute("loginUser");
PostBean postBean = (PostBean)session.getAttribute("postBean");
%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title>マイページ</title>
    <link rel="stylesheet" type="text/css" href="css/mypage.css">
  </head>
  <body>
  	<h1>Mypage</h1>
    <div class="menu">
      <a href="/CharPost/Main?action=main"><img src="img/mainicon.png"></a>
      <a href="/CharPost/Posting" class="hover"><img src="img/homeicon.png"></a>
      <a href="/CharPost/Account" class="hover"><img src="img/humanicon.png"></a>
      <a href="/CharPost/CardSearch" class="hover"><img src="img/searchicon.png"></a>
      <a href="/CharPost/Logout" class="hover"><img src="img/exiticon.png"></a>
    </div>
	<h1>Welcome <%= loginUser.getName() %>!</h1>
	<p class="font">What is Today's Post?</p>
	<h2>-TimeLine-</h2>
<%
	ArrayList<PostBean> postlist = (ArrayList<PostBean>)request.getAttribute("postList");

	if (postlist == null || postlist.size() == 0){
%>
	<br>
	<div class="businessCard">
	<p>　投稿<br>　　なし</p>
	</div>
	<br>
<%	} else { %>

<%		for(int i =postlist.size()-1; i >=0; i--){ %>
<div class="timeline">
<div class="onehaiku">
<%= postlist.get(i).getName() %>
<%= postlist.get(i).getTime() %>
</div>
<p  style="color: <%= postlist.get(i).getCharcolor() %>; font-family: '<%= postlist.get(i).getCharfont() %>',sans-serif;">
<%= postlist.get(i).getChara() %></p>
<div class="back"> 
<div class="backchild">
<a href="LikeDislike?action=like"><img src="img/like.png"></a><p><%=postlist.get(i).getLike() %></p>
</div>
<div class="backchild">
<a href="LikeDislike?action=dislike"><img src="img/dislike.png"></a><p><%=postlist.get(i).getDislike() %></p>
</div>
</div>
</div>
	<% } %>

<% } %>
  </body>
</html>

