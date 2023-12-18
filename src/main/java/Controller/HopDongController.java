package Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.HoSoDAO;
import DAO.HopDongDAO;
import Models.HoSo;
import Models.HopDong;
import Models.NhanVien;

@WebServlet("/hopdongcontrol")
public class HopDongController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	HopDongDAO hdDAO = null;
	HoSoDAO hsDAO = null;

	public HopDongController() {
		super();
		hdDAO = new HopDongDAO();
		hsDAO = new HoSoDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		System.out.println("Action: " + action);
		try {
			switch (action) {
			case "insertHD":
				insertHopDong(request, response);
				break;
			case "list":
				listHopDong(request,response,action);
				break;
			case "forwardInsertQTCT":
				forwordInsertQTCT(request,response);
				break;
			case "edit":
				listHopDong(request,response,action);
				break;
			case "delete":
				deleteHD(request,response);
				break;
			default:
				RequestDispatcher dispatcher = request.getRequestDispatcher("pages/login.jsp");
				dispatcher.forward(request, response);
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

	private void deleteHD(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String maHD = request.getParameter("mahd");
		hdDAO.deleteHD(maHD);

		String maHS = request.getParameter("mahs");
		HoSo hs = hsDAO.selectHoSo(maHS);
		request.setAttribute("manv", hs.getMaNV());

		listHopDong(request, response, "delete");
	}
	
	private void insertHopDong(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		try {
			String maHD = request.getParameter("inputMaHD");

			String maHS = request.getParameter("inputMaHS");

			String loaiHopDong = request.getParameter("inputLoaiHopDong");

			String soQuyetDinh = request.getParameter("inputSoQuyetDinh");

			LocalDate ngayKyHD = LocalDate.parse(request.getParameter("inputNgayKyHD"));

			LocalDate hanHD = LocalDate.parse(request.getParameter("inputHanHD"));

			String trangThai = request.getParameter("cbbTrangThai");

			String noiDung = request.getParameter("inputNoiDung");

			HopDong newHopDong = new HopDong(maHD, maHS, loaiHopDong, soQuyetDinh, ngayKyHD, hanHD, trangThai, noiDung);
			hdDAO.insertHopDong(newHopDong);
			HoSo hs = hsDAO.selectHoSo(maHS);
			request.setAttribute("hoso", hs);
			request.setAttribute("hanhdongthemnhanvien", "hopdong");

			listHopDong(request,response,"insert");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void listHopDong(HttpServletRequest request, HttpServletResponse response, String action)
			throws SQLException, IOException, ServletException {

		HoSo hs = null;
		if ("edit".equals(action)) {
			String maNV = request.getParameter("manv");
			hs = hsDAO.selectHoSoByMaNV(maNV);
		} else if ("delete".equals(action)) {
			String maNV = (String)request.getAttribute("manv");
			hs = hsDAO.selectHoSoByMaNV(maNV);
		} else {
			hs = (HoSo) request.getAttribute("hoso");
		}

		List<HopDong> listHopDong = hdDAO.selectAllHopDongTheoMaHS(hs.getMaHS());
		request.setAttribute("listHopDong", listHopDong);
		
		request.setAttribute("hoso", hs);
		request.setAttribute("hanhdongthemnhanvien", "hopdong");
		if ("edit".equals(action)) {
			request.setAttribute("hanhdongtacdong", "edit");
		}
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/themnhanvien.jsp");
		dispatcher.forward(request, response);
	}
	
	private void forwordInsertQTCT(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String maHS = request.getParameter("showMaHS");
		HoSo hs = hsDAO.selectHoSo(maHS);
		request.setAttribute("hoso", hs);
		request.setAttribute("hanhdongthemnhanvien", "quatrinhcongtac");
		System.out.println(hs.getMaHS()  + "HDController");

		RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/themnhanvien.jsp");
		dispatcher.forward(request, response);
	}
}
