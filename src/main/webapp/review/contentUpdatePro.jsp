<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="review.reviewDTO" %>
<%@ page import="review.reviewDAO" %>
<%@ page import="com.oreilly.servlet.MultipartRequest" %>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %> 
<%
	String sid = (String)session.getAttribute("sid");
	if(sid == null){%>
	<script>
		alert("로그인후 사용가능합니다.");
		window.location="/ProTwo/account/loginForm.jsp";
	</script>
<%	}else{ 
		String dir = request.getRealPath("image");
		int size = 1024*1024*10;
		String enc = "UTF-8";
		DefaultFileRenamePolicy dp = new DefaultFileRenamePolicy();
		MultipartRequest mr = new MultipartRequest(request,dir,size,enc,dp);
		
		String type = mr.getContentType("image");
		String [] split = type.split("/");
		String ex = split[0];
			
		if(!ex.equals("image")){
			mr.getFile("image").delete();
%>
		<script>
			alert("사진만 업로드 가능합니다");
			history.go(-1);
		</script>
<% 		}else{
			int renum = Integer.parseInt(mr.getParameter("renum"));
			String image = mr.getFilesystemName("image");
			String title = mr.getParameter("title");
			String content = mr.getParameter("content");

			reviewDTO dto = new reviewDTO();
			reviewDAO dao = new reviewDAO();

			dto.setRenum(renum);
			dto.setTitle(title);
			dto.setContent(content);
			dto.setImage(image);
			
			int result = dao.reviewupdate(dto);
%>
		<script>
			alert("글 작성완료");
			window.location="/ProTwo/review/reviewContent.jsp?renum=<%=renum%>";
		</script>
<% } 
}%>