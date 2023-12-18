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
				<h1 class="text-center mb-5">Kỷ luật</h1>	
											
					<table id="employeeTable" class="table table-bordered border-primary table-fixed">
						<thead class="table-dark">
							<tr>
								<th>Mã số</th>
								<th>Số quyết định</th>
								<th>Nội dung kỷ luật</th>	
								<th>Ngày gửi</th>
								<th style="width: 100px;">Thao tác</th>
								
							</tr>
						</thead>
						<tbody>
							<c:forEach var="kyluat" items="${listKL}">
								<tr>
									<td id="${kyluat.maDG}" ><c:out value="${kyluat.maDG}" /></td>
									<td id="${kyluat.soQD}" ><c:out value="${kyluat.soQD}" /></td>
									<td id="${kyluat.noiDung}"><c:out value="${kyluat.noiDung}" /></td>
									<td>
							      		<fmt:parseDate value="${kyluat.ngayGui}" pattern="yyyy-MM-dd" var="parsedDate" type="date" />
										<fmt:formatDate value="${parsedDate}" pattern="dd-MM-yyyy" />
							      	</td>
							      	
	    
							      	<td>											      	
										<a href="<%=duongDanIndex%>/ktkl?action=xoaDonKT&maQD_input=<c:out value="${kyluat.maDG}"/>"/>Xóa</a>
									</td>
								</tr>
								
							</c:forEach>
						</tbody>
					</table>
					<!-- thanh đếm trang -->
					<nav aria-label="Page navigation pagePos" style="display: flex; float: left;">
					  <ul class="pagination justify-content-center">
					    <li class="page-item disabled">
					      <a class="page-link" href="#" tabindex="-1">Previous</a>
					    </li>
					    <li class="page-item"><a class="page-link" href="#">1</a></li>
					    <li class="page-item"><a class="page-link" href="#">2</a></li>
					    <li class="page-item"><a class="page-link" href="#">3</a></li>
					    <li class="page-item">
					      <a class="page-link" href="#">Next</a>
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