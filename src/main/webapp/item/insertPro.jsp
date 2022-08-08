<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% request.setCharacterEncoding("UTF-8"); %>
<jsp:useBean id="dto" class="item.P02_itemDTO" />
<jsp:setProperty property="*" name="dto" />
<jsp:useBean id="dao" class="item.P02_itemDAO" />
<%
	String admin = (String)session.getAttribute("admin");
	if(admin == null){
%>
	<script>
		alert("관리자 로그인 후 사용가능합니다.");
		window.location="/ProTwo/account/loginForm.jsp";
	</script>
<% }
	int result = dao.InsertItem(dto);
%>
	<script>
		alert("등록완료");
		window.location="/ProTwo/item/list.jsp";
	</script>
