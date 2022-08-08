<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<br/>
<br/>
<h2>관리자 로그인</h2>
<form method="post" action="/ProTwo/manager/managerLogin_ok.jsp">
	관리자 아이디	<input type="text" name="adminId" /><br/>
	관리자 비밀번호	<input type="password" name="adminPw" /><br/>
				<input type="submit" value="로그인" />
</form>
<br/>
<br/>	
<%@ include file="/footer.jsp" %>		