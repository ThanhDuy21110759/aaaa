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
				<h1 class="text-center mb-5">Khiếu nại</h1>
				<table id="employeeTable" style="height: 750px"
					class="table table-bordered border-primary table-fixed">
					<thead class="table-dark">
						<tr>
							<th>Mã khiếu nại</th>
							<th>Nội dung khiếu nại</th>
							<th>Ngày gửi</th>
							<th style="width: 350px;">Thao tác</th>

						</tr>
					</thead>
					<tbody>
						<c:forEach var="khieunai" items="${listKhieunaiChuaduyet}">
							<tr>
								<td><c:out value="${khieunai.maKN}" /></td>
								<td><c:out value="${khieunai.noidungKN}" /></td>

								<td><fmt:parseDate value="${khieunai.ngayGui}"
										pattern="yyyy-MM-dd" var="parsedDate" type="date" /> <fmt:formatDate
										value="${parsedDate}" pattern="dd-MM-yyyy" /></td>
								<td>  <a href="#"
									onclick="openForm2('${khieunai.maKN}','${khieunai.ngayGui}','${khieunai.noidungKN}')">Xem</a>
									&nbsp;&nbsp;&nbsp;&nbsp;<a href="#"
									onclick="openForm1('${khieunai.maKN}')">Tạo Phản Hồi
										khiếu nại</a>
									&nbsp;&nbsp;&nbsp;&nbsp; <a href="#"
									onclick="openForm3('${khieunai.maKN}')">Từ Chối</a></td>
							</tr>
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
	<script type="text/javascript" src="../js/KNchuaduyet.js"></script>
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
	<form class="form-container"
		action="<%=duongDanIndex%>/khieunai">
		<h1>Phản hồi</h1>
		<input type="hidden" name="action" value="updatePH" />
		
		<div class="row gx-3 mb-3">
			<div class="col-md-3">
				<label class="small mb-1" for="makn_input1">Mã khiếu nại</label> <input
					class="form-control" id="makn_input1" name="makn_input1" type="text"
				 value="">
			</div>		
		</div>

		<div class="row gx-3 mb-3">
			<div class="col-md-12">
				<label class="small mb-1" for="noidung_input1">Nội Dung</label>
				<textarea class="form-control" id="noidung_input1" name="noidung_input1"
					placeholder="Nhập nội dung" rows="3"></textarea>
			</div>
		</div>

		<button type="submit" class="btn btn-primary">Submit</button>
		<button type="button" class="btn btn-secondary" onclick="closeForm1()">Close</button>
	</form>
</div>

<!-- Form xem khiếu nại-->
<div class="formpopup" id="myForm2"
	style="display: none; position: fixed; top: 50%; left: 50%; transform: translate(-50%, -50%);">
	<form class="form-container"
		action="<%=duongDanIndex%>/khieunai">
		<h1>Xem khiếu nại</h1>
		<input type="hidden" name="action" value="listKhieunai_chuaduyet" />
		
		<div class="row gx-3 mb-3">
			<div class="col-md-3">
				<label class="small mb-1" for="makn_input2">Mã khiếu nại</label> <input
					class="form-control" id="makn_input2" name="makn_input2" type="text"
					placeholder="Nhập mã nhân viên" value="">
			</div>		
		</div>
		<div class="row gx-3 mb-3">
			<div class="col-md-3">
				<label for="message-text" class="col-form-label">Ngày
								gửi:</label> <input type="date" class="form-control" id="ngaygui_date" name="ngaygui_date"
								value="${formattedDate}" readonly>
			</div>		
		</div>
		
		<div class="row gx-3 mb-3">
			<div class="col-md-12">
				<label class="small mb-1" for="noidung_input2">Nội Dung</label>
				<textarea class="form-control" id="noidung_input2" name="noidung_input2"
					placeholder="Nhập nội dung" rows="3" readonly></textarea>
			</div>
		</div>
		<button type="button" class="btn btn-secondary" onclick="closeForm2()">Close</button>
	</form>
</div>

<div class="formpopup" id="myForm3"
	style="display: none; position: fixed; top: 50%; left: 50%; transform: translate(-50%, -50%);">
	<form class="form-container" action="<%=duongDanIndex%>/khieunai">
		<h1>Xóa</h1>
		<input type="hidden" name="action" value="deletekn_chuaduyet" />

		<div class="row gx-3 mb-3">
			<div class="col-md-3" style="width: 700px">
				<input type="hidden" id="makn_input3" name="makn_input3">
				<h2>Bạn có chắc chắn muốn xóa nội dung này</h2>
			</div>
		</div>
		
		<button type="submit" class="btn btn-primary">Xóa</button>
		<button type="button" class="btn btn-secondary" onclick="closeForm3()">Hủy</button>
	</form>
</div>

<script>

	function openForm1(id) {
		document.getElementById("myForm1").style.display = "block";
		document.getElementById("makn_input1").value = id;
	}

	function closeForm1() {
		document.getElementById("myForm1").style.display = "none";
	}
	
	function openForm2(id,date,nd) {
		document.getElementById("myForm2").style.display = "block";
		
		document.getElementById("makn_input2").value = id;
		document.getElementById("ngaygui_date").value = date;
	    document.getElementById("noidung_input2").value = nd;
			
	}

	function closeForm2() {
		document.getElementById("myForm2").style.display = "none";
	}
	
	function openForm3(id) {
		document.getElementById("myForm3").style.display = "block";	
		document.getElementById("makn_input3").value = id;
		
	}
	function closeForm3() {
		document.getElementById("myForm3").style.display = "none";
	}
</script>