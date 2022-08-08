<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="product.P02_productDAO" %>
<%@ page import="product.P02_productDTO" %>
<%@ include file="/top.jsp" %>
<br/>
<br/>
<h2>판매하기</h2>
<% 
	String id = (String)session.getAttribute("sid");
	int itemNum = Integer.parseInt(request.getParameter("itemNum")); 
	P02_productDAO dao = new P02_productDAO();
	P02_productDTO dto = dao.price(itemNum);
%>
<script> 
function changesell(){ 
	var pdsellSelect = document.getElementById("selectbox"); 
	var selectValue = pdsellSelect.options[pdsellSelect.selectedIndex].value; 
	var selectText = pdsellSelect.options[pdsellSelect.selectedIndex].text; 

	if(selectValue == 0){
		document.frm1.style.display="block";
		document.frm2.style.display="none";
	}else{
		document.frm1.style.display="none";
		document.frm2.style.display="block";
	}
} 
</script> 
<%	if(id != null){
		if(dto.getPrice3() != 0){%> <!--재고도없고 S_record에서도 거래기록이없는 아이템 판매시 입찰만 가능하게 함 -->
		판매방식 설정 : <select id="selectbox" name="pdsell" onchange="changesell()">
						<option value="0">입찰판매</option>
						<option value="1">일반판매</option>
					</select><br/>
		<form action="/ProTwo/product/selltenderPro.jsp" name="frm1" method="get" style="display:block;">
			입찰판매
					<input type="hidden" name="itemNum" value="<%=dto.getItemNum()%>"><br/>
			가격 :	<input type="text" name="price2">  <br/>
					<input type="hidden" name="sellId" value="<%=id%>"> <br/>
			계좌입력 :<input type="text" name="acc" placeholder="계좌번호를입력해주세요">
					<input type="submit" value="지금판매하기">
		</form>
		<form action="/ProTwo/product/sellPro.jsp" name="frm2" method="get" style="display:none;">
			일반판매
			가격 : <%=dto.getPrice3()%>
					<input type="hidden" name="itemNum" value="<%=dto.getItemNum()%>"><br/>
					<input type="hidden" name="price1" value="<%=dto.getPrice3()%>"><br/>
					<input type="hidden" name="sellId" value="<%=id%>"> <br/>
			계좌입력 :<input type="text" name="acc" placeholder="계좌번호를입력해주세요">
					<input type="submit" value="지금판매하기">
		</form>	
<%		} else{ %>
		<form action="/ProTwo/product/selltenderPro.jsp" name="frm1" method="get" style="display:block;">
			입찰판매
					<input type="hidden" name="itemNum" value="<%=dto.getItemNum()%>"><br/>
			가격 :	<input type="text" name="price2">  <br/>
					<input type="hidden" name="sellId" value="<%=id%>"> <br/>
			계좌입력 :<input type="text" name="acc" placeholder="계좌번호를입력해주세요">
			<input type="submit" value="지금판매하기">
		</form>
<%		} %>
	물품검수 후 영업일기준 3일 내 입금됩니다.
<%	} else {%>
		<script>
		alert("로그인 후 사용가능합니다.");
		window.location="/ProTwo/account/loginForm.jsp";
		</script>	
<%	} %>
<br/>
<br/>	
<%@ include file="/footer.jsp" %>	
<!-- 
	판매방식에 따라 폼을 만들어놓음(일반, 입찰판매)
	현재 폼은 판매재고가 없는 제품의 입찰판매창임
	(* 판매재고가 있는 경우에는 일반판매창으로 연결됨)
	
	selectbox로 입찰판매와 일반판매를 선택함 
	(* onchange - selectbox가 선택되었을때 script의 changesell 메소드가 실행됨)
	changesell - selectbox에서 선택된 value값을 받아와서 변수에 저장함
	if문으로 저장된 변수를 값과 비교하는 조건을 넣고 
	select 0(입찰판매) - 입찰판매 표시, 일반판매 미표시
	select 1(일반판매) - 일반판매 표시, 입찰판매 미표시
	아무것도 고르지 않았을때 (페이지 넘어오자마자 상황)는 기본적으로 일반판매를 표시로 두어 default의 형태를 설정해놓음 
-->