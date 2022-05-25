<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "Survery.*, java.util.*" %>
<%
infoDao id = infoDao.getInstance();
List<infoDto> ls = id.allInfo();
int number = id.countInfo();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>전체 보기</title>
</head>
<body>
	<h2>목록</h2>
	총 <%= number %>명
	<%
	for(int i=0; i<ls.size(); i++){ 
	%>
		<h4><%= ls.get(i).toString() %></h4>
	<%
	}
	%>
	<a href="viewForm.jsp">뒤로가기</a>
</body>
</html>