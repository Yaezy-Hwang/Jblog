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
			
				<table id="admin-cate-list">
					<colgroup>
						<col style="width: 50px;">
						<col style="width: 200px;">
						<col style="width: 100px;">
						<col>
						<col style="width: 50px;">
					</colgroup>
		      		<thead>
			      		<tr>
			      			<th>번호</th>
			      			<th>카테고리명</th>
			      			<th>포스트 수</th>
			      			<th>설명</th>
			      			<th>삭제</th>      			
			      		</tr>
		      		</thead>
		      		<tbody id="cateList">
		      			<!-- 리스트 영역 -->
		      			<c:set var="listSize" value="${cateList.size()}" scope="page"></c:set>
				      	<c:forEach items="${cateList}" var="cate" varStatus="ro">
			      			<tr id="cate-${cate.cateNo}">
								<td>${listSize - ro.index}</td>
								<td>${cate.cateName}</td>
								<td>${cate.countPost}</td>
								<td>${cate.description}</td>
							    <td class='text-center'>
							    	<img class="btnCateDel" data-post="${cate.countPost}" data-cateno="${cate.cateNo}" src="${pageContext.request.contextPath}/assets/images/delete.jpg">
							    </td>
							</tr>
						</c:forEach>
						
						<!-- 리스트 영역 -->
					</tbody>
				</table>
      	
		      	<table id="admin-cate-add" >
		      		<colgroup>
						<col style="width: 100px;">
						<col style="">
					</colgroup>
		      		<tr>
		      			<td class="t">카테고리명</td>
		      			<td><input type="text" name="name" value=""></td>
		      		</tr>
		      		<tr>
		      			<td class="t">설명</td>
		      			<td><input type="text" name="desc"></td>
		      		</tr>
		      	</table> 
			
				<div id="btnArea">
		      		<button id="btnAddCate" class="btn_l" type="submit" >카테고리추가</button>
		      	</div>
			
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

	$(document).ready(function(){
		$("#cate").addClass("selected");
	});
	
	//카테고리 추가
	$("#btnAddCate").on("click", function(){
		
		var newCategory ={
			cateName: $("[name='name']").val(),
			description: $("[name='desc']").val()
		}; 
		
		$.ajax({

			//보낼 때 옵션
			url : "${pageContext.request.contextPath}/${blogVo.id}/admin/addCategory",
			type : "post",
			data : newCategory,

			//받을 때 옵션
			dataType : "json",
			success : function(newCate) {
				render(newCate);

				//받은 다음 form box 비우기
				$("[name='name']").val("");
				$("[name='desc']").val("");

			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		})
		
	});
	
	//카테고리 삭제
	$("#cateList").on("click", ".btnCateDel", function(){
		var cateNo = $(this).data("cateno");
		var countPost = $(this).data("post");
		
		if(countPost != 0){
			alert("삭제할 수 없습니다.");
			
			return false;
		}
		
		$.ajax({

			//보낼 때 옵션
			url : "${pageContext.request.contextPath}/${blogVo.id}/admin/delCategory",
			type : "post",
			data : {cateNo: cateNo},

			//받을 때 옵션
			dataType : "json",
			success : function() {
				$("#cate-"+cateNo).remove();
				
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		})
		
	});
	
	function render(newCate){
		
		var str = '';
		str += '<tr id="cate-'+ newCate.cateNo +'">';
		str += '	<td> New </td>';
		str += '	<td>'+ newCate.cateName +'</td>';
		str += '	<td> 0 </td>';
		str += '	<td>'+ newCate.description +'</td>';
		str += '    <td class="text-center">';
		str += '    	<img class="btnCateDel" data-post="0" data-cateno="'+ newCate.cateNo +'" src="${pageContext.request.contextPath}/assets/images/delete.jpg">';
		str += '    </td>';
		str += '</tr>';
		
		
		$("#cateList").prepend(str);
	}

</script>
</html>