<%@page import="com.oreilly.servlet.MultipartRequest"%>
<%@page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy"%>
<%@page import="qna.P02_qnaDTO"%>
<%@page import="qna.P02_qnaDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/top.jsp" %>
<br/>
<br/>
<h2>Q&A 수정</h2><br/>
<%	
	sid = (String)session.getAttribute("sid");
	if(sid == null){
%>
	<script>
		alert("로그인 후 사용가능합니다.");
		window.location="/ProTwo/account/loginForm.jsp";
	</script>
<% }
	P02_qnaDAO dao = new P02_qnaDAO();
	P02_qnaDTO dto = new P02_qnaDTO();
	
	String dir = request.getRealPath("image");
	int size = 1024*1024*10;
	String enc = "UTF-8";
	DefaultFileRenamePolicy dp = new DefaultFileRenamePolicy();
	MultipartRequest mr = new MultipartRequest(request, dir, size, enc, dp);
	
	int qnaNum = Integer.parseInt(mr.getParameter("qnaNum"));
	String title = mr.getParameter("title");
	String image = mr.getFilesystemName("image");
	String originalImage = mr.getParameter("originalImage");
	String content = mr.getParameter("content");
	
	dto.setTitle(title);
	if(image == null){
		dto.setImage(originalImage);
	}else{
		dto.setImage(image);
	}
	dto.setContent(content);
	int result = dao.updateQna(dto, qnaNum);
%>
<h4>수정되었습니다</h4><br/>
<input type="button" value="게시판으로" onclick="window.location='/ProTwo/qna/list.jsp'" />
<br/>
<br/>	
<%@ include file="/footer.jsp" %>	