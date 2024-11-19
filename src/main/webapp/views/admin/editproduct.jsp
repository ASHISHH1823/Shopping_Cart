<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
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
			<div class="col-md-8 offset-md-2">
				<div class="card card-sh">
					<div class="card-header text-center">
						<p class="fs-4">Edit Product</p>
						<c:if test="${not empty sessionScope.succMsg}">
							<p class="text-success fw-bold">${sessionScope.succMsg}</p>
							<c:set var="ignored" value="${sessionScope.remove('succMsg')}" />
						</c:if>
						<c:if test="${not empty sessionScope.errorMsg}">
							<p class="text-danger fw-bold">${sessionScope.errorMsg}</p>
							<c:set var="ignored" value="${sessionScope.remove('errorMsg')}" />
						</c:if>
					</div>
					<div class="card-body">
						<form:form action="/admin/updateproducts" method="post"
							modelAttribute="command" enctype="multipart/form-data">
							<form:hidden path="id" />
							<div class="mb-3">
								<label>title</label>
								<form:input path="title" class="form-control" />
							</div>
							<div class="mb-3">
								<label>Description</label>
								<textarea rows="3" cols="" class="form-control"
									name="description">${command.description} </textarea>
							</div>


							<div class="mb-3">
								<label>Category</label>
								<form:select path="category" class="form-control">
									<!-- Selected category option -->
									<form:option value="${command.category}">${command.category}</form:option>

									<!-- Loop through categories for other options -->
									<c:forEach var="c" items="${catt}">
										<form:option value="${c.name}">${c.name}</form:option>
									</c:forEach>
								</form:select>
							</div>

							<div class="row">
								<div class="mb-3 col">
									<label> Enter Price</label>
									<form:input path="price" class="form-control" />
								</div>
							</div>
							<div class="row">
								<div class="mb-3 col">
									<label>Discount</label>
									<form:input path="discount" class="form-control"
										value="${command.discount }" />
								</div>
								<div class="mb-3 col">
									<label>Discount price</label>
									<form:input path="discountprice" class="form-control"
										value="${command.discountprice }" readonly="true" />
								</div>
								<div class="mb-3">
									<label>Status</label>

									<div class="form-check">
										<input class="form-check-input" type="radio" checked
											value="true" name="isactive" id="flexRadioDefault1">
										<label class="form-check-label" for="flexRadioDefault1">
											Active </label>
									</div>
									<div class="form-check">
										<input class="form-check-input" type="radio" name="isactive"
											value="false" id="flexRadioDefault2"> <label
											class="form-check-label" for="flexRadioDefault2">
											Inactive </label>
									</div>

								</div>

							</div>
							<div class="mb-3">
								<label>stock</label>
								<form:input path="stock" class="form-control" />
							</div>

							<div class="mb-3">
								<label>Upload Image</label> <input type="file" name="file"
									class="form-control" />
							</div>
							<img alt=""
								src="<c:url value='/images/productimg/${command.imagename }'></c:url>"
								width="100px" height="100px">
							<button class="btn btn-primary col-md-12 mt-2">Update</button>
						</form:form>
					</div>
				</div>
			</div>
		</div>
	</div>

	<%@include file="../fotter.jsp"%>

</body>
</html>