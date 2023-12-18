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

import DAO.BangPhanQuyenDAO;
import DAO.ChiNhanhDAO;
import DAO.PhongBanDAO;
import Models.BangPhanQuyen;
import Models.HoSo;
import Models.HopDong;
import Models.QuaTrinhCongTac;

@WebServlet("/bangphanquyencontrol")
public class BangPhanQuyenController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private BangPhanQuyenDAO bpqDAO;
	private ChiNhanhDAO cnDAO;
	private PhongBanDAO pbDAO;

	public BangPhanQuyenController() {
		super();
		bpqDAO = new BangPhanQuyenDAO();
		cnDAO = new ChiNhanhDAO();
		pbDAO = new PhongBanDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		System.out.println("Action: " + action);
		try {
			switch (action) {
			case "insertBPQC":
				insertBPQ(request, response);
				break;
			case "list":
				listBPQ(request, response, action);
				break;
			case "delete":
				deleteBPQ(request, response);
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

	private void deleteBPQ(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String maPQ = request.getParameter("mabqp");
		BangPhanQuyen bpq = bpqDAO.selectBangPhanQuyen(maPQ);
		System.out.println("TEst: " + bpq.getMaQH());

		if (bpq.getMaQH().equals("truongphong")) {
			bpqDAO.CapNhapQuyenHanPB("", bpq.getMaPB());
		} else if (bpq.getMaQH().equals("giamdoc")) {
			bpqDAO.CapNhapQuyenHanCN("", bpq.getMaPQ());
		}

		bpqDAO.deleteBPQ(maPQ);
		listBPQ(request, response, "delete");
	}

	private void insertBPQ(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		try {
			String maPQ = request.getParameter("inputMaPQ");
			String maNV = request.getParameter("inputMaNV");
			String maQH = request.getParameter("inputMaQuyenHan");
			String maPB = request.getParameter("inputPhongBan");
			String maCN = request.getParameter("inputMaChiNhanh");
			String maVT = request.getParameter("inputViTri");

			if (maQH.equals("truongphong")) {
				bpqDAO.CapNhapQuyenHanPB(maNV, maPB);
			} else if (maQH.equals("giamdoc")) {
				bpqDAO.CapNhapQuyenHanCN(maNV, maCN);
			}

			BangPhanQuyen newBPQ = new BangPhanQuyen(maPQ, maNV, maQH, maPB, maCN, maVT);
			bpqDAO.insertBPQ(newBPQ);
			listBPQ(request, response, "insert");

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private void listBPQ(HttpServletRequest request, HttpServletResponse response, String action)
			throws SQLException, IOException, ServletException {

		List<BangPhanQuyen> listBPQ = bpqDAO.selectAllBangPhanQuyen();
		request.setAttribute("listBPQ", listBPQ);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/bangphanquyen.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
