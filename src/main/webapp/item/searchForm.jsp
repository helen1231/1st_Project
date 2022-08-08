<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/top.jsp" %>
<br/>
<br/>
<h2>상품 검색</h2>   
<form action="/ProTwo/item/search.jsp" method="get">
   	브랜드선택
   	<input type="checkbox" name="searchBrand1" value="0" id="brand1" /> 롤렉스
   	<input type="hidden" name="searchBrand1" value='3' id="brand1_hidden"/>
	<input type="checkbox" name="searchBrand2" value="1"> IWC
	<input type="hidden" name="searchBrand2" value='3' id="brand2_hidden"/>
	<input type="checkbox" name="searchBrand3" value="2"> 오데마피게 
	<input type="hidden" name="searchBrand3" value='3' id="brand3_hidden"/>
   	<br>
	<select name="column">
		<option value="itemName">상품명</option>
	</select>
	<input type="text" name="search" />
	<input type="submit" value="검색" />
</form>
<br/>
<br/>	
<%@ include file="/footer.jsp" %>	