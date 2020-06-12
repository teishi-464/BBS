<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="bean.Comment" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<% ArrayList<Comment> ac = (ArrayList<Comment>)session.getAttribute("ac"); %>
	<div>編集画面</div>
	<form action="/BBS/EditServlet" method="post">
		投稿者<br><input type="text" name="name" value="名無しさん">
		email:<input type="email" name="email"><br>
		内容<br><textarea name="comment"></textarea><br>
		<input type="submit" value="投稿">
	</form>

		<br>

</body>
</html>