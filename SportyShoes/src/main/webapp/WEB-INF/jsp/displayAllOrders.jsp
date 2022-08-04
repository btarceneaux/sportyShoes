<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>All Orders</title>
</head>
<body>
<h2>Orders</h2>
<h2>Orders</h2><br>
  <table border="1">
    <tr>
      <th>Order ID</th>
      <th>Order Date</th>
      <th>User ID</th>
      <th>Order Total</th>
      <th>Display Details</th>
    </tr>
    <c:forEach items="${requestScope.allOrders }" var="ord" varStatus="loop">
        <tr>
          <td><c:out value="${ord.orderId}"></c:out></td>
          <td><c:out value="${ord.orderDate}"></c:out></td>
          <td><c:out value="${ord.userId}"></c:out></td>
          <td><c:out value="${ord.orderTotal}"></c:out></td>
          <td><a href="displayLineItem?orderId=${ord.orderId }">Display Line Item</a></td>
        </tr>
    </c:forEach>
  </table><br>
</body>
</html>