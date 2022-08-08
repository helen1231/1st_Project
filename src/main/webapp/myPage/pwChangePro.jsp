<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:useBean id="dto" class="account.P02_accountDTO" />
<jsp:useBean id="dao" class="account.P02_accountDAO" />
<%
	String id = request.getParameter("id");
	String pw = request.getParameter("pw"); 		// DB에 저장된 비밀번호를 hidden으로 보내서 받음
	String pw_old = request.getParameter("pw_old"); // 고객이 확인용으로 입력한 기존 비밀번호
	String pw_new = request.getParameter("pw_new");
	String pw_new_check = request.getParameter("pw_new_check");
	
	if(pw_old.equals(pw)){
		if(pw_new.equals(pw_new_check)){
			int result = dao.pwChange(id, pw_old, pw_new);
			if(result == 1){
			
%>			<script>
				alert("비밀번호가 변경되었습니다.");
				window.location="/ProTwo/myPage/updateForm.jsp";
			</script>
<%			}else{
%>				<script>
					alert("변경할 비밀번호를 입력하여 주세요.");
					history.go(-1);
				</script>
<%			}
		}else{
%>	<script>
		alert("변경할 비밀번호를 동일하게 입력하여 주세요.");
		history.go(-1);
	</script>
<%		}
	}else{ %>	
		<script>
			alert("기존 비밀번호를 확인하여 주세요.");
			history.go(-1);
		</script>	
<%	}%>