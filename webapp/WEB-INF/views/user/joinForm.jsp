<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBlog</title>
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">

</head>
<body>
	<div id="center-content">

		<c:import url="/WEB-INF/views/includes/main-header.jsp"></c:import>
		<!-- 메인 해더 -->

		<div>
			<form id="joinForm" method="post" action="${pageContext.request.contextPath}/user/join">
				<table>
					<colgroup>
						<col style="width: 100px;">
						<col style="width: 170px;">
						<col style="">
					</colgroup>
					<tr>
						<td><label for="txtId">아이디</label></td>
						<td><input id="txtId" type="text" name="id" value=""></td>
						<td><button id="btnIdCheck" type="button">아이디체크</button></td>
					</tr>
					<tr>
						<td></td>
						<td id="tdMsgOk" colspan="2">사용할 수 있는 아이디 입니다.</td>
						<td id="tdMsgNo" colspan="2">다른 아이디로 가입해 주세요.</td> 
					</tr>
					<tr>
						<td><label for="txtPassword">패스워드</label></td>
						<td><input id="txtPassword" type="password" name="password"
							value=""></td>
						<td></td>
					</tr>
					<tr>
						<td><label for="txtUserName">이름</label></td>
						<td><input id="txtUserName" type="text" name="userName"
							value=""></td>
						<td></td>
					</tr>
					<tr>
						<td><span>약관동의</span></td>
						<td colspan="3">
							<input id="chkAgree" type="checkbox" name="agree" value="y">
							<label for="chkAgree">서비스 약관에 동의합니다.</label>
						</td>
					</tr>
				</table>
				<div id="btnArea">
					<button id="btnJoin" class="btn" type="submit">회원가입</button>
				</div>

			</form>

		</div>
		
		<c:import url="/WEB-INF/views/includes/main-footer.jsp"></c:import>
		<!-- 메인 푸터  자리-->

	</div>

</body>
<script type="text/javascript">

	//아이디 체크 메세지 초기화
	window.onload = function(){
		$("#tdMsgOk").hide();
		$("#tdMsgNo").hide();
	};
	
	//중복 아이디 체크
	$("#btnIdCheck").on("click", function(){
		
		var newId = $("#txtId").val();

		$.ajax({
			
			url : "${pageContext.request.contextPath}/user/idcheck",		
			type : "post",
			data : {newId: newId},

			dataType : "json",
			success : function(result){
				console.log(result);
				
				/*성공시 처리해야될 코드 작성*/
				if(result == true){
					$("#tdMsgNo").hide();
					$("#tdMsgOk").show();
				} else{
					$("#tdMsgOk").hide();
					$("#tdMsgNo").show();
				}
				
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			} 
		})
	});
	
	//약관 동의 체크
	$("#joinForm").on("submit", function(){
		
		var agree = $("#chkAgree").is(":checked");
		
		if(agree == true){
			return true;
		}else {
			alert("약관에 동의해주세요");
			
			return false;
		}
	});


</script>

</html>