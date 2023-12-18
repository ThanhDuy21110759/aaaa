package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Models.HopDong;
import Models.QuaTrinhCongTac;
import Util.HandleException;
import Util.JDBCUtils;

public class QuaTrinhCongTacDAO {
	private static final String INSERT_QTCT_SQL = "INSERT INTO quatrinhcongtac"
			+ "  (MaQTCT, MaHS, ThoiGianBatDau, ThoiGianKetThuc,  DonViCT) VALUES "
			+ " (?, ?, ?, ?, ?);";
	
	private static final String SELECT_QTCT_BY_MAHS = "SELECT * FROM quatrinhcongtac WHERE MaHS = ?";
	private static final String DELETE_QTCT_BY_MAQTCT = "delete from quatrinhcongtac where MaQTCT = ?;";

	
	public boolean deleteQTCT(String maQTCT) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement statement = connection.prepareStatement(DELETE_QTCT_BY_MAQTCT);) {
			statement.setString(1, maQTCT);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}	

	public void insertQTCT(QuaTrinhCongTac qtct) throws SQLException {
		System.out.println(INSERT_QTCT_SQL);
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_QTCT_SQL)) {

			preparedStatement.setString(1, qtct.getMaQTCT());
			preparedStatement.setString(2, qtct.getMaHS());
			preparedStatement.setDate(3, JDBCUtils.getSQLDate(qtct.getThoiGianBatDau()));
			preparedStatement.setDate(4, JDBCUtils.getSQLDate(qtct.getThoiGianKetThuc()));
			preparedStatement.setString(5, qtct.getDonViCT());

			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {
			HandleException.printSQLException(exception);
		}
	}

	public List<QuaTrinhCongTac> selectAllQTCTTheoMaHS(String MaHS) {

		List<QuaTrinhCongTac> qtcts = new ArrayList<>();

		try (Connection connection = JDBCUtils.getConnection();

			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_QTCT_BY_MAHS);) {
	        preparedStatement.setString(1, MaHS);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
	            String maQTCT = rs.getString("MaQTCT");
	            LocalDate thoiGianBatDau = rs.getDate("ThoiGianBatDau").toLocalDate();
	            LocalDate thoiGianKetThuc = rs.getDate("ThoiGianKetThuc").toLocalDate();
	            String dvct = rs.getString("DonViCT");
	            qtcts.add(new QuaTrinhCongTac(maQTCT, MaHS, thoiGianBatDau, thoiGianKetThuc, dvct));
			}
		} catch (SQLException exception) {
			HandleException.printSQLException(exception);
		}
		return qtcts;
	}
	
}
