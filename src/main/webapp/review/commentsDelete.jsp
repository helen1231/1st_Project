<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="review.reviewDTO"%>
<%@ page import="review.reviewDAO"%>
<%
	String sid = (String)session.getAttribute("sid");
	if(sid == null){%>
	<script>
		alert("로그인후 사용가능합니다.");
		window.location="/ProTwo/account/loginForm.jsp";
	</script>
<%	}else{
		String id = request.getParameter("com_id");
		if(sid.equals(id) == false){%>
		<script>
			alert("작성자가 일치하지 않습니다");
			history.go(-1);
		</script>
<%		}else{
			int comnum = Integer.parseInt(request.getParameter("comnum"));
			int renum = Integer.parseInt(request.getParameter("renum"));
			reviewDAO dao = new reviewDAO();
			int result = dao.deleteComments(renum, id,comnum);
			if(result == 1){%>
				<script>
				alert("댓글이 삭제되었습니다")
				window.location.href="/ProTwo/review/comments.jsp?renum=<%=renum%>";
			</script>
<% 			}
		}
	}%>
<!-- 대댓글 삭제 -->