package Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.HoSoDAO;
import DAO.HopDongDAO;
import DAO.NhanVienDAO;
import DAO.QuaTrinhCongTacDAO;
import Models.HoSo;
import Models.HopDong;
import Models.LoginBean;
import Models.NhanVien;
import Models.QuaTrinhCongTac;

@WebServlet("/thongtincanhancontrol")
public class ThongTinCaNhanController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NhanVienDAO nvDAO;
	private HoSoDAO hsDAO;
	private HopDongDAO hdDAO;
	private QuaTrinhCongTacDAO qtctDAO;

	public ThongTinCaNhanController() {
		super();
		nvDAO = new NhanVienDAO();
		hsDAO = new HoSoDAO();
		hdDAO = new HopDongDAO();
		qtctDAO = new QuaTrinhCongTacDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");

		System.out.println("Action: " + action);
		try {
			switch (action) {
			case "laythongtin":
				laythongtin(request, response);
				break;
			case "xemhs":
				xemThongTinNhanSu(request, response);
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

	private void xemThongTinNhanSu(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		try {
			String maNV = request.getParameter("manv");
			
			NhanVien nvAccLogin = nvDAO.selectNhanVien(maNV);
			HoSo hsAccLogin = hsDAO.selectHoSoByMaNV(maNV);
			List<HopDong> listHopDongAccLogin = hdDAO.selectAllHopDongTheoMaHS(hsAccLogin.getMaHS());
			List<QuaTrinhCongTac> listQTCTAccLogin = qtctDAO.selectAllQTCTTheoMaHS(hsAccLogin.getMaHS());

			request.setAttribute("nhanvien", nvAccLogin);
			request.setAttribute("hoso", hsAccLogin);
			request.setAttribute("listQTCT", listQTCTAccLogin);
			request.setAttribute("listHopDong", listHopDongAccLogin);
			request.setAttribute("hanhdongtacdong", "xemthongtincanhan");

			RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/thongtincanhan.jsp");
			dispatcher.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private void laythongtin(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException {
		try {
			HttpSession session = request.getSession();
			LoginBean accLogin = (LoginBean) session.getAttribute("accLogin");

			if (accLogin == null) {
				String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
						+ request.getContextPath();
				response.sendRedirect(url + "/pages/login.jsp");
				return;
			}

			NhanVien nvAccLogin = nvDAO.selectNhanVien(accLogin.getMaNhanvien());
			HoSo hsAccLogin = hsDAO.selectHoSoByMaNV(accLogin.getMaNhanvien());
			List<HopDong> listHopDongAccLogin = hdDAO.selectAllHopDongTheoMaHS(hsAccLogin.getMaHS());
			List<QuaTrinhCongTac> listQTCTAccLogin = qtctDAO.selectAllQTCTTheoMaHS(hsAccLogin.getMaHS());

			request.setAttribute("nhanvien", nvAccLogin);
			request.setAttribute("hoso", hsAccLogin);
			request.setAttribute("listQTCT", listQTCTAccLogin);
			request.setAttribute("listHopDong", listHopDongAccLogin);
			request.setAttribute("hanhdongtacdong", "xemthongtincanhan");

			RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/thongtincanhan.jsp");
			dispatcher.forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}
}
