<!DOCTYPE html>
<html>
<head>
    <title>Payment Method</title>
</head>
<%@include file="../allcss.jsp" %>
<body>
<%@include file="../navbar.jsp"%>
    <h1>Choose Your Payment Method</h1>
    <form action="/user/payments" method="post">
       <label>
            <input type="radio" name="paymentType" value="online"> Online Delivery
        </label><br>
        <label>
            <input  type="radio" name="paymentType" value="cod"> cash On Delivery
        </label><br>
        <button type="submit">Submit Payment</button>
    </form>
    <%@include file="../fotter.jsp"%>
</body>
</html>
