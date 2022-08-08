<%@page import="bid.P02_bidDTO"%>
<%@page import="auction.P02_auctionDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/top.jsp" %>
<br/>
<br/>
<h2>경매 입찰</h2>    
<%
	sid = (String)session.getAttribute("sid");
	if(sid == null){
%>
	<script>
		alert("로그인 후 사용가능합니다.");
		window.location="/ProTwo/account/loginForm.jsp";
	</script>
<% }
	int aucNum = Integer.parseInt(request.getParameter("aucNum"));
	P02_auctionDAO dao = new P02_auctionDAO();
	P02_bidDTO dto = dao.confirmProduct(aucNum);
%>
<h2>입찰하기</h2>
<form action="/ProTwo/auction/bidPro.jsp" method="post">
		<input type="hidden" name="aucNum" value="<%=aucNum%>"/><br/>
	금액	<input type="number" name="price" value=<%=dto.getPrice() %> /><br/>
 		<input type="submit" value="입찰" />
</form>
<br/>
<br/>	
<%@ include file="/footer.jsp" %>	
<!-- 입찰을 위한 폼 -->