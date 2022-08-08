<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="product.P02_productDTO" %>
<jsp:useBean id="dao" class="product.P02_productDAO" />
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
	int count = dao.sellCount(id);
%>
   <h2>판매내역 (총 판매상품 개수: <%=count%>) </h2>
   <table border=1>
   		<tr>
   			<th>거래품번호</th>
   			<th>상품번호</th>
   			<th>판매가격</th>
   			<th>판매여부</th>
   			<th>판매방식</th>
   			<th>상품등록일</th>
   		</tr>
<%	
	ArrayList<P02_productDTO> list = dao.sellList(id, start, end);
	if(list.size() > 0) {
		for(P02_productDTO dto : list){
%>
		<tr>
			<td><%=dto.getPdNum()%></td>
			<td><a href="/ProTwo/item/content.jsp?itemNum=<%=dto.getItemNum()%>"><%=dto.getItemNum()%></a></td>
			<td><%=dto.getPrice1()%></td>
			<td><%=dto.getPstatus()%></td>
			<td><%=dto.getPpdSell()%></td>
			<td><%=dto.getPdReg()%></td>
		</tr>
<%      }
	}else{
%>        <tr><td colspan="6" align=center> 판매상품이 없습니다.. </td></tr>
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
			<a href="/ProTwo/myPage/sellList.jsp?pageNum=<%=startPage - 10%>">[이전]</a>
<%		}
		for(int i = startPage ; i <= endPage ; i++){	%>
			<a href="/ProTwo/myPage/sellList.jsp?pageNum=<%=i%>">[<%=i%>]</a> 
<% 		}
		if(endPage < pageCount){ %>
		<a href="/ProTwo/myPage/sellList.jsp?pageNum=<%=startPage + 10%>">[다음]</a>
<%		}
	}
%>
<br/>
<br/>	
<%@ include file="/footer.jsp" %>		