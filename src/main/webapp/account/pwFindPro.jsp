<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="dao" class="account.P02_accountDAO" />
<% 
	String id = request.getParameter("id");
	String question = request.getParameter("question");
	String ans = request.getParameter("ans");
	
	String result = dao.pwFind(id, question, ans);
	if(result != null){
%>		<script>
			alert("<%=id%> 회원님의 비밀번호는 <%=result%>입니다.");
			window.location="/ProTwo/account/main.jsp";
		</script>
<%		} else {
%>		<script>
			alert("입력값들을 확인해주세요.");
			history.go(-1);
		</script>
<% 	} %>
