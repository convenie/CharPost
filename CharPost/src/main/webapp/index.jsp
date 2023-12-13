<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="css/login.css">
<title>ログイン画面</title>
</head>
<body>
<div class="accr">
<!-- <input type=button class="Form-Btn" value="戻る" onclick="history.back();"> -->
<p>ログイン画面</p>
<form action="Login" method="post">
<input class="in" type="text" name="user_name" placeholder="アカウント名"><br>
<input class="in" type="password" name="password" placeholder="password"><br><br>
<input class="button" type="submit" value="ログイン"><br>
</form>
</div>
</body>
</html>