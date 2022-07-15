<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
Products page
<table border="1">
  <tr>
    <th>Product Name</th>
	<th>Description</th>
	<th>Category</th>
	<th>Price</th>
  </tr>
  <c:forEach items="${requestScope.productList}" var="pr">
    <tr>
      <td><c:out value="${pr.product_name}"></c:out></td>
      <td><c:out value="${pr.product_desc}"></c:out></td>
      <td><c:out value="${pr.product_category}"></c:out></td>
      <td><c:out value="${pr.product_price}"></c:out></td>
    </tr>
  </c:forEach>
</table>
</body>
</html>