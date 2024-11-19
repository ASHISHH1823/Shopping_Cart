<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Edit Category</title>
<%@ include file="../allcss.jsp" %>
</head>
<body>
<%@ include file="../navbar.jsp" %>
<div class="container-fluid p-5 mt-5">
    <div class="row">
        <div class="col-md-3 offset-md-5">
            <div class="card card-sh">
                <div class="card-header text-center">
                    <p class="fs-4">Edit Category</p>
                    <c:if test="${not empty sessionScope.succMsg}">
                        <p class="text-success fw-bold">${sessionScope.succMsg}</p>
                        <c:set var="ignored" value="${sessionScope.remove('succMsg')}" />
                    </c:if>
                    <c:if test="${not empty sessionScope.errorMsg}">
                        <p class="text-danger fw-bold">${sessionScope.errorMsg}</p>
                        <c:set var="ignored" value="${sessionScope.remove('errorMsg')}" />
                    </c:if>
                </div>
                <div class="card-body">
                    <form:form action="/admin/updatecategorys" method="post" modelAttribute="command" enctype="multipart/form-data">
                        <form:hidden path="id" />
                        <div class="mb-3">
                            <label>Enter Category</label>
                            <form:input path="name" class="form-control" />
                        </div>
                        <div class="mb-3">
                            <label>Status</label>
                            <div class="form-check">
                                <form:radiobutton path="isActive" value="true" id="flexRadioDefault1" cssClass="form-check-input" />
                                <label class="form-check-label" for="flexRadioDefault1">Active</label>
                            </div>
                            <div class="form-check">
                                <form:radiobutton path="isActive" value="false" id="flexRadioDefault2" cssClass="form-check-input" />
                                <label class="form-check-label" for="flexRadioDefault2">Inactive</label>
                            </div>
                        </div>
                        <div class="mb-3">
                            <label>Upload Image</label>
                            <input type="file" name="file" class="form-control" />
                        </div>
                        <img alt="" src="<c:url value='/images/categorysimg/${command.imagename }'></c:url>" width="100px" height="100px">
                        <button class="btn btn-primary col-md-12 mt-2">Update</button>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="../fotter.jsp" %>
</body>
</html>
