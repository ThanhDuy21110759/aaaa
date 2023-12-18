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
				<h1 class="text-center mb-5">Đã Duyệt</h1>

				<table id="employeeTable"
					class="table table-bordered border-primary table-fixed">
					<thead>
						<tr>
							<th>Mã khiếu nại</th>
							<th>Nội dung phản hồi</th>
							<th>Ngày gửi</th>
							<th>Ngày gửi</th>
							<th>Người phản hồi</th>
							<th style="width: 150px;">Thao tác</th>

						</tr>
					</thead>
					<tbody>
						<c:forEach var="khieunai" items="${listKhieunaiDaduyet}">
							<tr>
								<td><c:out value="${khieunai.maKN}" /></td>
								<td><c:out value="${khieunai.noidungPH}" /></td>

								<td><fmt:parseDate value="${khieunai.ngayGui}"
										pattern="yyyy-MM-dd" var="parsedDate" type="date" /> <fmt:formatDate
										value="${parsedDate}" pattern="dd-MM-yyyy" /></td>
								<td><fmt:parseDate value="${khieunai.ngayDuyet}"
										pattern="yyyy-MM-dd" var="parsedDate" type="date" /> <fmt:formatDate
										value="${parsedDate}" pattern="dd-MM-yyyy" /></td>

								<td><c:out value="${khieunai.maNgnhan}" /></td>
								<td><a onclick="openForm1()" href="#" role="button">Phản hồi</a>
									&nbsp;&nbsp;&nbsp;&nbsp; 
									<a href="#"
									onclick="openForm2('${khieunai.maKN}')">Xóa</a></td>
						</c:forEach>
					</tbody>
				</table>
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

			</div>

		</div>
	</div>


	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

	<script type="text/javascript" src="../js/main.js"></script>
	<script type="text/javascript" src="../js/KNdaduyet.js"></script>
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

<!-- Form tạo khiếu nại -->
<div class="formpopup" id="myForm1"
	style="display: none; position: fixed; top: 50%; left: 50%; transform: translate(-50%, -50%);">
	<form class="form-container" action="<%=duongDanIndex%>/khieunai">
		<h1>Tạo khiếu nại</h1>
		<input type="hidden" name="action" value="createkn_daduyet" />

		<div class="row gx-3 mb-3">
			<div class="col-md-3">
				<label class="small mb-1" for="inputMaNV">Người nhận</label> <input
					class="form-control" id="inputMaNV" name="inputMaNV" type="text"
					placeholder="@gmail.com" value="">
			</div>
		</div>

		<div class="row gx-3 mb-3">
			<div class="col-md-12">
				<label class="small mb-1" for="noidung_input">Nội Dung</label>
				<textarea class="form-control" id="noidung_input1"
					name="noidung_input1" placeholder="Nhập nội dung" rows="3"></textarea>
			</div>
		</div>

		<button type="submit" class="btn btn-primary">Submit</button>
		<button type="button" class="btn btn-secondary" onclick="closeForm1()">Close</button>
	</form>
</div>

<div class="formpopup" id="myForm2"
	style="display: none; position: fixed; top: 50%; left: 50%; transform: translate(-50%, -50%);">
	<form class="form-container" action="<%=duongDanIndex%>/khieunai">
		<h1>Xóa</h1>
		<input type="hidden" name="action" value="deletekn_daduyet" />

		<div class="row gx-3 mb-3">
			<div class="col-md-3" style="width: 700px">
				<input type="hidden" id="maKN_input2" name="maKN_input2">
				<h2>Bạn có chắc chắn muốn xóa nội dung này</h2>
			</div>
		</div>

		<button type="submit" class="btn btn-primary">Xóa</button>
		<button type="button" class="btn btn-secondary" onclick="closeForm3()">Hủy</button>
	</form>
</div>

<script>
	function openForm1() {
		document.getElementById("myForm1").style.display = "block";
	}

	function closeForm1() {
		document.getElementById("myForm1").style.display = "none";
	}

	function openForm2(id) {
		document.getElementById("myForm2").style.display = "block";
		document.getElementById("maKN_input2").value = id;
	}
	function closeForm2() {
		document.getElementById("myForm2").style.display = "none";
	}
</script>
