<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="Util.JDBCUtils"%>
<%@ page import="Models.NhanVien"%>
<%@ page import="Models.LoginBean"%>
<%@ page import="Models.BangPhanQuyen"%>
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
	
<link href="<%=url%>/css/sidebar.css" rel="stylesheet">
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

				<h1>Danh sách nhân viên</h1>
				<div class="row my-3">
					<div class="col">
						<a
							href="<%=request.getContextPath()%>/nhanviencontrol?action=themnhanvien"
							class="btn btn-primary" tabindex="-1" role="button"
							aria-disabled="true">Thêm nhân viên</a>
					</div>
					<div class="col-md-4 offset-md-4 d-flex">
						<div class="ms-auto">
							<button class="btn btn-success"
								onclick="window.location.href='<%=url%>/nhanviencontrol?action=import'">Import
								NV</button>
						</div>
						<div class="ms-auto">
							<button class="btn btn-success"
								onclick="window.location.href='<%=url%>/hosocontrol?action=import'">Import
								Hồ Sợ</button>
						</div>
					</div>
				</div>
				<div class="row">
					<table class="table table-bordered">
						<thead>
							<tr>
								<th>Mã nhân viên</th>
								<th>Họ Tên</th>
								<th>Lương cơ bản</th>
								<th>Email công việc</th>
								<th>Trạng thái</th>
								<th>Chi tiết thông tin</th>
								<th>Ảnh cá nhân</th>
								<th>Thao tác</th>
							</tr>
						</thead>
						<tbody>
							<c:forEach var="nhanvien" items="${listNhanVien}">
								<tr>
									<td><c:out value="${nhanvien.maNV}" /></td>
									<td><c:out value="${nhanvien.hoTen}" /></td>
									<td><fmt:formatNumber value="${nhanvien.luongCoBan}"
											type="number" pattern="#,##0 VNĐ" /></td>
									<td><c:out value="${nhanvien.emailCongViec}" /></td>
									<td><c:out value="${nhanvien.trangThai}" /></td>
									<td><a
										href="<%=url %>/hosocontrol?action=edit&manv=<c:out value='${nhanvien.maNV}'/>">Hồ
											sơ</a> <br> <a
										href="<%=url %>/hopdongcontrol?action=edit&manv=<c:out value='${nhanvien.maNV}'/>">Hợp
											đồng</a> <br> <a
										href="<%=url %>/quatrinhcongtaccontrol?action=edit&manv=<c:out value='${nhanvien.maNV}'/>">Quá
											trình công tác</a> <br> <a
										href="<%=url %>/taikhoancontrol?action=laythongtintk&manv=<c:out value='${nhanvien.maNV}'/>">Tài khoản</a>
										<br></td>
									<td><img
										src="<%=url%>/AnhCaNhan/<c:out value='${nhanvien.duongDanAnh}' />"
										width="100" height="80" /></td>
									<td><a
										href="<%=url %>/nhanviencontrol?action=edit&manv=<c:out value='${nhanvien.maNV}' />">Edit</a>
										&nbsp;&nbsp;&nbsp;&nbsp;</td>
								</tr>
							</c:forEach>
						</tbody>
					</table>
				</div>


			</div>

		</div>
	</div>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
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