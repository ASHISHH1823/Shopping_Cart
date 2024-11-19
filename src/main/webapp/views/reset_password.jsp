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
						<p class="fs-4 text-center">Reset Password</p>
					</div>
					<div class="card-body">
						<form action="/reseting_password" method="post">
							<div class="mb-3">
								<label class="form-label">New Password</label> <input
									class="form-control" name="password" type="password">
							</div>
							<div class="mb-3">
								<label class="form-label">Confirm Password</label> <input
									class="form-control" type="password">
							</div>
							<input type="hidden" value="${token}" name="token">
							<button class="btn bg-primary text-white col-md-12" type="submit">Reset</button>
						</form>
					</div>
				</div>
			</div>
		</div>
	</div>
	<%@include file="fotter.jsp"%>

</body>
</html>