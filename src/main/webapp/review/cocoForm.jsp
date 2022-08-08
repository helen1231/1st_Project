<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<h2>대댓글</h2>
<%
	String sid = (String)session.getAttribute("sid");
	if(sid == null){%>
	<script>
		alert("로그인후 사용가능합니다.");
		window.location="/ProTwo/account/main.jsp";
	</script>
<% } %>
<script>
	function contentCheck(){
		if(document.cocoform.com_content.value.length == 0){
			alert("내용을 입력해주세요!!");
			return false;
		}
		if(document.cocoform.com_content.value.length > 200){
			alert("댓글은 200자 이내로 작성해주세요!!")
			return false;
		}
	}
</script>
<%	
	int renum = Integer.parseInt(request.getParameter("renum"));
	String com_id = request.getParameter("com_id");
	int comnum = Integer.parseInt(request.getParameter("comnum"));
	int com_ref = Integer.parseInt(request.getParameter("com_ref"));
	int com_step = 1;
%>
<form action="/ProTwo/review/cocoPro.jsp" method="post" name="cocoform" onsubmit="return contentCheck();">
	<input type="hidden" name="renum" value="<%=renum%>"/>
	<input type="hidden" name="com_id" value="<%=com_id%>"/>
	<input type="hidden" name="comnum" value="<%=comnum%>"/>
	<input type="hidden" name="com_step" value="<%=com_step%>"/>
	<input type="hidden" name="com_ref" value="<%=com_ref%>"/>
	<table>
		<tr><th>아이디 : <%=sid%></th></tr>
	</table>
	<textarea rows="5" cols="40" name="com_content">@<%=com_id%> </textarea>
	<input type="submit" value="작성 완료"/>
</form>
<!-- 대댓글 등록 -->