package Controller;

import java.io.File;
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

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import DAO.NhanVienDAO;
import Models.NhanVien;
import DAO.TaiKhoanDAO;
import Models.TaiKhoan;

@WebServlet("/nhanviencontrol")
public class NhanVienController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private NhanVienDAO nhanvienDAO;
	private TaiKhoanDAO taikhoanDAO;
	private List<FileItem> files;

	public NhanVienController() {
		super();
		nhanvienDAO = new NhanVienDAO();
		taikhoanDAO = new TaiKhoanDAO();
	}

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		String action = null;
		String filePath =null;
		if (session.getAttribute("action") != null) {
			action = (String) session.getAttribute("action");
			session.removeAttribute("action");
			System.out.println("Action from session: " + action);
		} else {
			action = request.getParameter("action");
			filePath = request.getParameter("filePath");
			System.out.println("Action from request parameter: " + action);
			System.out.println("parameter: " + filePath);

		}

		System.out.println("Action: " + action);
		try {
			switch (action) {
			case "delete":
				deleteNhanVien(request, response);
				break;
			case "edit":
				showEditForm(request, response);
				break;
			case "themnhanvien":
				showInsertForm(request, response);
				break;
			case "list":
				listNhanVien(request, response);
				break;
			case "import":
				importFromExcel(request,response,"D:/nhanvien.xlsx");
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

    public void importFromExcel(HttpServletRequest request, HttpServletResponse response,String filePath) throws SQLException, IOException, ServletException {
        try {
            List<NhanVien> nhanVienList = ExcelImporter.importFromExcelNV(filePath);

            for (NhanVien nhanVien : nhanVienList) {
            	nhanvienDAO.insertNhanVien(nhanVien);
            	TaiKhoan newTK = new TaiKhoan(nhanVien.getMaNV(),"1",nhanVien.getMaNV());
            	taikhoanDAO.insertTK(newTK);
            }

            System.out.println("Dữ liệu đã được import thành công.");
			listNhanVien(request, response);
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Có lỗi khi import dữ liệu từ Excel.");
        }
    }
	
	private void deleteNhanVien(HttpServletRequest request, HttpServletResponse response) throws SQLException, IOException {
		String maNV = request.getParameter("manv");
		nhanvienDAO.deleteNhanVien(maNV);
		response.sendRedirect("list");
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("-- Do Post--");
		boolean isMultipart = ServletFileUpload.isMultipartContent(request);
		String action = null;

		if (isMultipart) {
			handleFormMultipart(request);
			action = getActionFormMultipart(request);
			if ("insert".equals(action)) {
				themNhanVienMultipart(request, response);
				return;
			}
			else if ("update".equals(action)) {
				capNhatNhanVienMultipart(request, response);
				return;}
		} else {
			action = request.getParameter("action");
		}
		System.out.println(action);
	}

	private void handleFormMultipart(HttpServletRequest request) {
		try {
			String folder = getServletContext().getRealPath("AnhCaNhan");
			System.out.println(folder);
			int maxFileSize = 5000 * 1024;
			int maxMemSize = 5000 * 1024;
			String contentType = request.getContentType();
			if (contentType != null && contentType.indexOf("multipart/form-data") >= 0) {
				DiskFileItemFactory factory = new DiskFileItemFactory();
				factory.setSizeThreshold(maxMemSize);
				ServletFileUpload upload = new ServletFileUpload(factory);
				upload.setSizeMax(maxFileSize);
				files = upload.parseRequest(request);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	private String getActionFormMultipart(HttpServletRequest request) {
		String actionValue = null;
		try {
			String contentType = request.getContentType();

			if (contentType.indexOf(contentType) >= 0) {
				System.out.println("Bắt đầu test");
				for (FileItem item : files) {
					if (item.isFormField()) {
						String fieldName = item.getFieldName();
						String value = item.getString();

						System.out.println(fieldName + " : " + value);

						if ("insert".equals(value) && "action".equals(fieldName)) {
							actionValue = "insert";
							break;
						}
						else if ("update".equals(value) && "action".equals(fieldName)) {
							actionValue = "update";
							break;
						}
						
					} else {
						continue;
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return actionValue;
	}

	private void capNhatNhanVienMultipart(HttpServletRequest request, HttpServletResponse response) {
	    try {
	        String folder = getServletContext().getRealPath("AnhCaNhan");
			System.out.println(folder);
	        File file;

	        String contentType = request.getContentType();
	        if (contentType != null && contentType.indexOf("multipart/form-data") >= 0) {
	            String maNv = null;
	            String hoTen = null;
	            float luongCoBan = 0.0f;
	            String emailCV = null;
	            String trangThai = null;
	            String duongDanAnh = null;

	            for (FileItem fileItem : files) {
	                if (!fileItem.isFormField()) {
	                    String fileName = System.currentTimeMillis() + fileItem.getName();
	                    String path = folder + "\\" + fileName;
	                    file = new File(path);
	                    fileItem.write(file);

	                    duongDanAnh = fileName;
	                } else {
	                    String name = fileItem.getFieldName();
	                    String value = fileItem.getString();

	                    if ("inputMaNV".equals(name)) {
	                        maNv = value;
	                    } else if ("inputHoTen".equals(name)) {
	                        hoTen = value;
	                    } else if ("inputLuongCoBan".equals(name)) {
	                        luongCoBan = Float.parseFloat(value);
	                    } else if ("inputEmailCV".equals(name)) {
	                        emailCV = value;
	                    } else if ("cbbTrangThai".equals(name)) {
	                        trangThai = value;
	                    }
	                }
	            }
	            NhanVien updatedNV = new NhanVien(maNv, hoTen, luongCoBan, emailCV, trangThai, duongDanAnh);

	            nhanvienDAO.updateNhanVien(updatedNV);
	            if (updatedNV != null) {
					request.setAttribute("action", "list");
					RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/themnhanvien.jsp");
					dispatcher.forward(request, response);
	            }
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	
	private void themNhanVienMultipart(HttpServletRequest request, HttpServletResponse response) {
		try {
			String folder = getServletContext().getRealPath("AnhCaNhan");
			System.out.println(folder);
			File file;

			String contentType = request.getContentType();
			System.out.println(contentType);
			if (contentType.indexOf(contentType) >= 0) {
				String maNv = null;
				String hoTen = null;
				float luongCoBan = 0.0f;
				String emailCV = null;
				String trangThai = null;
				String duongDanAnh = null;
				System.out.println("Bắt đầu test");

				for (FileItem fileItem : files) {
					if (!fileItem.isFormField()) {
						String fileName = System.currentTimeMillis() + fileItem.getName();
						String path = folder + "\\" + fileName;
						file = new File(path);
						fileItem.write(file);

						duongDanAnh = fileName;
						System.out.println(duongDanAnh + "Test");

					} else {
						String name = fileItem.getFieldName();
						String value = fileItem.getString();
						System.out.println(name + " : " + value);
						if ("inputMaNV".equals(name)) {
							maNv = value;
							System.out.println("Kiểm tra có vào if hay không");
						} else if ("inputHoTen".equals(name)) {
							hoTen = value;
							System.out.println("Kiểm tra có vào if hay không");
						} else if ("inputLuongCoBan".equals(name)) {
							luongCoBan = Float.parseFloat(value);
						} else if ("inputEmailCV".equals(name)) {
							emailCV = value;
						} else if ("cbbTrangThai".equals(name)) {
							trangThai = value;
						}
					}
				}
				System.out.println(maNv + " : " + hoTen);
				NhanVien newNV = new NhanVien(maNv, hoTen, luongCoBan, emailCV, trangThai, duongDanAnh);
				TaiKhoan newTK = new TaiKhoan(maNv, "1", maNv);
				nhanvienDAO.insertNhanVien(newNV);
				TaiKhoanDAO tkDao = new TaiKhoanDAO();
				tkDao.insertTK(newTK);
				
				if (newNV != null) {
					request.setAttribute("nhanvien", newNV);
					request.setAttribute("hanhdongthemnhanvien", "hosoForm");
					RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/themnhanvien.jsp");
					dispatcher.forward(request, response);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void listNhanVien(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		List<NhanVien> listNhanVien = nhanvienDAO.selectAllNhanVien();
		request.setAttribute("listNhanVien", listNhanVien);
		RequestDispatcher dispatcher = request.getRequestDispatcher("pages/danhsachnhanvien.jsp");
		dispatcher.forward(request, response);
	}

	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String maNV = request.getParameter("manv");
		System.out.println("Action from session: " + maNV);

		NhanVien existingNV = nhanvienDAO.selectNhanVien(maNV);
		request.setAttribute("nhanvien", existingNV);
		request.setAttribute("hanhdongtacdong", "edit");

		RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/themnhanvien.jsp");
		dispatcher.forward(request, response);
	}

	private void showInsertForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, IOException, ServletException {
		String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
				+ request.getContextPath();
		response.sendRedirect(url + "/pages/themnhanvien.jsp");
	}


	public static void main(String[] args) {
	}
}
