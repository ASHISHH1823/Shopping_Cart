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
	<div id="carouselExampleControls" class="carousel slide"
		data-bs-ride="carousel">
		<div class="carousel-inner">
			<div class="carousel-item active">
				<img src="views/image/ecom.png" class="d-block w-100" alt="..."
					height="350px">
			</div>
			<div class="carousel-item">
				<img src="views/image/ecom2.jpg" class="d-block w-100" alt="..."
					height="350px">
			</div>
			<div class="carousel-item">
				<img src="views/image/ecom3.jpg" class="d-block w-100" alt="..."
					height="350px">
			</div>
		</div>
		<button class="carousel-control-prev" type="button"
			data-bs-target="#carouselExampleControls" data-bs-slide="prev">
			<span class="carousel-control-prev-icon" aria-hidden="true"></span> <span
				class="visually-hidden">Previous</span>
		</button>
		<button class="carousel-control-next" type="button"
			data-bs-target="#carouselExampleControls" data-bs-slide="next">
			<span class="carousel-control-next-icon" aria-hidden="true"></span> <span
				class="visually-hidden">Next</span>
		</button>
	</div>
	<div class="container">
		<div class="row">
			<p class="text-center fs-4">CATEGORY</p>
			<c:forEach var="c" items="${ categorys}">
				<div class="col-md-2">
					<div class="card rounded-circle">
						<div class="card-body text-center">
							<img alt="" src="/images/categorysimg/${c.imagename }"
								width="65%" height="140px"> <br>
							
							<a	href="<c:url value='/products' > <c:param name='category' value='${c.name }'/></c:url>"
								class="text-center text-decoration-none" >${c.name }</a>
						</div>
					</div>
				</div>
			</c:forEach>
			<!--<div class="col-md-2">
				<div class="card rounded-circle">
					<div class="card-body text-center">
						<img alt="" src="views/image/categoryimg/appli.png" width="65%"
							height="140px">
						<p>Eloctronics</p>
					</div>
				</div>
			</div>-->
			<!--  <div class="col-md-2">
				<div class="card rounded-circle">
					<div class="card-body text-center">
						<img alt="" src="views/image/categoryimg/mobile.png" width="65%"
							height="140px">
						<p>Mobiles</p>
					</div>
				</div>
			</div>-->
			<!--	<div class="col-md-2">
				<div class="card rounded-circle">
					<div class="card-body text-center">
						<img alt="" src="views/image/categoryimg/pant.png" width="65%"
							height="140px">
						<p>mens</p>
					</div>
				</div>
			</div>-->
			<!--	<div class="col-md-2">
				<div class="card rounded-circle">
					<div class="card-body text-center">
						<img alt="" src="views/image/categoryimg/groccery.jpg" width="65%"
							height="140px">
						<p>Groccery</p>
					</div>
				</div>
			</div>
		<!--	<div class="col-md-2">
				<div class="card rounded-circle">
					<div class="card-body text-center">
						<img alt="" src="views/image/categoryimg/beuty.png" width="65%"
							height="140px">
						<p>Beauty</p>
					</div>
				</div>
			</div>
		</div>-->
		</div>
		<div class="container-fluid bg-light p-3">
			<div class="row">
				<p class="text-center fs-4">Latest Product</p>
				<c:forEach var="p" items="${products }">
					<div class="col-md-3">
						<div class="card-body text-center">
							<img alt="" src="/images/productimg/${p.imagename }" width="65%"
								height="140px"><br>
								 <a href="<c:url value='/viewproduct/${p.id}'></c:url>"
								class="text-center text-decoration-none">${p.title }</a>
						</div>
					</div>
				</c:forEach>
				<!-- 	<div class="col-md-3">
				<div class="card-body text-center">
					<img alt="" src="views/image/latestimg/hp1.jpg" width="65%"
						height="140px">
					<p class="text-center">HP LAPTOP</p>
				</div>
			</div>
			
			<div class="col-md-3">
				<div class="card-body text-center">
					<img alt="" src="views/image/latestimg/hp1.jpg" width="65%"
						height="140px">
					<p class="text-center">HP LAPTOP</p>
				</div>
			</div>
			
			<div class="col-md-3">
				<div class="card-body text-center">
					<img alt="" src="views/image/latestimg/hp1.jpg" width="65%"
						height="140px">
					<p class="text-center">HP LAPTOP</p>
				</div>
			</div>
			
			<div class="col-md-3">
				<div class="card-body text-center">
					<img alt="" src="views/image/latestimg/hp1.jpg" width="65%"
						height="140px">
					<p class="text-center">HP LAPTOP</p>
				</div>
			</div> -->
			</div>
		</div>
		<%@include file="fotter.jsp"%>
</body>
</html>