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
<link href="<%=url%>/css/sidebar.css" rel="stylesheet">
<link href="<%=url%>/css/profile.css" rel="stylesheet">
<title>Quản lý nhân viên</title>
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
	<div class="container-fluid">
		<div class="row">
			<div class="col-2" style="padding-left: 0px;">
				<jsp:include page="../layout/sidebar.jsp"></jsp:include>

			</div>
			<div class="col-10" id="content">
				<jsp:include page="../layout/navbar.jsp"></jsp:include>
				<div class="row gx-3 mb-3">
					<div class="col-auto">
						<button type="button" id="themQTCT"
							class="open-button btn btn-primary" onclick="openFormQTCT()">
							Thêm</button>
					</div>
				</div>
				<div id="thongtinhopdong">
					<div class="row">
						<table class="table table-bordered">
							<thead>
								<tr>
									<th>Mã Phân Quyền</th>
									<th>Mã Nhân Viên</th>
									<th>Mã Quyền Hạn</th>
									<th>Phòng ban</th>
									<th>Mã Chi Nhánh</th>
									<th>Vị trí</th>
									<th>Thao tác</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach var="bangphanquyen" items="${listBPQ}">
									<tr>
										<td><c:out value="${bangphanquyen.maPQ}" /></td>
										<td><c:out value="${bangphanquyen.maNV}" /></td>
										<td><c:out value="${bangphanquyen.maQH}" /></td>
										<td><c:out value="${bangphanquyen.maPB}" /></td>
										<td><c:out value="${bangphanquyen.maCN}" /></td>
										<td><c:out value="${bangphanquyen.viTri}" /></td>
										<td><a
											href="<%=url %>/bangphanquyencontrol?action=delete&mabqp=<c:out value='${bangphanquyen.maPQ}'/>&manv=<c:out value='${bangphanquyen.maNV}'/>">Xóa</a>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
					</div>
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


<div class="formpopup" id="formQuaTrinhCongTac"
	style="display: none; position: fixed; top: 50%; left: 50%; transform: translate(-50%, -50%);">
	<form class="form-container"
		action="<%=url%>/bangphanquyencontrol?action=insertBPQC">
		<h1>Thông tin quá trình công tác</h1>
		<input type="hidden" name="action" value="insertBPQC" />
		<div class="row gx-3 mb-3">
			<div class="col-md-3">
				<label class="small mb-1" for="inputMaPQ">Mã phân quyền</label> <input
					class="form-control" id="inputMaPQ" name="inputMaPQ" type="text"
					placeholder="Nhập mã phân quyền" required>
			</div>
			<div class="col-md-3">
				<label class="small mb-1" for="inputMaNV">Mã nhân viên</label> <input
					class="form-control" id="inputMaNV" name="inputMaNV" type="text"
					value="<c:out value='${hoso.maHS}'/>"
					placeholder="Nhập mã nhân viên" required>
			</div>
		</div>
		<div class="row gx-3 mb-3">
			<label class="small mb-1" for="selectMaQuyenHan">Chọn quyền
				hạn</label> 
			<select class="form-control" id="inputMaQuyenHan" name="inputMaQuyenHan"
				name="selectMaQuyenHan" required>
				<option value="nhanvien">Nhân Viên</option>
				<option value="truongphong">Trưởng phòng</option>
				<option value="giamdoc">Giám đốc</option>
				<option value="admin">Quản trị</option>
			</select>

			<div class="col-md-3">
				<label class="small mb-1" for="inputPhongBan">Phòng ban</label> <input
					class="form-control" id="inputPhongBan" type="text"
					name="inputPhongBan" placeholder="Nhập phòng ban" required>
			</div>
			<div class="col-md-3">
				<label class="small mb-1 " for="inputMaChiNhanh">Mã chi
					nhánh</label> <input class="form-control mt-0" id="inputMaChiNhanh"
					name="inputMaChiNhanh" type="text" placeholder="Nhập mã chi nhánh"
					required>
			</div>

			<div class="col-md-3">
				<label class="small mb-1 " for="inputViTri">Vị trí</label> <input
					class="form-control mt-0" id="inputViTri" name="inputViTri"
					type="text" placeholder="Nhập vị trí" required>
			</div>
		</div>

		<button type="submit" class="btn btn-primary">Submit</button>
		<button type="button" class="btn btn-secondary"
			onclick="closeFormQTCT()">Close</button>
	</form>
</div>

<script>
	function openFormQTCT() {
		document.getElementById("formQuaTrinhCongTac").style.display = "block";
	}

	function closeFormQTCT() {
		document.getElementById("formQuaTrinhCongTac").style.display = "none";
	}
</script>