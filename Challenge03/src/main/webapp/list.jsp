<%@page import="java.util.ArrayList"%>
<%@page import="model.Human"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<p>・ユーザー一覧画面</p>
	<a href="input.html">戻る</a>

	<p>現在のメンバー</p>
	<%
	ArrayList<Human> list = (ArrayList<Human>) request.getAttribute("list");

	for (int i = 0; i < list.size(); i++) {
		Human human = list.get(i);
	%>
	<p><%=(i + 1)%>人目
	</p>
	<p>
		氏名：<%=human.getName()%></p>
	<p>
		年齢：<%=human.getAge()%></p>
	<p>
		所属グループ：<%=human.getGroup()%></p>
	<%
}
%>
</body>
</html>