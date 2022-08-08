<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<a href="/ProTwo/account/main.jsp">홈으로</a>  
<br/>
<br/>
<h2>회원가입</h2>
<%
	String adminId = (String)session.getAttribute("admin");
%>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
	function execDaumPostcode() {
		new daum.Postcode({
			oncomplete: function(data) {
				// 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.
	            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
	            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
	            var addr = ''; // 주소 변수
	            var extraAddr = ''; // 참고항목 변수
	
	            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
	            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
	                addr = data.roadAddress;
	            } else { 							// 사용자가 지번 주소를 선택했을 경우(J)
	                addr = data.jibunAddress;
	            }
	
	            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
	            if(data.userSelectedType === 'R'){
	                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
	                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
	                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
	                    extraAddr += data.bname;
	                }
	                
	                // 건물명이 있고, 공동주택일 경우 추가한다.
	                if(data.buildingName !== '' && data.apartment === 'Y'){
	                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
	                }
	                
	                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
	                if(extraAddr !== ''){
	                    extraAddr = ' (' + extraAddr + ')';
	                }
	                
	                // 조합된 참고항목을 해당 필드에 넣는다.
	                document.getElementById("extraAddress").value = extraAddr;
	            
	            } else {
	                document.getElementById("extraAddress").value = '';
	            }
	
	            // 우편번호와 주소 정보를 해당 필드에 넣는다.
	            document.getElementById('postcode').value = data.zonecode;
	            document.getElementById("address").value = addr;
	            // 커서를 상세주소 필드로 이동한다.
	            document.getElementById("detailAddress").focus();
	        }
	    }).open();
	}
	
	// id 중복확인 & 'admin' 문자열 포함여부 확인(0:포함됨, -1:없음)
	function idDuplicate(){
		id = document.frm.id.value;
		if(id.indexOf('admin') == -1){ 
			window.open("/ProTwo/account/idDuplicate.jsp?id="+id, 'idDuplicate', 'width=400, height=150');
		}else{
			alert("아이디에는 admin을 포함할 수 없습니다.");
		}
	}
	
	// 유효성 검사
	function checkValue(){
		if(!idCheck(frm.id.value)) {
            return false;
        }
		else if(!pwCheck(frm.pw.value)) {
            return false;
        }
		else if(!nameCheck(frm.name.value)) {
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
		else if(!postcodeCheck(frm.postcode.value)) {
            return false;
        }
		else if(!addressCheck(frm.address.value)) {
            return false;
        }
		else if(!ansCheck(frm.ans.value)) {
            return false;
        }
        return true;
    }

	function idCheck(id){
		if(!checkExist(id, "아이디를 "))
			return false;

		idSize = frm.id.value.length;
		if(idSize < 6 || idSize > 12) {
			alert("아이디는 6~12자 이내로 입력하세요.");
			frm.id.value="";
			frm.id.focus();
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
	
	function nameCheck(name){
		if(!checkExist(name, "이름을 "))
			return false;
		
		nameSize = frm.name.value.length;
		if(nameSize < 2 || nameSize > 5) {
			alert("이름은 2~5자 이내로 입력하세요.");
			frm.name.value="";
			frm.name.focus();
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
	
	// 공백 사용 금지
	function noSpaceForm(obj) { // 공백사용못하게
		var str_space = /\s/;  // 공백체크
		if(str_space.exec(obj.value)) { //공백 체크
			alert("공백을 사용할 수 없습니다.");
			obj.focus();
			obj.value = obj.value.replace(' ',''); // 공백제거
			return false;
		}
	}
	
	// 비밀번호 입력시 capsLock 확인
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
<form name="frm" action="/ProTwo/account/joinPro.jsp" method="post" onsubmit="return checkValue();">
	<table>
		<tr>
			<td>아이디</td>
			<td><input type="text" name="id" placeholder="아이디는 6~12자 이내로 입력하세요." onkeyup="noSpaceForm(this);" onchange="noSpaceForm(this);"/></td>
			<td><input type="button" value="중복확인" onclick="idDuplicate();" /></td>
		</tr>
		<tr>
			<td>비밀번호</td>
			<td><input type="password" name="pw" value="" onKeyPress="capsLock(event)" placeholder="비밀번호는 6~18자 이내로 입력하세요." onkeyup="noSpaceForm(this);" onchange="noSpaceForm(this);"/></td>
		</tr>
		<tr>
			<td>이  름</td>
			<td><input type="text" name="name" placeholder="이름은 2~5자 이내로 입력하세요." onkeyup="noSpaceForm(this);" onchange="noSpaceForm(this);"/></td>
		</tr>
		<tr>
			<td>생년월일</td>
			<td><input type="date" name="birth" required/></td>
			<td><div id="birthCheck"></div></td>
		</tr>
		<tr>
			<td>전화번호</td>
			<td>
				<select name="pC">
					<option>SKT</option>
					<option>KT</option>
					<option>LGU+</option>
					<option>알뜰폰</option>
				</select>
			</td>
			<td><input type="text" name="phone1" onkeyup="noSpaceForm(this);" onchange="noSpaceForm(this);"/></td>
			<td align="center">-</td>
			<td><input type="text" name="phone2" onkeyup="noSpaceForm(this);" onchange="noSpaceForm(this);"/></td>
			<td align="center">-</td>
			<td><input type="text" name="phone3" onkeyup="noSpaceForm(this);" onchange="noSpaceForm(this);"/></td>
		</tr>
		<tr>
			<td>이메일</td>
			<td><input type="text" name="mail1" onkeyup="noSpaceForm(this);" onchange="noSpaceForm(this);"/></td>
			<td>@</td>
			<td>
				<select name="mail2">
					<option>naver.com</option>
					<option>daum.net</option>
					<option>gmail.com</option>
					<option>직접입력(빌덥 (후순위) 필요~)</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>주  소</td>
			<td><input type="text" id="postcode" name="postcode" placeholder="우편번호" ></td>
			<td><input type="button" onclick="execDaumPostcode()" value="우편번호 찾기"></td>
			<td><input type="text" id="address" name="address" placeholder="주소"></td>
			<td><input type="text" id="detailAddress" name="detailAddress" placeholder="상세주소"></td>
			<td><input type="text" id="extraAddress" name="extraAddress" placeholder="참고항목"></td>
		</tr>
		<tr>
			<td>비밀번호 찾기 질문</td>
			<td>
				<select name="question">
					<option>어머니 성함은?</option>
					<option>초등학교 이름은?</option>
					<option>어렸을적 별명은?</option>
				</select>
			</td>
		</tr>
		<tr>
			<td>비밀번호 찾기 답</td>
			<td><input type="text" name="ans" onkeyup="noSpaceForm(this);" onchange="noSpaceForm(this);"/></td>
		</tr>
	</table>
		<input type="submit" value="회원가입" /> <br />
		<input type="hidden" value=<%=adminId%> />
</form>
<br/>
<br/>	
<%@ include file="/footer.jsp" %>	
<!-- 일반 회원과 관리자 모두 같은 회원가입푬을 통해 회원 등록한다
주소의 경우 다음 주소 검색 부분을 가져왔고 dto에서 한번 스크립트에서 한번씩 유효성 검사를 넣어놓았다. -->