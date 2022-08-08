<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/manager/top.jsp" %>
<br/>
<br/>
<h2>상품 등록</h2>
<%
	String admin = (String)session.getAttribute("admin");
	if(admin == null){
%>
	<script>
		alert("관리자 로그인 후 사용가능합니다.");
		window.location="/ProTwo/account/loginForm.jsp";
	</script>
<% } %>
    <script>
    	function check(){
    		if(document.frm.itemName.value == ""){
    			alert("상품이름을 입력해주세요");
    			return false;
    		}
			if(document.frm.content.value == ""){
    			alert("설명을 입력해주세요");
    			return false;
    		}
    	}
    </script>
<form name="frm" action="/ProTwo/item/insertPro.jsp" method="post" onsubmit="return check();" >
	상품이름	:	<input type="text" name="itemName" /> <br />
	브랜드	:	<input type="radio" name="itemBrand" value="0" checked />롤렉스
 				<input type="radio" name="itemBrand" value="1" />IWC
  				<input type="radio" name="itemBrand" value="2" />오데마 피게 <br />	
   	재질		:	<input type="radio" name="itemMat" value="0" checked />메탈
   				<input type="radio" name="itemMat" value="1" />가죽 <br />
   	사이즈	:	<input type="radio" name="itemSize" value="0"  checked />male
   				<input type="radio" name="itemSize" value="1" />female <br />
   	설명		:	<textarea name="content" rows="10" cols="40" ></textarea> <br />
   				<input type="submit" value="등록" />
</form>
<br/>
<br/>	
<%@ include file="/footer.jsp" %>	