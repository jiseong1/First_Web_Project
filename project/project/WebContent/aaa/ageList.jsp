<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나이 선택</title>
</head>
<body>
	<h2>나이 선택</h2>
		<form action="ageListView.jsp" method="get">
			<select name="age">
					<option value="<%=0%>">10대 미만</option>
				<% for(int i = 10; i<100; i+=10){%>
					<option value="<%=i%>"><%= i%>대</option>
				<%
				}
				%>
					<option value="<%=100%>">100세 이상</option>
			</select>	
					<input type="submit" value="확인">
					<input type="button" value="취소" onclick="javascript:window.location='viewForm.jsp'">
		</form>
</body>
</html>