<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/top.jsp" %>
<br/>
<br/>
<% 
	sid = (String)session.getAttribute("sid");
	String adminId = (String)session.getAttribute("admin");
	String cid=null, cpw=null, cauto=null;
	Cookie[] cookies = request.getCookies();
   
	if(sid == null){
		if(cookies != null){
			for(Cookie c : cookies){
				String cname = c.getName();
				if(cname.equals("cid")) cid = c.getValue();
				if(cname.equals("cpw")) cpw = c.getValue();
				if(cname.equals("cauto")) cauto = c.getValue();
			}
        	if(cauto != null && cid != null && cpw != null){
            	response.sendRedirect("/ProTwo/account/loginPro.jsp");
			}
		}
%>
<h2>와치샵에 오신걸 환영합니다</h2>
	<input type="button" value="로그인" onclick="window.location='/ProTwo/account/loginForm.jsp?uri=<%=request.getRequestURI()%>'">
	<input type="button" value="가입" onclick="window.location='/ProTwo/account/joinForm.jsp'" >
	<input type="button" value="상품보기" onclick="window.location='/ProTwo/item/list.jsp'" >
<% }else{ %>
	<h2><%=sid%>님 환영합니다.</h2>
<%
		if(adminId != null){
%>
 	   <input type="button" value="관리자 홈" onclick="window.location='/ProTwo/manager/managerMain.jsp' ">
<%
		}
	} %>
<br/>
<br/>	
<%@ include file="/footer.jsp" %>	