<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.oreilly.servlet.MultipartRequest" %>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>
<%@ page import="item.P02_itemDAO" %>
<%
	String admin = (String)session.getAttribute("admin");
	if(admin == null){
%>
	<script>
		alert("관리자 로그인 후 사용가능합니다.");
		window.location="/ProTwo/account/loginForm.jsp";
	</script>
<% } 
	String dir = request.getRealPath("image");
	int size = 1024*1024*10;
	String enc = "UTF-8";
	DefaultFileRenamePolicy dp = new DefaultFileRenamePolicy();
	MultipartRequest mr = new MultipartRequest(request, dir, size, enc, dp);
	
	String img = mr.getFilesystemName("img");
	int itemNum = Integer.parseInt(mr.getParameter("itemNum"));
	
	P02_itemDAO dao = new P02_itemDAO();
	int result = dao.changeImageItem(img, itemNum);
	
	response.sendRedirect("list.jsp");
%>