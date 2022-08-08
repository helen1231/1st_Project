<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="review.reviewDAO" %>
<%@ page import="review.reviewDTO" %>
<%
	if(session.getAttribute("admin") == null){
%>
	<script>
		alert("접근하실 수 없습니다.");
		history.go(-1);
	</script>
<% }else{
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		int renum = Integer.parseInt(request.getParameter("renum"));

		reviewDAO dao = new reviewDAO();
		int result = dao.reviewdelete(renum);
	
		if(result == 1) { %>
			<script>
				alert("삭제되었습니다.");
				window.location='/ProTwo/review/reviewList.jsp';
			</script>
<%		}
	}%>	

	


	