<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="Util.JDBCUtils"%>

<%
String duongDanQTCT = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
		+ request.getContextPath();
%>

<form id="thongtincoban-form">
	<div class="row">
		<div class="col-xl-8">
			<div class="card mb-4">
				<div class="card-header">Quá trình công tác</div>
				<div class="card-body">
					<div class="row gx-3 mb-3">
						<div class="col-md-6">
							<label class="small mb-1" for="inputMaHS">Mã hồ sơ</label> <input
								class="form-control" id="inputMaHS" type="text"
								name="
									inputMaHS" placeholder="Mã nhân viên"
								value="<c:out value='${hoso.maHS}'/>" readonly>
						</div>
					</div>
					<div id="thongtincongtac">
						<div class="row">
							<table class="table table-bordered">
								<thead>
									<tr>
										<th>Mã quá trình công tác</th>
										<th>Thời gian bắt đầu</th>
										<th>Thời gian kết thúc</th>
										<th>Đơn vị công tác</th>
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
									<c:forEach var="qtct" items="${listQTCT}">
										<tr>
											<td><c:out value="${qtct.maQTCT}" /></td>
											<td><fmt:formatDate
													value="${JDBCUtils.getSQLDate(qtct.thoiGianBatDau)}"
													pattern="dd/MM/yyyy" /></td>
											<td><fmt:formatDate
													value="${JDBCUtils.getSQLDate(qtct.thoiGianKetThuc)}"
													pattern="dd/MM/yyyy" /></td>
											<td><c:out value="${qtct.donViCT}" /></td>
											<c:if test="${hanhdongtacdong == 'edit'}">
												<td><a
													href="<%=duongDanQTCT %>/quatrinhcongtaccontrol?action=delete&maqtct=<c:out value='${qtct.maQTCT}'/>&mahs=<c:out value='${qtct.maHS}'/>">Xóa</a>
												</td>
											</c:if>
											<c:if test="${empty hanhdongtacdong}">
												<td><a
													href="<%=duongDanQTCT %>/quatrinhcongtaccontrol?action=delete&maqtct=<c:out value='${qtct.maQTCT}'/>&mahs=<c:out value='${qtct.maHS}'/>">Xóa</a>
												</td>
											</c:if>
										</tr>



									</c:forEach>
								</tbody>
							</table>
						</div>
					</div>
					<c:if test="${hanhdongtacdong == 'xemthongtincanhan'}">
					</c:if>
					<c:if test="${hanhdongtacdong == 'edit'}">
						<div class="row gx-3 mb-3">
							<button type="button" id="themQTCT"
								class="open-button btn btn-primary" onclick="openFormQTCT()">
								Thêm</button>
						</div>
					</c:if>
					<c:if test="${empty hanhdongtacdong}">
						<div class="row gx-3 mb-3">
							<button type="button" id="themQTCT"
								class="open-button btn btn-primary" onclick="openFormQTCT()">
								Thêm</button>

							<div class="d-flex px-3 justify-content-end">
								<a href="<%=duongDanQTCT%>/pages/themnhanvien.jsp"
									class="text-decoration-none px-3 py-2 d-block "> <i
									class="fal fa-sub-item-icon"></i> Hoàn tất thêm -> Quay về thêm
									nhân viên
								</a>
							</div>
						</div>
					</c:if>
				</div>
			</div>
		</div>
	</div>
</form>
<div class="formpopup" id="formQuaTrinhCongTac"
	style="display: none; position: fixed; top: 50%; left: 50%; transform: translate(-50%, -50%);">
	<form class="form-container"
		action="<%=duongDanQTCT%>/quatrinhcongtaccontrol">
		<h1>Thông tin quá trình công tác</h1>
		<input type="hidden" name="action" value="insertQTCT" />

		<div class="row gx-3 mb-3">
			<div class="col-md-3">
				<label class="small mb-1" for="inputMaQTCT">Mã Quá Trình
					Công Tác</label> <input class="form-control" id="inputMaQTCT"
					name="inputMaQTCT" type="text"
					placeholder="Nhập mã quá trình công tác" required>
			</div>
			<div class="col-md-3">
				<label class="small mb-1" for="inputMaHS">Mã Hồ Sơ</label> <input
					class="form-control" id="inputMaHS" name="inputMaHS" type="text"
					value="<c:out value='${hoso.maHS}'/>" placeholder="Nhập mã hồ sơ"
					required readonly>
			</div>
		</div>

		<div class="row gx-3 mb-3">
			<div class="col-md-3">
				<label class="small mb-1" for="inputThoiGianBatDau">Thời
					Gian Bắt Đầu</label> <input class="form-control" id="inputThoiGianBatDau"
					type="date" name="inputThoiGianBatDau" required>
			</div>
			<div class="col-md-3">
				<label class="small mb-1" for="inputThoiGianKetThuc">Thời
					Gian Kết Thúc</label> <input class="form-control" id="inputThoiGianKetThuc"
					type="date" name="inputThoiGianKetThuc" required>
			</div>
			<div class="col-md-3">
				<label class="small mb-1 " for="inputDonViCT">Đơn Vị Công
					Tác</label> <input class="form-control mt-0" id="inputDonViCT"
					name="inputDonViCT" type="text" placeholder="Nhập đơn vị công tác"
					required>
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