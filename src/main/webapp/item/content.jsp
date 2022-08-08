<%@page import="s_record.P02_s_recordDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="item.P02_itemDAO" %>
<%@ page import="item.P02_itemDTO" %>
<%@ include file="/top.jsp" %>
<br/>
<br/>
<h2>진열된 상품</h2>
<%
	sid = (String)session.getAttribute("sid");
	int itemNum = Integer.parseInt(request.getParameter("itemNum"));
	
	P02_itemDAO dao = new P02_itemDAO();
	P02_itemDTO dto = dao.readContent(itemNum);
	
	int countPrefer = dao.countCartItem(itemNum);
	
	P02_s_recordDAO sdao = new P02_s_recordDAO();
	int recentPrice = sdao.recentPrice(itemNum);
	int maxPrice = sdao.maxPrice(itemNum);
	int minPrice = sdao.minPrice(itemNum);
	int today = sdao.avgToday(itemNum);
	int tomorrow = sdao.avgTomo(itemNum);
	int three = sdao.avgThree(itemNum);
%>
	<table width=1050 border="5px" >
		<tr> <th colspan="2"  ><%=dto.getItemName()%></th> </tr>
		<tr> <td width=200>아이템번호</td> <td><%=dto.getItemNum()%></td> </tr>
		<tr> <td width=200>상품재질</td> <td><%=dto.getImat()%></td> </tr>
		<tr> <td width=200>상품브랜드</td> <td><%=dto.getIbrand()%></td> </tr>
		<tr> <td width=200>상품사이즈</td> <td><%=dto.getIsize()%></td> </tr>
		<tr> <th colspan="2"  >상품정보</th> </tr>
		<tr> <th colspan="2"  ><%=dto.getContent()%></th> </tr> <!-- 동일-->
		<tr> <th colspan="2"  ><img src="/ProTwo/image/<%=dto.getItemImage()%>" /></th> </tr>
		<tr> <td width=200>상품재고</td> <td><%=dto.getItemStock()%></td> </tr>
		<tr> <td width=200>관심상품</td><td><%=countPrefer %></td></tr>
		
		<tr> <th colspan="2"  ><a href="/ProTwo/review/itemReviewList.jsp?itemname=<%=dto.getItemName()%>">이 상품의 리뷰보기</a></th> </tr>
	</table>


		<input type="button" value="관심상품등록" onclick="window.location='/ProTwo/myPage/prefer.jsp?itemNum=<%=dto.getItemNum() %>'" />
<% 
	if(dto.getItemStock() == 0){ 
%>
		<input type="button" value="판매입찰하기" onclick="window.location='/ProTwo/product/selltenderClause.jsp?itemNum=<%=dto.getItemNum() %>'" />
<%
	} else { 
%> 
 		<input type="button" value="판매하기" onclick="window.location='/ProTwo/product/sellClause.jsp?itemNum=<%=dto.getItemNum() %>'" />
		<input type="button" value="구매하기" onclick="window.location='/ProTwo/product/buyClause.jsp?itemNum=<%=dto.getItemNum() %>'" />
<% }%>

	<table border="1">
		<tr>
			<td>최근거래가</td><td>최고가</td><td>최저가</td>
		</tr>
		<tr>
			<td><%=recentPrice %></td><td><%=maxPrice %></td><td><%=minPrice %></td>
		</tr>
		<tr>
			<td>당일 거래가</td><td>-1일 거래가</td><td>-2일 거래가</td>
		</tr>
		<tr>
			<td><%=today %></td><td><%=tomorrow %></td><td><%=three %></td>
		</tr>
	</table>
<br/>
<br/>	
<%@ include file="/footer.jsp" %>		