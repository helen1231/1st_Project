<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="account.P02_accountDTO" %>
<jsp:useBean id="dao" class="account.P02_accountDAO" />
<% 
	String name = request.getParameter("name");
	String pCompany = request.getParameter("pC");
	String phone1 = request.getParameter("phone1");
	String phone2 = request.getParameter("phone2");
	String phone3 = request.getParameter("phone3");
	String phone = phone1+"-"+phone2+"-"+phone3;
	
	ArrayList<P02_accountDTO> list = dao.idFind(name, pCompany, phone);
	if(list.size() > 0){
		for(P02_accountDTO dto : list){
%>		
		<script>	
			alert("<%=name%>님의 아이디는 <%=dto.getId()%>입니다.");
		</script>
<%		}%>
		<script>
			window.location="/ProTwo/account/main.jsp";
		</script>
<%	}else {
%>		<script>
			alert("이름과 전화번호를 확인해주세요");
			history.go(-1);
		</script>
<%	} %>	