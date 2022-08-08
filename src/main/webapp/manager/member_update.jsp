<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.sql.*"%>
<%@ page import="account.P02_accountDTO" %>
<jsp:useBean id="dao" class="account.P02_accountDAO" />
<%@ include file="top.jsp" %>
<br/>
<br/>
<h2>회원정보 수정</h2>
<script>
// 유효성 검사
	function checkValue(){
		if(!pwCheck(frm.pw.value)) {
            return false;
        }
		else if(!phone1Check(frm.phone1.value)) {
            return false;
        }
		else if(!phone2Check(frm.phone2.value)) {
            return false;
        }
		else if(!phone3Check(frm.phone3.value)) {
            return false;
		}
		else if(!mailCheck(frm.mail1.value)) {
            return false;
        }
		else if(!ansCheck(frm.ans.value)) {
            return false;
        }
        return true;
    }

	function pwCheck(pw){
		if(!checkExist(pw, "비밀번호를 "))
			return false;

		pwSize = frm.pw.value.length;
		if(pwSize < 6 || pwSize > 18) {
			alert("비밀번호는 6~18자 이내로 입력하세요.");
			frm.pw.value="";
			frm.pw.focus();
			return false;
		}
		return true;
	}
	
	function phone1Check(phone1){
		if(!checkExist(phone1, "전화번호를 "))
			return false;
		
		phone1Size = frm.phone1.value.length;
		if(phone1Size != 3) {
			alert("전화번호를 바르게 입력해주세요");
			frm.phone1.value="";
			frm.phone1.focus();
			return false;
		}
		
		if(isNaN(phone1)){
			alert("숫자만 입력 가능합니다.");
			frm.phone1.value="";
			frm.phone1.focus();
			return false;
		}
		return true;
	}
	
	function phone2Check(phone2){
		if(!checkExist(phone2, "전화번호를 "))
			return false;
				
		phone2Size = frm.phone2.value.length;
		if(phone2Size < 3 || phone2Size > 4) {
			alert("전화번호를 바르게 입력해주세요");
			frm.phone2.value="";
			frm.phone2.focus();
			return false;
		}
		
		if(isNaN(phone2)){
			alert("숫자만 입력 가능합니다.");
			frm.phone2.value="";
			frm.phone2.focus();
			return false;
		}
		return true;
	}
	
	function phone3Check(phone3){
		if(!checkExist(phone3, "전화번호를 "))
			return false;
		
		phone3Size = frm.phone3.value.length;
		if(phone3Size != 4) {
			alert("전화번호를 바르게 입력해주세요");
			frm.phone3.value="";
			frm.phone3.focus();
			return false;
		}
		
		if(isNaN(phone3)){
			alert("숫자만 입력 가능합니다.");
			frm.phone3.value="";
			frm.phone3.focus();
			return false;
		}
		return true;
	}
	
	function mailCheck(mail1){
		if(!checkExist(mail1, "이메일을 ")){
			return false;
		}
		return true;
	}
	
	function ansCheck(ans){
		if(!checkExist(ans, "비밀번호 찾기의 답을 ")){
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
	
	function noSpaceForm(obj) { // 공백사용못하게
		var str_space = /\s/;  // 공백체크
		if(str_space.exec(obj.value)) { //공백 체크
			alert("공백을 사용할 수 없습니다.");
			obj.focus();
			obj.value = obj.value.replace(' ',''); // 공백제거
			return false;
			}
		}
	
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
<%
	String admin = (String)session.getAttribute("admin");
	if(admin == null){
%>
<script>
	alert("관리자 로그인 후 사용가능합니다.");
	window.location="/ProTwo/account/loginForm.jsp";
</script>
<% 	}
	String id = request.getParameter("id");
	P02_accountDTO dto = dao.readAccountInform(id);
%>

<form action="/ProTwo/manager/memberUpdateOK.jsp" method="post" onsubmit="return checkValue();">
	아이디 : <%=dto.getId()%> <br />
			<input type="hidden" name="id" value="<%=dto.getId()%>" />
	비밀번호 : <input type="text" name="pw" value="<%=dto.getPw()%>" placeholder="비밀번호를 입력하세요." onKeyPress="capsLock(event)" 
						onkeyup="noSpaceForm(this);" onchange="noSpaceForm(this);"/> <br />
	이  름 : <%=dto.getName()%> <br />
	생년월일 : <%=dto.getBirth()%> <br />
	등록된 전화번호 : <%=dto.getpC()%> <%=dto.getPhone()%><br />
	&nbsp; 신규 전화번호 : <select name="pC">
				<option>SKT</option>
				<option>KT</option>
				<option>LGU+</option>
				<option>알뜰폰</option>
			</select>
			<input type="text" name="phone1" onkeyup="noSpaceForm(this);" onchange="noSpaceForm(this);"/>
			-
			<input type="text" name="phone2" onkeyup="noSpaceForm(this);" onchange="noSpaceForm(this);"/>
			-
			<input type="text" name="phone3" onkeyup="noSpaceForm(this);" onchange="noSpaceForm(this);"/>
			<br />
	등록된 이메일 : <%=dto.getMail()%><br />
	&nbsp; 신규 이메일 : <input type="text" name="mail1" onkeyup="noSpaceForm(this);" onchange="noSpaceForm(this);"/>@
				<select name="mail2">
					<option>naver.com</option>
					<option>daum.net</option>
					<option>gmail.com</option>
				</select>
				<br />
	주  소 : <%=dto.getAdrs()%>
			<input type="hidden" value=<%=dto.getAdrs()%> name="adrs" />
			<input type="button" value="주소 변경" onclick="window.location='/ProTwo/manager/member_updateAdrs.jsp?id=<%=id%>' " /><br />
	비밀번호 찾기 질문 : <select name="question">
						<option selected><%=dto.getQuestion()%></option>
						<option>어머니 성함은?</option>
						<option>초등학교 이름은?</option>
						<option>어렸을적 별명은?</option>
					  </select> <br />
	비밀번호 찾기 답 : <input type="text" name="ans" value="<%=dto.getAns()%>" onkeyup="noSpaceForm(this);" onchange="noSpaceForm(this);"/> <br />
	관리자 비밀번호 확인 : <input type="password" name="adminPw" placeholder="관리자 비밀번호를 입력하세요." onKeyPress="capsLock(event)" onkeyup="noSpaceForm(this);" onchange="noSpaceForm(this);"/>
						<input type="hidden" name="adminId" value="<%=admin%>" />
	<br /><br />	
		<input type="submit" value="회원정보 수정"/>
</form>
<br/>
<br/>	
<%@ include file="/footer.jsp" %>		