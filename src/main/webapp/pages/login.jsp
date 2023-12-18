<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="Models.LoginBean"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN"
	crossorigin="anonymous">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<link rel="stylesheet"
	href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css" />
<title>Insert title here</title>
</head>
<body>
	<%
	String errMsg = (String) request.getAttribute("errMsg");
	LoginBean acc = (LoginBean) session.getAttribute("accLogin");
	%>
	<%
	if (acc == null && errMsg != null) {
	%>
	<div style="text-align: center; margin-top: 50px;">
		<p
			style="color: red; font-weight: bold; border: 1px solid red; padding: 10px; display: inline-block;"><%=errMsg%></p>
	</div>
	<%
	}
	%>
	<form class="login100-form validate-form"
		action="<%=request.getContextPath()%>/login" method="post">
		<section class="vh-100 gradient-custom">
			<div class="container py-5 h-100">
				<div
					class="row d-flex justify-content-center align-items-center h-100">
					<div class="col-12 col-md-8 col-lg-6 col-xl-5">
						<div class="card bg-dark text-white" style="border-radius: 1rem;">
							<div class="card-body p-5 text-center">

								<div class="mb-md-5 mt-md-4 pb-5">

									<h2 class="fw-bold mb-2 text-uppercase">Login</h2>
									<p class="text-white-50 mb-5">Please enter your login and
										password!</p>

									<div class="form-outline form-white mb-4">
										<input type="text" id="typeEmailX"
											class="form-control form-control-lg" name="username" /> <label
											class="form-label" for="typeEmailX">Tài khoản</label>
									</div>

									<div class="form-outline form-white mb-4">
										<input type="password" id="typePasswordX"
											class="form-control form-control-lg" name="password" /> <label
											class="form-label" for="typePasswordX">Password</label>
									</div>
									<button class="btn btn-outline-light btn-lg px-5" type="submit">Login</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
	</form>
</body>
</html>