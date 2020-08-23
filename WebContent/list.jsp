<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>

<%@ page import = "model.Message" %>
<%@ page import = "model.MessageListView" %>
<%@ page import = "service.GetMessageListService" %> 
    
<!DOCTYPE html>

<%
	String pageNumberStr = request.getParameter("page");
	int pageNumber = 1;
	if(pageNumberStr != null)
		pageNumber = Integer.parseInt(pageNumberStr);
	
	GetMessageListService messageListService = 
			GetMessageListService.getInstance();
	MessageListView viewData = 
			messageListService.getMessageList(pageNumber);
%>

<html>
<head>
<meta charset="EUC-KR">
<title>방명록 메시지 목록</title>
</head>
<body>
	<form action = "writeMessage.jsp" method="post">	<br />
		이름 : <input type="text" name="guestName">	<br />
		암호 : <input type="password" name="password">	<br />
		메시지: <textarea name="message" rows="3" cols="30"></textarea>	<br />
		<input type ="submit" value="메시지넘기기" />
	</form>
	
	<hr>
	
	<%
		if(viewData.isEmpty()) { 
			out.print("등록된 메시지가 없습니다.");
		}
		else{
	%>		
			<table border ="1">
		<%
			for(Message message : viewData.getMessageList())
			{
		%>	
				<tr>
					<td>
						메시지 번호 : <%= message.getId() %> <br />
						손님 이름 : <%= message.getGuestName() %> <br />
						메시지 : <%= message.getMessage() %> <br />
						<a href="confirmDelection.jsp?messageId=<%=message.getId()%>">
							삭제하기
						</a>
					</td>
				</tr>
		<%	
			} 
		%>
			
			</table>
		<% for(int i = 1; i <= viewData.getPageTotalCount() ; i++) { %>
			
			<a href = "list.jsp?page=<%=i %>">[<%= i %>]</a>	
		
	<% 
			}
		}
	%>
	
	
</body>
</html>