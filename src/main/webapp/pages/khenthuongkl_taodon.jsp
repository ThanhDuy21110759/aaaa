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
				<h1 class="text-center mb-5">Tạo đơn kỷ luật & khiếu nại</h1>

				<table id="employeeTable" style="height: 750px"
					class="table table-bordered border-primary table-fixed"
					style="height: 750px">
					<thead class="table-dark">
						<tr>
							<th>Mã số</th>
							<th>Nội dung khen thưởng</th>
							<th>Ngày gửi</th>
							<th>Trạng thái</th>
							<th style="width: 100px;">Thao tác</th>

						</tr>
					</thead>
					<tbody>
						<c:forEach var="ktkl" items="${listKTKL_trangthai}">
							<tr>
								<td id="${ktkl.maDG}"><c:out value="${ktkl.maDG}" /></td>
								<td id="${ktkl.noiDung}"><c:out value="${ktkl.noiDung}" /></td>
								<td><fmt:parseDate value="${ktkl.ngayGui}"
										pattern="yyyy-MM-dd" var="parsedDate" type="date" /> <fmt:formatDate
										value="${parsedDate}" pattern="dd-MM-yyyy" /></td>
								<td id="${ktkl.trangThai}"><c:out value="${ktkl.trangThai}" /></td>

								<td>											      	
									<a href="<%=duongDanIndex%>/ktkl?action=xoaTaodon&maQD_input=<c:out value="${ktkl.maDG}"/>"/>Xóa</a>
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

				<div class="d-grid gap-2 d-md-block "
					style="display: flex; float: right;">
					<button class="btn btn-primary" type="button"
						onclick="openForm1()">Tạo
						đơn khen thưởng</button>
					<button class="btn btn-primary" type="button"
						onclick="openForm2()">Tạo
						đơn kỷ luật</button>
				</div>

			</div>

		</div>
	</div>


	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

	<script type="text/javascript" src="../js/main.js"></script>
	<script type="text/javascript" src="../js/TaoDonKTKL.js"></script>
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

<!-- Form tạo khen thưởng -->
<div class="formpopup" id="myForm1"
	style="display: none; position: fixed; top: 50%; left: 50%; transform: translate(-50%, -50%);">
	<form class="form-container"
		action="<%=duongDanIndex%>/ktkl">
		<h1>Tạo đơn khen thưởng</h1>
		<input type="hidden" name="action" value="taoDonKT" />
		
		<div class="row gx-3 mb-3">
			<div class="col-md-3">
				<label class="small mb-1" for="inputMaNV">Mã Người nhận</label> <input
					class="form-control" id="inputMaNV" name="inputMaNV" type="text"
					placeholder="Nhập mã nhân viên" value="">
			</div>		
		</div>
		<div class="row gx-3 mb-3">
			<div class="col-md-3">
				<label class="small mb-1" for="tieude_input">Tiêu đề</label> <input class="form-control" id="tieude_input" type="text"
					name="tieude_input" placeholder="" value="">
			</div>
			
		</div>

		<div class="row gx-3 mb-3">
			<div class="col-md-12">
				<label class="small mb-1" for="noidung_input">Nội Dung</label>
				<textarea class="form-control" id="noidung_input" name="noidung_input"
					placeholder="Nhập nội dung" rows="3"></textarea>
			</div>
		</div>

		<button type="submit" class="btn btn-primary">Submit</button>
		<button type="button" class="btn btn-secondary" onclick="closeForm1()">Close</button>
	</form>
</div>
<!-- Form tạo kỷ luật-->
<div class="formpopup" id="myForm2"
	style="display: none; position: fixed; top: 50%; left: 50%; transform: translate(-50%, -50%);">
	<form class="form-container"
		action="<%=duongDanIndex%>/ktkl">
		<h1>Tạo đơn kỷ luật</h1>
		<input type="hidden" name="action" value="taoDonKL" />
		
		<div class="row gx-3 mb-3">
			<div class="col-md-3">
				<label class="small mb-1" for="inputMaNV">Nhân viên bị kỷ luật:</label> <input
					class="form-control" id="inputMaNV" name="inputMaNV" type="text"
					placeholder="Nhập mã nhân viên" value="">
			</div>		
		</div>
		<div class="row gx-3 mb-3">
			<div class="col-md-3">
				<label class="small mb-1" for="tieude_input">Tiêu đề</label> <input class="form-control" id="tieude_input" type="text"
					name="tieude_input" placeholder="" value="">
			</div>
			
		</div>

		<div class="row gx-3 mb-3">
			<div class="col-md-12">
				<label class="small mb-1" for="noidung_input">Nội Dung</label>
				<textarea class="form-control" id="noidung_input" name="noidung_input"
					placeholder="Nhập nội dung" rows="3"></textarea>
			</div>
		</div>

		<button type="submit" class="btn btn-primary">Submit</button>
		<button type="button" class="btn btn-secondary" onclick="closeForm2()">Close</button>
	</form>
</div>
<script>
	function openForm1() {
		document.getElementById("myForm1").style.display = "block";
	}

	function closeForm1() {
		document.getElementById("myForm1").style.display = "none";
	}
	function openForm2() {
		document.getElementById("myForm2").style.display = "block";
	}

	function closeForm2() {
		document.getElementById("myForm2").style.display = "none";
	}
</script>