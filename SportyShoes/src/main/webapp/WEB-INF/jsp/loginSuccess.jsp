<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Login Success</title>
</head>
<body>

<h2>Login Successful.</h2>
Please enter your credit card information to complete the purchase.
<form action="storeOrder?id=${requestScope.id}" method="post">
   <table>
     <h3>Payment</h3>
     <tr>
       <td>Name : </td>
     </tr>
     <tr>
       <td><input type="text" name="creditCardName" required></td>
     </tr>
     <tr>
       <td>Credit Card Number : </td>
     </tr>
     <tr>
       <td><input type="number" name="creditCardNumber" required></td>
     </tr>
     <tr>
       <td>Expiration Date : </td>
     </tr>
     <tr>
       <td><input type="date" name="expirationDate" required></td>
     </tr>
     <tr>
       <td>CVV Code : </td>
     </tr>
     <tr>
       <td><input type="number" name="creditCardNumber" required></td>
     </tr>
   </table>
   <input type="submit" value="submit"><br/> 
   <input type="reset" value="reset"><br/>
 </form>
</body>
</html>