<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="account.P02_accountDTO" %>
<%@ page import="account.P02_accountDAO" %>
<a href="/ProTwo/account/main.jsp">Home</a>  
<br/>
<br/>
<h2>가입시 입력했던 아이디, 비밀번호 찾기 질문, 답을 입력해주세요.</h2>
<form action="/ProTwo/account/pwFindPro.jsp" method="post">
	아이디 : <input type="text" name="id" /><br />
	비밀번호 찾기 질문 : <select name="question">
					<option>질문을 선택하세요.</option>
					<option>어머니 성함은?</option>
					<option>초등학교 이름은?</option>
					<option>어렸을적 별명은?</option>
					</select> <br />
	비밀번호 찾기 답 : <input type="text" name="ans" /> <br />
			<input type="submit" value="비밀번호 찾기" />
</form>
<br/>
<br/>	
<%@ include file="/footer.jsp" %>	
