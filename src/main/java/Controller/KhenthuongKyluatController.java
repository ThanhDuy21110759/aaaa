package Controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.KhenThuongKyLuatDAO;
import Models.KhenThuongKyLuat;
import Models.LoginBean;

@WebServlet("/ktkl")
public class KhenthuongKyluatController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private KhenThuongKyLuatDAO dao;

	public void init(ServletConfig config) throws ServletException {	
		dao = new KhenThuongKyLuatDAO();
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		System.out.print(action);
		
		try {
			switch (action) {
			case "taoDonKT":
				taoDonKT(request,response);
				break;
			case "taoDonKL":
				taoDonKL(request,response);
				break;
			case "duyetDonKT":
				duyetDonKT(request,response);
				break;
			case "listKTKL_duyet":
				listKTKL_duyet(request,response);
				break;
			case "listKTKL_trangthai":
				listKTKL_trangthai(request,response);
				break;
			case "listKT":	
				listKT(request,response);
				break;
			case "listKL":	
				listKL(request,response);
				break;
			case "xoaDonKT":	
				xoaDonKT(request,response);
				break;
			case "xoaDonKL":	
				xoaDonKL(request,response);
				break;
			case "xoaTaodon":	
				xoaTaodon(request,response);
				break;
			case "xoaDuyet":	
				xoaDuyet(request,response);
				break;
			default:
				response.sendRedirect(request.getContextPath() + "/pages/khieunaiChuaDuyet.jsp");
				break;
			}
		} catch (SQLException ex) {
			throw new ServletException(ex);
		}	
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private void listKT(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		LoginBean acc =(LoginBean) request.getSession().getAttribute("accLogin");
		List<KhenThuongKyLuat> listKT = dao.LayKT(acc.getMaNhanvien());
		request.getSession().setAttribute("listKT", listKT);
	    response.sendRedirect(request.getContextPath() + "/pages/KhenThuong.jsp");
	}
	private void listKL(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		LoginBean acc =(LoginBean) request.getSession().getAttribute("accLogin");
		List<KhenThuongKyLuat> listKL = dao.LayKL(acc.getMaNhanvien());
		request.getSession().setAttribute("listKL", listKL);
	    response.sendRedirect(request.getContextPath() + "/pages/KyLuat.jsp");
	    
	}
	
	private void listKTKL_trangthai(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		LoginBean acc =(LoginBean) request.getSession().getAttribute("accLogin");
		List<KhenThuongKyLuat> listKTKL = dao.LayKTKL_all(acc.getMaNhanvien());
		request.setAttribute("listKTKL_trangthai", listKTKL);
	    RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/khenthuongkl_taodon.jsp");
		dispatcher.forward(request, response);
	}
	private void listKTKL_duyet(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<KhenThuongKyLuat> listKTKL = dao.LayKTKL_duyet();
		
		request.setAttribute("listKTKL_duyet", listKTKL);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/khenthuongkl_duyetdon.jsp");
		dispatcher.forward(request, response);
	}
	
	private void taoDonKT(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		LoginBean acc = (LoginBean) request.getSession().getAttribute("accLogin");
		String noidung =  request.getParameter("noidung_input");
		String maNgnhan =  request.getParameter("inputMaNV");

		dao.taoKT(acc.getMaNhanvien(),maNgnhan,noidung);   
		    
		listKTKL_trangthai(request,response);
	}
	
	private void taoDonKL(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		LoginBean acc = (LoginBean) request.getSession().getAttribute("accLogin");
		String noidung =  request.getParameter("noidung_input");
		String maNgnhan =  request.getParameter("inputMaNV");

		dao.taoKL(acc.getMaNhanvien(),maNgnhan,noidung);   		    
		listKTKL_trangthai(request,response);
	}
	
	private void duyetDonKT(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		LoginBean acc = (LoginBean) request.getSession().getAttribute("accLogin");
		String maQD =  request.getParameter("maQD_input");
		String soQD =  request.getParameter("soQD_input");
	
		
		dao.duyetKTKL(maQD,acc.getMaNhanvien(),soQD); 		    
		listKTKL_duyet(request,response);
	}
	
	private void xoaDonKT(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		String maQD =  request.getParameter("maQD_input");		
		dao.xoaKTKL(maQD); 		    
		listKT(request,response);
	}	
	private void xoaDonKL(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		String maQD =  request.getParameter("maQD_input");		
		dao.xoaKTKL(maQD); 		    
		listKL(request,response);
	}
	private void xoaTaodon(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		String maQD =  request.getParameter("maQD_input");		
		dao.xoaKTKL(maQD); 		    
		listKTKL_trangthai(request,response);
	}
	private void xoaDuyet(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException, ServletException {
		String maQD =  request.getParameter("maQD_input2");		
		dao.xoaKTKL(maQD); 		    
		listKTKL_duyet(request,response);
	}
	
	
	
	
	

}
