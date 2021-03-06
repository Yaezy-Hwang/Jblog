<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%> 

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">
<script type="text/javascript" src="${pageContext.request.contextPath}/assets/js/jquery/jquery-1.12.4.js"></script>

</head>

<body>
	<div id="wrap">

		<c:import url="/WEB-INF/views/includes/blog-header.jsp"></c:import>
		<!-- 개인블로그 해더 -->
		
		<div id="content" class="clearfix">
			<div id="profilecate_area">
				<div id="profile">
					<c:if test="${blogVo.logoFile == 'default'}">
						<img id="proImg" src="${pageContext.request.contextPath}/assets/images/spring-logo.jpg">
					</c:if>
					<c:if test="${blogVo.logoFile != 'default'}">
						<img id="proImg" src="${pageContext.request.contextPath}/upload/${blogVo.logoFile}">
					</c:if>
					<div id="nick">${blogVo.userName}(${blogVo.id})님</div>
				</div>
				<div id="cate">
					<div class="text-left">
						<strong>카테고리</strong>
					</div>
					<ul id="cateList" class="text-left">
						<c:forEach items="${cateList}" var="cateVo">
							<li><a href="${pageContext.request.contextPath}/${blogVo.id}?crtCateNo=${cateVo.cateNo}">${cateVo.cateName}</a></li>
						</c:forEach>
					</ul>
				</div>
			</div>
			<!-- profilecate_area -->
			
			<div id="post_area">
				<c:if test="${not empty postVo}">
					<div id="postBox" class="clearfix">
							<div id="postTitle" class="text-left"><strong>${postVo.postTitle}</strong></div>
							<div id="postDate" class="text-left"><strong>${postVo.regDate}</strong></div>
							<div id="postNick">${blogVo.userName}(${blogVo.id})님</div>
					</div>
					<!-- //postBox -->
				
					<div id="post" >
						${fn:replace(postVo.postContent, newLine, "<br>") }
					</div>
					<!-- //post -->
				</c:if>
				
				<c:if test="${empty postVo}">
					<div id="postBox" class="clearfix">
							<div id="postTitle" class="text-left"><strong>등록된 글이 없습니다.</strong></div>
							<div id="postDate" class="text-left"><strong></strong></div>
							<div id="postNick"></div>
					</div>
					<!-- //postBox -->

					<!-- //post -->
				</c:if>
				<div id="comment" >
					<c:if test="${!empty authUser}">
						<form>
							<table>
								<colgroup>
									<col style="width: 100px;">
									<col style="width: 590px;">
									<col style="width: 100px;">
								</colgroup>
								<tr>
									<td>댓글</td>
									<td><input id="txtComment" type="text" name="comment" value=""></td>
									<td><button id="btnComm" type="button">등록</button></td>
								</tr>
							</table>
						</form>
					</c:if>
					<br>
					<table id="commT">
						<colgroup>
							<col style="width: 100px;">
							<col style="width: 500px;">
							<col style="width: 190px;">
						</colgroup>
						<c:forEach items="${commentList}" var="cmt">
							<tr>
								<td>${cmt.userName}</td>
								<td>${cmt.cmtContent}</td>
								<td>${cmt.regDate}</td>
							</tr>
						</c:forEach>
					</table>
					
					
				</div>
				
				<div id="list">
					<div id="listTitle" class="text-left"><strong>카테고리의 글</strong></div>
					<table>
						<colgroup>
							<col style="">
							<col style="width: 20%;">
						</colgroup>
						<c:forEach items="${postList}" var="postVo">
							<tr>
								<td class="text-left"><a href="${pageContext.request.contextPath}/${blogVo.id}?crtCateNo=${param.crtCateNo}&postNo=${postVo.postNo}">${postVo.postTitle}</a></td>
								<td class="text-right">${postVo.regDate}</td>
							</tr>
						</c:forEach>
					</table>
				</div>
				<!-- //list -->
			</div>
			<!-- //post_area -->
			
		</div>	
		<!-- //content -->
		<div class=></div>
		
	
		<c:import url="/WEB-INF/views/includes/blog-footer.jsp"></c:import>
		<!-- 개인블로그 푸터 -->
	</div>
	<!-- //wrap -->
</body>
<script type="text/javascript">
	$("#btnComm").on("click",function(){
		
		var commentVo = {
			cmtContent: $("#txtComment").val(),
			postNo: ${postVo.postNo},
			userNo: ${authUser.userNo}
		};
		
		$.ajax({

			//보낼 때 옵션
			url : "${pageContext.request.contextPath}/${blogVo.id}/comment",
			type : "post",
			data : commentVo,

			//받을 때 옵션
			dataType : "json",
			success : function(newCmt) {
				render(newCmt);

			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		})
			
	});

	function render(commentVo){
		var str = '';

		str += '<tr>';
		str += '	<td>'+ commentVo.userName +'</td>';
		str += '	<td>'+ commentVo.cmtContent +'</td>';
		str += '	<td>'+ commentVo.regDate +'</td>';
		str += '</tr>';
		
		$("#commT").prepend(str);
		
	};




</script>
</html>