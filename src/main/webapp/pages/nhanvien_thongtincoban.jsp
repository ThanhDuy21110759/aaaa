<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="Util.JDBCUtils"%>
<%
String url1 = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
		+ request.getContextPath();
%>
<form id="thongtincoban-form" class="form"
	action="<%=url1%>/nhanviencontrol" method="post"
	enctype="multipart/form-data">
	<div class="row">

		<c:if test="${hanhdongtacdong == 'edit'}">
			<input type="hidden" name="action" value="update" />
			<h1>Sửa thông tin</h1>
		</c:if>
		<c:if test="${empty hanhdongtacdong}">
			<input type="hidden" name="action" value="insert" />
			<h1>Thêm nhân viên</h1>
		</c:if>

		<div class="col-xl-4">
			<!-- Profile picture card-->
			<div class="card mb-4 mb-xl-0">
				<div class="card-header">Profile Picture</div>
				
				<div class="card-body text-center">
					<input type="file" class="form-control" id="inputDuongDanAnh"
						name="inputDuongDanAnh" onchange="showImage(this);"
						value="<%=url1%>/AnhCaNhan/<c:out value='${nhanvien.duongDanAnh}' />"> 
						<label
						for="inputDuongDanAnh">Ảnh Cá Nhân: </label> <br> <img
						id="previewImage"
						src="<%=url1%>/AnhCaNhan/<c:out value='${nhanvien.duongDanAnh}' />"
						alt="Preview" style="max-width: 100%; max-height: 200px;">
				</div>
				
			</div>
		</div>
		<div class="col-xl-8">
			<div class="card mb-4">
				<div class="card-header">Thông tin nhân viên</div>
				<div class="card-body">
					<input type="hidden" name="action" value="insert" />
					<div class="mb-3">
						<c:if test="${hanhdongtacdong == 'xemthongtincanhan'}">
							<label class="small mb-1" for="inputMaNV">Mã nhân viên</label>
							<input class="form-control" id="inputMaNV" type="text"
								placeholder="Nhập mã nhân viên" name="inputMaNV"
								value="<c:out value='${nhanvien.maNV}'/>" readonly>
						</c:if>
						<c:if test="${hanhdongtacdong == 'edit'}">
							<label class="small mb-1" for="inputMaNV">Mã nhân viên</label>
							<input class="form-control" id="inputMaNV" type="text"
								placeholder="Nhập mã nhân viên" name="inputMaNV"
								value="<c:out value='${nhanvien.maNV}'/>" readonly>
						</c:if>
						<c:if test="${empty hanhdongtacdong}">
							<label class="small mb-1" for="inputMaNV">Mã nhân viên</label>
							<input class="form-control" id="inputMaNV" type="text"
								placeholder="Nhập mã nhân viên" name="inputMaNV"
								value="<c:out value='${nhanvien.maNV}'/>">
						</c:if>
					</div>
					<div class="row gx-3 mb-3">
						<div class="mb-3">
							<label class="small mb-1" for="inputHoTen">Họ và Tên</label> <input
								class="form-control" id="inputHoTen" type="text"
								name="inputHoTen" placeholder="Nhập Họ và Tên"
								value="<c:out value='${nhanvien.hoTen}'/>">
						</div>
					</div>
					<!-- Form Row -->
					<div class="row gx-3 mb-3">
						<div class="col-md-6">
							<label class="small mb-1" for="inputLuongCoBan">Lương cơ
								bản</label>
							<c:set var="formattedLuongCoBan"
								value="${String.format('%.2f', nhanvien.luongCoBan)}" />
							<input class="form-control" id="inputLuongCoBan"
								name="inputLuongCoBan" type="text" placeholder=""
								value="<c:out value='${formattedLuongCoBan}'/>">
						</div>

						<!-- Form Group (location)-->
						<div class="col-md-6 mt-auto">
							<label class="small mb-1 fs-10">VNĐ</label>
						</div>
					</div>
					<div class="mb-3">
						<label class="small mb-1" for="inputEmailCV">Email công
							việc</label> <input class="form-control" id="inputEmailCV" type="email"
							name="inputEmailCV" placeholder="Enter your email address"
							value="<c:out value='${nhanvien.emailCongViec}'/>">
					</div>
					<div class="row gx-3 mb-3">
						<div class="col-md-6">
							<label class="small mb-1" for="cbbTrangThai">Trạng thái</label> <select
								class="form-control" id="cbbTrangThai" name="cbbTrangThai">
								<option value="active"
									${nhanvien.trangThai eq 'active' ? 'selected' : ''}>Active</option>
								<option value="inactive"
									${nhanvien.trangThai eq 'inactive' ? 'selected' : ''}>Inactive</option>
							</select>
						</div>
					</div>
					<c:if test="${hanhdongtacdong == 'xemthongtincanhan'}">
					</c:if>
					<c:if test="${hanhdongtacdong == 'edit'}">
						<div class="d-flex justify-content-end">
							<button type="submit" class="btn btn-primary">Xác nhận</button>
						</div>
					</c:if>
					<c:if test="${empty hanhdongtacdong}">
						<div class="d-flex justify-content-end">
							<button type="submit" class="btn btn-primary">Xác nhận</button>
						</div>
					</c:if>

				</div>
			</div>
		</div>
	</div>
</form>

<script>
	function showImage(input) {
		var fileInput = input;
		var previewImage = document.getElementById('previewImage');

		if (fileInput.files && fileInput.files[0]) {
			var reader = new FileReader();
			reader.onload = function(e) {
				previewImage.src = e.target.result;
			};
			reader.readAsDataURL(fileInput.files[0]);
		}
	}
	

</script>
