<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="item.P02_itemDTO" %>
<%@ page import="item.P02_itemDAO" %>
<%@ page import="product.P02_productDAO" %>
<%@ page import="product.P02_productDTO" %>
<%@ page import="s_record.P02_s_recordDAO" %>
<%@ page import="s_record.P02_s_recordDTO" %>
<%@ page import="java.util.ArrayList" %>
<%@ include file="top.jsp" %>
<br/>
<br/>
<h2>상품목록</h2>
<%
	String admin = (String)session.getAttribute("admin");
	if(admin == null){
%>
<script>
	alert("관리자 로그인 후 사용가능합니다.");
	window.location="/ProTwo/account/loginForm.jsp";
</script>
<% 	}
	P02_productDAO pdao = new P02_productDAO();
	pdao.update();
	
	P02_itemDAO idao = new P02_itemDAO();
	idao.stockupdate();

	String pageNum = request.getParameter("pageNum");
	if(pageNum == null){
		pageNum = "1";
	}
	int pageSize = 10;	
	int currentPage = Integer.parseInt(pageNum);
	int start = (currentPage - 1) * pageSize + 1;
	int end = (currentPage) * pageSize;

	P02_itemDAO dao = new P02_itemDAO();
	int count = dao.countListItem();
%>
	<table>
		<tr>
			<th>상품번호</th><th>이미지</th><th>상품명</th><th>조회수</th><th>관심상품</th><th>수량</th><th>최근거래가</th>
		</tr>
<%
		ArrayList<P02_itemDTO> list = dao.listItem(start, end);
		if(list.size() > 0){
			for(P02_itemDTO dto : list){
%>		<tr>

<%if(dto.getItemNum() != 0){ %>
			<td><%=dto.getItemNum()%></td>
			<td><img width="75" height="75" src="/ProTwo/image/<%=dto.getItemImage()%>">
<%		
			if(admin != null){
%>
			<input type="button" value="이미지 수정" onclick="window.location='/ProTwo/item/imageForm.jsp?itemNum=<%=dto.getItemNum() %>'" /></td>
<%			 } %>

			<td><a href="/ProTwo/item/content.jsp?itemNum=<%=dto.getItemNum()%>"><%=dto.getItemName()%></a>
<%			 if(dto.getReadCount() + dto.getItemGood() >100){%>
			&nbsp; Hot
<%			 }%></td>
			<td><%=dto.getReadCount()%></td>
			<td><%=dto.getItemGood()%></td>
			<td><%=dto.getItemStock()%></td>
			<%	P02_s_recordDAO sdao = new P02_s_recordDAO();
			int recentPrice = sdao.recentPrice(dto.getItemNum()); %>
			<td><%=recentPrice%></td>
		</tr>
<%			}
			}
		}else{
%>
		<tr><td></td><td>상품이 없습니다 </td></tr>
<%		}	%>		
	</table>

<%
	if (count > 0){
		int pageCount = count / pageSize + ( count % pageSize == 0 ? 0 : 1);
		int startPage = (int)(currentPage / 10)*10 + 1;
		int pageBlock = 10;
		int endPage = startPage + pageBlock - 1;
		if(endPage > startPage){
			endPage = pageCount;
		}
		if(startPage>10){
%>
			<a href="/ProTwo/manager/item_list.jsp?pageNum=<%=startPage-10%>">[이전]</a>
<%			
		}
		for(int i = startPage ; i <=endPage ; i++){
%>
			<a href="/ProTwo/manager/item_list.jsp?pageNum=<%=i %>">[<%=i %>]</a>
<%
		}
		if(endPage < pageCount){
%>
			<a href="/ProTwo/manager/item_list.jsp?pageNum=<%=startPage+10%>">[다음]</a>
<%			
		}
	}%>
<br/>
<br/>	
<%@ include file="/footer.jsp" %>			