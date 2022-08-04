<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Line Item Detail</title>
</head>
<body>
<h2>Line Item Detail</h2>
<table border="1">
    <tr>
      <th>Order ID</th>
      <th>Product ID</th>
      <th>Quantity</th>
      <th>Price</th>
    </tr>
    <c:forEach items="${requestScope.liList }" var="lil" varStatus="loop">
        <tr>
          <td><c:out value="${lil.orderId}"></c:out></td>
          <td><c:out value="${lil.itemId}"></c:out></td>
          <td><c:out value="${lil.quantity}"></c:out></td>
          <td><c:out value="${lil.lineItemPrice}"></c:out></td>
        </tr>
    </c:forEach>
  </table><br>
</body>
</html>