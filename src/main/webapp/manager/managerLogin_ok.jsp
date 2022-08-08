<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="dto" class="manager.P02_managerDTO" />
<jsp:setProperty property="*" name="dto"/>
<jsp:useBean id="dao" class="manager.P02_managerDAO" />
<%
	String adminId = request.getParameter("adminId");
	boolean result = dao.adminLogin(dto);
	if(result){
		session.setAttribute("admin", adminId);
		response.sendRedirect("/ProTwo/manager/managerMain.jsp");
	}else{
%>
		<script>
			alert("id/pw 확인하세요");
			history.go(-1);
		</script>
<% } %>