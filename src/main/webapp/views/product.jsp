<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
	<div class="container-fluid bg-primary p-5 mt-5">
		<div class="row">

			<div class="col-md-8 offset-md-2">
				<form action="/products" method="get">
					<div class="input-group">
						<input type="text" class="form-control" name="ch">
						<button class="btn btn-light text-dark ms-3">
							<i class="fa-solid fa-magnifying-glass"></i>Search
						</button>
					</div>
				</form>
			</div>
		</div>
	</div>
	<div class="container-fluid mt-1">
		<div class="row">

			<div class="col-md-2 p-0">
				<div class="list-group">
					<a href="#" class="list-group-item list-group-item-action "
						aria-current="true"> Categories </a> <a href="/products"
						class="list-group-item list-group-item-action ${paramValue == '' ? 'active' : '' }"
						aria-current="true"> All </a>

					<c:forEach var="c" items="${categorie}">
						<a
							href="<c:url value='/products' > <c:param name='category' value='${c.name }'/></c:url>"
							class="list-group-item list-group-item-action ${ paramValue == c.name ? 'active' : '' }">${c.name }</a>

					</c:forEach>
				</div>
			</div>
			<div class="col-md-10">
				<div class="card">
					<div class="card-body">
						<p class="fs-3 text-center">Products</p>
						<div class="row">
							<c:choose>
								<c:when test="${productSize>0 }">
									<c:forEach var="p" items="${products}">
										<div class="col-md-3">

											<div class="card shadow p-3 mb-5 bg-body rounded">
												<div class="card-body text-center">
													<img alt="" src="images/productimg/${p.imagename}"
														width="100%" height="150px">
													<p class="fs-5 text-center">${p.title}</p>
													<div class="row text-center">
														<p class="fs-6 fw-bold">
															<span>&#8377;${p.discountprice}</span> <br> <span>&#8377;<del>${p.price}</del></span>

															<span class="fs-6 text-success">${p.discount }%</span>
														</p>
														<a href="<c:url value='/viewproduct/${p.id}'></c:url>"
															class="btn btn-primary col-md-6 offset-md-3">view
															Details</a>
													</div>
												</div>
											</div>
										</div>
									</c:forEach>
								</c:when>
								<c:otherwise>
									<p class="fs-4 text-danger text-center mt-4">products not
										available</p>
								</c:otherwise>
							</c:choose>
						</div>
					</div>
				</div>
				<div class="row">
					<div class="col-md-4">Total products: ${Totalelement }</div>
					<div class="col-md-6">
						<c:choose>
							<c:when test="${productSize>0 }">
								<nav aria-label="Page navigation example">
									<ul class="pagination">
										<li class="page-item  ${ isFirst ? "disabled" : ""} %>"><a
											class="page-link" href="/products?pageNo=${ pageNo-1}">Previous</a></li>
										<c:forEach var="i" begin="1" end="${ Totalpages}">
											<li class="page-item ${pageNo +1 ==i ? "active":"" }"><a
												class="page-link" href="/products?pageNo=${i-1 }"> ${i}
											</a></li>
										</c:forEach>
										<li class="page-item ${isLast ? "disabled":"" }"><a
											class="page-link" href="/products?pageNo=${pageNo+1 }">Next</a></li>
									</ul>
							</c:when>
						</c:choose>
						
					</div>
				</div>
			</div>

		</div>
	</div>
	<%@include file="fotter.jsp"%>
</body>
</html>