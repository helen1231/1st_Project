<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="account.P02_accountDTO" %>
<%@ page import="account.P02_accountDAO" %>
<a href="/ProTwo/account/main.jsp">홈으로</a>
<br/>
<br/>
<h2>회원정보에 등록한 이름과 전화번호를 입력하세요.</h2>
<form action="/ProTwo/account/idFindPro.jsp" method="get">
	이  름: 	<input type="text" name="name" /> <br />
	전화번호:	<select name="pC">
				<option>SKT</option>
				<option>KT</option>
				<option>LGU+</option>
				<option>알뜰폰</option>
			</select>
			<input type="text" name="phone1" onkeyup="noSpaceForm(this);" onchange="noSpaceForm(this);"/>
			-
			<input type="text" name="phone2" onkeyup="noSpaceForm(this);" onchange="noSpaceForm(this);"/>
			-
			<input type="text" name="phone3" onkeyup="noSpaceForm(this);" onchange="noSpaceForm(this);"/><br/>
			<input type="submit" value="아이디 찾기" />
</form>
<br/>
<br/>	
<%@ include file="/footer.jsp" %>	