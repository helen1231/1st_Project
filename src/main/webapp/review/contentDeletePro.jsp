<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="review.reviewDAO" %>
<%@ page import="review.reviewDTO" %>
<%
	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	int renum = Integer.parseInt(request.getParameter("renum"));
	
	reviewDAO dao = new reviewDAO();
	boolean re = dao.passwordCheck(id, pw);
	
	if(re != true){%>
	<script>
		alert("아이디와 비밀번호가 일치하지 않습니다")
		history.go(-1);
	</script>
<%	}else{
		int result = dao.reviewdelete(renum);
		if(result ==1) { %>
		<script>
			alert("삭제되었습니다.");
			window.location='/ProTwo/review/reviewList.jsp';
		</script>
<%		}
	}%>	