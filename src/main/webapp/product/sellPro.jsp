<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="product.P02_productDAO" %>
<%@ page import="product.P02_productDTO" %>
<jsp:useBean id="dto" class="product.P02_productDTO" />
<jsp:setProperty property="*" name="dto"/>
<jsp:useBean id="dao" class="product.P02_productDAO" />
<%@ include file="/top.jsp" %>
<br/>
<br/>
<h2>판매완료</h2>
<%
	String id = (String)session.getAttribute("sid");
	int itemNum = Integer.parseInt(request.getParameter("itemNum"));
	int price1	= Integer.parseInt(request.getParameter("price1"));
	String sellId = request.getParameter("sellId");

	if(id != null){
		int result = dao.Sell1Product(dto);
%>
	<h4>상품이 등록되었습니다.</h4>
	<input type="button" value="홈으로" onclick="window.location='/ProTwo/account/main.jsp'" />
<%	} else {%>
	<script>
		alert("로그인 후 사용가능합니다.");
		window.location="/ProTwo/account/loginForm.jsp";
	</script>	
<%	} %>
<br/>
<br/>	
<%@ include file="/footer.jsp" %>	