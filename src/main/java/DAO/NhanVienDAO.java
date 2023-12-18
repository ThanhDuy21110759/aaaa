package DAO;

import java.sql.Connection;
import java.math.BigDecimal;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Models.NhanVien;
import Util.HandleException;
import Util.JDBCUtils;

public class NhanVienDAO {
	
	private static final String SELECT_ALL_NHANVIENS = "select * from nhanvien";
	private static final String INSERT_NHANVIENS_SQL = "INSERT INTO nhanvien"
			+ "  (MaNV, HoTen, LuongCoBan, EmailCongViec,  TrangThai, DuongDanAnh) VALUES " + " (?, ?, ?, ?, ?, ?);";
	private static final String UPDATE_DUONGDAN_NHANVIENS_SQL =  "UPDATE nhanvien SET DuongDanAnh = ? WHERE MaNV = ?";
	private static final String SELECT_NHANVIEN_BY_MANV = "SELECT * FROM nhanvien WHERE MaNV = ?";
	private static final String DELETE_NHANVIEN_BY_MANV = "delete from nhanvien where MaNV = ?;";
    public static final String UPDATE_NHANVIEN_BY_MANV = "UPDATE nhanvien SET HoTen = ?, LuongCoBan = ?, EmailCongViec = ?, TrangThai = ?, DuongDanAnh = ? WHERE MaNV = ?";
    public static final String SELECT_ALL_NHANVIENS_BY_MAPB =
    	    "SELECT DISTINCT NV.MaNV, NV.HoTen, NV.LuongCoBan, NV.EmailCongViec, NV.TrangThai, NV.DuongDanAnh " +
    	    "FROM NhanVien NV " +
    	    "JOIN BangPhanQuyen PQ ON NV.MaNV = PQ.MaNV " +
    	    "WHERE PQ.MaPB = ?";

    
    
    public boolean updateNhanVien(NhanVien nv) throws SQLException {
        boolean rowUpdated;
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATE_NHANVIEN_BY_MANV);) {
            statement.setString(1, nv.getHoTen());
            statement.setDouble(2, nv.getLuongCoBan());
            statement.setString(3, nv.getEmailCongViec());
            statement.setString(4, nv.getTrangThai());
            statement.setString(5, nv.getDuongDanAnh());
            statement.setString(6, nv.getMaNV());
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
    
	public boolean deleteNhanVien(String maNV) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_NHANVIEN_BY_MANV);) {
			statement.setString(1, maNV);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}
	
	public List<NhanVien> selectAllNhanVienByMaPB(String maPB) {
		List<NhanVien> nhanviens = new ArrayList<>();
		try (Connection connection = JDBCUtils.getConnection();

			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_NHANVIENS_BY_MAPB);) {
			preparedStatement.setString(1, maPB);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String maNv = rs.getString("MaNV");
				String hoTen = rs.getString("HoTen");
			    BigDecimal luongCoBanDecimal = rs.getBigDecimal("LuongCoBan");
			    float luongCoBanFloat = luongCoBanDecimal.floatValue();
				String emailCV = rs.getString("EmailCongViec");
				String trangThai = rs.getString("TrangThai");
				String duongDanAnh = rs.getString("DuongDanAnh");
				nhanviens.add(new NhanVien(maNv, hoTen, luongCoBanFloat, emailCV, trangThai,duongDanAnh));
			}
		} catch (SQLException exception) {
			HandleException.printSQLException(exception);
		}
		return nhanviens;
	}
	
	
	public List<NhanVien> selectAllNhanVien() {
		List<NhanVien> nhanviens = new ArrayList<>();
		try (Connection connection = JDBCUtils.getConnection();

			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_NHANVIENS);) {
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				String maNv = rs.getString("MaNV");
				String hoTen = rs.getString("HoTen");
			    BigDecimal luongCoBanDecimal = rs.getBigDecimal("LuongCoBan");
			    float luongCoBanFloat = luongCoBanDecimal.floatValue();
				String emailCV = rs.getString("EmailCongViec");
				String trangThai = rs.getString("TrangThai");
				String duongDanAnh = rs.getString("DuongDanAnh");
				nhanviens.add(new NhanVien(maNv, hoTen, luongCoBanFloat, emailCV, trangThai,duongDanAnh));
			}
		} catch (SQLException exception) {
			HandleException.printSQLException(exception);
		}
		return nhanviens;
	}
	
	public NhanVien selectNhanVien(String MaNv) {
		NhanVien nv = null;
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_NHANVIEN_BY_MANV);) {
			preparedStatement.setString(1, MaNv);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String maNv = rs.getString("MaNV");
				String hoTen = rs.getString("HoTen");
			    BigDecimal luongCoBanDecimal = rs.getBigDecimal("LuongCoBan");
			    float luongCoBanFloat = luongCoBanDecimal.floatValue();
				String emailCV = rs.getString("EmailCongViec");
				String trangThai = rs.getString("TrangThai");
				String duongDanAnh = rs.getString("DuongDanAnh");
				nv = new NhanVien(maNv, hoTen, luongCoBanFloat, emailCV, trangThai, duongDanAnh);
			}
		} catch (SQLException exception) {
			HandleException.printSQLException(exception);
		}
		
		System.out.println(nv.getMaNV());
		return nv;
	}
	
	public void insertNhanVien(NhanVien nv) throws SQLException {
		System.out.println(INSERT_NHANVIENS_SQL);
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_NHANVIENS_SQL)) {
			preparedStatement.setString(1, nv.getMaNV());
			preparedStatement.setString(2, nv.getHoTen());
			preparedStatement.setFloat(3, nv.getLuongCoBan());
			preparedStatement.setString(4, nv.getEmailCongViec());
			preparedStatement.setString(5, nv.getTrangThai());
			preparedStatement.setString(6, nv.getDuongDanAnh());

			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {
			HandleException.printSQLException(exception);
		}
	}
	
	public int updateImage(NhanVien nv)  throws SQLException{
		int ketQua = 0;
		System.out.println(UPDATE_DUONGDAN_NHANVIENS_SQL);
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_DUONGDAN_NHANVIENS_SQL)) {
			preparedStatement.setString(1, nv.getDuongDanAnh());
			preparedStatement.setString(2, nv.getMaNV());

			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {
			HandleException.printSQLException(exception);
		}

		return ketQua;
	}
	

}
