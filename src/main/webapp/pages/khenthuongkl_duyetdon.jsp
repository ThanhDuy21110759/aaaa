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
String duongDanIndex = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
		+ request.getContextPath();
%>
<link href="<%=duongDanIndex%>/css/sidebar.css" rel="stylesheet">
<link href="<%=duongDanIndex%>/css/profile.css" rel="stylesheet">
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
		response.sendRedirect("/pages/login.jsp");
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
				<h1 class="text-center mb-5">Duyệt đơn kỷ luật & khiếu nại</h1>

				<table id="employeeTable"
					class="table table-bordered border-primary table-fixed"
					style="height: 750px">
					<thead class="table-dark">
						<tr>
							<th>Mã số</th>
							<th>Mã Người Gửi</th>
							<th>Mã Người Nhận</th>
							<th>Nội dung</th>
							<th>Ngày gửi</th>
							<th>Trạng thái</th>
							<th>Loại</th>
							<th style="width: 250px;">Thao tác</th>
						</tr>
					</thead>
					<tbody>
						<c:forEach var="ktkl" items="${listKTKL_duyet}">
							<tr >
								<td id="${ktkl.maDG}"><c:out value="${ktkl.maDG}" /></td>
								<td id="${ktkl.maNgGui}"><c:out value="${ktkl.maNgGui}" /></td>
								<td id="${ktkl.maNgNhan}"><c:out value="${ktkl.maNgNhan}" /></td>
								<td id="${ktkl.noiDung}"><c:out value="${ktkl.noiDung}" /></td>
								<td><fmt:parseDate value="${ktkl.ngayGui}"
										pattern="yyyy-MM-dd" var="parsedDate" type="date" /> <fmt:formatDate
										value="${parsedDate}" pattern="dd-MM-yyyy" /></td>
								<td id="${ktkl.trangThai}"><c:out value="${ktkl.trangThai}" /></td>
								<td id="${ktkl.loaiDG}"><c:out value="${ktkl.loaiDG}" /></td>
								<td><a href="#" class="btn btn-primary"
									onclick="openForm1('${ktkl.maDG}','${ktkl.maNgNhan}','${ktkl.noiDung}')">Duyệt</a>
									<hr> <a href="#" class="btn btn-primary"
									onclick="openForm2('${ktkl.maDG}')">Xóa</a></td>
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

			</div>

		</div>
	</div>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>

	<script type="text/javascript" src="../js/TaoDonKTKL.js"></script>
	<script type="text/javascript" src="../js/main.js">
		
	</script>

	<script type="text/javascript">
		
	</script>

</body>
</html>

<!-- Form duyệt  -->
<div class="formpopup" id="myForm1"
	style="display: none; position: fixed; top: 50%; left: 50%; transform: translate(-50%, -50%);">
	<form class="form-container" action="<%=duongDanIndex%>/ktkl">
		<h1>Duyệt</h1>
		<input type="hidden" name="action" value="duyetDonKT" />

		<div class="row gx-3 mb-3">
			<div class="col-md-3">
				<label class="small mb-1" for="maQD_input">Mã quyết định</label> <input
					class="form-control" readonly id="maQD_input" name="maQD_input" type="text"
					placeholder="" value="" >
			</div>
		</div>
		<div class="row gx-3 mb-3">
			<div class="col-md-3">
				<label class="small mb-1" for="soQD_input">Số quyết định</label> <input
					class="form-control" id="soQD_input" type="text" name="soQD_input"
					placeholder="" value="">
			</div>

		</div>

		<div class="row gx-3 mb-3">
			<div class="col-md-3">
				<label class="small mb-1" for="maNV_input">Mã người nhận</label> <input
					class="form-control" id="maNV_input" type="text" readonly
					name="maNV_input" placeholder="" value="<c:out value='${ktkl.maNgNhan}'/>">
			</div>

		</div>

		<div class="row gx-3 mb-3">
			<div class="col-md-12">
				<label class="small mb-1" for="noidung_input">Nội Dung</label>
				<textarea class="form-control" id="noidung_input" readonly
					name="noidung_input" placeholder="Nhập nội dung" rows="3"
					value="<c:out value='${ktkl.noiDung}'/>"></textarea>
			</div>
		</div>

		<button type="submit" class="btn btn-primary">Submit</button>
		<button type="button" class="btn btn-secondary" onclick="closeForm1()">Close</button>
	</form>
</div>


<!-- Form xóa -->
<div class="formpopup" id="myForm2"
	style="display: none; position: fixed; top: 50%; left: 50%; transform: translate(-50%, -50%);">
	<form class="form-container" action="<%=duongDanIndex%>/ktkl">
		<h1>Xóa</h1>
		<input type="hidden" name="action" value="xoaDuyet" />

		<div class="row gx-3 mb-3">
			<div class="col-md-3" style="width: 700px">
				<input type="hidden" id="maQD_input2" name="maQD_input2">
				<h2>Bạn có chắc chắn muốn xóa nội dung này</h2>
			</div>
		</div>
		
		<button type="submit" class="btn btn-primary">Xóa</button>
		<button type="button" class="btn btn-secondary" onclick="closeForm2()">Hủy</button>
	</form>
</div>

<script>
	function openForm1(maDG,maNV,noiDung) {
				
		document.getElementById("myForm1").style.display = "block";
		
		document.getElementById("maQD_input").value = maDG;
	    document.getElementById("maNV_input").value = maNV;
	    document.getElementById("noidung_input").value = noiDung;
	}

	function closeForm1() {
		document.getElementById("myForm1").style.display = "none";
	}
	function openForm2(maDG) {
		document.getElementById("myForm2").style.display = "block";
		document.getElementById("maQD_input2").value = maDG;
	}

	function closeForm2() {
		document.getElementById("myForm2").style.display = "none";
	}
</script>