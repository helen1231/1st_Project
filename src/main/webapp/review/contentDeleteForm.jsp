<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="review.reviewDAO"%>
<%@ page import="review.reviewDTO"%>
<%@ include file="/top.jsp" %>
<br/>
<br/>
<h2>리뷰 삭제</h2>
<% 
	sid = (String)session.getAttribute("sid");
	if(sid == null){%>
	<script>
		alert("로그인후 사용가능합니다.");
		window.location="/ProTwo/account/loginForm.jsp";
	</script>
<% }else{ 
		int renum = Integer.parseInt(request.getParameter("renum"));
		String admin = (String)session.getAttribute("admin");
	
		reviewDAO dao = new reviewDAO();
		reviewDTO dto = dao.idCheck(renum);
		String id = dto.getId();	
	
		if(!id.equals(sid)){
%>
		<script>
			alert("글 작성자가 아닙니다.");
			history.go(-1);
		</script>
<%		}else{ %>
			<a href="/ProTwo/review/reviewContent.jsp?renum=<%=renum%>">글로 돌아가기</a>
			<form action=contentDeletePro.jsp method="post">
				글 삭제를 위해서는 본인 비밀번호가 필요합니다<br/>
				<input type="hidden" name="renum" value="<%=renum%>"/>
				<input type="hidden" name="id" value="<%=id%>"/>
				비밀번호 : <input type="password" name="pw"/>
				<input type="submit" value="확인"/>
			</form>		
<%		} 
	} %>