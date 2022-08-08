<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/top.jsp" %>
<br/>
<br/>
<h2>회원탈퇴</h2>
<%
	String id = (String)session.getAttribute("sid");
	if(session.getAttribute("sid") == null){ 
%>
	<script>
		alert("로그인 후 사용가능합니다.");
		window.location="/ProTwo/account/loginForm.jsp";
	</script>
<% } %>
<h4>탈퇴하시려면, 비밀번호를 입력해주세요.</h4>
<script>
	function capsLock(e){
		var keyCode = 0;
		var shiftKey = false;
		keyCode = e.keyCode;
		shiftKey = e.shiftKey;
		if (((keyCode >= 65 && keyCode <= 90) && !shiftKey) || ((keyCode >= 97 && keyCode <= 122) && shiftKey)){
			alert("CapsLock이 켜져 있습니다");
			return;
		}
	}
</script>
<form action="/ProTwo/myPage/deletePro.jsp" method="post" >
	아이디: <%=id%> <br/>
			<input type="hidden" name="id" value="<%=id%>" />
	비밀번호: <input type="password" name="pw" value="" onKeyPress="capsLock(event)" /><br />
			<input type="submit" value="탈퇴" />
			<input type="button" value="메인" onclick="window.location='/ProTwo/account/main.jsp'" />
</form>
<br/>
<br/>	
<%@ include file="/footer.jsp" %>	