<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="review.reviewDAO" %>
<%@ page import="review.reviewDTO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.Timestamp"%>
<%@ include file="/top.jsp" %>
<br/>
<br/>
<h2>글쓴이로 찾기</h2>
<%
	sid = (String)session.getAttribute("sid");
	if(sid == null){
%>
	<script>
		alert("로그인후 사용가능합니다.");
		window.location="/ProTwo/account/loginForm.jsp";
	</script>
<%	}else{
		int renum = Integer.parseInt(request.getParameter("renum"));
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null){
			pageNum="1";
		}
		int pageSize = 10;
		int currentPage = Integer.parseInt(pageNum);
		int start = (currentPage - 1) * pageSize + 1;
		int end = currentPage * pageSize;
		reviewDAO dao = new reviewDAO();
	
		ArrayList<reviewDTO> list = dao.reviewSearchMove(sid);
		int count = list.size();
%>
	<h2>(글목록 : <%=list.size()%>)</h2><a href="/ProTwo/review/reviewList.jsp">리뷰 목록</a>
		<a href="/ProTwo/review/reviewContent.jsp?renum=<%=renum%>">본글 돌아가기</a>
		<table border="1">
			<tr>
				<th>글번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>상품번호</th>
				<th>날짜</th>
				<th>조회수</th>
				<th>좋아요</th>
			</tr>
<%		if(list.size() > 0){
			for(reviewDTO dto : list){%>
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
<%		}%>
		</table>
<% 	
	if(count>0){
		int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
		int startPage = (int)(currentPage/10)*10+1;
		int pageBlock = 10;
		int endPage = startPage + pageBlock-1;
		if (endPage > pageCount){
			endPage = pageCount;
		}
			if(startPage > 10){ %>
				<a href="/ProTwo/review/WriterSearch.jsp?pageNum=<%=startPage - 10%>&renum=<%=renum%>&id=<%=sid%>">[이전]</a>
<%			} 
			for(int i = startPage; i<= endPage ; i++){%>
				<a href="/ProTwo/review/WriterSearch.jsp?pageNum=<%=i%>&renum=<%=renum%>&id=<%=sid%>">[<%=i%>]</a>
<%			} 
			if(endPage < pageCount){ %>
				<a href="/ProTwo/review/WriterSearch.jsp?pageNum=<%=startPage + 10%>&renum=<%=renum%>&id=<%=sid%>">[다음]</a>
<%			}
		}
	}%>
<br/>
<br/>	
<%@ include file="/footer.jsp" %>	