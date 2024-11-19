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

	<div class="container mt-5 p-2">
		<div class="card card-sh">
			<div class="card-header text-center">
			
				<c:if test="${userType==1}"><p class="fs-4">Users</p></c:if>
				<c:if test="${userType==2}"><p class="fs-4">Admins</p></c:if>
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
							<th scope="col">Sl no.</th>
							<th scope="col">Prifile img</th>
							<th scope="col">Name</th>
							<th scope="col">MobileNo</th>
							<th scope="col">Email</th>
							<th scope="col">Address</th>
							<th scope="col">Status</th>
							<th scope="col">action</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="u" items="${users}">
							<tr>

								<td>${u.id }</td>
								<td><img alt=""
									src="<c:url value='/images/profileimg/${u.profileimage }'></c:url>"
									height="50px" width="50px"></td>
								<td>${u.name }</td>
								<td>${u.mobilenumber}</td>
								<td>${u.email }</td>
								<td>${u.address}</td>
								<td>${u.isEnable }</td>
								<td>
    <a href="${pageContext.request.contextPath}/admin/updateStatus?status=true&id=${u.id}&type=${userType}" 
       class="btn btn-sm btn-primary">Active</a>
       
    <a href="${pageContext.request.contextPath}/admin/updateStatus?status=false&id=${u.id}&type=${userType}" 
       class="btn btn-sm btn-danger">Inactive</a>
</td>
								

<!--  
								<td><a
									href="<c:url value='/admin/updateStatus'>
                <c:param name='status' value='true'/>
                <c:param name='id' value='${u.id}'/>
            </c:url>"
									class="btn btn-primary btn-sm"> 
										 Active
								</a> 
								
								<a href="<c:url value='/admin/updateStatus'>
                <c:param name='status' value='false'/>
                <c:param name='id' value='${u.id}'/>
            </c:url>"
									class="btn btn-danger btn-sm"> 
										 Inactive
								</a></td> -->

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