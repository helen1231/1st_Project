<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@	page import="account.P02_accountDAO"%>
<%
	String id = (String)session.getAttribute("sid");
	if(session.getAttribute("sid") == null){
%>
	<script>
		alert("로그인 후 사용가능합니다.");
		window.location="/ProTwo/account/loginForm.jsp";
	</script>
<% }
	int itemNum = Integer.parseInt(request.getParameter("itemNum"));
	P02_accountDAO dao = new P02_accountDAO();
	int result = dao.cartDelete(id, itemNum);
	if(result == 1){
%>
	<script>
		alert("변경되었습니다");
		window.location="/ProTwo/myPage/cartList.jsp";
	</script>
<%		
	}else{
%>
	<script>
		alert("관리자에게 문의해주세요");
		history.go(-1);
	</script>
<%	} %>
