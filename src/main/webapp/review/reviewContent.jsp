<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="review.reviewDAO" %>
<%@ page import="review.reviewDTO" %>
<%@ page import="java.sql.Timestamp" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date"%>
<%@ include file="/top.jsp" %>
<br/>
<br/>
<h2>리뷰보기</h2>
<%
	sid = (String)session.getAttribute("sid");
	String admin = (String)session.getAttribute("admin");	
	if(sid == null){%>
	<script>
		alert("로그인후 사용가능합니다.");
		window.location="/ProTwo/account/loginForm.jsp";
	</script>
<%	}else{ 
		//조회수증가, renum 글검색 , 좋아요 여부확인 후 0 or 1 넘김
		int renum = Integer.parseInt(request.getParameter("renum"));
		
		reviewDAO dao = new reviewDAO();
		reviewDTO dto = dao.readContent(renum, sid);
		int goodcheck = dao.goodcheck(renum, sid); 
		if(dto.getStatus() != 0){%>
		<script>
			alert("삭제된 게시글입니다.");
			history.go(-1);
		</script>
<%		} 
		if(admin != null){%>
			<button type="button" onclick="location.href='contentDeleteAdmin.jsp?renum=<%=renum%>'">글삭제</button>
<%		}
		int goodCheck = dto.getGoodCheck();
		Date date = new Date(dto.getRevreg().getTime());
		//api에서 Timestamp.getTime()은 1970년부터의 기간을 long타입으로 리턴 
		// Date의 생성자가 long 타입을 받기 때문에 long타입의 값이 넘어감
%>
		<a href="/ProTwo/review/reviewList.jsp">리뷰 목록</a>
<%		if(sid.equals(dto.getId())){%>
			<a href="/ProTwo/review/contentUpdateForm.jsp?renum=<%=dto.getRenum()%>">글수정</a>
			<a href="/ProTwo/review/contentDeleteForm.jsp?renum=<%=dto.getRenum()%>">글삭제</a>
<%		}%>
	<div id = "header" style="height : 120px; width:512px;">
		작성자 : <a href="/ProTwo/review/WriterSearch.jsp?id=<%=dto.getId()%>&renum=<%=renum%>"><%=dto.getId()%></a>	<%=dao.calculateTime(date)%> 
<%		if(!(dto.getImage().equals("0"))){ %>
			<div id = "body" style="height : 600px; width:512px; ">
			<img width="300" height="400" src ="/ProTwo/image/<%=dto.getImage()%> "/>
			</div>
<%		}else{ %>
			<h2>no image</h2>
<%		} %>
		<div> <%=dto.getContent()%> </div>
		<div id = "footer" style="backgound-color : yellow; height : 200px;">
<%		
		if(goodCheck == 0){ %>
			<a href="/ProTwo/review/goodPro.jsp?renum=<%=renum%>&id=<%=sid%>"><img width="75px" height="75" src="/ProTwo/image/nullheart.png"/></a>
			<a href="javascript:void(0);" onclick="window.open('/ProTwo/review/comments.jsp?renum=<%=dto.getRenum()%>','comments','width=400 height=600')"><img width="75px" height="75" src="/ProTwo/image/talk.png"></a>[<%=dao.commentsCount(renum)%>]
<%		}else{%>
			<a href="/ProTwo/review/goodMinor.jsp?renum=<%=renum%>&id=<%=sid%>"><img width="75px" height="75" src="/ProTwo/image/pinkheart.png"/></a>
			<a href="javascript:void(0);" onclick="window.open('/ProTwo/review/comments.jsp?renum=<%=dto.getRenum()%>','comments','width=400 height=600')"><img width="75px" height="75" src="/ProTwo/image/talk.png"></a>[<%=dao.commentsCount(renum)%>]
<%		}
		int count = dao.goodcount(renum);%>
				<h5><a href="javascript:void(0);" onclick="window.open('/ProTwo/review/whoGood.jsp?renum=<%=renum%>&good=<%=count%>', 'good', 'width=200, height=200');">좋아요</a> <%=count%>개</h5>
		</div>
	<br/>
	<br/>	
	<%@ include file="/footer.jsp" %>	
<%	} %>
	</div>