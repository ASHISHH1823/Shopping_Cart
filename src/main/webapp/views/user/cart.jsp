<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<%@ include file="../allcss.jsp"%>
</head>
<body>
	<%@ include file="../navbar.jsp"%>
	<div class="container mt-5 p-2">
		<div class="card card-sh">
			<div class="card-header text-center">
				<p class="fs-4">Cart Page</p>
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
				<table class="table">
					<thead>
						<tr>
							<th scope="col">Sl No</th>
							<th scope="col">Image</th>
							<th scope="col">Product Name</th>
							<th scope="col">Price</th>
							<th scope="col" class="text-center">Quantity</th>
							<th scope="col">Total Price</th>
							<th scope="col">Action</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="cart" items="${carts}" varStatus="status">
							<tr>

								<td>${status.count }</td>
								<td><img alt=""
									src="<c:url value='/images/productimg/${cart.product.imagename }'></c:url>"
									height="50px" width="50px"></td>

								<td>${cart.product.title}</td>
								<td>${cart.product.price }</td>
								<td class="text-center"><a
									href="/user/CartQuantityUpdate?sy=de&cid=${cart.id}">
									<i class="fa-solid fa-minus"></i></a> [${cart.quantity}] <a
									href="/user/CartQuantityUpdate?sy=in&cid=${cart.id}"> <i
									class="fa-solid fa-plus"></i></a></td>
								<td>${cart.totalprice }</td>
								<td><a href="/user/prdelete/${cart.id}" class="btn btn-primary btn-sm"><i class="fa-regular fa-trash-can"></i></a></td>
							</tr>

						</c:forEach>

						<tr>
							<td colspan="4"></td>
							<td class="fw-bold">Total Order Price</td>
							<td class="fw-bold">&#8377; ${totalorder}</td>
						</tr>

					</tbody>
				</table>
				<div class=" text-center">
				<a href="/user/order" class=" btn btn-warning">Place Order</a>
				</div>
			</div>
		</div>
	</div>
	<%@ include file="../fotter.jsp"%>

</body>
</html>