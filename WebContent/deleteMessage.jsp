<%@page import="service.ServiceException"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
<%@ page import="service.DeleteMessageService" %>
    
   
<!DOCTYPE html>

<%
	int messageId = Integer.parseInt(request.getParameter("messageId"));
	String password = request.getParameter("password");
	boolean invalidPassword=false;
	try{
		DeleteMessageService deleteService = 
				DeleteMessageService.getInstance();
		deleteService.delete(messageId, password);
	}catch(ServiceException ex){
		invalidPassword = true;
	}
%>

<html>
<head>
<meta charset="EUC-KR">
<title>방명록 메시지 삭제함</title>
</head>
<body>
	<% 
		if(!invalidPassword)
			out.println("메시지지를 삭제하였습니다.");
		else
			out.println("암호가 올바르지 않습니다. 암호를 확인해주십시오");
	%>
	<a href="list.jsp">[목록 보기]</a>
</body>
</html>