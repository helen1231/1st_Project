<%@page import="account.P02_accountDTO"%>
<%@page import="account.P02_accountDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8");%>
<%@ page import="product.P02_productDTO" %>
<%@ page import="product.P02_productDAO" %>
<%@ include file="/top.jsp" %>
<br/>
<br/>
<h2>구매하기</h2>
<% 
	sid = (String)session.getAttribute("sid");	
	if(sid == null){
%>
	<script>
		alert("로그인 후 사용가능합니다.");
		window.location="/ProTwo/account/loginForm.jsp";
	</script>
<% }
	int itemNum = Integer.parseInt(request.getParameter("itemNum")); 
	P02_productDAO dao = new P02_productDAO();
	P02_productDTO dto = dao.showProduct(itemNum);

	P02_accountDAO adao = new P02_accountDAO();
	P02_accountDTO adto = adao.readAccountInform(sid);
	adao.showpoint(sid);
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
		// 기존 주소 or 배대지
	function changeAdrs(){
		var adrsSelect = document.getElementById("selectbox");
		var selectVal = adrsSelect.options[adrsSelect.selectedIndex].value;
		var selectText = adrsSelect.options[adrsSelect.selectedIndex].text;
		if(selectVal == 0){
			document.frm1.style.display="block";
			document.frm2.style.display="none";
		}else{
			document.frm1.style.display="none";
			document.frm2.style.display="block";
		}
	}

	// 공백방지 유효성검사,사용포인트 검사
	function check1(){
		if(document.frm1.adrs.value == ""){
			alert("주소를 입력해주세요");
			return false;
		}
		if(document.frm1.acc.value == ""){
			alert("계좌를 입력해주세요");
			return false;
		}
		if(document.frm1.usepoint.value > <%=dto.getPrice1()%>){
			alert("구매금액이상 포인트를 사용할 수 없습니다.");
			document.frm1.usepoint.value="";
			document.frm1.usepoint.focus();
			return false;
		}
		if(document.frm1.usepoint.value >  <%=adto.getPoint()%>){
			alert("사용가능한 포인트를 확인해주세요");
			document.frm1.usepoint.value="";
			document.frm1.usepoint.focus();
			return false;
		}
	}
	function check2(){
		if(document.frm2.acc.value == ""){
			alert("계좌를 입력해주세요");
			return false;
		} 
		if(document.frm2.usepoint.value > <%=dto.getPrice1()%>){
			alert("구매금액이상 포인트를 사용할 수 없습니다.");
			document.frm2.usepoint.value="";
			document.frm2.usepoint.focus();
			return false;
		}
		if(document.frm2.usepoint.value > <%=adto.getPoint()%>){
			alert("사용가능한 포인트를 확인해주세요");
			document.frm2.usepoint.value="";
			document.frm2.usepoint.focus();
			return false;
		}
	}
</script>
<select id="selectbox" name="adrs" onchange="changeAdrs()">
	<option value = "0">신규배송지</option>
	<option value = "1">기존배송지</option>
</select><br/>
<form name="frm1" action="/ProTwo/product/buyPro.jsp" style="display:block;" onsubmit="return check1();">
	즉시구매
	가격 : <%=(int)(dto.getPrice1()*1.1)%>
			<input type="hidden" name="itemNum" value="<%=dto.getItemNum()%>" />
			<input type="hidden" name="pdNum" value="<%=dto.getPdNum()%>" /><br/>
			<input type="hidden" name="price" value="<%=(int)(dto.getPrice1()*1.1)%>" /><br/>
	주소		<input type="text" id="postcode" name="postcode" placeholder="우편번호" >
			<input type="button" onclick="execDaumPostcode()" value="우편번호 찾기"><br/>
			<input type="text" id="address" name="address" placeholder="주소">
			<input type="text" id="detailAddress" name="detailAddress" placeholder="상세주소">
			<input type="text" id="extraAddress" name="extraAddress" placeholder="참고항목"><br/>
	계좌번호 	<input type="text" name="acc" /> <br/>
	포인트	<input type="text" name="usepoint" value="0" /> <br/>
	사용가능한 포인트	 <%=adto.getPoint()%><br/>
			<input type="submit" value="즉시구매하기">
</form>
<form name="frm2" action="/ProTwo/product/buyPro.jsp" style="display:none;" onsubmit="return check2();">
	즉시구매
	가격 : <%=(int)(dto.getPrice1()*1.1)%>
			<input type="hidden" name="itemNum" value="<%=dto.getItemNum()%>" />
			<input type="hidden" name="pdNum" value="<%=dto.getPdNum()%>" /><br/>
			<input type="hidden" name="price" value="<%=(int)(dto.getPrice1()*1.1)%>" /><br/>
	주소		<input type="text" name="adrs" value="<%=adto.getAdrs() %>" size="40"/> <br/>
	계좌번호 	<input type="text" name="acc" /> <br/>
	포인트	<input type="text" name="usepoint" value="0" /> <br/>
	사용가능한 포인트	<%=adto.getPoint()%><br/>
	<input type="submit" value="즉시구매하기">
</form>
<br/>
<br/>	
<%@ include file="/footer.jsp" %>	