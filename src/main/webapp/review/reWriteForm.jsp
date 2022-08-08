<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="review.reviewDAO" %>
<%@ page import="review.reviewDTO" %>
<%@ page import="com.oreilly.servlet.MultipartRequest" %>
<%@ page import="com.oreilly.servlet.multipart.DefaultFileRenamePolicy" %>
<%@ page import="java.util.ArrayList" %>
<%@ include file="/top.jsp" %>
<br/>
<br/>
<h2>리뷰작성</h2>
<%
	sid = (String)session.getAttribute("sid"); 
	if(sid == null){
%>
	<script>
		alert("로그인후 사용가능합니다.");
		window.location="/ProTwo/account/loginForm.jsp";
	</script>
<% } %> 
<script>
	function checkLogin(){
		if(document.frm.title.value == ""){
			alert("제목을 입력해 주세요");
			return false;
		}

		a=document.frm.title.value.length
		if(a>20){
			alert("제목은 20자 이내로 작성해주세요")
			return false;
		}
	}
</script>
<% 
	reviewDAO dao = new reviewDAO();
	ArrayList<reviewDTO> list = dao.buyList(sid);
%>
<form action="/ProTwo/review/reWritePro.jsp" name=frm onsubmit="return checkLogin();" method="post" enctype="multipart/form-data">
			<input type="hidden" name="id" value="<%=sid%>"/>
	제품명 :	<select name = "itemname">
				<option>선택안함</option>
<%				 if(list.size() > 0){
					for(reviewDTO dto : list){
%>
				<option><%=dto.getItemname()%></option>
<%					}
				} %>
			</select>
			<br/>
	제목 :	<input type="text" name="title"/><br/>
	사진 :	<input type="file" name="image"/><br/>
	내용 :	<textarea rows="5" cols="40" name="content"></textarea>
			<input type="submit" value="작성 완료"/>
</form>
<br/>
<br/>	
<%@ include file="/footer.jsp" %>