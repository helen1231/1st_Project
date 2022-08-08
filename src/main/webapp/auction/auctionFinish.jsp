<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="auction.P02_auctionDTO" %>
<%@ page import="auction.P02_auctionDAO" %>   
<h2>옥션 강제 종료</h2>
<%
	P02_auctionDAO dao = new P02_auctionDAO();
 	int result = dao.finish();
	if(result == 1){
%>
	<script>
		alert("경매가 종료되었습니다");
		window.location='/ProTwo/auction/auction.jsp';
	</script>
	<%}else{%>
	<script>
		alert("경매가 종료되지않았습니다.");
		history.go(-1);
	</script>
<%} %>