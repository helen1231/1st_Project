<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="product.P02_productDTO"%>
<%@ page import="product.P02_productDAO"%>    
<%@ page import="account.P02_accountDTO"%>
<%@ page import="account.P02_accountDAO"%>  
<jsp:useBean id="dto" class="product.P02_productDTO" />
<jsp:setProperty property="*" name="dto" />
<jsp:useBean id="dao" class="product.P02_productDAO" />    
<%@ include file="/top.jsp" %>
<br/>
<br/>
<h2>구매완료</h2>
<%	
	sid = (String)session.getAttribute("sid");
	if(sid == null){
%>
	<script>
		alert("로그인 후 사용가능합니다.");
		window.location="/ProTwo/account/loginForm.jsp";
	</script>
<% }
	int pdNum = Integer.parseInt(request.getParameter("pdNum"));
	int itemNum = Integer.parseInt(request.getParameter("itemNum"));
	int usepoint = Integer.parseInt(request.getParameter("usepoint"));
	int update = dao.buyProduct(itemNum, pdNum, sid, usepoint);
	
	String adrs = request.getParameter("adrs");
	String postcode = request.getParameter("postcode");
	String address = request.getParameter("address");
	String detailAddress = request.getParameter("detailAddress");
	String extraAddress = request.getParameter("extraAddress");
	
	int price = Integer.parseInt(request.getParameter("price"));
	int pay = price - usepoint;
	int profit = (int)(pay * 0.1);
	int stackpoint = (int)(pay * 0.01);
	
	P02_productDTO info = dao.iteminfo(itemNum);
	
	P02_accountDAO adao = new P02_accountDAO();
	adao.UsePoint(sid, usepoint);
	adao.StackPoint(sid, stackpoint);
	
%>
	<%=info.getItemName() %>을 구매하셨습니다<br/>
	가격 : <%=pay%><br/>
<%
	if(adrs == null){
%>
	배송지 : <%=postcode %><%=address %><%=detailAddress %><%=extraAddress%><br/>
<%	}else{%>	
	배송지 : <%=adrs %><br/>
<%	} %>
	<%=stackpoint%> 포인트 적립되었습니다<br/>
	<input type="button" value="구매내역확인" onclick="window.location='/ProTwo/myPage/buyList.jsp'" />
	<input type="button" value="계속 쇼핑하기" onclick="window.location='/ProTwo/item/list.jsp'" />
<br/>
<br/>	
<%@ include file="/footer.jsp" %>	