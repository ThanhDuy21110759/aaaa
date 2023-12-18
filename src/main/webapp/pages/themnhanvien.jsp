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
				<caption>
					<h2>
						<c:if test="${nhanvien != null and hanhdongthemnhanvien == null}">
					            	Chỉnh sửa thông tin nhân viên - ${nhanvien.hoTen} | ${nhanvien.maNV}
					        	</c:if>
						<c:if test="${nhanvien == null}">
						</c:if>
					</h2>
				</caption>
				<c:if test="${empty hanhdongthemnhanvien}">
					<jsp:include page="./nhanvien_thongtincoban.jsp"></jsp:include>
				</c:if>

				<c:if test="${hanhdongthemnhanvien == 'hosoForm'}">
					<jsp:include page="./nhanvien_hoso.jsp"></jsp:include>
				</c:if>

				<c:if test="${hanhdongthemnhanvien == 'hopdong'}">
					<jsp:include page="./nhanvien_hopdong.jsp"></jsp:include>
				</c:if>

				<c:if test="${hanhdongthemnhanvien == 'quatrinhcongtac'}">
					<jsp:include page="./nhanvien_quatrinhcongtac.jsp"></jsp:include>
				</c:if>

				<c:if test="${hanhdongthemnhanvien == 'taikhoannv'}">
					<jsp:include page="./nhanvien_taikhoan.jsp"></jsp:include>
				</c:if>
				<hr class="mt-0 mb-4">
			</div>
		</div>
	</div>

	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/js/bootstrap.bundle.min.js"></script>
	<script src="<%=duongDanIndex%>/js/validate_form.js"></script>

	<script type="text/javascript" src="<%=duongDanIndex%>/js/main.js"></script>
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