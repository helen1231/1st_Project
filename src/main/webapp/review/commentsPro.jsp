<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="dto" class="review.reviewDTO"/>
<jsp:setProperty property="*" name="dto"/>
<jsp:useBean id="dao" class="review.reviewDAO" />
<%
	String sid =(String)session.getAttribute("sid");
	if(sid == null){%>
		<script>
			alert("로그인후 사용가능합니다.");
			window.location="/ProTwo/account/loginForm.jsp";
		</script>
<%	}else{
		int result = dao.commentsInsert(dto);
		if(result == 1) {%>
		<script>
			alert("댓글이 작성되었습니다")
			window.opener.parent.location.reload();
			window.location.href='/ProTwo/review/comments.jsp?renum=<%=dto.getRenum()%>';
		</script>
<%		}
	}%>
<!-- 댓글 등록 -->	