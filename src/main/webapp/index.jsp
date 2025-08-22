<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
    response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate"); // HTTP 1.1.
    response.setHeader("Pragma", "no-cache"); // HTTP 1.0.
    response.setDateHeader("Expires", 0); // Proxies
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/css/bootstrap.min.css" rel="stylesheet"  crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.7/dist/js/bootstrap.bundle.min.js" crossorigin="anonymous"></script>
<link href='https://fonts.googleapis.com/css?family=Special Elite' rel='stylesheet'>
<script src="https://code.jquery.com/jquery-3.7.1.js" crossorigin="anonymous"></script>
<link rel="icon" type="image/x-icon" href="images/anger-symbol-svgrepo-com.svg">
<link href="styles.css" rel="stylesheet">
<script src="myScript.js"></script>
<title>myHR Employee Portal</title>
</head>
<body>
	
	<nav class="navbar navbar-expand-sm bg-dark navbar-dark">
		<div class="container-fluid">
			<a  class="navbar-brand" href="index.jsp">
				<img src="images/anger-symbol-svgrepo-com.svg" alt="company logo svg" height="50vh" width="50vh">
				 <span id="logo">myHR</span>
			</a>
			<span id="navText" class="navbar-text">Employee Portal</span>
			<button class="navbar-toggler" data-bs-toggle="collapse" data-bs-target="#collapsibleNavbar">
				<span class="navbar-toggler-icon"></span>
			</button>
			<div id="collapsibleNavbar" class="collapse navbar-collapse">				
				<ul class="navbar-nav ms-auto me-3">
					<li class="nav-item">
						<a class="nav-link active" href="index.jsp">Home</a>
					</li>
					<li class="nav-item">
						<a class="nav-link" href="aboutUs.jsp">About us</a>
					</li>
				</ul>
			</div>
		</div>
	</nav>
	<div class="container-fluid">
		<div id="contents">
			<div id="welcome-container" class="overflow-hidden text-wrap">
				<div class="text-primary">
					<div id="hr-portal-text1" class="special-elite-font hr-portal-text ">
						Welcome
					</div>
					<div id="hr-portal-text2" class="special-elite-font hr-portal-text ">
						To
					</div>
					<div id="hr-portal-text3" class="special-elite-font hr-portal-text">
						<span class="text-bg-danger p-2">myHR</span> Portal.
					</div>
				</div>
			</div>
			<div id="login-container" class="p-2 p-5 my-5 overflow-hidden shadow border border-dark border-2 rounded">
				<form action="LoginServlet" method="post">
					<div class="row">
						<div class="col-sm-*">
							<label for="uname" class="form-label">Username: </label>
						</div>		
					</div>
					<div class="row">
						<div class="col-sm-*">
							<input class="form-control" type="text"  name="uname" id="uname" placeholder="Enter Username" pattern="[a-zA-Z][a-zA-Z0-9\.]*@myHR\.in" title="must not contain spaces, must start with an alphabet, can contain alphabets dots and numbers, must end with domain @myHR.in " required>						
						</div>
					</div>
					<div class="row">
						<div class="col-sm-*">
							<label for="pwd" class="form-label">Password: </label>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-*">
							<input class="form-control" type="password" name="pwd" id="pwd" placeholder="Enter Password" pattern="^(([a-z](?=[^\s]*[A-Z]+[^\s]*))|([A-Z](?=[^\s]*[a-z]+[^\s]*)))(?=[^\s]*[^a-zA-Z0-9]+[^\s]*)(?=[^\s]*[0-9]+[^\s]*)[^\s]*" title="Must start with a letter (either lowercase or uppercase)., If it starts lowercase → must contain an uppercase somewhere.,If it starts uppercase → must contain a lowercase somewhere.,Must contain at least one special character.,Must contain at least one digit.,Must not contain spaces." required>
						</div>
					</div>
					<c:if test="${not empty errorMessage }">
						<div class="row">
							<div class="col-sm-*">
								<span class="text-danger">${errorMessage }</span>
							</div>
						</div>
					</c:if>
					<div class="row">
						<div class="col-sm-*">
							<button type="submit" class="btn btn-danger mt-3 w-100">Login</button>
						</div>
					</div>
					<div class="row">
						<div class="col-sm-* text-end">
							<a href="index.jsp" class="ms-auto">Forgot password?</a>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
	<footer class="text-bg-dark text-center w-100">
		<span>Icon "Anger Symbol 2" by Google (Noto Emoji), licensed under Apache 2.0.</span>
	</footer>
</body>
</html>