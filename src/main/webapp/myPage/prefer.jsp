<%@page import="item.P02_itemDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="account.P02_accountDAO"%>    
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
	P02_itemDAO idao = new P02_itemDAO();
	int update = idao.updateGood(itemNum);
	int result = dao.productCart(id, itemNum);
	if(result == 0){
%>

<%=result %>
	<script>
		alert("이미 넣었습니다");
		history.go(-1);
	</script>
<% }else{
%>
	<script>
		alert("장바구니에 넣었습니다");
		history.go(-1);
	</script>
<% } %>