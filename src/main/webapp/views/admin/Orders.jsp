<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<%@include file="../allcss.jsp"%>
</head>
<body>
<%@include file="../navbar.jsp"%>
	 <section>
        <div class="container-fluid mt-5 p-1">
            <div class="row">
                <p class="text-center fs-3 mt-2">All Orders</p>
                <hr>
                <a href="/admin/" class="text-decoration-none"><i class="fa-solid fa-arrow-left"></i> Back</a>

                <!-- Success Message -->
                <c:if test="${not empty sessionScope.succMsg}">
                    <p class="text-success fw-bold text-center">${sessionScope.succMsg}</p>
                    <c:set var="sessionScope.succMsg" value="${null}" />
                </c:if>

                <!-- Error Message -->
                <c:if test="${not empty sessionScope.errorMsg}">
                    <p class="text-danger fw-bold text-center">${sessionScope.errorMsg}</p>
                    <c:set var="sessionScope.errorMsg" value="${null}" />
                </c:if>

                <!-- Search Order Form -->
                <div class="col-md-4 p-4">
                    <form action="/admin/search-order" method="get">
                        <div class="row">
                            <div class="col">
                                <input type="text" class="form-control" name="orderid" placeholder="Enter order id">
                            </div>
                            <div class="col">
                                <button class="btn btn-primary col">Search</button>
                            </div>
                        </div>
                    </form>
                </div>

                <!-- Orders Table -->
                <div class="col-md-12 ps-4 pe-4">
                    <table class="table table-bordered card-sh">
                        <thead class="table-light">
                            <tr>
                                <th scope="col">Order Id</th>
                                <th scope="col">Deliver Details</th>
                                <th scope="col">Date</th>
                                <th scope="col">Product Details</th>
                                <th scope="col">Price</th>
                                <th scope="col">Status</th>
                                <th scope="col">Action</th>
                            </tr>
                        </thead>
                        <tbody>
                            <!-- Search Result -->
                            <c:if test="${srch}">
                                <c:if test="${orderDtls != null}">
                                    <tr>
                                        <th scope="row">${orderDtls.orderid}</th>
                                        <td>
                                            Name: ${orderDtls.orderAdress.firstname} ${orderDtls.orderAdress.lastname} <br>
                                            Email: ${orderDtls.orderAdress.email} <br>
                                            Mobno: ${orderDtls.orderAdress.mobileno} <br>
                                            Address: ${orderDtls.orderAdress.address} <br>
                                            City: ${orderDtls.orderAdress.city} <br>
                                            State: ${orderDtls.orderAdress.state}, ${orderDtls.orderAdress.pincode}
                                        </td>
                                        <td>${orderDtls.orderDate}</td>
                                        <td>${orderDtls.product.title}</td>
                                        <td>
                                            Quantity: ${orderDtls.quantity} <br>
                                            Price: ${orderDtls.price} <br>
                                            Total Price: ${orderDtls.quantity * orderDtls.price}
                                        </td>
                                        <td>${orderDtls.status}</td>
                                        <td>
                                            <form action="/admin/update-order-status" method="post">
                                                <div class="row">
                                                    <div class="col">
                                                        <select class="form-control" name="st">
                                                            <option>--select--</option>
                                                            <option value="1">In Progress</option>
                                                            <option value="2">Order Received</option>
                                                            <option value="3">Product Packed</option>
                                                            <option value="4">Out for Delivery</option>
                                                            <option value="5">Delivered</option>
                                                            <option value="6">Cancelled</option>
                                                        </select>
                                                    </div>
                                                    <input type="hidden" name="id" value="${orderDtls.id}">
                                                    <div class="col">
                                                        <c:if test="${orderDtls.status == 'Cancelled' || orderDtls.status == 'Delivered'}">
                                                            <button class="btn btn-primary btn-sm col disabled">Update</button>
                                                        </c:if>
                                                        <c:if test="${orderDtls.status != 'Cancelled' && orderDtls.status != 'Delivered'}">
                                                            <button class="btn btn-sm btn-primary">Update</button>
                                                        </c:if>
                                                    </div>
                                                </div>
                                            </form>
                                        </td>
                                    </tr>
                                </c:if>
                                <c:if test="${orderDtls == null}">
                                    <p class="fs-3 text-center text-danger">${errorMsg}</p>
                                </c:if>
                            </c:if>

                            <!-- Full Orders List -->
                            <c:if test="${!srch}">
                                <c:forEach var="o" items="${orders}">
                                    <tr>
                                        <th scope="row">${o.orderid}</th>
                                        <td>
                                            Name: ${o.orderAdress.firstname} ${o.orderAdress.lastname} <br>
                                            Email: ${o.orderAdress.email} <br>
                                            Mobno: ${o.orderAdress.mobileno} <br>
                                            Address: ${o.orderAdress.address} <br>
                                            City: ${o.orderAdress.city} <br>
                                            State: ${o.orderAdress.state}, ${o.orderAdress.pincode}
                                        </td>
                                        <td>${o.orderDate}</td>
                                        <td>${o.product.title}</td>
                                        <td>
                                            Quantity: ${o.quantity} <br>
                                            Price: ${o.price} <br>
                                            Total Price: ${o.quantity * o.price}
                                        </td>
                                        <td>${o.status}</td>
                                        <td>
                                            <form action="/admin/update-order-status" method="post">
                                                <div class="row">
                                                    <div class="col">
                                                        <select class="form-control" name="st">
                                                            <option>--select--</option>
                                                            <option value="1">In Progress</option>
                                                            <option value="2">Order Received</option>
                                                            <option value="3">Product Packed</option>
                                                            <option value="4">Out for Delivery</option>
                                                            <option value="5">Delivered</option>
                                                            <option value="6">Cancelled</option>
                                                        </select>
                                                    </div>
                                                    <input type="hidden" name="id" value="${o.id}">
                                                    <div class="col">
                                                        <c:if test="${o.status == 'Cancelled' || o.status == 'Delivered'}">
                                                            <button class="btn btn-primary btn-sm col disabled">Update</button>
                                                        </c:if>
                                                        <c:if test="${o.status != 'Cancelled' && o.status != 'Delivered'}">
                                                            <button class="btn btn-sm btn-primary">Update</button>
                                                        </c:if>
                                                    </div>
                                                </div>
                                            </form>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                        </tbody>
                    </table>
<c:if test="${!srch }">
             <div class="row">
					<div class="col-md-4">Total products: ${Totalelement }</div>
					<div class="col-md-6">
						
								<nav aria-label="Page navigation example">
									<ul class="pagination">
										<li class="page-item  ${ isFirst ? "disabled" : ""} %>"><a
											class="page-link" href="/admin/orderss?pageNo=${ pageNo-1}">Previous</a></li>
										<c:forEach var="i" begin="1" end="${ Totalpages}">
											<li class="page-item ${pageNo +1 ==i ? "active":"" }"><a
												class="page-link" href="/admin/orderss?pageNo=${i-1 }"> ${i}
											</a></li>
										</c:forEach>
										<li class="page-item ${isLast ? "disabled":"" }"><a
											class="page-link" href="/admin/orderss?pageNo=${pageNo+1 }">Next</a></li>
									</ul>
					</div>
				</div>
				</c:if>
                </div>
            </div>
        </div>
    </section>
    	<%@include file="../fotter.jsp"%>
</body>
</html>
