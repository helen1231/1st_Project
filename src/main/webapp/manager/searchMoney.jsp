<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="s_record.P02_s_recordDTO"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="manager.P02_managerDAO"%>
<%@ include file="top.jsp" %>
<br/>
<br/>
<%
	String admin = (String)session.getAttribute("admin");
	if(admin == null){
%>
<script>
	alert("관리자 로그인 후 사용가능합니다.");
	window.location="/ProTwo/account/loginForm.jsp";
</script>
<% 	}
	String selectDay = request.getParameter("selectDay");
	P02_managerDAO dao = new P02_managerDAO();

	int sumPrice = dao.sumSelectPrice(selectDay);
	int sumProfit = dao.sumSelectProfit(selectDay);
	int sumPoint = dao.sumSelectProfit(selectDay);
%>
	<h2><%=selectDay %>의 매출 내역</h2><br/>
	<table border="1" align="center">
		<tr>
			<td>총거래액</td>
			<td><%=sumPrice + sumProfit - sumPoint %>원</td>
			<td>순이익</td>
			<td><%=sumProfit %>원</td>
		</tr>
	</table><br/><br/>
	<table border="1" align="center">
<%
	ArrayList<P02_s_recordDTO> list = dao.selectList(selectDay);
%>
		<tr>
			<th>거래번호</th>
			<th>아이템번호</th>
			<th>상품번호</th>
			<th>경매번호</th>
			<th>거래형태</th>
			<th>구매자</th>
			<th>판매자</th>
			<th>원가</th>
			<th>이익</th>
			<th>사용된포인트</th>
			<th>실거래액</th>
			<th>거래일자</th>
		</tr>
<%
		for(P02_s_recordDTO dto : list){
%>
		<tr>
		<tr>
			<td><%=dto.getOrderNum() %></td>
			<td><%=dto.getItemNum() %></td>
			<td><%=dto.getPdNum() %></td>
			<td><%=dto.getAucNum() %></td>
<%			if(dto.getPdSell() == 1){
%>
				<td>일반거래</td>
<%			}else if(dto.getPdSell() == 2){
%>
				<td>경매</td>
<%			} %>			
			<td><%=dto.getBuyId() %></td>
			<td><%=dto.getSellId() %></td>
			<td><%=dto.getPrice() %></td>
			<td><%=dto.getProfit() %></td>
			<td><%=dto.getUsepoint() %></td>
			<td><%=dto.getPrice() + dto.getProfit() - dto.getUsepoint() %></td>
			<td><%=dto.getSelReg() %></td>
		</tr>
<%	}	%>
	</table>
<br/>
<br/>	
<%@ include file="/footer.jsp" %>			