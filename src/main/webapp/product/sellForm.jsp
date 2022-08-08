<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="product.P02_productDAO" %>
<%@ page import="product.P02_productDTO" %>
<%@ include file="/top.jsp" %>
<br/>
<br/>
<h2>판매하기</h2>
<% 
	sid = (String)session.getAttribute("sid");
	int itemnum = Integer.parseInt(request.getParameter("itemNum")); 
	P02_productDAO dao = new P02_productDAO();
	P02_productDTO dto = dao.price(itemnum);
%>
<%	if(sid != null){ %>
<form action="/ProTwo/product/sellPro.jsp" name="frm1" method="get">
	일반판매
	가격 : <%=dto.getPrice3()%>
	<input type="hidden" name="itemNum" value="<%=dto.getItemNum()%>"><br/>
	<input type="hidden" name="price1" value="<%=dto.getPrice3()%>"><br/>
	<input type="hidden" name="sellId" value="<%=sid%>"><br/>
	입금계좌입력 : <input type="text" name="acc" placeholder="계좌번호를입력해주세요">
	<input type="submit" value="지금판매하기">
</form>	
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