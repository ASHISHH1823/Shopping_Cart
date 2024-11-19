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
			<div class="col-md-6 p-5">
				<img alt="" src="views/image/ecom.png" width="100%" height="400px">
			</div>
			<div class="col-md-6 mt-5 p-5">
				<div class="card">
					<div class="card-header">
						<p class="fs-4 text-center">Login</p>

						<c:if
							test="${not empty sessionScope['SPRING_SECURITY_LAST_EXCEPTION']}">
							<div class="alert alert-danger text-center">
								${sessionScope['SPRING_SECURITY_LAST_EXCEPTION'].message}</div>
						</c:if>


						<c:if test="${not empty logout}">
							<div class="text-success text-center">${logout}</div>

						</c:if>



					</div>
					<div class="card-body">
						<form action="/login" method="post">
							<div class="mb-3">
								<label class="form-label">Email</label> <input
									class="form-control" name="username" type="email">
							</div>
							<div class="mb-3">
								<label class="form-label">Password</label> <input
									class="form-control" name="password" type="password">
							</div>
							<button class="btn bg-primary text-white col-md-12" type="submit">login</button>
						</form>
					</div>
					<div class="card-fotter text-center">
						<a href="/forgot-password" class="text-decoration-none">Forget Password</a><br>
						Don't have an account? <a href="#" class="text-decoration-none">Create
							New Account</a>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@include file="fotter.jsp"%>

</body>
</html>