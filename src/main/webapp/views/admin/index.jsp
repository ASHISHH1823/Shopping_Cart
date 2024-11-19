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
	<div class="container p-5">
		<p class="text-center fs-3 mt-4">Admin dashboard</p>
		<div class="row p-5">
			<div class="col-md-4 mt-2">
				<a href="/admin/addproducts" class="text-decoration-none">
					<div class="card card-sh">
						<div class="card-body text-center text-primary">
							<i class="fa-solid fa-square-plus fa-3x"></i>
							<h4>Add Products</h4>
						</div>
					</div>
				</a>
			</div>
			<div class="col-md-4 mt-2">
				<a href="/admin/categories" class="text-decoration-none">
					<div class="card card-sh">
						<div class="card-body text-center text-warning">
						<i class="fa-solid fa-list fa-3x"></i>
							<h4>Category</h4>
						</div>
					</div>
				</a>
			</div>
			<div class="col-md-4 mt-2">
				<a href="/admin/products" class="text-decoration-none">
					<div class="card card-sh">
						<div class="card-body text-center text-success">
						<i class="fa-solid fa-table-list fa-3x"></i>
							<h4>view product</h4>
						</div>
					</div>
				</a>
			</div>
			<div class="col-md-4 mt-4">
				<a href="/admin/orderss" class="text-decoration-none">
					<div class="card card-sh">
						<div class="card-body text-center text-warning">
						<i class="fa-solid fa-box-open fa-3x"></i>
							<h4>Orders</h4>
						</div>
					</div>
				</a>
			</div>
			<div class="col-md-4 mt-4">
				<a href="/admin/users?type=1" class="text-decoration-none">
					<div class="card card-sh">
						<div class="card-body text-center text-primary">
							<i class="fa-solid fa-circle-user fa-3x"></i>
							<h4>User</h4>
						</div>
					</div>
				</a>
			</div>
			
			<div class="col-md-4 mt-4">
				<a href="/admin/users?type=2" class="text-decoration-none">
					<div class="card card-sh">
						<div class="card-body text-center text-primary">
							<i class="fa-solid fa-circle-user fa-3x"></i>
							<h4>Admin</h4>
						</div>
					</div>
				</a>
			</div>
			<div class="col-md-4 mt-4">
				<a href="/admin/addadmin" class="text-decoration-none">
					<div class="card card-sh">
						<div class="card-body text-center text-primary">
							<i class="fa-solid fa-user-plus fa-3x"></i>
							<h4>Add Admin</h4>
						</div>
					</div>
				</a>
			</div>
		</div>
	</div>

	<%@include file="../fotter.jsp"%>

</body>
</html>