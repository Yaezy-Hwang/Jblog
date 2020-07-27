<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>JBlog</title>
<link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/jblog.css">

</head>
<body>
	<div id="center-content">

		<c:import url="/WEB-INF/views/includes/main-header.jsp"></c:import>
		<!--메인 해더 자리 -->

		<form id="search-form">
			<fieldset>
				<input type="text" name="keyword">
				<button id="btnSearch" type="submit">검색</button>
			</fieldset>

			<fieldset>
				<label for="rdo-title">블로그 제목</label> <input id="rdo-title"
					type="radio" name="kwdOpt" value="optTitle"> <label
					for="rdo-userName">블로거 이름</label> <input id="rdo-userName"
					type="radio" name="kwdOpt" value="optName">
			</fieldset>
		</form>

		<div id="resultList">
		
			<c:forEach items="${rList}" var="result">
				<table>
					<colgroup>
						<col style="width: 100px;">
						<col style="width: 300px;">
						<col style="width: 100px;">
						<col style="width: 100px;">
					</colgroup>
					<tr>
						<td>
							<a href="${pageContext.request.contextPath}/${result.id}" target="_blank">
								<img id="resultImg"
			      					<c:if test="${result.logoFile == 'default'}">
			      						src="${pageContext.request.contextPath}/assets/images/spring-logo.jpg"
			      					</c:if>
									<c:if test="${result.logoFile != 'default'}">
										src="${pageContext.request.contextPath}/upload/${result.logoFile}"
									</c:if>
								>
							</a>
						</td>
						<td><a href="${pageContext.request.contextPath}/${result.id}" target="_blank">${result.blogTitle}</a></td>
						<td>${result.userName}(${result.id})</td>
						<td>${result.joinDate}</td>
					</tr>
				</table>
			</c:forEach>


		</div>

		<c:import url="/WEB-INF/views/includes/main-footer.jsp"></c:import>
		<!-- 메인 푸터  자리-->


	</div>
	<!-- //center-content -->
</body>
</html>