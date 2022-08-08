<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="review.reviewDTO"%>
<%@ page import="review.reviewDAO"%>
<%@ page import="java.util.ArrayList"%>
<% 
	String sid = (String)session.getAttribute("sid");
	if(sid == null){%>
	<script>
		alert("로그인후 사용가능합니다.");
		window.location="/ProTwo/account/loginForm.jsp";
	</script>
<%	}else{
		int renum = Integer.parseInt(request.getParameter("renum"));
		int good = Integer.parseInt(request.getParameter("good"));
		reviewDAO dao = new reviewDAO();
		ArrayList<reviewDTO> list = dao.whoGood(renum);
%>
	<table>
		<tr><th>좋아요 누른 사람 <%=good%>명</th></tr>
<%		if(list.size() > 0){ 
			for(reviewDTO dto : list){%>
			<tr><td><a href=javascript:void(0); onclick="opener.location.href='/ProTwo/review/WriterSearch.jsp?renum=<%=renum%>&id=<%=dto.getGoodPerson()%>',self.close()"><%=dto.getGoodPerson()%></a></td></tr>
<%			}
		}else{ %>
			<tr><td>좋아요를 누른 사람이 없습니다...</td></tr>
	</table>
<%		}
	}%>