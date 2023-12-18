package Controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import DAO.ChiNhanhDAO;
import Models.ChiNhanh;
import Models.PhongBan;

@WebServlet("/nhanhcontrol")
public class CauTrucNhanhController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private ChiNhanhDAO cnDAO = null; 
	
    public CauTrucNhanhController() {
        super();
        cnDAO = new ChiNhanhDAO();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<ChiNhanh> chinhanhs = cnDAO.SelectAllChiNhanh();
		request.setAttribute("listCN", chinhanhs);
		request.getRequestDispatcher("/pages/quanlynhansu_tructhuoc.jsp").forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
