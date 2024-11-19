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
	<div class="container p-5 mt-3">
		<div class="row">
			<div class="col-md-6 offset-md-3">
				<div class="card card-sh">
					<div class="card-header text-center fs-4">
						<p>Add Product</p>
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
						<form action="/admin/saveproduct" method="post"
							enctype="multipart/form-data">
							<div class="mb-3">
								<label>Enter title</label> <input type="text" name="title"
									class="form-control">
							</div>
							<div class="mb-3">
								<label>Enter Description</label>
								<textarea rows="3" cols="" class="form-control"
									name="description"></textarea>
							</div>
							<div class="mb-3">
								<label>Category</label> <select class="form-control"
									name="category">
									<option>--select--</option>

									<c:forEach var="c" items="${categoriess}">
										<option>${c.name}</option>
									</c:forEach>

								</select>
							</div>
							<div class="mb-3">
								<label>Enter Price</label> <input type="number" name="price"
									class="form-control">
							</div>
							<div class="mb-3">
								<label>Status</label>

								<div class="form-check">
									<input class="form-check-input" type="radio" checked
										value="true" name="isactive" id="flexRadioDefault1"> <label
										class="form-check-label" for="flexRadioDefault1">
										Active </label>
								</div>
								<div class="form-check">
									<input class="form-check-input" type="radio" name="isactive"
										value="false" id="flexRadioDefault2"> <label
										class="form-check-label" for="flexRadioDefault2">
										Inactive </label>
								</div>

							</div>
							<div class="row">
								<div class="mb-3 col">
									<label>Enter Stock</label> <input type="number" name="stock"
										class="form-control">
								</div>
								<div class="mb-3 col">
									<label>Upload image</label> <input type="file" name="file"
										class="form-control">
								</div>
							</div>
							<button class="btn btn-primary col-md-12">submit</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<%@include file="../fotter.jsp"%>

</body>
</html>