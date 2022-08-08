<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="top.jsp" %>
<br/>
<br/>
<%
	String admin = (String)session.getAttribute("admin");
	if(admin == null){
%>
		<script>
			alert("관리자 로그인이 필요합니다");
			window.location="/ProTwo/manager/managerLogin.jsp"
		</script>
<%	}else{ %>
		<br/><br/>
		관리자 페이지입니다.<br/>
		업무종료 후 로그아웃을 꼭 해주시기 바랍니다<br/>
		<input type="button" value="로그아웃" onclick="window.location='/ProTwo/manager/managerLogout.jsp'" />
		<br/><br/>
<%	}	%>   
<br/>
<br/>	
<%@ include file="/footer.jsp" %>		