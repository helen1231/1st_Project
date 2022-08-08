<%@ page import="java.util.ArrayList"%>
<%@ page import="account.P02_accountDTO"%>
<%@ page import="manager.P02_managerDAO"%>
<%@ page import="java.text.*" %>
<%@ page import="java.util.Date" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="top.jsp" %>
<br/>
<br/>
<h2>회원정보 관리</h2>
<%
	String admin = (String)session.getAttribute("admin");
	if(admin == null){
%>
<script>
	alert("관리자 로그인 후 사용가능합니다.");
	window.location="/ProTwo/account/loginForm.jsp";
</script>
<% 	}
	P02_managerDAO dao = new P02_managerDAO();
	ArrayList<P02_accountDTO> list = dao.accountList();
%>

<br><br>
	<table border="1" align="center">
		<tr>
			<td>회원아이디</td>
			<td>회원비번</td>
			<td>회원이름</td>
			<td>통신사</td>
			<td>전화번호</td>
			<td>회원생일정보</td>
			<td>email</td>
			<td>주소</td>
			<td>포인트</td>
			<td>회원상태</td>
			<td>회원질문</td>
			<td>회원답변</td>
			<td>회원로그인날짜</td>
			<td>수정</td>
			<td>삭제</td>
		</tr>
<%
		for(P02_accountDTO dto : list){				
%>
		<tr>
			<td><%=dto.getId() %></td>
			<td><%=dto.getPw() %></td>
			<td><%=dto.getName() %></td>
			<td><%=dto.getpC() %></td>
			<td><%=dto.getPhone() %></td>
			<td><%=dto.getBirth() %></td>
			<td><%=dto.getMail() %></td>
			<td><%=dto.getAdrs() %></td>
			<td><%=dto.getPoint() %></td>
<%		if(dto.getStatus() == 0){
%>			<td>일반</td>
<%		}else if(dto.getStatus() == 1){
%>			<td>탈퇴</td>
<%		}%>
			<td><%=dto.getQuestion() %></td>
			<td><%=dto.getAns() %></td>
			<td><%=dto.getLoginTime() %></td>
			<td><input type="button" value="회원정보 변경" onclick="window.location='/ProTwo/manager/member_update.jsp?id=<%=dto.getId()%>' "/>
			<td><input type="button" value="회원삭제" onclick="window.location='/ProTwo/manager/memberDeleteOK.jsp?id=<%=dto.getId()%>' "></td>
		</tr>
<%		}%>
	</table>
<br/>
<br/>	
<%@ include file="/footer.jsp" %>			