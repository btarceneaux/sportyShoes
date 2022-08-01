<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Cart</title>
</head>
<body>
<h3>
<%
  String productId = request.getParameter("productId");
  out.println("Please Enter The Quantity");
%>
</h3>
<form action="addItemsToCart" method="post">
  <table border="1">
    <tr>
      <th>Product ID</th>
      <th>Name</th>
      <th>Description</th>
      <th>Category</th>
      <th>Price</th>
      <th>Select Quantity</th>
    </tr>
    <c:forEach items="${requestScope.myProductList}" var="pr">
    <tr>
        <td><c:out value="${pr.productId}"></c:out></td>
        <td><c:out value="${pr.productName}"></c:out></td>
        <td><c:out value="${pr.productDesc}"></c:out></td>
        <td><c:out value="${pr.productCategory}"></c:out></td>
        <td><c:out value="${pr.productPrice}"></c:out></td>
        <td><input type="number" name="quantitySelection" min="0" max="10" required></td>
      </tr>
    </c:forEach>
  </table>
  <input type="submit" value="Submit">
  <input type="reset" value="Reset">
</form>
</body>
</html>