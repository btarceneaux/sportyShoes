<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product Management</title>
</head>
<body>
  <h2>Product Management</h2><br>
  <table border="1">
    <tr>
      <th>Product ID</th>
      <th>Name</th>
      <th>Description</th>
      <th>Category</th>
      <th>Price</th>
      <th>Delete Product</th>
    </tr>
    <c:forEach items="${requestScope.productList}" var="pr" varStatus="loop">
      <tr>
        <td><c:out value="${pr.productId}"></c:out></td>
        <td><c:out value="${pr.productName}"></c:out></td>
        <td><c:out value="${pr.productDesc}"></c:out></td>
        <td><c:out value="${pr.productCategory}"></c:out></td>
        <td><c:out value="${pr.productPrice}"></c:out></td>
        <td><a href="deleteProduct?id=${pr.productId }">  Delete Product </a></td>
      </tr>
    </c:forEach>
  </table><br>
  <hr>
  <h2>Add A New Product</h2>
  <form action="addProduct" method="post">
    <table>
      <tbody>
        <tr>
          <td>Product Name</td>
          <td><input type="text" name="productName" required></td>
        </tr>
        <tr>
          <td>Product Description</td>
          <td><input type="text" name="productDescription" required></td>
        </tr>
        <tr>
        <tr>
          <td>Product Category</td>
          <td><input type="text" name="productCategory" required></td>
        </tr>
        <tr>
          <td>Price</td>
          <td><input type="text" name="price" required></td>
        </tr>
      </tbody>
    </table>
    <br>
    <input type="submit" value="submit"><br/> 
    <input type="reset" value="reset"><br/>
  </form>
</body>
</html>