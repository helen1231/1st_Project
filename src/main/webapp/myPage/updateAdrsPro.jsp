<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="dto" class="account.P02_accountDTO" />
<jsp:setProperty property="*" name="dto"/>
<jsp:useBean id="dao" class="account.P02_accountDAO" />
<%
	int result = dao.adrsUpdate(dto);
	
	if(result == 1){
%> 	<script>
		alert("주소가 변경되었습니다.");
		window.location="/ProTwo/myPage/updateForm.jsp";
	</script>		
<% 	}%>