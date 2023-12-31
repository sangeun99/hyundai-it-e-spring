<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ include file="../includes/header.jsp" %>

<script type="text/javascript">
$(document).ready(function() {
	var formObj = $("form");
	
	$('button').on("click", function(e) {
		e.preventDefault();
	
		var operation = $(this).data("oper");
		
		console.log(operation);
		
		if (operation === 'remove') {
			formObj.attr("action", "/board/remove");
		}
		else if (operation === 'list') {
			formObj.attr("action", "/board/list").attr("method", "get");
			var pageNumTag = $("input[name='pageNum']").clone();
			var amountTag = $("input[name='amount']").clone();
			
			formObj.empty();
			formObj.append(pageNumTag);
			formObj.append(amountTag);
		}
		formObj.submit();
	})
})
</script>

<div class="row">
	<div class="col-lg-12">
		<h1 class="page-header">Board Modify</h1>
	</div>
</div>

<div class="row">
	<div class="col-lg-12">
		<div class="panel panel-default">
			<div class="panel-heading">Board Board Modify Page</div>
			
			<div class="panel-body">
				<form role="form" action="/board/modify" method="post">
				
					<input type='hidden' name='pageNum' value='<c:out value="${cri.pageNum}"/>'>
					<input type='hidden' name='amount' value='<c:out value="${cri.amount}"/>'>
					
					<div class="form-group">
						<label>Bno</label> <input class="form-control" name='bno' value='<c:out value="${board.bno}"/>' readonly="readonly">
					</div>
					
					<div class="form-group">
						<label>Title</label> <input class="form-control" name='title' value='<c:out value="${board.title}"/>'>
					</div>
					
					<div class="form-group">
						<label>Text area</label> <textarea class="form-control" rows="3" name='content'><c:out value="${board.content}"/></textarea>
					</div>
					
					<div class="form-group">
						<label>Writer</label> <input class="form-control" name='writer' value='<c:out value="${board.writer}"/>' readonly="readonly">
					</div>
				
					<button data-oper='modify' class="btn btn-default" >Modify</button>
					<button data-oper='remove' class="btn btn-default" >Remove</button>
					<button data-oper='list' class="btn btn-info">List</button>
				</form>
			</div>
		</div>
	</div>
</div>
<%@ include file="../includes/footer.jsp" %>