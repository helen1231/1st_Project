<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="dto" class="account.P02_accountDTO" />
<jsp:setProperty property="*" name="dto"/>
<jsp:useBean id="dao" class="account.P02_accountDAO" />
<%
	int result = dao.accountDelete(dto);
	if(result == 1){
		Cookie [] cookies = request.getCookies();
		for(Cookie c : cookies){
			String cname = c.getName();
			if(cname.equals("cid")){
				c.setPath("/ProTwo/");
				c.setMaxAge(0);
				response.addCookie(c);
			}
			if(cname.equals("cpw")){
				c.setPath("/ProTwo/");
				c.setMaxAge(0);
				response.addCookie(c);
			}
			if(cname.equals("cauto")){
				c.setPath("/ProTwo/");
				c.setMaxAge(0);
				response.addCookie(c);
			}
		}
		session.invalidate();
%>	<script>
		alert("탈퇴되었습니다.");
		window.location="/ProTwo/account/main.jsp";
	</script>		
<%	} else {
%>	<script>
		alert("비밀번호를 확인해주세요.");
		history.go(-1);
	</script>				
<% } %>