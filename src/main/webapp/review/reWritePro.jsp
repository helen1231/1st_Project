<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="review.reviewDTO" %>
<%@ page import="review.reviewDAO" %>
<%@ page import="com.oreilly.servlet.MultipartRequest" %>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %> 
<%
	if(session.getAttribute("sid") == null){
%>
	<script>
		alert("로그인후 사용가능합니다.");
		window.location="/ProTwo/account/loginForm.jsp";
	</script>
<% }else{ 	
		//파일 업로드
		String dir = request.getRealPath("image");
		int size = 1024*1024*10;
		String enc = "UTF-8";
		DefaultFileRenamePolicy dp = new DefaultFileRenamePolicy();
		MultipartRequest mr = new MultipartRequest(request,dir,size,enc,dp);
		String image = mr.getFilesystemName("image");
	
		if(image == null){
			image="0";
		}
	
		if(!image.equals("0")){
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
<%			} 
		}
		String id = mr.getParameter("id");
		String itemname = mr.getParameter("itemname");
		String title = mr.getParameter("title");
		String content = mr.getParameter("content");

		reviewDTO dto = new reviewDTO();
		reviewDAO dao = new reviewDAO();
		dto.setId(id);
		dto.setItemname(itemname);
		dto.setTitle(title);
		dto.setContent(content);
		dto.setImage(image);

		int result = dao.reviewInsert(dto);

		String img = mr.getFilesystemName("image"); //업로드된 파일명
	
		if(result == 1){		
			int maxrenum = dao.maxrenum(id);%>
			<script>
				alert("글 작성완료");
				window.location="/ProTwo/review/reviewContent.jsp?renum=<%=maxrenum%>";
			</script>
<%		}else{ %>
			<script>
				alert("글이 작성되지 았습니다")
				history.go(-1)
			</script>
<% 		} 
	}%>