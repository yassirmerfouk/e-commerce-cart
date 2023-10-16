<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<!-- === Coding by CodingLab | www.codinglabweb.com === -->
<html lang="en">
<head>
<meta charset="UTF-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<!-- ===== Iconscout CSS ===== -->
<link rel="stylesheet" href="https://unicons.iconscout.com/release/v4.0.0/css/line.css">

<!-- ===== CSS ===== -->
<link rel="stylesheet" href="<%=application.getInitParameter("BOOTSTRAP_ASSETS")%>css/bootstrap.min.css">
<link rel="stylesheet" href="<%=application.getInitParameter("LOGIN_ASSETS")%>style.css">
<!--<title>Login & Registration Form</title>-->
</head>
<body>

	<div class="container">
		<div class="forms">
			<div class="form login">
				<span class="title">Login</span>

				<form action="../admin/login" method="POST">
					<div class="input-field">
						<input type="text" placeholder="Enter your login" name="login">
						<i class="uil uil-envelope icon"></i>
					</div>
					<c:if test="${!empty error_login }">
						<p class="text-danger mt-2">${error_login }</p>
					</c:if>
					<c:remove var="error_login" scope="session" />
					<div class="input-field">
						<input type="password" class="password" placeholder="Enter your password" name="password">
						<i class="uil uil-lock icon"></i>
						<i class="uil uil-eye-slash showHidePw"></i>
					</div>
					<c:if test="${!empty error_password }">
						<p class="text-danger mt-2">${error_password }</p>
					</c:if>
					<c:remove var="error_password" scope="session" />
					<div class="input-field button">
						<input type="submit" value="Login">
					</div>
				</form>

			</div>

		</div>
	</div>


	<script src="<%=application.getInitParameter("LOGIN_ASSETS")%>script.js"></script>
</body>
</html>
