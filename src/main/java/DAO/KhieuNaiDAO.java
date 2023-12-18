package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Models.KhieuNai;
import Models.LoginBean;
import Util.HandleException;
import Util.JDBCUtils;

public class KhieuNaiDAO {
	private static final String querySelectAll = "select * from khieunai";
	private static final String queryDelete = "update khieunai set TrangThai=?  where MaKN =?;";
	private static final String queryUpdate = "update khieunai set NoiDungKhieuNai =? where MaKN =?;";
	private static final String queryCreate = "insert into khieunai (MaKN, MaNgGui, MaNgNhan, EmailCV, NoiDungKhieuNai, NgayGui, TrangThai) VALUES (?,?,?,?,?,?,?);";
	private static final String queryLayMaNV = "select MaNV from NhanVien WHERE EmailCongViec = ?;";
	private static final String queryLaySTT = "SELECT MAX(CAST(SUBSTRING(MaKN, 3) AS SIGNED)) FROM KhieuNai";
	private static final String queryUpdatePH = "update khieunai set NoiDungPhanHoi = ?, NgayDuyet = ?, TrangThai= ? where MaKN =?;";
	

	public List<KhieuNai> LayKNChuaDuyet(LoginBean id) {
		List<KhieuNai> arrayKN = new ArrayList<>();
		try {
			Connection con = JDBCUtils.getConnection();
			PreparedStatement pre = con.prepareStatement(querySelectAll);
			System.out.print(querySelectAll);
			ResultSet result = pre.executeQuery();
			while (result.next()) {
				if ("pending".equals(result.getString("TrangThai"))
						&& id.getMaNhanvien().equals(result.getString("MaNgNhan"))) {
					String makn = result.getString("MaKN");
					String nd = result.getString("NoiDungKhieuNai");
					LocalDate ngaygui = result.getDate("NgayGui").toLocalDate();
					arrayKN.add(new KhieuNai(makn, nd, ngaygui));
				}

			}
		} catch (SQLException e) {
			HandleException.printSQLException(e);
		}
		return arrayKN;
	}

	public List<KhieuNai> LayKNDaDuyet(LoginBean id) {
		List<KhieuNai> arrayKN = new ArrayList<>();
		try {
			Connection con = JDBCUtils.getConnection();
			PreparedStatement pre = con.prepareStatement(querySelectAll);
			System.out.print(querySelectAll);
			ResultSet result = pre.executeQuery();
			while (result.next()) {
				if ("approved".equals(result.getString("TrangThai"))
						&& (id.getMaNhanvien().equals(result.getString("MaNgGui")))) {
					String makn = result.getString("MaKN");
					String noidung = result.getString("NoiDungPhanHoi");
					LocalDate ngaygui = result.getDate("NgayGui").toLocalDate();
					String nguoiduyet = result.getString("MaNgNhan");
					LocalDate ngayduyet = result.getDate("NgayDuyet").toLocalDate();
					arrayKN.add(new KhieuNai(makn, noidung, ngaygui, ngayduyet, nguoiduyet));
				}
			}
		} catch (SQLException e) {
			HandleException.printSQLException(e);
		}

		return arrayKN;
	}

	public List<KhieuNai> LayKNChoDuyet(LoginBean id) {
		List<KhieuNai> arrayKN = new ArrayList<>();
		try {
			Connection con = JDBCUtils.getConnection();
			PreparedStatement pre = con.prepareStatement(querySelectAll);
			System.out.print(querySelectAll);
			ResultSet result = pre.executeQuery();
			while (result.next()) {
				/* String k = result.getString("TrangThai"); */
				System.out.print(id.getMaNhanvien());

				if ("pending".equals(result.getString("TrangThai"))
						&& (id.getMaNhanvien().equals(result.getString("MaNgGui")))) {
					String makn = result.getString("MaKN");
					String manv = result.getString("MaNgGui");
					String noidung = result.getString("NoiDungKhieuNai");
					LocalDate ngaygui = result.getDate("NgayGui").toLocalDate();
					arrayKN.add(new KhieuNai(makn, manv, noidung, ngaygui));
				}
			}
		} catch (SQLException e) {
			HandleException.printSQLException(e);
		}

		return arrayKN;
	}

	public String LayMaNV(String email) {
		String str = "";

		try (Connection con = JDBCUtils.getConnection();
				// Step 2:Create a statement using connection object
				PreparedStatement preparedStatement = con.prepareStatement(queryLayMaNV)) {
			preparedStatement.setString(1, email);

			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				str = rs.getString("MaNV");
			}

		} catch (SQLException e) {
			HandleException.printSQLException(e);
		}
		return str;
	}

	private static int getNextComplaintNumber() {
		int nextNumber = 1;
		try (Connection con = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = con.prepareStatement(queryLaySTT);
				ResultSet resultSet = preparedStatement.executeQuery()) {

			if (resultSet.next()) {
				int maxNumber = resultSet.getInt(1);
				nextNumber = maxNumber + 1;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return nextNumber;
	}

	public static String generateComplaintCode() {
		String prefix = "KN";
		int nextNumber = getNextComplaintNumber();
		// Format the number with leading zeros if needed
		String formattedNumber = String.format("%03d", nextNumber);
		return prefix + formattedNumber;
	}

	public boolean xoaKhieunai(String id) throws SQLException {
		boolean rowDeleted;
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement statement = connection.prepareStatement(queryDelete);) {
			statement.setString(1, "cancel");
			statement.setString(2, id);
			rowDeleted = statement.executeUpdate() > 0;
		}
		return rowDeleted;
	}

	public boolean capnhatKhieunai(String id, String content) throws SQLException {
		boolean rowUpdated;
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement statement = connection.prepareStatement(queryUpdate);) {
			statement.setString(1, content);
			statement.setString(2, id);
			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	public boolean taoKhieunai(String maNggui, String email, String noiDung) throws SQLException {
		boolean rowUpdated;
		LocalDate currentDate = LocalDate.now();
		Date sqlDate = Date.valueOf(currentDate);

		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement statement = connection.prepareStatement(queryCreate);) {
			statement.setString(1, generateComplaintCode());
			statement.setString(2, maNggui);
			String k = LayMaNV(email);
			statement.setString(3, LayMaNV(email));
			statement.setString(4, email);
			statement.setString(5, noiDung);
			statement.setDate(6, sqlDate);
			statement.setString(7, "pending");
			System.out.print(queryUpdate);
			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}

	public boolean capnhatPH(String id, String content) throws SQLException {
		boolean rowUpdated;
		LocalDate currentDate = LocalDate.now();
		Date sqlDate = Date.valueOf(currentDate);

		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement statement = connection.prepareStatement(queryUpdatePH);) {
			statement.setString(1, content);
			statement.setDate(2, sqlDate);
			statement.setString(3, "approved");
			statement.setString(4, id);
			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	
	

}
