<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/top.jsp" %>
<br/>
<br/>
<h2>사이트맵</h2>
<table border="1">
	<tr>
		<th>상품 페이지</th>
		<th>회원 페이지 </th>
		<th>나의 메뉴 </th>
		<th>고객센터 </th>
	</tr>
	<tr>
		<td><a href="/ProTwo/item/list.jsp">상품보기</a></td>
		<td><a href="/ProTwo/account/loginForm.jsp">로그인</a></td>
		<td><a href="/ProTwo/myPage/buyList.jsp">구매내역</a></td>	
		<td><a href="/ProTwo/qna/list.jsp">QNA</a></td>
	</tr>
	<tr>
		<td><a href="/ProTwo/item/searchForm.jsp">상품검색</a></td>
		<td><a href="/ProTwo/account/idFindForm.jsp">아이디찾기</a></td>
		<td><a href="/ProTwo/myPage/cartList.jsp">관심상품</a></td>	
		<td> </td>
	</tr>
	<tr>
		<td><a href="/ProTwo/auction/auction.jsp">경매</a></td>
		<td><a href="/ProTwo/account/pwFindForm.jsp">비밀번호찾기</a></td>
		<td><a href="/ProTwo/myPage/pwChangeForm.jsp">비밀번호변경</a></td>	
		<td> </td>
	</tr>
	<tr>
		<td><a href="/ProTwo/review/reviewList.jsp">전체리뷰보기</a></td>
		<td><a href="/ProTwo/account/joinForm.jsp">회원가입</a></td>
		<td><a href="/ProTwo/myPage/updateAdrs.jsp">주소변경</a></td>	
		<td> </td>
	</tr>
	<tr>
		<td> </td>
		<td> </td>
		<td><a href="/ProTwo/myPage/buyList.jsp">구매내역조회</a></td>	
		<td> </td>
	</tr>
	<tr>
		<td> </td>
		<td> </td>
		<td><a href="/ProTwo/myPage/sellList.jsp">판매내역조회</a></td>	
		<td> </td>
	</tr>
	<tr>
		<td> </td>
		<td> </td>
		<td><a href="/ProTwo/myPage/deleteForm.jsp">회원탈퇴</a></td>	
		<td> </td>
	</tr>
	<tr>
		<td> </td>
		<td> </td>
		<td><a href="/ProTwo/myPage/updateForm.jsp">회원정보수정</a></td>
		<td> </td>
	</tr>
	<tr>
		<td> </td>
		<td> </td>
		<td><a href="/ProTwo/review/myWriterList.jsp">나의 리뷰</a></td>
		<td> </td>
	</tr>
	<tr>
		<td> </td>
		<td> </td>
		<td><a href="/ProTwo/myPage/qnaList.jsp">나의 QNA</a></td>
		<td> </td>
	</tr>
</table>
