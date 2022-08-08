<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="cart.P02_cartDTO" %>
<jsp:useBean id="dao" class="cart.P02_cartDAO" />
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
	int count = dao.cartCount(id);
%>
	<h2>관심상품 (총 관심상품 개수: <%=count%>)</h2>
	<table border=1>
		<tr>
			<th>상품번호</th>
			<th>상품이름</th>
			<th>상품브랜드</th>
			<th>최근 거래가</th>
			<th>최근 거래일시</th>
			<th></th>
		</tr>
<%	
	ArrayList<P02_cartDTO> list = dao.cartList(id, start, end);
	if(list.size() > 0){
		for(P02_cartDTO dto : list){ 
%>		
		<tr>
			<td><a href="/ProTwo/item/content.jsp?itemNum=<%=dto.getItemNum()%>"><%=dto.getItemNum()%></a></td>
			<td><%=dto.getItemName()%></td>
			<td><%=dto.getIbrand()%></td>
			<td><%=dto.getPrice()%></td>
			<td><%=dto.getSelReg()%></td>
			<td><input type="button" value="관심상품 취소" onclick="window.location='/ProTwo/myPage/dislike.jsp?itemNum=<%=dto.getItemNum()%>'" /></td>
		</tr>
<%		}
	}else{
%>		  <tr><td colspan="5" align=center> 관심상품이 없습니다.. </td></tr>
<%	}
%>		
	</table>
<% 
	if(count > 0){
		int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
		int startPage = (int)(currentPage/10)*10+1;
		int pageBlock = 10;
		int endPage = startPage + pageBlock-1;
		if(endPage > pageCount){
			endPage = pageCount;
		}
		if(startPage > 10){ %>
			<a href="/ProTwo/myPage/cartList.jsp?pageNum=<%=startPage - 10%>">[이전]</a>
<%		}
		for(int i = startPage ; i <= endPage ; i++){	%>
			<a href="/ProTwo/myPage/cartList.jsp=<%=i%>">[<%=i%>]</a> 
<% 		}
		if(endPage < pageCount){ %>
			<a href="/ProTwo/myPage/cartList.jsp=<%=startPage + 10%>">[다음]</a>
<%		}
	}
%>
<br/>
<br/>	
<%@ include file="/footer.jsp" %>	