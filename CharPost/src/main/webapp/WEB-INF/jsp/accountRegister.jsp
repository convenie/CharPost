<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!doctype html>
<html lang="ja">
    <head>
        <meta charset="UTF-8">
        <title>新規作成</title>
        <link rel="stylesheet" href="css/newaccount.css">
    </head>
	<main>
		<div class="accr">
        <form method="post" action="Main">
        
        <h1>新規作成</h1>

	    <div class="Form-Item">
	        <label class="Form-Item-Label">氏名</label>
	        <input class="in" name=name type="text" class="Form-Item-Input" placeholder="例）山田太郎">
	    </div>

	    <div class="Form-Item">
	        <label class="Form-Item-Label">パスワード</label>
	        <input class="in" name=password type="text" class="Form-Item-Input" placeholder="例）＊＊＊＊">
	    </div>
	    <div class="Form-Item">
	        <label class="Form-Item-Label">パスワード(確認)</label>
	        <input class="in" name=passwordCheck type="text" class="Form-Item-Input" placeholder="例）＊＊＊＊">
	    </div>
		<div class="create">
		<input type="submit" class="button" value="新規作成">
		</div>

	    
	    </form>
		</div>
    </main>

</html>