<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.UserBean,model.PostBean,model.AccountInfoBean,model.Mutter,java.util.List" %>
<%
UserBean loginUser=(UserBean)session.getAttribute("loginUser");
PostBean postBean =  (PostBean)session.getAttribute("postBean");
%>
<!DOCTYPE html>
<html>
  <head>
    <meta charset="UTF-8">
    <title><%= loginUser.getName() %></title>
    <link rel="stylesheet" type="text/css" href="css/mypage.css">
  </head>
  <body>

  	<h1>-<%= loginUser.getName() %>'s Posting-</h1>
    <div class="menu">
      <a href="/CharPost/Main?action=main"><img src="img/mainicon.png"></a>
      <a href="/CharPost/Posting" class="hover"><img src="img/homeicon.png"></a>
      <a href="/CharPost/Account" class="hover"><img src="img/humanicon.png"></a>
      <a href="/CharPost/CardSearch" class="hover"><img src="img/searchicon.png"></a>
      <a href="/CharPost/Logout" class="hover"><img src="img/exiticon.png"></a>
    </div>
    <div class="profile">
    <p>Input your soul!</p>
    <form method="post" action="Posting">
	   	<div class="Form-Item">
		    <label class="Form-Item-Label">文字</label>
		    <input class="in" name=chara type="text" class="Form-Item-Input" placeholder="例）あ" maxlength="1">
	    </div>
	    <label class="Form-Item-Label">
	    文字色
	    <select name="charcolor">
	    <option value="" disabled>文字色</option>
		<option value="red">赤</option>
		<option value="blue">青</option>
		<option value="yellow">黄</option>
		<option value="purple">紫</option>
		<option value="green">緑</option>
		<option value="black">黒</option>
		<option value="white">白</option>
		<option value="aqua">アクア</option>
		<option value="gray">グレー</option>
		<option value="lime">ライム</option>
		<option value="maroon">マロン</option>
		<option value="orange">オレンジ</option>
		</select>
		</label>
		<label class="Form-Item-Label">
		フォント
	    <select name="charfont">
	    <option value="" disabled>フォント</option>
		<option value="Arial">Arial</option>
		<option value="游明朝">游明朝</option>
		<option value="Meiryo UI">メイリオ</option>
		<option value="Hannari">はんなり明朝</option>
		<option value="Hannotate SC">Hannotate SC</option>
		<option value="HanziPen SC">HanziPen SC</option>
		</select>
		</label>
	    <br>
		<input class="button" type="submit" value="投稿！"><br>

	</form>
	</div>
  </body>
</html>
