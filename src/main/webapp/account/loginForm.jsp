<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<a href="/ProTwo/account/main.jsp">홈으로</a>
<br/>
<br/>  
<h2>로그인</h2>
<%
	String uri = request.getParameter("uri");
%>
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
<form action="/ProTwo/account/loginPro.jsp" method="post">
	아이디: <input type="text" name="id" /> <br />
	비밀번호: <input type="password" name="pw" value="" onKeyPress="capsLock(event)" /><br />
			<input type="checkbox" name="auto" value="1">로그인상태유지(자동로그인)<br/>
			<input type="hidden" name="uri" value="<%=uri%>">
			<input type="submit" value="로그인" />
			<input type="button" value="메인" onclick="window.location='/ProTwo/account/main.jsp'"/><br />
</form>
<br/>
<br/>
<h4>아이디와 비밀번호를 잊어버렸어요.</h4>
<a href="/ProTwo/account/idFindForm.jsp">아이디 찾기</a> &nbsp; &nbsp;
<a href="/ProTwo/account/pwFindForm.jsp">비밀번호 찾기</a>
<br/>
<br/>	
<%@ include file="/footer.jsp" %>	
	
