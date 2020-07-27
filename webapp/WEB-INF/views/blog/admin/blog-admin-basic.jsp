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
	<div id="wrap">
	
		<c:import url="/WEB-INF/views/includes/blog-header.jsp"></c:import>
		<!-- 개인블로그 해더 -->

		<div id="content">

			<c:import url="/WEB-INF/views/includes/blog-admin-menu.jsp"></c:import>
			<!-- //admin-menu -->
			
			<div id="admin-content">
				<form action="${pageContext.request.contextPath}/${blogVo.id}/admin/updateBasic" method="post" enctype="multipart/form-data">
	 		      	<table id="admin-basic">
	 		      		<colgroup>
							<col style="width: 100px;">
							<col style="">
						</colgroup>
			      		<tr>
			      			<td><label for="textTitle">블로그 제목</label></td>
			      			<td><input id="textTitle" type="text" name="blogTitle" value="${blogVo.blogTitle}"></td>
			      		</tr>
			      		<tr>
			      			<td><label>로고이미지</label></td>
			      			<td class="text-left">
				      				<img id="proImg"
				      					<c:if test="${blogVo.logoFile == 'default'}">
				      						src="${pageContext.request.contextPath}/assets/images/spring-logo.jpg"
				      					</c:if>
										<c:if test="${blogVo.logoFile != 'default'}">
											src="${pageContext.request.contextPath}/upload/${blogVo.logoFile}"
										</c:if>
									> <!-- img 태그 끝 -->
								
							</td>
			      			   
			      		</tr>      		
			      		<tr>
			      			<td>&nbsp;</td>
			      			<td><input id="textLogo" type="file" name="logoFile"></td>      			
			      		</tr>           		
			      	</table>
			      	<div id="btnArea">
			      		<button class="btn_l" type="submit" >기본설정변경</button>
			      	</div>
				</form>
			
			</div>
			<!-- //admin-content -->
		</div>	
		<!-- //content -->
		
		<c:import url="/WEB-INF/views/includes/blog-footer.jsp"></c:import>
		<!-- 개인블로그 푸터 -->
	</div>
	<!-- //wrap -->
</body>
<script type="text/javascript">
	//상단 탭 컬러링
	$(document).ready(function(){
		$("#basic").addClass("selected");
	});

	//프로필 미리보기
    $("#textLogo").on("change", function(){
    	var reader = new FileReader();
    	
        reader.onload = function (e) {
            document.getElementById("proImg").src = e.target.result;
        };

        reader.readAsDataURL(this.files[0]);
    });
    
</script>
</html>
