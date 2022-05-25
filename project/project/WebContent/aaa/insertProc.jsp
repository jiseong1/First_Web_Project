<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "Survery.*" %>
<%
infoDao id = infoDao.getInstance();
String rel = (String)request.getParameter("selRel");
int age = Integer.parseInt((String)request.getParameter("selAge"));
id.insertInfo(rel, age);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>정보 입력 결과</title>
</head>
<body>
	<script>
		alert("성공적으로 입력되었습니다.");
		location.href="main.jsp";
	</script>
</body>
</html>