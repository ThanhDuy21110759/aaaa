<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="Util.JDBCUtils"%>
<%@ page import="Models.NhanVien"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0">

<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css">
<link rel="stylesheet"
	href="https://pro.fontawesome.com/releases/v5.10.0/css/all.css">

<%
String duongDanIndex = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
		+ request.getContextPath();
%>


<link href="<%=duongDanIndex%>/css/sidebar.css" rel="stylesheet">
<link href="<%=duongDanIndex%>/css/profile.css" rel="stylesheet">
<title>Quản lý nhân viên</title>


</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<div class="col-2" style="padding-left: 0px;">
				<jsp:include page="../layout/sidebar.jsp"></jsp:include>

			</div>
			<div class="col-10" id="content">
				<jsp:include page="../layout/navbar.jsp"></jsp:include>

				<h3>Đổi mật khẩu</h3>

				<form action="<%=duongDanIndex%>/taikhoancontrol">
						<input type="hidden" name="action" value="doimatkhau" />
				
					<div class="form-outline form-white mb-4">
						<input type="text" id="typeEmailX"
							class="form-control form-control-lg" name="username" /> <label
							class="form-label" for="typeEmailX">Tài khoản</label>
					</div>

					<div class="form-outline form-white mb-4">
						<input type="password" id="typePasswordX"
							class="form-control form-control-lg" name="password" /> <label
							class="form-label" for="typePasswordX">Mật khẩu</label>
					</div>

					<div class="form-outline form-white mb-4">
						<input type="password" id="typePasswordX"
							class="form-control form-control-lg" name="newpassword" /> <label
							class="form-label" for="typePasswordX">Mật khẩu mới</label>
					</div>
					<button type="submit" class="btn btn-primary">Xác nhận</button>
				</form>



			</div>

		</div>
	</div>

	<script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

	<script type="text/javascript" src="../js/main.js"></script>
	<script type="text/javascript">
		$(document).ready(function() {
			$('#sidebarCollapse').on('click', function() {
				$('#sidebar').toggleClass('active');
				$('#content').toggleClass('active');
			});
		});
	</script>

</body>
</html>