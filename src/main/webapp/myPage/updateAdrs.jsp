<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="account.P02_accountDTO" %>
<jsp:useBean id="dao" class="account.P02_accountDAO" /> 
<%@ include file="/top.jsp" %>
<br/>
<br/>
<h2>주소 변경</h2>
<%
	String id = (String)session.getAttribute("sid");
	if(session.getAttribute("sid") == null){
%>
	<script>
		alert("로그인 후 사용가능합니다.");
		window.location="/ProTwo/account/loginForm.jsp";
	</script>	
<%	}
	P02_accountDTO dto = dao.readAccountInform(id);
%>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
	function execDaumPostcode() {
	    new daum.Postcode({
	        oncomplete: function(data) {
	            var addr = ''; 
	            var extraAddr = '';
	
	            if (data.userSelectedType === 'R') { 
	                addr = data.roadAddress;
	            } else { 
	                addr = data.jibunAddress;
	            }
	
	            if(data.userSelectedType === 'R'){
	                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
	                    extraAddr += data.bname;
	                }
	                if(data.buildingName !== '' && data.apartment === 'Y'){
	                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
	                }
	                if(extraAddr !== ''){
	                    extraAddr = ' (' + extraAddr + ')';
	                }
	                document.getElementById("extraAddress").value = extraAddr;
	            
	            } else {
	                document.getElementById("extraAddress").value = '';
	            }
	
	            document.getElementById('postcode').value = data.zonecode;
	            document.getElementById("address").value = addr;
	            document.getElementById("detailAddress").focus();
	        }
	    }).open();
	}
	
	function checkValue(){
		if(!postcodeCheck(frm.postcode.value)) {
            return false;
        }
		else if(!addressCheck(frm.address.value)) {
            return false;
        }
        return true;
    }
	
	function postcodeCheck(postcode){
		if(!checkExist(postcode, "우편번호 찾기로 검색하여 우편번호를 ")){
			return false;
		}
		return true;
	}
	
	function addressCheck(address){
		if(!checkExist(address, "우편번호 찾기로 검색하여 주소를 ")){
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
</script>

<form name="frm" action="/ProTwo/myPage/updateAdrsPro.jsp" method="post" onsubmit="return checkValue();">
	주소변경:	<input type="text" id="postcode" name="postcode" placeholder="우편번호">
			<input type="button" onclick="execDaumPostcode()" value="우편번호 찾기"><br />
			<input type="text" id="address" name="address" placeholder="주소">
			<input type="text" id="detailAddress" name="detailAddress" placeholder="상세주소">
			<input type="text" id="extraAddress" name="extraAddress" placeholder="참고항목"><br/>
			<input type="hidden" name="id" value="<%=dto.getId()%>" />
			<input type="submit" value="변경완료" />
</form>
<br/>
<br/>	
<%@ include file="/footer.jsp" %>	