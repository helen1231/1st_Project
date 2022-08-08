<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="review.reviewDAO" %>
<%@ page import="review.reviewDTO" %>
<%@ include file="/top.jsp" %>
<br/>
<br/>
<h2>리뷰게시판</h2>
<%
	sid = (String)session.getAttribute("sid");
	String pageNum = request.getParameter("pageNum");
	String itemname = request.getParameter("itemname");
	
	if(pageNum == null){
	    pageNum="1";
	}
	int pageSize = 10;
	int currentPage = Integer.parseInt(pageNum);
	int start = (currentPage - 1) * pageSize + 1;
	int end = currentPage * pageSize;
	
	reviewDAO dao = new reviewDAO();
	int count = dao.reviewCount();
%>
	글목록(전체글 : <%=count%>)<br/>
	<table border="1">
		<tr>
			<th>글번호</th>
			<th>제목</th>
			<th>작성자</th>
			<th>상품명</th>
			<th>날짜</th>
			<th>조회수</th>
			<th>좋아요</th>
		</tr>
<%
		ArrayList<reviewDTO> list = dao.itemReviewList(start,end,itemname);
		if(list.size() > 0 ){
			for(reviewDTO dto : list){%>
				<tr>
					<td><%=dto.getRenum()%></td>
					<td><a href="/ProTwo/review/reviewContent.jsp?renum=<%=dto.getRenum()%>"><%=dto.getTitle()%></a></td>
<%					boolean result = dao.buyCheck(dto.getId() , dto.getItemname());
					if(result == true){ %>
					<td>(구매)<%=dto.getId()%></td>
<%					}else{ %>
					<td><%=dto.getId()%></td>
<%					} %>
					<td><%=dto.getItemname()%></td>
					<td><%=dto.getRevreg()%></td>
					<td><%=dto.getReadCount()%></td>
					<td><%=dto.getGood()%></td>
				</tr>
<%			}
		}else{ %>
			<tr><td>작성된 글이 없습니다..</td></tr>
<%		} %>
	</table>
<br/>
<button type="button" onclick="window.location='/ProTwo/review/reWriteForm.jsp'">글쓰기</button>
<button type="button" onclick="window.location='/ProTwo/review/myWriterList.jsp?id=<%=sid%>'">나의 글 목록</button>
<form action="reviewSearch.jsp" method="get">
	<select name="column">
		<option value="id">작성자</option>
		<option value="title">제목</option>
		<option value="content">내용</option>
	</select>
	<input type="text" name="search"/>
	<input type="submit" value="검색"/>
</form>
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
			<a href="/ProTwo/review/reviewList.jsp?pageNum=<%=startPage - 10%>">[이전]</a>
<%		}
		for(int i = startPage ; i <= endPage ; i++){	%>
			<a href="/ProTwo/review/reviewList.jsp?pageNum=<%=i%>">[<%=i%>]</a> 
<%		}
		if(endPage < pageCount){ %>
			<a href="/ProTwo/review/reviewList.jsp?pageNum=<%=startPage + 10%>">[다음]</a>
<%		}
	}
%>
<br/>
<br/>	
<%@ include file="/footer.jsp" %>