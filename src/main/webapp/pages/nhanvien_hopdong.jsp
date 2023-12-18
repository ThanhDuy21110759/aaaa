<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="Util.JDBCUtils"%>

<%
String duongDanHopDong = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
		+ request.getContextPath();
%>
<form id="hopdong-form">
	<div class="row">
		<div class="col-xl-8">
			<!-- Account details card-->
			<div class="card mb-4">
				<div class="card-header">Hợp Đồng</div>
				<div class="card-body">
					<div class="row gx-3 mb-3">
						<div class="col-md-6">
							<label class="small mb-1" for="showMaHS">Mã hồ sơ</label> <input
								class="form-control" id="showMaHS" type="text" name="showMaHS"
								placeholder="Mã hồ sơ" value="<c:out value='${hoso.maHS}'/>"
								readonly>
						</div>
					</div>
					<div id="thongtinhopdong">
						<div class="row">
							<table class="table table-bordered">
								<thead>
									<tr>
										<th>Mã hợp đồng</th>
										<th>Loại hợp đồng</th>
										<th>Ngày ký hợp đồng</th>
										<th>Hạn Hợp Đồng</th>
										<th>Trạng thái</th>
										<th>Nội dung</th>
										<c:if test="${hanhdongtacdong == 'xemthongtincanhan'}">
										</c:if>
										<c:if test="${hanhdongtacdong == 'edit'}">
											<th>Thao tác</th>
										</c:if>
										<c:if test="${empty hanhdongtacdong}">
											<th>Thao tác</th>
										</c:if>
									</tr>
								</thead>
								<tbody>
									<c:forEach var="hopdong" items="${listHopDong}">
										<tr>
											<td><c:out value="${hopdong.maHD}" /></td>
											<td><c:out value="${hopdong.loaiHD}" /></td>
											<td><fmt:formatDate
													value="${JDBCUtils.getSQLDate(hopdong.ngayKyHD)}"
													pattern="dd/MM/yyyy" /></td>
											<td><fmt:formatDate
													value="${JDBCUtils.getSQLDate(hopdong.hanHD)}"
													pattern="dd/MM/yyyy" /></td>
											<td><c:out value="${hopdong.trangThai}" /></td>
											<td><c:out value="${hopdong.noiDung}" /></td>
											<c:if test="${hanhdongtacdong == 'xemthongtincanhan'}">
											</c:if>
											<c:if test="${hanhdongtacdong == 'edit'}">
												<td><a
													href="<%=duongDanHopDong %>/hopdongcontrol?action=delete&mahd=<c:out value='${hopdong.maHD}'/>&mahs=<c:out value='${hopdong.maHS}'/>">Xóa</a></td>
											</c:if>
											<c:if test="${empty hanhdongtacdong}">
												<td><a
													href="<%=duongDanHopDong %>/hopdongcontrol?action=delete&mahd=<c:out value='${hopdong.maHD}'/>&mahs=<c:out value='${hopdong.maHS}'/>">Xóa</a></td>
											</c:if>
										</tr>
									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
					<!-- button-->
					<div class="row gx-3 mb-3">

						<c:if test="${hanhdongtacdong == 'xemthongtincanhan'}">

						</c:if>
						<c:if test="${hanhdongtacdong == 'edit'}">
							<button type="button" id="themHopDong"
								class="open-button btn btn-primary" onclick="openForm()">
								Thêm</button>
						</c:if>
						<c:if test="${empty hanhdongtacdong}">
							<button type="button" id="themHopDong"
								class="open-button btn btn-primary" onclick="openForm()">
								Thêm</button>
							<div class="d-flex px-3 justify-content-end">
								<a
									href="<%=duongDanHopDong %>/hopdongcontrol?showMaHS=${hoso.maHS}&action=forwardInsertQTCT"
									class="text-decoration-none px-3 py-2 d-block "> <i
									class="fal fa-sub-item-icon"></i> Hoàn tất hợp đồng -> Quá
									trình công tác
								</a>
							</div>
						</c:if>



					</div>
				</div>
			</div>
		</div>
	</div>
</form>

<div class="formpopup" id="myForm"
	style="display: none; position: fixed; top: 50%; left: 50%; transform: translate(-50%, -50%);">
	<form class="form-container"
		action="<%=duongDanHopDong%>/hopdongcontrol">
		<h1>Thông tin Hợp Đồng</h1>
		<input type="hidden" name="action" value="insertHD" />
		<div class="row gx-3 mb-3">
			<div class="col-md-3">
				<label class="small mb-1" for="inputMaHD">Mã Hợp Đồng</label> <input
					class="form-control" id="inputMaHD" name="inputMaHD" type="text"
					placeholder="Nhập mã hợp đồng" value="">
			</div>
			<div class="col-md-3">
				<label class="small mb-1" for="inputMaHS">Mã Hồ Sơ</label> <input
					class="form-control" id="inputMaHS" name="inputMaHS" type="text"
					placeholder="" value="<c:out value='${hoso.maHS}'/>" readonly>
			</div>
			<div class="col-md-3">
				<label class="small mb-1" for="inputLoaiHopDong">Loại Hợp
					Đồng</label> <input class="form-control" id="inputLoaiHopDong"
					name="inputLoaiHopDong" type="text"
					placeholder="Nhập loại hợp đồng" value="">
			</div>
			<div class="col-md-3">
				<label class="small mb-1" for="inputSoQuyetDinh">Số Quyết
					Định</label> <input class="form-control" id="inputSoQuyetDinh"
					name="inputSoQuyetDinh" type="text"
					placeholder="Nhập số quyết định" value="">
			</div>
		</div>

		<div class="row gx-3 mb-3">
			<div class="col-md-3">
				<label class="small mb-1" for="inputNgayKyHD">Ngày Ký Hợp
					Đồng</label> <input class="form-control" id="inputNgayKyHD" type="date"
					name="inputNgayKyHD" placeholder="" value="">
			</div>

			<div class="col-md-3">
				<label class="small mb-1" for="inputHanHD">Hạn Hợp Đồng</label> <input
					class="form-control" id="inputHanHD" type="date" name="inputHanHD"
					placeholder="" value="">
			</div>

			<div class="col-md-6">
				<label class="small mb-1" for="cbbTrangThai">Trạng Thái</label> <select
					class="form-control" id="cbbTrangThai" name="cbbTrangThai">
					<option value="dang-tiep-tuc">Đang tiếp tục</option>
					<option value="huy">Hủy</option>
					<option value="het-han">Hết Hạn</option>
				</select>
			</div>
		</div>

		<div class="row gx-3 mb-3">
			<div class="col-md-12">
				<label class="small mb-1" for="inputNoiDung">Nội Dung</label>
				<textarea class="form-control" id="inputNoiDung" name="inputNoiDung"
					placeholder="Nhập nội dung" rows="3"></textarea>
			</div>
		</div>

		<button type="submit" class="btn btn-primary">Submit</button>
		<button type="button" class="btn btn-secondary" onclick="closeForm()">Close</button>
	</form>
</div>

<script>
	function openForm() {
		document.getElementById("myForm").style.display = "block";
	}

	function closeForm() {
		document.getElementById("myForm").style.display = "none";
	}
</script>

