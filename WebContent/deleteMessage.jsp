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
<title>���� �޽��� ������</title>
</head>
<body>
	<% 
		if(!invalidPassword)
			out.println("�޽������� �����Ͽ����ϴ�.");
		else
			out.println("��ȣ�� �ùٸ��� �ʽ��ϴ�. ��ȣ�� Ȯ�����ֽʽÿ�");
	%>
	<a href="list.jsp">[��� ����]</a>
</body>
</html>