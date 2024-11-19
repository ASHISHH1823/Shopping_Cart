<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<%@include file="../allcss.jsp"%>
</head>
<body>
	<%@include file="../navbar.jsp"%>
	<div class="container-fluid p-5 mt-5">
		<div class="row">
			<div class="col-md-3">
				<div class="card card-sh">
					<div class="card-header text-center">
						<p class="fs-4">Add Category</p>
						<c:if test="${not empty sessionScope.succMsg}">
							<p class="text-success fw-bold">${sessionScope.succMsg}</p>
							<c:set var="ignored" value="${sessionScope.remove('succMsg')}" />
						</c:if>
						<c:if test="${not empty sessionScope.errorMsg}">
							<p class="text-danger fw-bold">${sessionScope.errorMsg }</p>
							<c:set var="ignored" value="${sessionScope.remove('errorMsg')}" />
						</c:if>
					</div>
					<div class="card-body">
						<form action="/admin/savecategories" method="post"
							enctype="Multipart/form-data">
							<div class="mb-3">
								<label>Enter Category</label> <input type="text" name="name"
									class="form-control">
							</div>
							<div class="mb-3">
								<label>Status</label>
								<div class="form-check">
									<input class="form-check-input" type="radio" value="true"
										name="active" id="flexRadioDefault1" checked> <label
										class="form-check-label" for="flexRadioDefault1">Active</label>
								</div>
								<div class="form-check">
									<input class="form-check-input" type="radio" value="false"
										name="active" id="flexRadioDefault2"> <label
										class="form-check-label" for="flexRadioDefault2">Inactive</label>
								</div>



							</div>
							<div class="mb-3">
								<label>Upload image</label> <input type="file" name="file"
									class="form-control">
							</div>
							<button class="btn btn-primary col-md-12 mt-2">Save</button>
						</form>
					</div>
				</div>
			</div>
			<div class="col-md-8">
				<div class="card card-sh">
					<div class="card-header text-center fs-4">Category Details</div>
					<div class="card-body">
						<table class="table">
							<thead>
								<tr>
									<th scope="col">Sl No</th>
									<th scope="col">Category</th>
									<th scope="col">Status</th>
									<th scope="col">Image</th>
									<th scope="col">Action</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="category" items="${categorys }">
									<tr>
										<td>${category.id}</td>
										<td>${category.name}</td>
										<td>${category.isActive}</td>
										<td><img alt=""
											src="<c:url value='/images/categorysimg/${category.imagename }'></c:url>"
											width="50px" height="50px"></td>

										<td><a href="/admin/edit/${category.id }" class=" btn btn-primary btn-sm"><i
												class="fa-solid fa-pen-to-square"></i>EDIT</a></td>

										<td><a href="/admin/delete/${category.id }" class="btn btn-primary btn-sm"><i
												class="fa-solid fa-trash-can"></i>DELETE</a></td>


									</tr>
								</c:forEach>
							</tbody>
						</table>
						<div class="row">
					<div class="col-md-4">Total Category: ${Totalelement }</div>
					<div class="col-md-6">
						<
								<nav aria-label="Page navigation example">
									<ul class="pagination">
										<li class="page-item  ${ isFirst ? "disabled" : ""} %>"><a
											class="page-link" href="/admin/categories?pageNo=${ pageNo-1}">Previous</a></li>
										<c:forEach var="i" begin="1" end="${ Totalpages}">
											<li class="page-item ${pageNo +1 ==i ? "active":"" }"><a
												class="page-link" href="/admin/categories?pageNo=${i-1 }"> ${i}
											</a></li>
										</c:forEach>
										<li class="page-item ${isLast ? "disabled":"" }"><a
											class="page-link" href="/admin/categories?pageNo=${pageNo+1 }">Next</a></li>
									</ul>
							
						
					</div>
				</div>
					</div>
				</div>
			</div>

		</div>
	</div>
	<%@include file="../fotter.jsp"%>
</body>
</html>