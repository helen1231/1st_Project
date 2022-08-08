<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="s_record.P02_s_recordDTO" %>
<jsp:useBean id="dao" class="s_record.P02_s_recordDAO" />
<%@ include file="/top.jsp" %>
<br/>
<br/>
<%
	String id = (String)session.getAttribute("sid");
	if(session.getAttribute("sid") == null){
%>
	<script>
		alert("로그인 후 사용가능합니다.");
		window.location="/ProTwo/account/loginForm.jsp";
	</script>
<% }
	String pageNum = request.getParameter("pageNum");
		if(pageNum == null){
			pageNum = "1";
	   	}
		
	int pageSize = 10;
	int currentPage = Integer.parseInt(pageNum);
	int start = (currentPage - 1) * pageSize + 1;  
	int end = currentPage * pageSize;
	int buyAllCount = dao.buyCount(id);
	int buyAucCount = dao.buyActionCount(id);
%>
   <h2>구매내역 (총 구매횟수: <%=buyAllCount%>, 경매낙찰횟수: <%=buyAucCount %> ) </h2>
   <table border=1>
   		<tr>
   			<th>주문번호</th>
   			<th>상품번호</th>
   			<th>상품이름</th>
   			<th>상품브랜드</th>
   			<th>구매가격</th>
   			<th>구매일</th>
   		</tr>
<%	
	ArrayList<P02_s_recordDTO> list = dao.buyList(id, start, end);
	if(list.size() > 0) {
		for(P02_s_recordDTO dto : list){ 
			 int itemNum = dto.getItemNum();
%>
		<tr>
			<td><%=dto.getOrderNum()%></td>
<%
			if(itemNum == 0){ %>
				<td>경매품</td>
				<td><%=dto.getItemName()%></td>
				<td><%=dto.getIbrand()%></td>
				<td><%=dto.getPrice()%></td>
				<td><%=dto.getSelReg() %></td>
			<%}else{%>
				<td><a href="/ProTwo/item/content.jsp?itemNum=<%=dto.getItemNum()%>"><%=dto.getItemNum()%></a></td>
				<td><%=dto.getItemName()%></td>
				<td><%=dto.getIbrand()%></td>
				<td><%=dto.getPrice()+dto.getProfit()-dto.getUsepoint()%></td>
				<td><%=dto.getSelReg() %></td>
		</tr>
<%      		}
			}
	}else{
%>        <tr><td colspan="7" align=center> 구매내역이 없습니다.. </td></tr>
<%	} 
%>
	</table>
<% 
	if(buyAllCount > 0){
		int pageCount = buyAllCount / pageSize + (buyAllCount % pageSize == 0 ? 0 : 1);
		int startPage = (int)(currentPage/10)*10+1;
		int pageBlock = 10;
		int endPage = startPage + pageBlock-1;
		if(endPage > pageCount){
			endPage = pageCount;
		}
		if(startPage > 10){ %>
			<a href="/ProTwo/myPage/buyList.jsp?pageNum=<%=startPage - 10%>">[이전]</a>
<%		}
		for(int i = startPage ; i <= endPage ; i++){	%>
			<a href="/ProTwo/myPage/buyList.jsp?pageNum=<%=i%>">[<%=i%>]</a> 
<% 		}
		if(endPage < pageCount){ %>
		<a href="/ProTwo/myPage/buyList.jsp?pageNum=<%=startPage + 10%>">[다음]</a>
<%		}
	}
%>
<br/>
<br/>	
<%@ include file="/footer.jsp" %>	