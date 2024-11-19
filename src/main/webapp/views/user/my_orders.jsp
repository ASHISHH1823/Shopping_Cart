<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<%@include file="../allcss.jsp"%>
<body>
	<%@include file="../navbar.jsp"%>
	<div class="container mt-5 p-5">
		<div class="row">
			<p class="text-center fs-3">My Orders</p>
			<c:if test="${not empty sessionScope.succMsg}">
				<p class="text-success fw-bold">${sessionScope.succMsg}</p>
				<c:set var="ignored" value="${sessionScope.remove('succMsg')}" />
			</c:if>
			<c:if test="${not empty sessionScope.errorMsg}">
				<p class="text-danger fw-bold">${sessionScope.errorMsg }</p>
				<c:set var="ignored" value="${sessionScope.remove('errorMsg')}" />
			</c:if>
			<div class="col-md-12 ">

				<table class="table table-bordered card-sh">
					<thead>
						<tr>
							<th scope="col">Order Id</th>
							<th scope="col">Date</th>
							<th scope="col">Product Details</th>
							<th scope="col">Price</th>
							<th scope="col">Status</th>
							<th scope="col">Action</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="o" items="${orders}">
							<tr>
								<th scope="row">${o.orderid}</th>
								<td>${o.orderDate}</td>
								<td>${o.product.title}</td>
								<td>Quantity: ${o.quantity} <br> Price: ${o.price} <br>
									Total Price: ${o.quantity * o.price}
								</td>
								<td>${o.status}</td>
								<td><c:if test="${o.status != 'Cancelled'}">
										<a
											href="${pageContext.request.contextPath}/user/update-status?id=${o.id}&st=6"
											class="btn btn-sm btn-danger">Cancel</a>
									</c:if> <c:if test="${o.status == 'Cancelled'}">
										<a href="#" class="btn btn-sm btn-danger disabled">Cancel</a>
									</c:if></td>
							</tr>
						</c:forEach>


					</tbody>
				</table>
			</div>
		</div>

	</div>

	<%@include file="../fotter.jsp"%>
</body>
</html>