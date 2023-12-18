package Controller;

import java.io.Console;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import DAO.ChiNhanhDAO;
import DAO.LoginDao;
import DAO.NhanVienDAO;
import Models.ChiNhanh;
import Models.LoginBean;
import Models.NhanVien;

@WebServlet("/login")
public class LoginController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private LoginDao loginDao;
	private NhanVienDAO nvDAO;
	private ChiNhanhDAO cnDAO;
	
	public void init() {
		loginDao = new LoginDao();
		nvDAO = new NhanVienDAO();
		cnDAO = new ChiNhanhDAO();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("login/login.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		authenticate(request, response);
	}

	private void authenticate(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(username);
		System.out.println(password);
		LoginBean loginBean = new LoginBean();
		loginBean.setUsername(username);
		loginBean.setPassword(password);
			
		try {
		    LoginBean acc = new LoginBean();
		    acc = LoginDao.validate(loginBean);

		    if (acc != null) {
		        HttpSession session = request.getSession();
		        boolean ktTrangThai = checkDangNhapQuaTrangThai(acc);
		        System.out.println("Check " + ktTrangThai);

		        if (!ktTrangThai) {
		            // Account is locked
		            request.setAttribute("errMsg", "Tài khoản của bạn đã bị khóa");
		            RequestDispatcher dispatcher = request.getRequestDispatcher("pages/login.jsp");
		            dispatcher.forward(request, response);
		            System.out.println("Thông báo 1");
		        } else {
		            // Account is not locked
		            session.setAttribute("accLogin", acc);	            
		            response.sendRedirect(request.getContextPath() + "/pages/index.jsp");
		        }
		    } else {
		        // Invalid credentials
		        request.setAttribute("errMsg", "Thông tin đăng nhập thất bại");
		        RequestDispatcher dispatcher = request.getRequestDispatcher("pages/login.jsp");
		        dispatcher.forward(request, response);
		        System.out.println("Thông báo 2");
		    }
		} catch (ClassNotFoundException e) {
		    e.printStackTrace();
		}

	}
	
	
	private boolean checkDangNhapQuaTrangThai(LoginBean acc) throws IOException, ServletException {
		NhanVien nvLogin = nvDAO.selectNhanVien(acc.getMaNhanvien());
		if(nvLogin.getTrangThai().equals("inactive"))
			return false;
		else
			return true;
	}
}
