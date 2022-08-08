<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/top.jsp" %>
<br/>
<br/>
<h2>Q&A 삭제</h2><br/>
<%	
	sid = (String)session.getAttribute("sid");
	int qnaNum = Integer.parseInt(request.getParameter("qnaNum"));
%>    
<form action="/ProTwo/qna/deletePro.jsp" method="post">
	<h4>비밀번호를 입력해주세요</h4><br/>
	<input type="hidden" name="qnaNum" value="<%=qnaNum %>"/>
	<input type="hidden" name="sid" value="<%=sid%>"/>
	<input type="password" name="pw"/><br/>
	<input type="submit" value="확인"/><br/>
</form>
<br/>
<br/>	
<%@ include file="/footer.jsp" %>	