<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<%@include file="../allcss.jsp" %>
<body>
<%@include file="../navbar.jsp"%>
<div class="container mt-5 p-5">
			<div class="row">
				<div class="col-md-6 offset-md-3 text-center">
					<i class="fa-solid fa-circle-check fa-5x text-success"></i>
					<p class="fs-3">Product Ordered Successfully</p>
					<p class="fs-4">Product will be delivered with in 7 days</p>
					<div class="text-center">
						<a href="/" class="btn btn-primary">Home</a> <a href="/user/user-orders"
							class="btn btn-primary">Your Order</a>
					</div>
				</div>
			</div>

		</div>
		<%@include file="../fotter.jsp"%>
</body>
</html>