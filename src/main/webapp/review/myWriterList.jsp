<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="review.reviewDAO" %>
<%@ page import="review.reviewDTO" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.sql.Timestamp"%>
<%@ include file="/top.jsp" %>
<br/>
<br/>
<h2>내가 쓴 글</h2>
<%	
	sid = (String)session.getAttribute("sid");
	if(sid == null){%>
	<script>
		alert("로그인후 사용가능합니다.");
		window.location="/ProTwo/account/loginForm.jsp";
	</script>
<% }else{
		sid = (String)session.getAttribute("sid");
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
		<h4>(글목록 : <%=list.size()%>)</h4><a href="/ProTwo/review/reviewList.jsp">리뷰 목록</a>
		<table border="1">
			<tr>
				<th>글번호</th>
				<th>제목</th>
				<th>작성자</th>
				<th>상품번호</th>
				<th>날짜</th>
				<th>조회수</th>
				<th>좋아요</th>
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
		}else{ %>
			<tr><td>작성된 글이 없습니다..</td></tr>
<%		}	%>
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
				<a href="/ProTwo/review/myWriterList.jsp?pageNum=<%=startPage - 10%>&id=<%=sid%>">[이전]</a>
<%			}
			for(int i = startPage ; i <= endPage ; i++){	%>
				<a href="/ProTwo/review/myWriterList.jsp?pageNum=<%=i%>&id=<%=sid%>">[<%=i%>]</a> 
<%			}
			if(endPage < pageCount){ %>
				<a href="/ProTwo/review/rmyWriterList.jsp?pageNum=<%=startPage + 10%>&id=<%=sid%>">[다음]</a>
<%			}
		}
	}%>
<br/>
<br/>	
<%@ include file="/footer.jsp" %>