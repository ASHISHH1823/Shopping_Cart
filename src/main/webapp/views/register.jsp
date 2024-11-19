<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<%@include file="allcss.jsp"%>
</head>
<body>
	<%@include file="navbar.jsp"%>
	<div class="container mt-5 p-5">
		<div class="row">
			<div class="col-md-5 p-5">
				<img alt="" src="views/image/ecom.png" width="100%" height="400px">
			</div>
			<div class="col-md-7 p-2">
				<div class="card">
					<div class="card-header">
						<p class="fs-4 text-center">Register</p>
						
						<c:if test="${not empty sessionScope.succMsg}">
							<p class="text-success fw-bold text-center">${sessionScope.succMsg}</p>
							<c:set var="ignored" value="${sessionScope.remove('succMsg')}" />
						</c:if>
						
						<c:if test="${not empty sessionScope.errorMsg}">
							<p class="text-danger fw-bold text-center">${sessionScope.errorMsg }</p>
							<c:set var="ignored" value="${sessionScope.remove('errorMsg')}" />
						</c:if>
						
					</div>
					<div class="card-body">
						<form action="/saveusers" enctype="multipart/form-data" method="post">
							<div class="row">
								<div class="col">
									<label class="form-label">Full Name</label> <input required="required"
										class="form-control" name="name" type="text">
								</div>
								<div class="col">
									<label class="form-label">Mobile Number</label> <input required="required"
										class="form-control" name="mobilenumber" type="text">
								</div>
							</div>
							<div class="mb-3">
								<label class="form-label">Email</label> <input required="required"
									class="form-control" name="email" type="email">
							</div>
							<div class="row">
								<div class="col">
									<label class="form-label">Address</label> <input required="required"
										class="form-control" name="address" type="text">
								</div>
							</div>
							<div class="col">
								<label class="form-label">City</label> <input required="required"
									class="form-control" name="city" type="text">
							</div>
							<div class="row">
								<div class="col">
									<label class="form-label">State</label> <input required="required"
										class="form-control" name="state" type="text">
								</div>
								<div class="col">
									<label class="form-label">Pincode</label> <input required="required"
										class="form-control" name="pincode" type="number">
								</div>
							</div>
							<div class="row">
								<div class="col">
									<label class="form-label">Password</label> <input required="required"
										class="form-control" name="password" type="password">
								</div>
								<div class="col">
									<label class="form-label">Conform Password</label> <input required="required"
										class="form-control" name="cpassword" type="password">
								</div>
							</div>
							<div class="mb-4">
								<label class="form-label">profile Image</label> <input required="required"
									class="form-control" name="file" type="file">
							</div>
							<button type="submit" class="btn bg-primary text-white col-md-4">
								Register</button>

						</form>
					</div>
					<div class="card-footer text-center">
						have an account? <a href="/signin" class="text-decoration-none">Login</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@include file="fotter.jsp"%>
</body>
</html>