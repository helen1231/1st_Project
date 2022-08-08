<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String sid = (String)session.getAttribute("sid");
%>
	<a href=/ProTwo/account/main.jsp>홈으로</a>&nbsp;&nbsp;&nbsp;
<%	if(sid != null){ %>
	<a href=/ProTwo/account/logout.jsp>로그아웃</a>&nbsp;&nbsp;&nbsp;
	<a href=/ProTwo/myPage/myPageForm.jsp>마이페이지</a>&nbsp;&nbsp;&nbsp;
	<a href=/ProTwo/auction/auction.jsp>경매</a>&nbsp;&nbsp;&nbsp;
	<a href=/ProTwo/review/reviewList.jsp>리뷰</a>&nbsp;&nbsp;&nbsp;
	<a href=/ProTwo/qna/list.jsp>Q&A</a>&nbsp;&nbsp;&nbsp;
<% }else{ %>
	<a href=/ProTwo/account/loginForm.jsp?uri=<%=request.getRequestURI()%>>로그인</a>&nbsp;&nbsp;&nbsp;
<%} %>
	<a href=/ProTwo/item/list.jsp>상품목록</a>&nbsp;&nbsp;&nbsp;
	<a href=/ProTwo/item/searchForm.jsp>상품검색</a>&nbsp;&nbsp;&nbsp;