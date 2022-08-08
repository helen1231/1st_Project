<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="dto" class="bid.P02_bidDTO" />
<jsp:setProperty property="*" name="dto" />
<jsp:useBean id="dao" class="bid.P02_bidDAO" />
<%
	String sid = (String)session.getAttribute("sid");
	if(sid == null){
%>
	<script>
		alert("로그인 후 사용가능합니다.");
		window.location="/ProTwo/account/loginForm.jsp";
	</script>
<% }
	int aucNum = Integer.parseInt(request.getParameter("aucNum"));
	int result = dao.insertBid(dto, aucNum, sid);
%>
	<script>
		window.location="/ProTwo/auction/auction.jsp";
	</script>