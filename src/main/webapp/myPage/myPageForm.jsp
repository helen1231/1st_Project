<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="account.P02_accountDTO" %>
<jsp:useBean id="dao" class="account.P02_accountDAO" />
<%@ include file="/top.jsp" %>
<br/>
<br/>
<h2>마이페이지</h2>
<%
	sid = (String)session.getAttribute("sid");
	if(session.getAttribute("sid") == null){
%>
	<script>
		alert("로그인 후 사용가능합니다.");
		window.location="/ProTwo/account/loginForm.jsp";
	</script>	
<%	}
	P02_accountDTO dto = dao.readAccountInform(sid);
%>
<h4>나의 쇼핑정보</h4>
<br/>
<a href="/ProTwo/myPage/buyList.jsp">구매내역</a> <br />
<a href="/ProTwo/myPage/sellList.jsp">판매내역</a> <br />
<a href="/ProTwo/myPage/cartList.jsp">관심상품</a> <br />
<br />
<h4>나의 정보</h4>
<a href="/ProTwo/myPage/updateForm.jsp">회원정보</a> <br />
<a href="/ProTwo/myPage/qnaList.jsp">내가 쓴 QNA</a> <br />
<a href="/ProTwo/review/myWriterList.jsp?id=<%=sid%>">내가 쓴 리뷰</a> <br />
현재 나의 포인트 : <%=dto.getPoint() %>
<br/>
<br/>	
<%@ include file="/footer.jsp" %>	