<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
	<%--
		<%! code java %> : Khai báo biến
		<% code java %> : Thẻ xử lý logic code
		<%= code java %> : Xuất giá trị của biến ra HTML
	 --%>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Insert title here</title>
	</head>
	<%! int count= 0; %>
	<% count++; %>
	<body>
		<h1>Hello â</h1>
		<h2 style="color:<%= count%2==0?"red":"black" %>;"><%= count %></h2>
	</body>
</html>