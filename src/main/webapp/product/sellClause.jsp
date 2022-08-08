<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="product.P02_productDAO" %>
<%@ page import="product.P02_productDTO" %>
<%@ include file="/top.jsp" %>
<br/>
<br/>
<h2>약관</h2>
<script> 
	function Check(){
	   var count = 0;
       var chk = document.getElementsByName("confirm[]");
       for(var i=0; i < chk.length; i++){
           if(chk[i].checked == true) {
               count++;
           }
       }
       if(count != 5){
           alert("모두다 동의하셔야 판매가 가능합니다.");
           return false;
       }
	}
</script>
<%
	sid = (String)session.getAttribute("sid");
	int itemnum = Integer.parseInt(request.getParameter("itemNum")); 
	P02_productDAO dao = new P02_productDAO(); 
	P02_productDTO dto = dao.iteminfo(itemnum);

	if(sid != null){
%>
	
	상품정보를 확인해주세요!!<br/>
	
	아이템번호 : <%=dto.getItemNum()%> <br/>
	상품재질 : <%= dto.getImat()%> <br/>
	상품브랜드 : <%=dto.getIbrand()%>  <br/>
	상품사이즈 : <%=dto.getIsize()%>  <br/>


	-------------------------------------------------------------------------<br/>
	<input type="checkbox" name="confirm[]" value="1"/> 상품정보를확인했습니다.<br/>
	-------------------------------------------------------------------------<br/>
	<input type="checkbox" name="confirm[]" value="2"/> 국내외에서 정식발매된 정품/새상품입니다.<br/>
	-------------------------------------------------------------------------<br/>
	<input type="checkbox" name="confirm[]" value="3"/> 동봉품,패키지의 상태를 확인했습니다.<br/>
	-------------------------------------------------------------------------<br/>
	<input type="checkbox" name="confirm[]" value="4"/> 이중포장하여 판매합니다.<br/>
	-------------------------------------------------------------------------<br/>
	<input type="checkbox" name="confirm[]" value="5"/> P02SHOP의 이용정책을 모두 확인했습니다.<br/>
	-------------------------------------------------------------------------<br/>

	<form name="frm" action="/ProTwo/product/sellForm.jsp" method="get" onsubmit="return Check()">
		<input type="hidden" name="itemNum" value="<%=dto.getItemNum()%>">
		<input type="submit" value="계속해서 판매하기">
	</form>
<%	} else {%>
	<script>
		alert("로그인 후 사용가능합니다.");
		window.location="/ProTwo/account/loginForm.jsp";
	</script>	
<%	} %>
<br/>
<br/>	
<%@ include file="/footer.jsp" %>	