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

	<div class="container mt-5 p-5">
		<div class="row">
			<div class="col-md-6 p-5">
				<img alt="" src="views/image/ecom.png" width="100%" height="400px">
			</div>
			<div class="col-md-6 mt-5 p-5">
				<div class="card">
					<div class="card-header">
						<p class="fs-4 text-center">Forgot password</p>
						<c:if test="${not empty sessionScope.succMsg}">
							<p class="text-success fw-bold text-center">${sessionScope.succMsg}</p>
							<c:set var="ignored" value="${sessionScope.remove('succMsg')}" />
						</c:if>
						
						<c:if test="${not empty sessionScope.errorMsg}">
							<p class="text-danger fw-bold text-center">${sessionScope.errorMsg }</p>
							<c:set var="ignored" value="${sessionScope.remove('errorMsg')}" />
						</c:if>
					</div>
					<div class="card-body">
						<form action="/sendingemail" method="post">
							<div class="mb-3">
								<label class="form-label">Email</label> <input
									class="form-control" name="email" type="email"
									placeholder="Enter your Email">
							</div>
							<div class="text-center">
								<button class="btn bg-primary text-white" type="submit">Send</button>
							</div>
						</form>
					</div>

				</div>
			</div>
		</div>
	</div>


	<%@include file="fotter.jsp"%>

</body>
</html>