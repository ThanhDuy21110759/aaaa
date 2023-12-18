<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="Util.JDBCUtils"%>
<%
String duongDanHoSo = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
		+ request.getContextPath();
%>
<form id="thongtincoban-form" class="form"
	action="<%=duongDanHoSo%>/taikhoancontrol" method="post">
	<div class="row">

		<c:if test="${hanhdongtacdong == 'edit'}">
			<input type="hidden" name="action" value="update" />
			<h1>Sửa thông tin</h1>
		</c:if>

		<div class="col-xl-8">
			<div class="card mb-4">
				<div class="card-header">Thông tin nhân viên</div>
				<div class="card-body">
					<input type="hidden" name="action" value="insert" />
					<div class="mb-3">
						<c:if test="${hanhdongtacdong == 'edit'}">
							<label class="small mb-1" for="inputTenTK">Tên tài khoản</label>
							<input class="form-control" id="inputTenTK" type="text"
								placeholder="" name="inputTenTK"
								value="<c:out value='${taikhoan.tenTaiKhoan}'/>"  readonly>
						</c:if>
						
						<c:if test="${hanhdongtacdong == 'edit'}">
							<label class="small mb-1" for="inputMaMK">Mật khẩu</label>
							<input class="form-control" id="inputMaMK" type="text"
								placeholder="Nhập mã nhân viên" name="inputMaMK"
								value="<c:out value='${taikhoan.matKhau}'/>">
						</c:if>
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

<script
	src="https://cdnjs.cloudflare.com/ajax/libs/axios/0.21.1/axios.min.js"></script>
<script src="<%=duongDanHoSo%>/js/api_tinhthanh.js"></script>
