<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="Survery.*, java.util.*" %>
<% 
infoDao id = infoDao.getInstance();
List<String> ls = id.getInfo();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<form method="get" action="insertProc.jsp" name="insertForm">
		<table>
			<tr>
				<th colspan="2">정보를 입력해주세요.</th>
			</tr>
			<tr>
				<td>종교 : <input type="text" name="selRel" list="rel" required/>
					<datalist id="rel">
						<%
						for(int i=0; i<ls.size(); i++){ %>
							<option value="<%= ls.get(i).toString() %>" label="<%=ls.get(i).toString()%>">
						<%} %>
					</datalist>
				</td>
			</tr>
			<tr>
				<td>나이 : <input type="text" name="selAge" required/>
				</td>
			</tr>
			<tr>
				<td></td>
				<td><input type="submit" value="확인"/>
					<input type="button" value="취소" onclick="javascript:window.location='main.jsp'"/>
			</td>
		</table>
	</form>
</body>
</html>