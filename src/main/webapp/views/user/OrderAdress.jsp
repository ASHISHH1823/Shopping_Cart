<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Delivery Address and Price Details</title>
<!-- Include Bootstrap CSS -->
<link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" rel="stylesheet">
<%@include file="../allcss.jsp"%>
</head>
<body>
<%@include file="../navbar.jsp"%>
<div class="container mt-5 p-5">
    <form action="/user/saveadd" method="post">
        <div class="row">

            <!-- Delivery Address Form -->
            <div class="col-md-6">
                <div class="mb-3 row">
                    <p class="text-center fs-4">DELIVERY ADDRESS</p>
                    <hr>
                    <div class="col p-1">
                        <label>First Name</label> 
                        <input type="text" name="firstname" required class="form-control mt-1">
                    </div>
                    <div class="col p-1">
                        <label>Last Name</label> 
                        <input type="text" name="lastname" required class="form-control mt-1">
                    </div>
                </div>

                <div class="mb-3 row">
                    <div class="col p-1">
                        <label>Email</label> 
                        <input type="email" name="email" required class="form-control col">
                    </div>
                    <div class="col p-1">
                        <label>Mobile Number</label> 
                        <input type="text" name="mobileno" required class="form-control col ms-2">
                    </div>
                    
                </div>

                <div class="mb-3 row">
                    <div class="col p-1">
                        <label>Address</label> 
                        <input type="text" name="address" required class="form-control col">
                    </div>
                    <div class="col p-1">
                        <label>City</label> 
                        <input type="text" name="city" required class="form-control col ms-2">
                    </div>
                </div>
                <div class="mb-3 row">
                    <div class="col p-1">
                        <label>State</label> 
                        <input type="text" name="state" required class="form-control col">
                    </div>
                    <div class="col p-1">
                        <label>Pincode</label> 
                        <input type="number" name="pincode" required class="form-control col ms-2">
                    </div>
                </div>
                
            </div>
           

            <!-- Price Details Section -->
            <div class="col-md-5 offset-md-1">
                <div class="card text-center">
                    <div class="card-header">
                        <p class="fw-bold mb-0">PRICE DETAILS</p>
                    </div>
                    <div class="card-body">
                        <table class="table table-borderless">
                            <tbody>
                                <tr>
                                    <td class="text-left">Price (2)</td>
                                    <td class="text-right">${totalorder}</td>
                                </tr>
                                <tr>
                                    <td class="text-left">Delivery Charges</td>
                                    <td class="text-right">
                                        <span style="">40</span> 
                                    </td>
                                </tr>
                                <tr>
                                    <td class="text-left font-weight-bold">Total Payable</td>
                                    <td class="text-right font-weight-bold">${totalorderprice}</td>
                                </tr>
                                
                            </tbody>
                        </table>
                    </div>
                </div>
                <div class="text-center mt-3">
                    <p>Safe and Secure Payments. Easy returns. 100% Authentic products.</p>
                </div>
                 <button type="submit" class="btn btn-warning">Place Order</button>
            </div>
        </div>
    </form>
</div>

<%@include file="../fotter.jsp"%>


</body>
</html>
