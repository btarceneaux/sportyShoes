<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Manage Orders</title>
</head>
<body>
<h2>Manage Orders</h2>
<h2>All Orders</h2><br>
  <table border="1">
    <tr>
      <th>Order ID</th>
      <th>Order Date</th>
      <th>User ID</th>
      <th>Order Total</th>
      <th>Product Id</th>
      <th>Quantity</th>
      <th>Line Item Price</th>
    </tr>
    <c:forEach items="${requestScope.allOrders}" var="orl" varStatus="loop">
      <tr>
        <td><c:out value="${orl.orderId}"></c:out></td>
        <td><c:out value="${orl.orderDate}"></c:out></td>
        <td><c:out value="${orl.userId}"></c:out></td>
        <td><c:out value="${orl.orderTotal}"></c:out></td>
        <c:forEach items="${orl.lineItem}" var="li" varStatus="loop">
          <td><c:out value="${li.itemId}"></c:out></td>
          <td><c:out value="${li.quantity}"></c:out></td>
          <td><c:out value="${li.lineItemPrice}"></c:out></td>
        </c:forEach>
      </tr>
    </c:forEach>
  </table><br>
</body>
</html>