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
<a href="displayAllOrders">View All Orders</a><br>
<hr>
<h2>Display Orders By Date</h2>
<form action="displayOrdersByDate" method="get">
  <input type="date" name = "selectedDate" required>
  <input type="submit"/>
  <input type="reset"/>
</form>
<hr>
<h2>Display Orders By Selected Categories</h2>
<form action="displayOrdersByCategory" method="get">
  <table border="1">
    <tr>
      <th>Product Category</th>
      <th>Select Category</th>
    </tr>
    <c:forEach items="${requestScope.allCategories }" var="cat" varStatus="loop">
    <tr>
      <td><c:out value="${cat}"></c:out></td>
      <td><a href="displayOrdersByCategory?category=${cat}">Select Category</a></td>
    </tr>
    </c:forEach>
  </table>
</form>
</body>
</html>