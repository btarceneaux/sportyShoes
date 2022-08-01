<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Create User</title>
</head>
<body>
<h2>Create User</h2>
<form action="verifyUserInformation" method="post">
    <h3>User Information</h3>
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
        <tr>
          <td>Email Address</td>
          <td><input type="text" name="email" required></td>
        </tr>
        <tr>
          <td>Mailing Address</td>
          <td><input type="text" name="mailingAddress" required></td>
        </tr>
        <tr>
          <td>City</td>
          <td><input type="text" name="city" required></td>
        </tr>
        <tr>
          <td>State</td>
          <td><input type="text" name="state" required></td>
        </tr>
        <tr>
          <td>Zip Code</td>
          <td><input type="text" name="zipCode" required></td>
        </tr>
        <tr>
          <td>Password</td>
          <td><input type="password" name="password" required></td>
        </tr>
        <tr>
          <td>Confirm Password</td>
          <td><input type="password" name="confirmPassword" required></td>
        </tr>
      </tbody>
    </table>
    <input type="submit" value="submit"><br/> 
    <input type="reset" value="reset"><br/>
  </form>
</body>
</html>