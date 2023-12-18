package Controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.TaiKhoanDAO;
import Models.TaiKhoan;

@WebServlet("/taikhoancontrol")
public class TaiKhoanController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private TaiKhoanDAO tkDAO = null;
	
    public TaiKhoanController() {
        super();
        tkDAO = new TaiKhoanDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");

		System.out.println("Action: " + action);
		try {
			switch (action) {
			case "doimatkhau":
				doiMatKhau(request, response);
				break;
			case "laythongtintk":
				selectTaiKhoan(request, response);
				break;
			case "update":
				updateTaiKhoan(request, response);
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
	
	private void updateTaiKhoan(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String maNV = request.getParameter("inputTenTK");
		String matKhau = request.getParameter("inputMaMK");

		tkDAO.doiMatKhau(maNV,matKhau);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("pages/themnhanvien.jsp");
		dispatcher.forward(request, response);
	}
	
	private void selectTaiKhoan(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String maNV = request.getParameter("manv");
		TaiKhoan tk = tkDAO.selectTaiKhoanByMaNV(maNV);

		request.setAttribute("taikhoan", tk);
		request.setAttribute("hanhdongthemnhanvien", "taikhoannv");
		request.setAttribute("hanhdongtacdong", "edit");

		RequestDispatcher dispatcher = request.getRequestDispatcher("pages/themnhanvien.jsp");
		dispatcher.forward(request, response);
	}

	private void doiMatKhau(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String taikhoan = request.getParameter("username");
		String matkhau = request.getParameter("password");
		String newMatKhau = request.getParameter("newpassword");
		System.out.println(taikhoan);
		System.out.println(matkhau);

		boolean ktTaiKhoan = tkDAO.kiemTraMatKhau(taikhoan, matkhau);
		System.out.println(ktTaiKhoan);

		if(ktTaiKhoan == true) {
			tkDAO.doiMatKhau(taikhoan,newMatKhau);
			System.out.println("Đổi mật khẩu thành công");
		}
		else
		{
			
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
