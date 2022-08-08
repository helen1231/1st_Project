<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList" %>
<%@ page import="review.reviewDAO" %>
<%@ page import="review.reviewDTO" %>
<h2>댓글</h2>
<% 
	String sid = (String)session.getAttribute("sid");
	if(sid == null){%>
	<script>
		alert("로그인후 사용가능합니다.");
		window.location="/ProTwo/account/loginForm.jsp";
	</script>
<%	}else{ %> 
	<script>
		function contentCheck(){
			if(document.frm.com_content.value == ""){
				alert("내용을 입력해주세요!!!")
				return false;
				}
			if(document.frm.com_content.value.length > 200){
				alert("댓글은 200자 이내로 작성해주세요!!")
				return false;
				}
		}
	</script>
<%	
		String pageNum = request.getParameter("pageNum");
		if(pageNum == null){
			pageNum="1";
		}
		int pageSize = 10; // 페이지 글개수 
		int currentPage = Integer.parseInt(pageNum);
		int start = (currentPage - 1) * pageSize + 1;  
		int end = currentPage * pageSize;
	
		int comnum = 0, com_ref=0, com_step=0;
		String com_id = (String) session.getAttribute("sid");
		int renum = Integer.parseInt(request.getParameter("renum"));
	
		reviewDAO dao = new reviewDAO();
		int count = dao.commentsCount(renum);
		ArrayList<reviewDTO> list = dao.comList(renum,start,end);
		if(list.size() > 0){
%>
		<h5>댓글 갯수(<%=count%>)개</h5>
			<table border="1">
<%			for(reviewDTO dto : list){	
				if(dto.getStatus() == 0){ %>		
					<tr>
<%					if(dto.getCom_step() == 1){ %>
						<td><h3>--</h3></td>
<%					}else{ %>
						<td>◎</td>
<%					} %>
						<td>작성자 : <a href="/ProTwo/review/myWriterList.jsp?id=<%=dto.getCom_id()%>"><%=dto.getCom_id()%></a></td>
						<td><%=dto.getCom_content()%><td>
						<form action="/ProTwo/review/cocoForm.jsp" method="post">
							<input type="hidden" name="renum" value="<%=dto.getRenum()%>"/>
							<input type="hidden" name="com_id" value="<%=dto.getCom_id()%>"/>
							<input type="hidden" name="comnum" value="<%=dto.getComnum()%>"/>
							<input type="hidden" name="com_step" value="<%=dto.getCom_step()%>"/>
							<input type="hidden" name="com_ref" value="<%=dto.getCom_ref()%>"/>
							<input type="submit" value="답글쓰기"/>
						</form>
<%					if(com_id.equals(dto.getCom_id()) == true ){  %>
						<button type="button" onclick="location.href='commentsDelete.jsp?renum=<%=renum%>&com_id=<%=dto.getCom_id()%>&comnum=<%=dto.getComnum()%>'">삭제</button>
<%					} %>
						<td><%=dto.getSetReg()%></td>
					</tr>
<%				}
			} %>
		</table>	
<%		}%>	
	<form action="/ProTwo/review/commentsPro.jsp" name=frm method="post" onsubmit="return contentCheck();">
		<input type="hidden" name="renum" value="<%=renum%>"/>
		<input type="hidden" name="com_id" value="<%=com_id%>"/>
		<table>
			<tr><th>아이디 : <%=com_id%> </th></tr>
		</table>
		<textarea rows="5" cols="40" name="com_content"></textarea>
		<input type="submit" value="작성 완료"/>
	</form>
<%
		if(count > 0){
			int pageCount = count / pageSize + (count % pageSize == 0 ? 0 : 1);
			int startPage = (int)(currentPage / 10)*10+1;
			int pageBlock=10;
			int endPage = startPage + pageBlock-1;
			if(endPage > pageCount){    
				endPage = pageCount; 
			}
			if(startPage > 10) { %>
				<a href="/ProTwo/review/comments.jsp?pageNum=<%=startPage - 10%>&renum=<%=renum%>">이전</a>
<%			}
			for(int i = startPage ; i <= endPage ; i++) { %>
				<a href="/ProTwo/review/comments.jsp?pageNum=<%=i%>&renum=<%=renum%>"><%=i%></a>
<%			} 
			if(endPage < pageCount) { %>
				<a href="/ProTwo/review/comments.jsp?pageNum=<%=startPage + 10%>&renum=<%=renum%>">다음</a>
<%			}
		}
	} %>
<!-- 댓글 보기 및 등록폼 -->