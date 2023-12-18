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
import DAO.NhanVienDAO;
import DAO.QuaTrinhCongTacDAO;
import Models.HoSo;
import Models.HopDong;
import Models.NhanVien;
import Models.QuaTrinhCongTac;

@WebServlet("/quatrinhcongtaccontrol")
public class QuaTrinhCongTacController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private QuaTrinhCongTacDAO qtctDAO = null;
	private HoSoDAO hsDAO = null;
	private NhanVienDAO nhanvienDAO = null;

	public QuaTrinhCongTacController() {
		super();
		qtctDAO = new QuaTrinhCongTacDAO();
		hsDAO = new HoSoDAO();
		nhanvienDAO = new NhanVienDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		System.out.println("Action: " + action);
		try {
			switch (action) {
			case "insertQTCT":
				insertQTCT(request, response);
				break;
			case "list":
				listQTCT(request, response, action);
				break;
			case "delete":
				deleteQTCT(request, response);
				break;
			case "edit":
				listQTCT(request, response, action);
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
		doGet(request, response);
	}


	private void deleteQTCT(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String maQTCT = request.getParameter("maqtct");
		qtctDAO.deleteQTCT(maQTCT);

		String maHS = request.getParameter("mahs");
		HoSo hs = hsDAO.selectHoSo(maHS);
		request.setAttribute("manv", hs.getMaNV());

		listQTCT(request, response, "delete");
	}

	private void insertQTCT(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		try {
			String maQTCT = request.getParameter("inputMaQTCT");

			String maHS = request.getParameter("inputMaHS");

			LocalDate thoiGianBatDau = LocalDate.parse(request.getParameter("inputThoiGianBatDau"));

			LocalDate thoiGianKetThuc = LocalDate.parse(request.getParameter("inputThoiGianKetThuc"));

			String donViCT = request.getParameter("inputDonViCT");

			QuaTrinhCongTac newQTCT = new QuaTrinhCongTac(maQTCT, maHS, thoiGianBatDau, thoiGianKetThuc, donViCT);
			qtctDAO.insertQTCT(newQTCT);
			HoSo hs = hsDAO.selectHoSo(maHS);
			request.setAttribute("hoso", hs);
			request.setAttribute("hanhdongthemnhanvien", "quatrinhcongtac");

			listQTCT(request, response, "insert");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void listQTCT(HttpServletRequest request, HttpServletResponse response, String action)
			throws SQLException, IOException, ServletException {
		HoSo hs = null;
		if ("edit".equals(action)) {
			String maNV = request.getParameter("manv");
			System.out.println(maNV);
			hs = hsDAO.selectHoSoByMaNV(maNV);
		} else if ("delete".equals(action)) {
			String maNV = (String)request.getAttribute("manv");
			System.out.println(maNV);
			hs = hsDAO.selectHoSoByMaNV(maNV);
		} else {
			hs = (HoSo) request.getAttribute("hoso");
		}

		List<QuaTrinhCongTac> listQTCT = qtctDAO.selectAllQTCTTheoMaHS(hs.getMaHS());
		request.setAttribute("listQTCT", listQTCT);

		request.setAttribute("hoso", hs);
		request.setAttribute("hanhdongthemnhanvien", "quatrinhcongtac");
		if ("edit".equals(action)) {
			request.setAttribute("hanhdongtacdong", "edit");
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/themnhanvien.jsp");
		dispatcher.forward(request, response);
	}
}
