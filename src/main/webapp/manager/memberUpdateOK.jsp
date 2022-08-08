<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="dao_account" class="account.P02_accountDAO" />
<jsp:useBean id="dao_member" class="manager.P02_managerDAO" />
<jsp:useBean id="dto" class="account.P02_accountDTO" />
<jsp:setProperty property="*" name="dto"/>
<%
	String admin = (String)session.getAttribute("admin");
	if(admin == null){
%>
<script>
	alert("관리자 로그인 후 사용가능합니다.");
	window.location="/ProTwo/account/loginForm.jsp";
</script>
<% 	}
	String adminId = (String)session.getAttribute("admin");
	int result = dao_account.accountUpdate(dto);	
	
	String adminPw = request.getParameter("adminPw"); 

	boolean adminIdCheck = dao_member.adminLogin(adminId, adminPw);
	if(result == 1){
		if(adminIdCheck){
%>	
	<script>
		alert("회원정보가 수정되었습니다");
		window.location="/ProTwo/manager/member_Revise.jsp";
	</script>	
<%			
		}
	}else {
%>
	<script>
		alert("관리자 비밀번호를 다시 입력해주세요.");
		history.go(-1);
	</script>	
<%} %>