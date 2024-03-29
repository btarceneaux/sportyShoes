<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Products Page</title>
</head>
<body>
  <h2>Product Selection</h2><br>
  <table border="1">
    <tr>
      <th>Product ID</th>
      <th>Name</th>
      <th>Description</th>
      <th>Category</th>
      <th>Price</th>
      <th>Select</th>
    </tr>
    <c:forEach items="${requestScope.productList}" var="pr" varStatus="loop">
      <tr>
        <td><c:out value="${pr.productId}"></c:out></td>
        <td><c:out value="${pr.productName}"></c:out></td>
        <td><c:out value="${pr.productDesc}"></c:out></td>
        <td><c:out value="${pr.productCategory}"></c:out></td>
        <td><c:out value="${pr.productPrice}"></c:out></td>
        <td><a href="displaySelection?id=${pr.productId }">  Add To Cart</a></td>
      </tr>
    </c:forEach>
  </table><br>
</body>
</html>