package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Models.KhenThuongKyLuat;
import Util.HandleException;
import Util.JDBCUtils;

public class KhenThuongKyLuatDAO {
	private static final String querySelectAll = "select * from danhgiaktkl";
	private static final String queryDelete = "update danhgiaktkl set TrangThai=?  where MaDG =?;";
	private static final String queryUpdate = "update danhgiaktkl set NoiDungKhieuNai =? where MaDG =?;";
	private static final String queryCreateDonKNKL = "INSERT INTO DanhGiaKTKL(MaDG,MaNgGui,MaNgNhan,NoiDung,NgayGui,LoaiDG,TrangThai) VALUES (?,?,?,?,?,?,?);";
	private static final String queryLaySTT = "SELECT MAX(CAST(SUBSTRING(MaDG, 3) AS SIGNED)) FROM danhgiaktkl";
	private static final String queryDuyet = "update danhgiaktkl set MaNgDuyet =?, SoQuyetDinh=?,  NgayDuyet=?, TrangThai=?  where MaDG =?;";

	public List<KhenThuongKyLuat> LayKT(String id) {
		List<KhenThuongKyLuat> arrayKT = new ArrayList<>();
		try {
			Connection con = JDBCUtils.getConnection();
			PreparedStatement pre = con.prepareStatement(querySelectAll);
			System.out.print(querySelectAll);
			ResultSet result = pre.executeQuery();
			while (result.next()) {

				if ("khenthuong".equals(result.getString("LoaiDG")) && id.equals(result.getString("MaNgNhan")) && "daduyet".equals(result.getString("TrangThai"))) {

					String maDG = result.getString("MaDG");
					String soQD = result.getString("SoQuyetDinh");
					String nd = result.getString("NoiDung");
					Float sotien = result.getFloat("SoTienThuong");
					LocalDate ngaygui = result.getDate("NgayGui").toLocalDate();

					arrayKT.add(new KhenThuongKyLuat(maDG, soQD, nd, sotien, ngaygui));
				}

			}
		} catch (SQLException e) {
			HandleException.printSQLException(e);
		}
		return arrayKT;
	}

	public List<KhenThuongKyLuat> LayKL(String id) {
		List<KhenThuongKyLuat> arrayKL = new ArrayList<>();
		try {
			Connection con = JDBCUtils.getConnection();
			PreparedStatement pre = con.prepareStatement(querySelectAll);
			System.out.print(querySelectAll);
			ResultSet result = pre.executeQuery();
			while (result.next()) {

				if ("kyluat".equals(result.getString("LoaiDG")) && id.equals(result.getString("MaNgNhan")) &&"daduyet".equals(result.getString("TrangThai"))) {

					String maDG = result.getString("MaDG");
					String soQD = result.getString("SoQuyetDinh");
					String nd = result.getString("NoiDung");
					LocalDate ngaygui = result.getDate("NgayGui").toLocalDate();

					arrayKL.add(new KhenThuongKyLuat(maDG, soQD, nd, ngaygui));
				}

			}
		} catch (SQLException e) {
			HandleException.printSQLException(e);
		}
		return arrayKL;
	}

	public List<KhenThuongKyLuat> LayKTKL_all(String id) {
		List<KhenThuongKyLuat> arrayKT = new ArrayList<>();
		try {
			Connection con = JDBCUtils.getConnection();
			PreparedStatement pre = con.prepareStatement(querySelectAll);
			System.out.print(querySelectAll);
			ResultSet result = pre.executeQuery();
			while (result.next()) {
				if ("chuaduyet".equals(result.getString("TrangThai")) && id.equals(result.getString("MaNgGui"))) {
					String maDG = result.getString("MaDG");
					String nd = result.getString("NoiDung");
					LocalDate ngaygui = result.getDate("NgayGui").toLocalDate();
					String trangThai = result.getString("TrangThai");

					arrayKT.add(new KhenThuongKyLuat(maDG, nd, ngaygui, trangThai));
				}
			}
		} catch (SQLException e) {
			HandleException.printSQLException(e);
		}
		return arrayKT;
	}
	
	public List<KhenThuongKyLuat> LayKTKL_duyet() {
		List<KhenThuongKyLuat> arrayKT = new ArrayList<>();
		try {
			Connection con = JDBCUtils.getConnection();
			PreparedStatement pre = con.prepareStatement(querySelectAll);
			System.out.print(querySelectAll);
			ResultSet result = pre.executeQuery();
			while (result.next()) {
				if ("chuaduyet".equals(result.getString("TrangThai"))) {
					
					String maDG = result.getString("MaDG");
					String maNggui = result.getString("MaNgGui");	
					String maNgnhan = result.getString("MaNgNhan");
					String noiDung = result.getString("NoiDung");
					LocalDate ngayGui = result.getDate("NgayGui").toLocalDate();
					String trangThai = result.getString("TrangThai");
					String loaiDG = result.getString("LoaiDG");
					arrayKT.add(new KhenThuongKyLuat(maDG, maNggui, maNgnhan,noiDung,ngayGui, trangThai,loaiDG));
				}
			}
		} catch (SQLException e) {
			HandleException.printSQLException(e);
		}
		return arrayKT;
	}

	public boolean taoKT(String maNgGui,String maNgNhan,String noiDung) throws SQLException {
		boolean rowUpdated;
		LocalDate currentDate = LocalDate.now();
		Date sqlDate = Date.valueOf(currentDate);
		
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement statement = connection.prepareStatement(queryCreateDonKNKL);) {
			statement.setString(1, generateComplaintCode());
			statement.setString(2, maNgGui);
			statement.setString(3, maNgNhan);
			statement.setString(4, noiDung);
			statement.setDate(5, sqlDate);	
			statement.setString(6, "khenthuong");		
			statement.setString(7, "chuaduyet");
			
			System.out.print(queryCreateDonKNKL);
			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	public boolean taoKL(String maNgGui,String maNgNhan,String noiDung) throws SQLException {
		boolean rowUpdated;
		LocalDate currentDate = LocalDate.now();
		Date sqlDate = Date.valueOf(currentDate);
		
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement statement = connection.prepareStatement(queryCreateDonKNKL);) {
			statement.setString(1, generateComplaintCode());
			statement.setString(2, maNgGui);
			statement.setString(3, maNgNhan);
			statement.setString(4, noiDung);
			statement.setDate(5, sqlDate);
			statement.setString(6, "kyluat");
			statement.setString(7, "chuaduyet");
			
			System.out.print(queryCreateDonKNKL);
			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	
	public boolean duyetKTKL(String maDG,String maNgduyet,String soQD) throws SQLException {
		boolean rowUpdated;
		LocalDate currentDate = LocalDate.now();
		Date sqlDate = Date.valueOf(currentDate);
		
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement statement = connection.prepareStatement(queryDuyet);) {
			statement.setString(1, maNgduyet);
			statement.setString(2, soQD);
			statement.setDate(3, sqlDate);
			statement.setString(4, "daduyet");
			statement.setString(5, maDG);
			
			System.out.print(queryDuyet);
			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
	}
	public boolean xoaKTKL(String maDG) throws SQLException {
		boolean rowUpdated;	
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement statement = connection.prepareStatement(queryDelete);) {			
			statement.setString(1, "huy");
			statement.setString(2, maDG);
	
			System.out.print(statement);
			rowUpdated = statement.executeUpdate() > 0;
		}
		return rowUpdated;
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
		String prefix = "DG";
		int nextNumber = getNextComplaintNumber();
		// Format the number with leading zeros if needed
		String formattedNumber = String.format("%03d", nextNumber);
		return prefix + formattedNumber;
	}

}
