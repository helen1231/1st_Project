<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@	page import="manager.P02_managerDAO"%>
<%
	String admin = (String)session.getAttribute("admin");
	if(admin == null){
%>
<script>
	alert("관리자 로그인 후 사용가능합니다.");
	window.location="/ProTwo/account/loginForm.jsp";
</script>
<% 	}
	String id = request.getParameter("id");
	P02_managerDAO dao = new P02_managerDAO();
	int result = dao.deleteUser(id);
	if(result == 1){
%>
	<script>
		alert("회원이 삭제되었습니다");
		window.location="/ProTwo/manager/member_Revise.jsp";
	</script>
<%	}else{
%>
	<script>
		alert("오류가 발생했습니다");
		history.go(-1);
	</script>
<%	}%>