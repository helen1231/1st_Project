<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="review.reviewDTO" %>
<%@ page import="review.reviewDAO" %>
<%@ page import="java.util.ArrayList" %> 
<%@ page import="java.sql.Timestamp" %>   
<%
	int renum = Integer.parseInt(request.getParameter("renum"));
	String id = request.getParameter("id");
	reviewDAO dao = new reviewDAO();
	
	int goodCheck = dao.gooddelete(renum,id);
%>
<script>
	window.location.href="/ProTwo/review/reviewContent.jsp?renum=<%=renum%>";
</script>
<!-- 좋아요 취소시 작동 , 글넘버-좋아요-누른사람이 저장된 테이블데이터 삭제  
원하는 장소 지정 후 히스토리 해서 중복창 없앰  -->