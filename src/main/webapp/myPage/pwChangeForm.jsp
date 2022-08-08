<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="account.P02_accountDTO" %>
<jsp:useBean id="dao" class="account.P02_accountDAO" /> 
<%@ include file="/top.jsp" %>
<br/>
<br/>
<h2>비밀번호 변경</h2>
<%
	String id = (String)session.getAttribute("sid");
	if(session.getAttribute("sid") == null){ 
%>
	<script>
		alert("로그인 후 사용가능합니다.");
		window.location="/ProTwo/account/loginForm.jsp";
	</script>
<% }
	P02_accountDTO dto = dao.readAccountInform(id);
%>
<script>
	function checkValue(){
		if(!pw_oldCheck(frm.pw_old.value)) {
	        return false;
	    }
		else if(!pw_newCheck(frm.pw_new.value)) {
	        return false;
	    }
		else if(!pw_new_checkCheck(frm.pw_new_check.value)) {
	        return false;
	    }
		return true;
	}
	
	function pwCheck(pw_old){
		if(!checkExist(pw_old, "기존 비밀번호를 "))
			return false;
		
		pw_oldSize = frm.pw_old.value.length;
		if(pw_oldSize < 6 || pw_oldSize > 18) {
			alert("비밀번호는 6~18자 이내로 입력하세요.");
			frm.pw.value="";
			frm.pw.focus();
			return false;
		}
		return true;
	}
	
	function pwCheck(pw_new){
		if(!checkExist(pw_new, "변경할 비밀번호를 "))
			return false;
		
		pw_newSize = frm.pw_new.value.length;
		if(pw_newSize < 6 || pw_newSize > 18) {
			alert("비밀번호는 6~18자 이내로 입력하세요.");
			frm.pw_new.value="";
			frm.pw_new.focus();
			return false;
		}
		return true;
	}
	
	function pw_new_checkCheck(pw_new_check){
		if(!checkExist(pw_new_check, "변경할 비밀번호를 "))
			return false;
		
		pw_new_checkSize = frm.pw_new_check.value.length;
		if(pw_new_checkSize < 6 || pw_new_checkSize > 18) {
			alert("비밀번호는 6~18자 이내로 입력하세요.");
			frm.pw_new_check.value="";
			frm.pw_new_check.focus();
			return false;
		}
		
		return true;
	}
	
	function checkExist(value, name){
		if(value == ""){
			alert(name + "입력해주세요.");
			return false;
		}
		return true;
	}
	
	// 공백사용 금지
	function noSpaceForm(obj) { // 공백사용못하게
		var str_space = /\s/;  // 공백체크
		if(str_space.exec(obj.value)) { //공백 체크
			alert("공백을 사용할 수 없습니다.");
			obj.focus();
			obj.value = obj.value.replace(' ',''); // 공백제거
			return false;
		}
	}
	
	// capsLock 확인
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
<form action="/ProTwo/myPage/pwChangePro.jsp" method="post" onsubmit="return checkValue();">
				<input type="hidden" name="id" value="<%=id%>" />
	기존 비밀번호 : <input type="password" name="pw_old" value="" onKeyPress="capsLock(event)" /> <br />
				<input type="hidden" name="pw" value="<%=dto.getPw()%>"/>
	변경할 비밀번호 : <input type="password" name="pw_new" value="" onKeyPress="capsLock(event)" /> <br />
	변경할 비밀번호 확인 : <input type="password" name="pw_new_check" value="" onKeyPress="capsLock(event)"/> <br />
				<input type="submit" value="전송" />
</form>
<br/>
<br/>	
<%@ include file="/footer.jsp" %>	