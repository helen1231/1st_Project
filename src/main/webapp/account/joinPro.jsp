<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="dto" class="account.P02_accountDTO" />
<jsp:setProperty property="*" name="dto"/>
<jsp:useBean id="dao" class="account.P02_accountDAO" />
<a href="/ProTwo/account/main.jsp">홈으로</a>
<br/>
<br/>
<%
	String admin = (String)session.getAttribute("admin");
	int result = dao.joinInsert(dto);

	if(result != 0){ %>
	<h2>회원가입이 완료되었습니다.</h2>
	<% } else { %>
	<script>
		alert("입력 값들을 확인해주세요.");
		history.go(-1);
	</script>
	<% }
	if(admin == null){
		session.setAttribute("sid", dto.getId());
%>		<input type="button"  value="메인" onclick="window.location='/ProTwo/account/main.jsp'"/>
<%	} else{ %>		
		<input type="button"  value="메인" onclick="window.location='/ProTwo/account/main.jsp'"/>
		<input type="button"  value="관리자 메인" onclick="window.location='/ProTwo/manager/managerMain.jsp'"/>
<% 	} %>
<br/>
<br/>	
<%@ include file="/footer.jsp" %>	