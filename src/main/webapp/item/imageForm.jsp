<%@  page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/manager/top.jsp" %>
<br/>
<br/>
<h2>상품이미지 변경</h2>
<%
	String admin = (String)session.getAttribute("admin");
	if(admin == null){
%>
	<script>
		alert("관리자 로그인 후 사용가능합니다.");
		window.location="/ProTwo/account/loginForm.jsp";
	</script>
<% } 
	int itemNum = Integer.parseInt(request.getParameter("itemNum"));	%>

<form action="/ProTwo/item/imagePro.jsp" method="post" enctype="multipart/form-data">
	<input type="hidden" name="itemNum" value="<%=itemNum %>" />
	<input type="file" name="img" /><br />
	<input type="submit" value="확인" />
</form>
<br/>
<br/>	
<%@ include file="/footer.jsp" %>	