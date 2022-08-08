<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="dto" class="account.P02_accountDTO" />
<jsp:setProperty property="*" name="dto"/>
<jsp:useBean id="dao" class="account.P02_accountDAO" />
<%
	int result = dao.accountUpdate(dto);
	
	if(result == 1){
%> 	<script>
		alert("회원정보가 수정되었습니다.");
		window.location="/ProTwo/account/main.jsp";
	</script>		
<% 	} else { 
%>
	<script>
		alert("비밀번호를 다시 입력해주세요.");
		history.go(-1);
	</script>		
<% } %>