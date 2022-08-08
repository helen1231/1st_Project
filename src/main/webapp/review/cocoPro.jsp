<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="dto" class="review.reviewDTO"/>
<jsp:setProperty name="dto" property="*"/>
<jsp:useBean id="dao" class="review.reviewDAO"/>
<%
	String sid = (String)session.getAttribute("sid");
	if(sid == null){%>
	<script>
		alert("로그인 후 사용하세요");
		window.location="/ProTwo/account/main.jsp";
	</script>
<%	}else{  
		int result = dao.cocoInsert(dto);
		if(result == 1){%>
		<script>
			alert("댓글이 작성되었습니다")
			window.opener.parent.location.reload();
			window.location.href='/ProTwo/review/comments.jsp?renum=<%=dto.getRenum()%>';
		</script>
<%		}
	}%>
<!-- 대댓글 등록
opener.parent.location=’부모창주소(원하는주소)’;
window.close(); 자식창 닫음 -->

 
