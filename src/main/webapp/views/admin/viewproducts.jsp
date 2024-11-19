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
	<div class="container-fluid mt-4 p-3">
		<div class="row">


			<p class="fs-4 text-center">view all product details</p>
			<hr>
			<a href="/admin/" class="text-decoration-none"><i
				class="fa-solid fa-arrow-left"></i> Back</a>
			<c:if test="${not empty sessionScope.succMsg}">
				<p class="text-success fw-bold text-center">${sessionScope.succMsg}</p>
				<c:set var="ignored" value="${sessionScope.remove('succMsg')}" />
			</c:if>
			<c:if test="${not empty sessionScope.errorMsg}">
				<p class="text-danger fw-bold text-center">${sessionScope.errorMsg }</p>
				<c:set var="ignored" value="${sessionScope.remove('errorMsg')}" />
			</c:if>
			<
			<div class="col-md-4 p-3">

				<form action="/admin/products" method="get">
					<div class="row">
						<div class="col">
							<input type="text" class="form-control " name="ch">
						</div>
						<div class="col">
							<button class="btn btn-primary col">Search</button>
						</div>
					</div>
				</form>

			</div>
			<div class="p-3">
				<table class="table table-bordered">
					<thead class="table-light ">
						<tr>
							<th scope="col">Sl no.</th>
							<th scope="col">Image</th>
							<th scope="col">Title</th>
							<th scope="col">Category</th>
							<th scope="col">Price</th>
							<th scope="col">status</th>
							<th scope="col">Discount</th>
							<th scope="col">DiscountPrice</th>
							<th scope="col">Stocks</th>
							<th scope="col">action</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="p" items="${products}">
							<tr>

								<td>${p.id }</td>
								<td><img alt=""
									src="<c:url value='/images/productimg/${p.imagename }'></c:url>"
									height="50px" width="50px"></td>
								<td>${p.title }</td>
								<td>${p.category}</td>
								<td>${p.price }</td>
								<td>${p.isactive}</td>
								<td>${p.discount }</td>
								<td>${p.discountprice}</td>
								<td>${p.stock }</td>

								<td><a href="/admin/pedit/${p.id }"
									class="btn btn-primary btn-sm"><i
										class="fa-regular fa-pen-to-square"></i>Edit</a> <a
									href="/admin/pdelete/${p.id }" class="btn btn-danger btn-sm"><i
										class="fa-solid fa-trash-can"></i>Delete</a></td>
							</tr>
						</c:forEach>
					</tbody>
				</table>
					<div class="row">
					<div class="col-md-4">Total Products: ${Totalelement }</div>
					<div class="col-md-6">
						<
								<nav aria-label="Page navigation example">
									<ul class="pagination">
										<li class="page-item  ${ isFirst ? "disabled" : ""} %>"><a
											class="page-link" href="/admin/products?pageNo=${ pageNo-1}">Previous</a></li>
										<c:forEach var="i" begin="1" end="${ Totalpages}">
											<li class="page-item ${pageNo +1 ==i ? "active":"" }"><a
												class="page-link" href="/admin/products?pageNo=${i-1 }"> ${i}
											</a></li>
										</c:forEach>
										<li class="page-item ${isLast ? "disabled":"" }"><a
											class="page-link" href="/admin/products?pageNo=${pageNo+1 }">Next</a></li>
									</ul>
							
						
					</div>
				</div>
			</div>
		</div>
	</div>

	<%@include file="../fotter.jsp"%>

</body>
</html>