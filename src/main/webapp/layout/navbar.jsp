<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="Models.LoginBean"%>
<%@ page import="Controller.QuanLyNhanSuTrucThuoc"%>
<%@ page import="Util.JDBCUtils"%>
<%
String duongDan = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
		+ request.getContextPath();
%>

<%
	String errMsg = (String) request.getAttribute("errMsg");
	LoginBean acc = (LoginBean) session.getAttribute("accLogin");
	QuanLyNhanSuTrucThuoc test = new QuanLyNhanSuTrucThuoc();
	String phanQuyen = (acc != null) ? test.kiemTraQuyenCaoNhat(acc.getMaNhanvien()) : null;
%>
<%
if (acc == null && errMsg != null) {
%>
<div style="text-align: center; margin-top: 50px;">
	<p
		style="color: red; font-weight: bold; border: 1px solid red; padding: 10px; display: inline-block;"><%=errMsg%></p>
</div>
<%
}
%>
<div class="navbar-header">
	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<div class="container-fluid">

			<button type="button" id="sidebarCollapse" class="btn btn-info">
				<i class="fas fa-align-left"></i> <span>Toggle Sidebar</span>
			</button>
			<button class="btn btn-dark d-inline-block d-lg-none ml-auto"
				type="button" data-bs-toggle="collapse"
				data-bs-target="#navbarSupportedContent"
				aria-controls="navbarSupportedContent" aria-expanded="false"
				aria-label="Toggle navigation">
				<i class="fas fa-align-justify"></i>
			</button>

			<div class="collapse navbar-collapse" id="navbarSupportedContent">
				<ul class="navbar-nav ms-auto mb-2 mb-lg-0">
                     <% if (acc != null) { %>
                            <li class="nav-item active">
                                <a class="nav-link" href="#">Tên tài khoản: <%=acc.getUsername()%></a>
                            </li>
                            <li class="nav-item active">
                            	 <a class="nav-link" href="#">Chức vụ: <%=phanQuyen%></a>
                           	</li>
                        <% } %>
					<li class="nav-item">
						<div class="btn-group">
							<button class="btn dropdown-toggle" type="button"
								data-bs-toggle="dropdown" aria-expanded="false"
								style="width: 100px">
								<i class="fa fa-bars"></i>
							</button>
							<ul class="dropdown-menu">
								<li><a class="dropdown-item" href="<%=duongDan%>/thongtincanhancontrol?action=laythongtin">Thông tin cá nhân</a></li>
								<li><a class="dropdown-item" href="<%=duongDan%>/pages/doimatkhau.jsp">Đổi mật khẩu</a></li>
								<li><a class="dropdown-item"
									href="<%=duongDan%>/logout">Đăng
										xuất</a></li>
							</ul>
						</div>
					</li>
				</ul>
			</div>
		</div>
	</nav>

	<!-- Your content goes here -->

</div>