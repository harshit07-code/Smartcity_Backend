<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Registration form</title>
</head>
<body>
<form class="row g-3">
  <div class="col-md-6">
    <label for="inputEmail4" class="form-label">Name</label>
    <input type="text" class="form-control" id="inputName">
  </div>
  <div class="col-md-6">
    <label for="inputLastName" class="form-label">LastName</label>
    <input type="text" class="form-control" id="inputLastName">
  </div>
  <div class="col-md-6">
    <label for="inputEmail4" class="form-label">Email</label>
    <input type="email" class="form-control" id="inputEmail4">
  </div>
  
  <div class="col-12">
    <label for="inputPhoneNo" class="form-label">Phone no.</label>
    <input type="number" class="form-control" id="inputPhoneno" placeholder="1234">
  </div>
  <div class="col-12">
    <label for="inputAddress" class="form-label">Address </label>
    <input type="text" class="form-control" id="inputAddress" placeholder="Apartment, studio, or floor">
  </div>
  <div class="col-md-6">
    <label for="inputPassword" class="form-label">Password</label>
    <input type="Password" class="form-control" id="inputPassword">
  </div>
 
  
  
  <div class="col-12">
    <button type="submit" class="btn btn-primary">Register</button>
  </div>
</form>
</body>
</html>