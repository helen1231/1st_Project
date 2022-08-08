<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="review.reviewDTO" %>
<%@ page import="review.reviewDAO" %>
<%@ page import="java.sql.Timestamp" %>    
<%@ include file="/top.jsp" %>
<br/>
<br/>
<h2>리뷰검색</h2>
<%
	sid = (String)session.getAttribute("sid");
	if(sid == null){%>
	<script>
		alert("로그인후 사용가능합니다.");
		window.location="/ProTwo/account/loginForm.jsp";
	</script>
<%	}else{ 
		String column = request.getParameter("column");
		String search = request.getParameter("search");
		reviewDAO dao = new reviewDAO();
		ArrayList<reviewDTO> list = dao.reviewSearch(column,search);
%>
		<a href="/ProTwo/review/reviewList.jsp">리뷰 목록</a>
		<h4>글목록 (전체글 : <%=list.size()%>)</h4>
		<table>
			<tr>
				<th>글번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>상품번호</th>
				<th>날짜</th>
				<th>조회수</th>
				<th>좋아요</th>
			</tr>
<% 	
		if(list.size() > 0 ){
			for(reviewDTO dto : list){
%>
			<tr>
				<td><%=dto.getRenum()%></td>
				<td><a href="/ProTwo/review/reviewContent.jsp?renum=<%=dto.getRenum()%>"><%=dto.getTitle()%></a></td>
				<td><%=dto.getId()%></td>
				<td><%=dto.getItemname()%></td>
				<td><%=dto.getRevreg()%></td>
				<td><%=dto.getReadCount()%></td>
				<td><%=dto.getGood()%></td>
			</tr>
<%			}
		}else{%>
			<tr><td>작성된 글이 없습니다..</td></tr>
		</table>
<%		}
	}%>
<br/>
<br/>	
<%@ include file="/footer.jsp" %>	