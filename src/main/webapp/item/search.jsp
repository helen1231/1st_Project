<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="item.P02_itemDTO" %>
<%@ page import="item.P02_itemDAO" %>
<%@ include file="/top.jsp" %>
<br/>
<br/>
<h2>상품검색</h2>
<%
	int searchBrand1 =  Integer.parseInt(request.getParameter("searchBrand1"));
	int searchBrand2 =  Integer.parseInt(request.getParameter("searchBrand2"));
	int searchBrand3 =  Integer.parseInt(request.getParameter("searchBrand3"));
	String column = request.getParameter("column");
	String search = request.getParameter("search");

	P02_itemDAO dao = new P02_itemDAO();
	ArrayList<P02_itemDTO> list = dao.search(searchBrand1, searchBrand2, searchBrand3,column, search);
%>
<table> 
 	<tr> 
 		<th>상품번호</th>
 		<th>이미지</th>
 		<th>상품명</th>
 		<th>조회수</th>
 		<th>좋아요</th> 
 	</tr>
<%
	if(list.size() > 0){
		for(P02_itemDTO dto : list){
%> 
	<tr> 
		<td><%=dto.getItemNum()%></td> 
		<td><img width="75" height="75" src="/ProTwo/image/<%=dto.getItemImage()%>"> 
 		<td><a href="/ProTwo/item/content.jsp?itemNum=<%=dto.getItemNum()%>"><%=dto.getItemName()%></a></td> 
 		<td><%=dto.getReadCount()%></td> 
 		<td><%=dto.getItemGood()%></td> 
 	</tr> 
<%		}
	}else{
%> 
 	<tr><td></td><td>상품이 없습니다 </td></tr> 
<%	}	%>		 
</table>  
<br/>
<br/>	
<%@ include file="/footer.jsp" %>		