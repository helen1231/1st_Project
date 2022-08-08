<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="auction.P02_auctionDAO"%>
<%@ page import="bid.P02_bidDTO" %>
<%
	String admin = (String)session.getAttribute("admin");
	if(admin == null){
%>
	<script>
		alert("관리자 로그인 후 사용가능합니다.");
		window.location="/ProTwo/account/loginForm.jsp";
	</script>
<% } 
	int aucNum = Integer.parseInt(request.getParameter("aucNum"));
	P02_auctionDAO dao = new P02_auctionDAO();
	
	int change = dao.endAuction(aucNum);
	
	P02_bidDTO dto = dao.confirmProduct(aucNum);
	String id = dto.getId();
	int price = dto.getPrice();
	
	int result = dao.resultAuction(aucNum, id, price);
%>
	<script>
		alert("정산되었습니다");
		window.location="/ProTwo/auction/auction.jsp";
	</script>
<!-- 버튼 누르면 경매 상태 변화 및 기록에 추가 -->	