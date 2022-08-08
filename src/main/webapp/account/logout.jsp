<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
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
	response.sendRedirect("main.jsp");
%>

