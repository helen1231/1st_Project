<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/manager/top.jsp" %>
<br/>
<br/>
<h2>경매물품 등록</h2>
<%
	String admin = (String)session.getAttribute("admin");
	if(admin == null){
%>
	<script>
		alert("관리자 로그인 후 사용가능합니다.");
		window.location="/ProTwo/account/loginForm.jsp";
	</script>
<% } %>

<form action="/ProTwo/auction/insertPro.jsp" method="post" enctype="multipart/form-data">
	경매품	<input type="text" name="aucName" />		<br/>	
	사진		<input type="file" name="aucImage"/>	<br/>
	설명		<textarea rows="7" cols="25" name="aucContent"></textarea><br/>
			<input type="submit" value="등록"/>
</form>
<br/>
<br/>	
<%@ include file="/footer.jsp" %>	
<!-- 상품 등록 폼 -->