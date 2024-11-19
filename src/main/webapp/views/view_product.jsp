<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
	<div class="container card-sh"
		style="margin-top: 70px; margin-bottom: 100px">

		<div class="col-md-12 p-5">
			<div class="row">
				<c:if test="${not empty sessionScope.succMsg}">
					<p class="text-success fw-bold">${sessionScope.succMsg}</p>
					<c:set var="ignored" value="${sessionScope.remove('succMsg')}" />
				</c:if>

				<c:if test="${not empty sessionScope.errorMsg}">
					<p class="text-danger fw-bold">${sessionScope.errorMsg }</p>
					<c:set var="ignored" value="${sessionScope.remove('errorMsg')}" />
				</c:if>

				<div class="col-md-6 text-end">
					<img alt="" src="/images/productimg/${product.imagename }"
						width="330px" height="400px">
				</div>
				<div class="col-md-6">
					<p class="fs-3">${product.title}</p>
					<p>
						<span class="fw-bold">Description:</span><br>${product.description}
					</p>

					<p>
						<span class="fw-bold">Product Details</span><br> Status:
						<c:choose>
							<c:when test="${product.stock > 0}">
								<span class="badge bg-success">Available</span>
								<br>
							</c:when>
							<c:otherwise>
								<span class="badge bg-warning">Out of stock</span>
								<br>
							</c:otherwise>
						</c:choose>

						Category: ${product.category} <br> Policy: 7 Day Replacement
						& return
					</p>


					<p class="fs-5 fw-bold">
						price:&nbsp; &nbsp; &nbsp; &nbsp;<i class="fas fa-rupee-sign"></i>${product.discountprice }
						<span class="fs-6 text-decoration--line-through text-secondary"><del>${product.price }</del>
							</del></span> <span class="fs-6 text-success">${product.discount }% off</span>
					</p>

					<div class="row">
						<div class="col-md-4 text-success text-center p-2">
							<i class="fas fa-money-bill-wave fa-2x"></i>
							<p>cash on Delivery</p>
						</div>
						<div class="col-md-4 text-danger text-center p-2">
							<i class="fas fa-undo-alt fa-2x"></i>
							<p>Return Available</p>
						</div>
						<div class="col-md-4 text-primary tect-center p-2">
							<i class="fa-solid fa-truck-fast fa-2x"></i>
							<p>free delivery</p>
						</div>
					</div>
					<c:choose>
						<c:when test="${product.stock > 0}">
							<c:choose>
								<c:when test="${user == null}">
									<a href="<c:url value='/signin' />"
										class="btn btn-danger col-md-12">Add To Cart</a>
								</c:when>
								<c:otherwise>
									<a
										href="<c:url value='/user/addcart?pid=${product.id}&uid=${user.id}' />"
										class="btn btn-danger col-md-12">Add To Cart</a>
								</c:otherwise>
							</c:choose>
						</c:when>
						<c:otherwise>
							<a href="#" class="btn text-white btn-warning col-md-12">Out
								of Stock</a>
						</c:otherwise>
					</c:choose>
				</div>

			</div>
		</div>
	</div>
	<%@include file="fotter.jsp"%>

</body>
</html>