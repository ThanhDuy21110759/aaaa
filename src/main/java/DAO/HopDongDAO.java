package DAO;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Models.HoSo;
import Models.HopDong;
import Models.NhanVien;
import Util.HandleException;
import Util.JDBCUtils;

public class HopDongDAO {
	private static final String INSERT_HOPDONG_SQL = "INSERT INTO hopdong"
			+ "  (MaHD, MaHS, LoaiHopDong, SoQuyetDinh,  NgayKyHD, HanHD, TrangThai, NoiDung) VALUES "
			+ " (?, ?, ?, ?, ?, ?,?,?);";
	
	private static final String SELECT_HOPDONG_BY_MAHS = "SELECT * FROM hopdong WHERE MaHS = ?";
	private static final String DELETE_QTCT_BY_MAHD= "delete from hopdong where MaHD = ?;";

	public boolean deleteHD(String maHD) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_QTCT_BY_MAHD);) {
			statement.setString(1, maHD);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}
	
	public void insertHopDong(HopDong hd) throws SQLException {
		System.out.println(INSERT_HOPDONG_SQL);
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_HOPDONG_SQL)) {

			preparedStatement.setString(1, hd.getMaHD());
			preparedStatement.setString(2, hd.getMaHS());
			preparedStatement.setString(3, hd.getLoaiHD());
			preparedStatement.setString(4, hd.getSoQD());
			preparedStatement.setDate(5, JDBCUtils.getSQLDate(hd.getNgayKyHD()));
			preparedStatement.setDate(6, JDBCUtils.getSQLDate(hd.getHanHD()));
			preparedStatement.setString(7, hd.getTrangThai());
			preparedStatement.setString(8, hd.getNoiDung());

			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {
			HandleException.printSQLException(exception);
		}
	}

	public List<HopDong> selectAllHopDongTheoMaHS(String MaHS) {

		List<HopDong> hopdongs = new ArrayList<>();

		try (Connection connection = JDBCUtils.getConnection();

			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_HOPDONG_BY_MAHS);) {
	        preparedStatement.setString(1, MaHS);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
	            String maHD = rs.getString("MaHD");
	            String loaiHD = rs.getString("LoaiHopDong");
	            String soQD = rs.getString("SoQuyetDinh");
	            LocalDate ngayKyHD = rs.getDate("NgayKyHD").toLocalDate();
	            LocalDate hanHD = rs.getDate("HanHD").toLocalDate();
	            String trangThai = rs.getString("TrangThai");
	            String noiDung = rs.getString("NoiDung");
				hopdongs.add(new HopDong(maHD, MaHS, loaiHD, soQD, ngayKyHD, hanHD, trangThai, noiDung));
			}
		} catch (SQLException exception) {
			HandleException.printSQLException(exception);
		}
		return hopdongs;
	}
	
	public HopDong selectHopDongByMaHS(String MaHS) {
	    HopDong hd = null;
	    try (Connection connection = JDBCUtils.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(SELECT_HOPDONG_BY_MAHS);) {
	        preparedStatement.setString(1, MaHS);
	        System.out.println(preparedStatement);
	        ResultSet rs = preparedStatement.executeQuery();

	        while (rs.next()) {
	            String maHD = rs.getString("MaHD");
	            String loaiHD = rs.getString("LoaiHopDong");
	            String soQD = rs.getString("SoQuyetDinh");
	            LocalDate ngayKyHD = rs.getDate("NgayKyHD").toLocalDate();
	            LocalDate hanHD = rs.getDate("HanHD").toLocalDate();
	            String trangThai = rs.getString("TrangThai");
	            String noiDung = rs.getString("NoiDung");

	            hd = new HopDong(maHD, MaHS, loaiHD, soQD, ngayKyHD, hanHD, trangThai, noiDung);
	        }
	    } catch (SQLException exception) {
	        HandleException.printSQLException(exception);
	    }

	    return hd;
	}

}
