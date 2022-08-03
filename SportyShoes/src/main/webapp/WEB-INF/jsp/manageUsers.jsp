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
<h2>All Users</h2>
<table border="1">
 <tr>
   <th>First Name</th>
   <th>Last Name</th>
   <th>Email Address</th>
   <th>Delete User</th>
 </tr>
  <c:forEach items="${requestScope.userList}" var="usr">
    <tr>
      <td><c:out value="${usr.firstName}"></c:out></td>
      <td><c:out value="${usr.lastName}"></c:out></td>
      <td><c:out value="${usr.emailAddress}"></c:out></td>
      <td><a href="deleteUser?id=${usr.userId }">  Delete User</a></td>
    </tr>
  </c:forEach>
</table>
<hr>
<h2>Search For User By First And Last Name</h2>
<form action="findByName" method="get">
    <table>
      <tbody>
        <tr>
          <td>First Name</td>
          <td><input type="text" name="firstName" required></td>
        </tr>
        <tr>
          <td>Last Name</td>
          <td><input type="text" name="lastName" required></td>
        </tr>
        <tr>
      </tbody>
    </table>
    <br>
    <input type="submit" value="submit"><br/> 
    <input type="reset" value="reset"><br/>
  </form>
<hr>
<h2>Search For User By Email Address</h2>
<form action="findByEmail" method="get">
    <table>
      <tbody>
        <tr>
          <td>Email Address</td>
          <td><input type="text" name="emailAddress" required></td>
        </tr>
      </tbody>
    </table>
    <br>
    <input type="submit" value="submit"><br/> 
    <input type="reset" value="reset"><br/>
  </form>
</body>
</html>