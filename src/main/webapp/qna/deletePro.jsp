<%@page import="qna.P02_qnaDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/top.jsp" %>
<br/>
<br/>
<h2>Q&A 삭제</h2><br/>
<%	
	sid = (String)session.getAttribute("sid");
	String pw = request.getParameter("pw");
	P02_qnaDAO dao = new P02_qnaDAO();
	boolean check = dao.checkUser(sid, pw);
	if(check == false){
%>
	<script>
		alert("비밀번호를 확인해주세요");
		history.go(-1);
	</script>
<%	}else{
		int qnaNum = Integer.parseInt(request.getParameter("qnaNum"));
		int result = dao.deleteQna(qnaNum);
	}
%>
<h4>삭제되었습니다</h4>
<br/>
<input type="button" value="게시판으로" onclick="window.location='/ProTwo/qna/list.jsp'" />
<br/>
<br/>	
<%@ include file="/footer.jsp" %>	