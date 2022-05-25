<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "Survery.*, java.util.*" %>
<% 
String age = request.getParameter("age");
infoDao id = infoDao.getInstance();
List<infoDto> ls = id.ageInfo(age);
int number = id.countAgeInfo(age);
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h2>목록</h2>
		<%if(age.equals("0")){%>
			<h3>10대 미만 결과</h3>
		<%}else if(age.equals("100")){ %>
			<h3>100세 이상 결과</h3>
		<%}else{%>
		<h3><%= age %>대 결과</h3>
		<%}%>
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