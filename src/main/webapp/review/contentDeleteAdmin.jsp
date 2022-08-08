<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="review.reviewDAO"%>
<%@ page import="review.reviewDTO"%>
<%@ include file="/manager/top.jsp" %>
<br/>
<br/>
<h2>관리자로 리뷰삭제</h2>
<% 
	String admin = (String)session.getAttribute("admin");
	if(admin == null){
%>
	<script>
		alert("접근하실 수 없습니다.");
		history.go(-1);
	</script>
<% }else{
		int renum = Integer.parseInt(request.getParameter("renum"));
		reviewDAO dao = new reviewDAO();
		reviewDTO dto = dao.idCheck(renum);
		String id = dto.getId();	
%>
		<a href="/ProTwo/review/reviewContent.jsp?renum=<%=renum%>">글로 돌아가기</a>
		<form action=contentDeleteAdminPro.jsp method="post">
				<input type="hidden" name="renum" value="<%=renum%>"/>
				<input type="hidden" name="id" value="<%=id%>"/>
		아이디 	<%=id%><br/>
		제목		<%=dto.getTitle() %><br/>
			<input type="submit" value="확인"/>
		</form>		
<%	} %>
<br/>
<br/>	
<%@ include file="/footer.jsp" %>	