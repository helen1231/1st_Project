<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="review.reviewDTO" %>
<%@ page import="review.reviewDAO" %>
<%@ page import="java.util.ArrayList" %>
<%
	int renum = Integer.parseInt(request.getParameter("renum"));
	String id = request.getParameter("id");
	reviewDAO dao = new reviewDAO();

	int goodCheck = dao.goodInsert(renum, id);
%>
<script>
	window.location.href="/ProTwo/review/reviewContent.jsp?renum=<%=renum%>&goodCheck=<%=goodCheck%>";
</script>
<!-- 좋아요 처음 눌렀을시 작동, 글넘버-좋아요 수-누른사람 저장
 원하는 장소 지정 후 히스토리 해서 중복창 없앰  -->
