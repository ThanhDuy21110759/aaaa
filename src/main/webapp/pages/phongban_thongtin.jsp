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
				<h1 class="text-center mb-5">Danh sách phòng ban</h1>

				<table id="employeeTable"
					class="table table-bordered border-primary table-fixed">
					<thead class="table-dark">
						<tr>
							<th>Mã phòng ban</th>
							<th>Tên phòng ban</th>
							<th>Mã trưởng phòng</th>
							<th>Mã chi nhánh</th>
							<th>Trạng thái</th>

							<th style="width: 100px;">Thao tác</th>

						</tr>
					</thead>
					<tbody>
						<c:forEach var="pb" items="${listPB}">
							<tr>
								<td id="${pb.maPB}"><c:out value="${pb.maPB}" /></td>
								<td id="${pb.tenPhongBan}"><c:out
										value="${pb.tenPhongBan}" /></td>
								<td id="${pb.maTrgPhg}"><c:out value="${pb.maTrgPhg}" /> <br>
									<a href="#" onclick="openForm3('${pb.maPB}')">Đổi trưởng
										phòng</a></td>
								<td id="${pb.maCN}"><c:out value="${pb.maCN}" /></td>
								<td id="${pb.trangThai}"><c:out value="${pb.trangThai}" /></td>
								<td><a href="#"
									onclick="openForm2('${pb.maPB}','${pb.tenPhongBan}','${pb.maTrgPhg}','${pb.trangThai}')">Chỉnh
										sửa phòng ban</a> <a href="#" onclick="openForm4('${pb.maPB}')">Xóa</a>
								</td>
							</tr>

						</c:forEach>
					</tbody>
				</table>
				<!-- thanh đếm trang -->
				<nav aria-label="Page navigation pagePos"
					style="display: flex; float: left;">
					<ul class="pagination justify-content-center">
						<li class="page-item disabled"><a class="page-link" href="#"
							tabindex="-1">Previous</a></li>
						<li class="page-item"><a class="page-link" href="#">1</a></li>
						<li class="page-item"><a class="page-link" href="#">2</a></li>
						<li class="page-item"><a class="page-link" href="#">3</a></li>
						<li class="page-item"><a class="page-link" href="#">Next</a>
						</li>
					</ul>
				</nav>
				<div class="d-flex justify-content-end">
					<button class="btn btn-primary btn-lg btnSize"
						onclick="openForm1()" role="button">Tạo phòng ban</button>
				</div>
			</div>

		</div>
	</div>



	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

	<script type="text/javascript" src="../js/main.js"></script>
	<script type="text/javascript" src="../js/chinhanhphongban.js"></script>
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

<!-- Form tạo chi nhánh -->
<div class="formpopup" id="myForm1"
	style="display: none; position: fixed; top: 50%; left: 50%; transform: translate(-50%, -50%);">
	<form class="form-container" action="<%=duongDanIndex%>/phongban">
		<h1>Tạo chi nhánh</h1>
		<input type="hidden" name="action" value="taoPB" />

		<div class="row gx-3 mb-3">
			<div class="col-md-3">
				<label class="small mb-1" for="mapb_input1">Mã phòng ban</label> <input
					class="form-control" id="mapb_input1" name="mapb_input1"
					type="text" value="">
			</div>
		</div>

		<div class="row gx-3 mb-3">
			<div class="col-md-3">
				<label class="small mb-1" for="macn_input1">Mã chi nhánh</label> <input
					class="form-control" id="macn_input1" name="macn_input1"
					type="text" value="">
			</div>
		</div>
		<div class="row gx-3 mb-3">
			<div class="col-md-12">
				<label class="small mb-1" for="tenpb_input1">Tên phòng ban</label> <input
					class="form-control" id="tenpb_input1" name="tenpb_input1"
					type="text" value="">
			</div>
		</div>

		<button type="submit" class="btn btn-primary">Submit</button>
		<button type="button" class="btn btn-secondary" onclick="closeForm1()">Close</button>
	</form>
</div>

<!-- Form sửa pb-->
<div class="formpopup" id="myForm2"
	style="display: none; position: fixed; top: 50%; left: 50%; transform: translate(-50%, -50%);">
	<form class="form-container" action="<%=duongDanIndex%>/phongban">
		<h1>Xem khiếu nại</h1>
		<input type="hidden" name="action" value="suaPB" />

		<div class="row gx-3 mb-3">
			<div class="col-md-3">
				<label class="small mb-1" for="mapb_input2">Mã chi nhánh</label> <input
					class="form-control" id="mapb_input2" name="mapb_input2"
					type="text" value="">
			</div>
		</div>
		<div class="row gx-3 mb-3">
			<div class="col-md-3">
				<label class="small mb-1" for="tenpb_input2">Tên chi nhánh</label> <input
					class="form-control" id="tenpb_input2" name="tenpb_input2"
					type="text" value="">
			</div>
		</div>


		<div class="row gx-3 mb-3">
			<div class="col-md-12">
				<label class="small mb-1" for="matp_input2">Mã trưởng phòng</label>
				<input class="form-control" id="matp_input2" name="matp_input2"
					type="text" value="" readonly>
			</div>
		</div>
		<div class="row gx-3 mb-3">
			<div class="col-md-12">
				<label for="trangthai" class="col-form-label">Trạng thái:</label> <select
					id="trangthai" name="trangthai" class="form-control">
					<option value="danghoatdong">Đang hoạt động</option>
					<option value="ngunghoatdong">Ngừng hoạt động</option>
				</select>
			</div>
		</div>
		<button type="submit" class="btn btn-primary">Submit</button>
		<button type="button" class="btn btn-secondary" onclick="closeForm2()">Close</button>
	</form>
</div>

<!-- Form cập nhật giám đốc-->
<div class="formpopup" id="myForm3"
	style="display: none; position: fixed; top: 50%; left: 50%; transform: translate(-50%, -50%);">
	<form class="form-container" action="<%=duongDanIndex%>/phongban">
		<h1>Xem khiếu nại</h1>
		<input type="hidden" name="action" value="capnhatGDPB" />

		<div class="row gx-3 mb-3">
			<div class="col-md-3">
				<label class="small mb-1" for="mapb_input3">Mã chi nhánh</label> <input
					class="form-control" id="mapb_input3" name="mapb_input3"
					type="text" value="">
			</div>
		</div>
		<div class="row gx-3 mb-3">
			<div class="col-md-3">
				<label class="small mb-1" for="matp_input3">Trưởng phòng</label> <input
					class="form-control" id="matp_input3" name="matp_input3"
					type="text" value="">
			</div>
		</div>

		<button type="submit" class="btn btn-primary">Submit</button>
		<button type="button" class="btn btn-secondary" onclick="closeForm3()">Close</button>
	</form>
</div>

<!-- xóa chi nhánh -->
<div class="formpopup" id="myForm4"
	style="display: none; position: fixed; top: 50%; left: 50%; transform: translate(-50%, -50%);">
	<form class="form-container" action="<%=duongDanIndex%>/phongban">
		<h1>Xóa</h1>
		<input type="hidden" name="action" value="xoaPB" />

		<div class="row gx-3 mb-3">
			<div class="col-md-3" style="width: 700px">
				<input type="hidden" id="mapb_input4" name="mapb_input4">
				<h2>Bạn có chắc chắn muốn xóa phòng ban này</h2>
			</div>
		</div>

		<button type="submit" class="btn btn-primary">Xóa</button>
		<button type="button" class="btn btn-secondary" onclick="closeForm4()">Hủy</button>
	</form>
</div>

<script>
	function openForm1(id) {
		document.getElementById("myForm1").style.display = "block";
	}

	function closeForm1() {
		document.getElementById("myForm1").style.display = "none";
	}

	function openForm2(id, ten, tp, tt) {
		document.getElementById("myForm2").style.display = "block";

		document.getElementById("mapb_input2").value = id;
		document.getElementById("tenpb_input2").value = ten;
		document.getElementById("matp_input2").value = tp;
		document.getElementById("trangthai").value = tt;

	}

	function closeForm2() {
		document.getElementById("myForm2").style.display = "none";
	}

	function openForm3(id) {
		document.getElementById("myForm3").style.display = "block";
		document.getElementById("mapb_input3").value = id;

	}
	function closeForm3() {
		document.getElementById("myForm3").style.display = "none";
	}

	function openForm4(id) {
		document.getElementById("myForm4").style.display = "block";
		document.getElementById("mapb_input4").value = id;

	}
	function closeForm4() {
		document.getElementById("myForm4").style.display = "none";
	}
</script>