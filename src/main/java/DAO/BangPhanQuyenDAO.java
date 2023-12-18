package DAO;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Models.BangPhanQuyen;
import Models.NhanVien;
import Util.HandleException;
import Util.JDBCUtils;

public class BangPhanQuyenDAO {
	private static final String SELECT_ALL_BANGPHANQUYEN = "select * from bangphanquyen";
	private static final String INSERT_BANGPHANQUYEN_SQL = "INSERT INTO bangphanquyen"
			+ "  (MaPQ, MaNV, MaQH, MaPB,  MaCN, ViTri) VALUES "
			+ " (?, ?, ?, ?, ?,?);";
	private static final String DELETE_BANGPHANQUYEN_BY_MAPQ = "delete from bangphanquyen where MaPQ = ?;";
	private static final String SELECT_ALL_BANGPHANQUYEN_BY_MAPB = "select * from bangphanquyen where MaPB = ?;";
	private static final String SELECT_BANGPHANQUYEN_BY_MAPQ = "select * from bangphanquyen where MaPQ = ?;";
	private static final String SELECT_BANGPHANQUYEN_BY_NV = "select * from bangphanquyen where MaNV = ?;";
	private static final String SELECT_BANGPHANQUYEN_BY_MAPB_MANV = "select * from bangphanquyen where MaNV = ? AND MaPB = ?;";
	private static final String SELECT_BANGPHANQUYEN_BY_MAPB_MACN = "select * from bangphanquyen where MaNV = ? AND MaCN = ?;";

	private static final String UpdateQuyenCN_BANGCHINHANH_PHANQUYEN = "UPDATE chinhanh set MaGiamDoc= ? where MaCN = ?";
	private static final String UpdateQuyenCN_PHONGBAN_PHANQUYEN = "UPDATE phongban set MaTrgPhg= ? where MaPB = ?";

	public String selectQuyenHanCaoNhat(String maNV) throws SQLException {
	    String quyenHanCaoNhat = null;
	    System.out.println(SELECT_BANGPHANQUYEN_BY_NV);
	    try (Connection connection = JDBCUtils.getConnection();
	        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BANGPHANQUYEN_BY_NV)) {
	        preparedStatement.setString(1, maNV);
	        ResultSet rs = preparedStatement.executeQuery();

	        while (rs.next()) {
	            String maQH = rs.getString("MaQH");

	            if (quyenHanCaoNhat == null) {
	                quyenHanCaoNhat = maQH;
	            } else {
	                quyenHanCaoNhat = compareAndSelectHigherPermission(maQH, quyenHanCaoNhat);
	            }
	        }
	    } catch (SQLException exception) {
	        HandleException.printSQLException(exception);
	    }
	    return quyenHanCaoNhat;
	}
	
	private String compareAndSelectHigherPermission(String permission1, String permission2) {
	    String[] priorities = {"nhanvien", "truongphong", "giamdoc", "admin"};

	    int index1 = Arrays.asList(priorities).indexOf(permission1.toLowerCase());
	    int index2 = Arrays.asList(priorities).indexOf(permission2.toLowerCase());

	    if (index1 > index2) {
	        return permission1;
	    } else {
	        return permission2;
	    }
	}
	
	public BangPhanQuyen selectBangPhanQuyenByMaCNMaNV(String maNV,String maCN) throws SQLException {
	    BangPhanQuyen bpq = null;
	    System.out.println(SELECT_BANGPHANQUYEN_BY_MAPB_MACN);
	    try (Connection connection = JDBCUtils.getConnection();
	        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BANGPHANQUYEN_BY_MAPB_MACN)) {
	        preparedStatement.setString(1, maNV);
	        preparedStatement.setString(2, maCN);
		    System.out.println(maNV);
		    System.out.println(maCN);

	        ResultSet rs = preparedStatement.executeQuery();

	        while (rs.next()) {
	            String maPQ = rs.getString("MaPQ");
	            String maPB = rs.getString("MaPB");
	            String maQH = rs.getString("MaQH");
	            String viTri = rs.getString("ViTri");
	    	    System.out.println("Test");

	            bpq = new BangPhanQuyen(maPQ,maNV,maQH,maPB,maCN,viTri);
	        }
	    } catch (SQLException exception) {
	        HandleException.printSQLException(exception);
	    }
	    return bpq;
	}
	
	public BangPhanQuyen selectBangPhanQuyenByMaPBMaNV(String maNV,String maPB) throws SQLException {
	    BangPhanQuyen bpq = null;
	    System.out.println(SELECT_BANGPHANQUYEN_BY_MAPB_MANV);
	    try (Connection connection = JDBCUtils.getConnection();
	        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BANGPHANQUYEN_BY_MAPB_MANV)) {
	        preparedStatement.setString(1, maNV);
	        preparedStatement.setString(2, maPB);
		    System.out.println(maNV);
		    System.out.println(maPB);

	        ResultSet rs = preparedStatement.executeQuery();

	        while (rs.next()) {
	            String maPQ = rs.getString("MaPQ");
	            String maQH = rs.getString("MaQH");
	            String maCN = rs.getString("MaCN");
	            String viTri = rs.getString("ViTri");
	    	    System.out.println("Test");

	            bpq = new BangPhanQuyen(maPQ,maNV,maQH,maPB,maCN,viTri);
	        }
	    } catch (SQLException exception) {
	        HandleException.printSQLException(exception);
	    }
	    return bpq;
	}
	
	
	public boolean CapNhapQuyenHanPB(String MaNV, String MaPB) throws SQLException {
        boolean rowUpdated;

        try (Connection connection = JDBCUtils.getConnection(); PreparedStatement statement = connection.prepareStatement(UpdateQuyenCN_PHONGBAN_PHANQUYEN);) {
            statement.setString(1, MaNV);
            statement.setString(2, MaPB);
 
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
	}
	
	public boolean CapNhapQuyenHanCN(String MaNV, String MaCN) throws SQLException {
        boolean rowUpdated;

        try (Connection connection = JDBCUtils.getConnection(); PreparedStatement statement = connection.prepareStatement(UpdateQuyenCN_BANGCHINHANH_PHANQUYEN);) {
            statement.setString(1, MaNV);
            statement.setString(2, MaCN);
 
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
	}
	
	public BangPhanQuyen selectBangPhanQuyen(String maBQP) throws SQLException {
	    BangPhanQuyen bpq = null;
	    System.out.println(SELECT_BANGPHANQUYEN_BY_MAPQ);
	    try (Connection connection = JDBCUtils.getConnection();
	        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_BANGPHANQUYEN_BY_MAPQ)) {
	        preparedStatement.setString(1, maBQP);
	        ResultSet rs = preparedStatement.executeQuery();

	        while (rs.next()) {
	            String maPQ = rs.getString("MaPQ");
	            String maNV = rs.getString("MaNV");
	            String maQH = rs.getString("MaQH");
	            String maPB = rs.getString("MaPB");
	            String maCN = rs.getString("MaCN");
	            String viTri = rs.getString("ViTri");
	            
	            bpq = new BangPhanQuyen(maPQ,maNV,maQH,maPB,maCN,viTri);
	        }
	    } catch (SQLException exception) {
	        HandleException.printSQLException(exception);
	    }
	    return bpq;
	}

	public List<BangPhanQuyen> selectAllBangPhanQuyenByMaPB(String maPB) {
		List<BangPhanQuyen> listBPQ = new ArrayList<>();

		try (Connection connection = JDBCUtils.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BANGPHANQUYEN_BY_MAPB);) {
			preparedStatement.setString(1, maPB);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String maPQ = rs.getString("MaPQ");
				String maNV = rs.getString("MaNV");
				String maQH = rs.getString("MaQH");
				String maCN = rs.getString("MaCN");
				String viTri = rs.getString("ViTri");
				
				listBPQ.add(new BangPhanQuyen(maPQ,maNV,maQH,maPB,maCN,viTri));
			}
		} catch (SQLException exception) {
			HandleException.printSQLException(exception);
		}
		return listBPQ;
	}
	
	public boolean deleteBPQ(String maPQ) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_BANGPHANQUYEN_BY_MAPQ);) {
			statement.setString(1, maPQ);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}
	
	public void insertBPQ(BangPhanQuyen bpq) throws SQLException {
		System.out.println(INSERT_BANGPHANQUYEN_SQL);
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_BANGPHANQUYEN_SQL)) {
			preparedStatement.setString(1, bpq.getMaPQ());
			preparedStatement.setString(2, bpq.getMaNV());
			preparedStatement.setString(3, bpq.getMaQH());
			preparedStatement.setString(4, bpq.getMaPB());
			preparedStatement.setString(5, bpq.getMaCN());
			preparedStatement.setString(6, bpq.getViTri());

			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {
			HandleException.printSQLException(exception);
		}
	}
	
	public List<BangPhanQuyen> selectAllBangPhanQuyen() {

		List<BangPhanQuyen> listBPQ = new ArrayList<>();

		try (Connection connection = JDBCUtils.getConnection();

			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_BANGPHANQUYEN);) {
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String maPQ = rs.getString("MaPQ");
				String maNV = rs.getString("MaNV");
				String maQH = rs.getString("MaQH");
				String maPB = rs.getString("MaPB");
				String maCN = rs.getString("MaCN");
				String viTri = rs.getString("ViTri");
				
				listBPQ.add(new BangPhanQuyen(maPQ,maNV,maQH,maPB,maCN,viTri));
			}
		} catch (SQLException exception) {
			HandleException.printSQLException(exception);
		}
		return listBPQ;
	}
	
	public BangPhanQuyenDAO() {
		
	}
}
