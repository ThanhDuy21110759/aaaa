<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="Models.LoginBean"%>
<%@ page import="Controller.QuanLyNhanSuTrucThuoc"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="Util.JDBCUtils"%>

<%
String duongDan = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
		+ request.getContextPath();
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="wrapper">
	<nav id="sidebar">
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
		<div class="sidebar-header">
			<h3>
				<a href="../pages/index.jsp">HR MANAGEMENT</a>
			</h3>
		</div>
		<ul class="list-unstyled components">

			<li><a
				href="<%=duongDan%>/thongtincanhancontrol?action=laythongtin">Thông
					tin cá nhân</a></li>

			<li class="row">
				<div class="col">
					<a id="pageSubmenu1" href="#pageSubmenu1" data-bs-toggle="collapse"
						aria-expanded="false" class="dropdown-toggle"
						onclick="toggleCollapseAndCall()"> Nhân sự trực thuộc </a>
				</div>
				<div class="col-sm-2">
					<a href="<%=duongDan%>/nhanhcontrol"> <i
						class="fas fa-caret-down fa-sm"></i></a>
				</div>
				<ul class="collapse list-unstyled show" id="pageSubmenu1">
					<c:forEach var="branch" items="${listCN}">
						<li><a href="#${branch.maCN}" data-bs-toggle="collapse"
							aria-expanded="false" class="dropdown-toggle">Chi nhánh: <c:out
									value="${branch.maCN}" /></a>
							<ul class="collapse list-unstyled" id="${branch.maCN}">
								<c:forEach var="pb" items="${branch.phongBanList}">
									<li><a
										href="<%=duongDan %>/quanlynhansutructhuoccontroller?action=laythongtin&mapb=${pb.maPB}&macn=${branch.maCN}">
											---> Mã PB: <c:out value="${pb.maPB}" />
									</a></li>
								</c:forEach>
							</ul></li>
					</c:forEach>
				</ul>
			</li>

			<li><a href="#pageSubmenu2" data-bs-toggle="collapse"
				aria-expanded="false" class="dropdown-toggle"> Khen thưởng & Kỷ
					luật</a>
				<ul class="collapse list-unstyled" id="pageSubmenu2">
					<li><a href="<%=duongDan%>/ktkl?action=listKT">Khen thưởng</a></li>
					<li><a href="<%=duongDan%>/ktkl?action=listKL">Kỷ luật</a></li>
					<%
					if (acc == null && errMsg != null) {
					%>
					<div style="text-align: center; margin-top: 50px;">
						<p
							style="color: red; font-weight: bold; border: 1px solid red; padding: 10px; display: inline-block;"><%=errMsg%></p>
					</div>
					<%
					} else if ("giamdoc".equals(phanQuyen) || "truongphong".equals(phanQuyen) || "admin".equals(phanQuyen)) {
					%>
					<!-- Display menu items for giamdoc, truongphong, and admin -->
					<li><a href="<%=duongDan%>/ktkl?action=listKTKL_trangthai">Tạo
							đơn KTKL</a></li>
					<li><a href="<%=duongDan%>/ktkl?action=listKTKL_duyet">Duyệt
							đơn KTKL</a></li>
					<%
					}
					%>
				</ul></li>

			<li><a href="#pageSubmenu3" data-bs-toggle="collapse"
				aria-expanded="false" class="dropdown-toggle"> Khiếu nại</a>
				<ul class="collapse list-unstyled" id="pageSubmenu3">
					<li><a
						href="<%=request.getContextPath()%>/khieunai?action=listKNchoD">Gửi
							khiếu nại</a></li>
					<li><a
						href="<%=request.getContextPath()%>/khieunai?action=listKNdaD">Khiếu
							nại được duyệt</a></li>
					<%
					if (acc == null && errMsg != null) {
					%>
					<div style="text-align: center; margin-top: 50px;">
						<p
							style="color: red; font-weight: bold; border: 1px solid red; padding: 10px; display: inline-block;"><%=errMsg%></p>
					</div>
					<%
					} else if ("truongphong".equals(phanQuyen) || "giamdoc".equals(phanQuyen) || "admin".equals(phanQuyen)) {
					%>
					<!-- Display menu items for truongphong, giamdoc, and admin -->
					<li><a
						href="<%=request.getContextPath()%>/khieunai?action=listKNchuaD">Duyệt
							khiếu nại</a></li>

					<%
					}
					%>
				</ul></li>


			<%
			if (acc == null && errMsg != null) {
			%>
			<div style="text-align: center; margin-top: 50px;">
				<p
					style="color: red; font-weight: bold; border: 1px solid red; padding: 10px; display: inline-block;">Không
					được phép PQ, Quản lý phòng ban</p>
			</div>
			<%
			} else if ("admin".equals(phanQuyen)) {
			%>

			<li><a href="<%=duongDan%>/bangphanquyencontrol?action=list">Phân
					quyền</a></li>

			<li><a href="#pageSubmenu4" data-bs-toggle="collapse"
				aria-expanded="false" class="dropdown-toggle">Chi nhánh & Phòng
					ban</a>
				<ul class="collapse list-unstyled" id="pageSubmenu4">
					<li><a
						href="<%=request.getContextPath()%>/chinhanh?action=listChinhanh">Chi
							nhánh</a></li>
					<li><a
						href="<%=request.getContextPath()%>/phongban?action=listPhongBan">Phòng
							ban</a></li>
				</ul></li>

			<li><a href="#pageSubmenu5" data-bs-toggle="collapse"
				aria-expanded="false" class="dropdown-toggle">Quản lý nhân viên</a>
				<ul class="collapse list-unstyled" id="pageSubmenu5">
					<li><a href="<%=duongDan%>/nhanviencontrol?action=list">Danh
							sách nhân viên</a></li>
					<li><a
						href="<%=duongDan%>/nhanviencontrol?action=themnhanvien">Thêm
							nhân viên</a></li>
				</ul></li>
			<%
			}
			%>

		</ul>


		<script>
    var isSubMenu1Open = false;

    function toggleCollapseAndCall() {
        // Toggle the collapse
        $('#pageSubmenu1').collapse('toggle');
        isSubMenu1Open = !isSubMenu1Open;

        // Construct the URL
        var url = "<%=duongDan%>
			/nhanhcontrol";
				if (!isSubMenu1Open) {
					window.location.href = url;
				}
			}
		</script>
	</nav>
</div>



