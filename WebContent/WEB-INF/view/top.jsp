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
	<% ArrayList<Comment> ac = (ArrayList<Comment>)session.getAttribute("ac");
	String edit = (String)session.getAttribute("edit");%>
	<a href="/BBS/TopServlet?edit=true"><button>編集画面へ</button></a>
	<form action="/BBS/TopServlet" method="post">
		投稿者<br><input type="text" name="name" value="名無しさん">
		email:<input type="email" name="email"><br>
		内容<br><textarea name="comment"></textarea><br>
		<input type="submit" value="投稿">
	</form>

	<%for(Comment c : ac){ %>
		<%=c.getId()+ " : " + c.getName() + " : " + c.getdateTime()%>
		<%if(edit != null && edit.equals("true")){ %>
			<a href="/BBS/EditServlet?eid=<%=c.getId() %>"><button>編集</button></a>
			<a href="/BBS/EditServlet?did=<%=c.getId() %>"><button>削除</button></a>
		<%} %>
		<br>
		<%=c.getComment() %><br>
		<br>
	<%} %>

</body>
</html>