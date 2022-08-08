<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="account.P02_accountDTO" %>
<jsp:useBean id="dao" class="account.P02_accountDAO" />

<%
	String id = request.getParameter("id");
	String pw = request.getParameter("pw");
	String auto = request.getParameter("auto");
	String uri = request.getParameter("uri");
	
	Cookie [] cookies = request.getCookies();
	if(cookies != null){
		for(Cookie c : cookies){
			String cname = c.getName();
			if(cname.equals("cid")) id = c.getValue();
			if(cname.equals("cpw")) pw = c.getValue();
			if(cname.equals("cauto")) auto = c.getValue();
		}
	} 
	
	P02_accountDTO dto = dao.readAccountInform(id);
	String dbid = dto.getId();
	String dbpw = dto.getPw();
	int dbstatus = dto.getStatus();
			
	if(id.equals(dbid) && pw.equals(dbpw) && dbstatus == 0){
		session.setAttribute("sid", id);
		session.setMaxInactiveInterval(60*60*24);
		if(auto != null){
			Cookie cid = new Cookie("cid", id);
			Cookie cpw = new Cookie("cpw", pw);
			Cookie cauto = new Cookie("cauto", auto);
			
			cid.setMaxAge(60*60*24*3);
			cpw.setMaxAge(60*60*24*3);
			cauto.setMaxAge(60*60*24*3);
			
			cid.setPath("/ProTwo/");
			cpw.setPath("/ProTwo/");
			cauto.setPath("/ProTwo/");
			// 다양한 경로에서 사용하므로, 각 쿠키를 프로젝트로 경로 설정해줌.

			response.addCookie(cid);
			response.addCookie(cpw);
			response.addCookie(cauto);
		}
		if(id.indexOf("admini") == 0){ 
			response.sendRedirect("/ProTwo/manager/managerLogin.jsp");
		}else if(uri.equals("null"))  { %>
            <script>
            	history.go(-2);
            </script>
<%		}else if(uri != null){
			response.sendRedirect(uri);
		}
	}else{
%>
         <script>
            alert("id/pw를 확인하세요.");
            history.go(-1);
         </script>
<%	} %>