<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="review.reviewDAO"%>
<%@ page import="review.reviewDTO"%>
<%@ include file="/top.jsp" %>
<br/>
<br/>
<h2>리뷰 수정</h2>
<%
	sid = (String)session.getAttribute("sid");
	if(sid == null){%>
	<script>
		alert("로그인후 사용가능합니다.");
		window.location="/ProTwo/account/loginForm.jsp";
	</script>
<%	}
	int renum = Integer.parseInt(request.getParameter("renum"));
	reviewDAO dao = new reviewDAO();
	reviewDTO dto = dao.idCheck(renum);
	String id = dto.getId();	
	
	if(!id.equals(sid)){
%>
	<script>
		alert("글 작성자가 아닙니다.");
		history.go(-1);
	</script>
<%	}%>
<form action="/ProTwo/review/contentUpdatePro.jsp" name=frm  method="post" enctype="multipart/form-data">
		 	<input type="hidden" name="renum" value="<%=renum%>"/>
	제목 :	<input type="text" name="title" value="<%=dto.getTitle()%>"/><br/>
	사진 :	<input type="file" name="image"/><br/>
	내용 :	<textarea rows="5" cols="40" name="content"><%=dto.getContent()%></textarea>
			<input type="submit" value="수정 완료"/>
</form>