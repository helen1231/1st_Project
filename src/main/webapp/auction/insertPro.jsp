<%@page import="auction.P02_auctionDAO"%>
<%@page import="auction.P02_auctionDTO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.oreilly.servlet.MultipartRequest" %>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>
<%
	String admin = (String)session.getAttribute("admin");
	if(admin == null){
%>
	<script>
		alert("관리자 로그인 후 사용가능합니다.");
		window.location="/ProTwo/account/loginForm.jsp";
	</script>
<% }
	P02_auctionDAO dao = new P02_auctionDAO();
	P02_auctionDTO dto = new P02_auctionDTO();

	String dir = request.getRealPath("image");
	int size = 1024*1024*10;
	String enc = "UTF-8";
	DefaultFileRenamePolicy dp = new DefaultFileRenamePolicy();
	MultipartRequest mr = new MultipartRequest(request, dir, size, enc, dp);
		
	String aucName = mr.getParameter("aucName");
	String aucContent = mr.getParameter("aucContent");
	String aucImage = mr.getFilesystemName("aucImage");
	dto.setAucName(aucName);
	dto.setAucContent(aucContent);
	dto.setAucImage(aucImage);
	int result = dao.insertAuction(dto);
%>
<script>
	alert("등록되었습니다");
	window.location="/ProTwo/auction/auction.jsp"
</script>
	
<!-- multipart로 경매품에 대한 정보들을 받아옴 -->