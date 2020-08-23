<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import = "model.Message" %>
<%@ page import = "service.WriteMessageService" %> 
    
<!DOCTYPE html>

<%
	request.setCharacterEncoding("euc-kr");
%>

<jsp:useBean id="message" class="model.Message">
	<jsp:setProperty name = "message" property="*" />
</jsp:useBean>

<%
	WriteMessageService writeService = WriteMessageService.getInstance();
	writeService.write(message);
%>

<html>
<head>
<meta charset="EUC-KR">
<title>방명록 메시지 남김</title>
</head>
<body>
	방명록에 메시지를 남겼습니다. <br />
	
	<a href="list.jsp">[목록 보기]</a>
</body>
</html>