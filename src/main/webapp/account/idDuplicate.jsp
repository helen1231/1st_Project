<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="dto" class="account.P02_accountDTO" />
<jsp:setProperty property="*" name="dto"/>
<jsp:useBean id="dao" class="account.P02_accountDAO" />
<h2>아이디 중복확인</h2>
<%
	String id = request.getParameter("id");
	int result = dao.idDuplicate(id);

	if(result != 0){ 
%>		이미 사용중인 아이디입니다.
<% } else { %>
		사용가능한 아이디입니다.
<% } %>	
<br />
<input type="button" value="닫기" onclick='window.close()' />