<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="bid.P02_bidDTO"%>
<%@ page import="auction.P02_auctionDTO" %>
<%@ page import="auction.P02_auctionDAO" %>
<%@ page import="java.sql.Timestamp" %>
<%@ include file="/top.jsp" %>
<br/>
<br/>
<h2>경매</h2>
<%
	sid = (String)session.getAttribute("sid");
	String admin = (String)session.getAttribute("admin");
	if(!(sid != null || admin != null)){
%>
	<script>
		alert("로그인 후 사용가능합니다.");
		window.location="/ProTwo/account/loginForm.jsp";
	</script>
<% }
	P02_auctionDAO dao = new P02_auctionDAO();
	P02_auctionDTO dto = dao.readContent();
	if(dto == null){
%>
	<h4>현재는 경매가 진행중이 아닙니다</h4><br/>
	<input type="button" value="메인으로" onclick="window.location='/ProTwo/account/main.jsp'" />
<% 		
	}else{ 
		int aucNum = dto.getAucNum();
		P02_bidDTO bdto = dao.confirmProduct(aucNum);
		
		Date today = new Date();
		long current = today.getTime();
		long endTime = dto.getEndReg().getTime();
		long remainTime = endTime - current ;
%>
<table  border=1>
	<tr><td>상품명</td><td colspan="2"><%=dto.getAucName() %></td></tr>
	<tr><td colspan="3"><img width="200" height="200" src="/ProTwo/image/<%=dto.getAucImage() %>" ></td></tr>
	<tr><td colspan="3">상품정보</td></tr>
	<tr><td colspan="3"><%=dto.getAucContent() %></td></tr>
	<tr><td>현재최고가격</td><td>입찰자</td><td>마감시간</td></tr>
	<tr>
		<td><%=bdto.getPrice() %></td>
<%
			if(bdto.getId() == null){
%>
			<td>현재 입찰자가 없습니다</td>
<%			}else{%>
			<td><%=bdto.getId() %></td>
<%			}%>
			<td><%=dto.getEndReg() %></td>
		</tr>
	</table>
<%		
		if(remainTime > 0){ %>	
			<input type="button" value="경매참여하기" onclick="window.location='/ProTwo/auction/bidForm.jsp?aucNum=<%=dto.getAucNum() %>' " />
			<input type="button" value="메인으로" onclick="window.location='/ProTwo/account/main.jsp'" />
<%			
			if(admin != null){%>
				<input type="button" value="경매즉시종료" onclick="window.location='/ProTwo/auction/auctionFinish.jsp'" />
<%			}
		}else{ 
%>
		<h4>경매가 종료되었습니다</h4>
<%
			if(admin != null){
%>
			<input type="button" value="정산하기" onclick="window.location='/ProTwo/auction/result.jsp?aucNum=<%=dto.getAucNum() %>'" />
<%			}
		}
	} %>
<br/>
<br/>	
<%@ include file="/footer.jsp" %>		

<!-- 경매는 이벤트 형식, 정기적으로 한 품목만 올라온다
readcontent로 현재 경매품에 대한 정보를 불러오고 confirmproduct로 현재까지 입찰된 내용 중 가장 상위 입찰자를 표시해준다
remaintime을 통해 현재시간과 경매시간의 차이가 양수일때만 입찰가능하고 음수이면 경매가 종료된다
상품등록과 정산(경매종료 이후 상태변환 및 결과 출력)은 관리자만 할 수 있다  -->