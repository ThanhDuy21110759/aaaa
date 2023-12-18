<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="Util.JDBCUtils"%>
<%@ page import="Models.NhanVien"%>
<%@ page import="Models.LoginBean"%>

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
String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
		+ request.getContextPath();
%>

	<link href="<%=url%>/css/sidebar.css" rel="stylesheet">
	<link href="<%=url%>/css/profile.css" rel="stylesheet" type="text/css" />

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
				<h1>Quản lý nhân sự phòng ban: <c:out value="${pb.tenPhongBan}"/> | <c:out value="${pb.maPB}"/></h1>

					<div class="row my-3">
						<div class="row my-3">
							<div class="col-md-6 text-md-right">
								<a href="<%=url %>/quanlynhansutructhuoccontroller?action=xuatexcel&mapb=<c:out value='${pb.maPB}'/>" class="btn btn-primary" role="button">Xuất bản lương của phòng ban</a>
							</div>
						</div>
					</div>
					<div class="row">
						<div id="thongtincongtac">
							<div class="row">
								<table class="table table-bordered">
									<thead>
										<tr>
											<th>Mã nhân viên</th>
											<th>Quyền hạn</th>
											<th>Vị trí</th>
											<th>Hồ sơ</th>
										</tr>
									</thead>
									<tbody>
										<c:forEach var="bpq" items="${listBPQ}">
											<tr>
												<td><c:out value="${bpq.maNV}" /></td>
												<td><c:out value="${bpq.maQH}" /></td>
												<td><c:out value="${bpq.viTri}" /></td>
												<td><a
													href="<%=url %>/thongtincanhancontrol?action=xemhs&manv=<c:out value='${bpq.maNV}'/>">Xem</a></td>
											</tr>
										</c:forEach>
									</tbody>
								</table>
							</div>
						</div>
					</div>
						
					
				
			</div>

		</div>
	</div>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
	<script src="<%=url%>/js/validate_form.js"></script>
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