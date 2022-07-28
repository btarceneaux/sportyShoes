<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>User Page</title>
</head>
<body>
<table border="1">
  <c:forEach items="${requestScope.userList}" var="usr">
    <tr>
      <td><c:out value="${usr.lastName}"></c:out></td>
      <td><c:out value="${usr.firstName}"></c:out></td>
      <td><c:out value="${usr.emailAddress}"></c:out></td>
      <td><c:out value="${usr.password}"></c:out></td>
    </tr>
  </c:forEach>
</table>
</body>
</html>