<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Checkout</title>
</head>
<body>
<H2>Checkout</H2>
<h3>Shopping Cart Items</h3>
<table border="1">
    <tr>
      <th>Order ID</th>
      <th>Product Id</th>
      <th>Product Name</th>
      <th>Quantity</th>
      <th>Remove</th>
    </tr>
    <c:forEach items="${requestScope.myCart}" var="cart" varStatus="loop">
      <tr>
        <td><c:out value="${cart.orderId}"></c:out></td>
        <td><c:out value="${cart.itemId}"></c:out></td>
        <td><c:out value="${cart.myProduct.productName}"></c:out></td>
        <td><c:out value="${cart.quantity}"></c:out></td>
        <td><a href="removeFromCart?id=${cart.itemId }"> Remove From Cart</a></td>
      </tr>
    </c:forEach>
  </table><br>
</body>
</html>