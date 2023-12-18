package DAO;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Models.TaiKhoan;
import Util.HandleException;
import Util.JDBCUtils;

public class TaiKhoanDAO {
	private static final String INSERT_TAIKHOAN_SQL = "INSERT INTO taikhoan" + "  (TenTaiKhoan, MatKhau, MaNV) VALUES "
			+ " (?, ?, ?);";

	private static final String SELECT_TAIKHOAN_MaNV_SQL = "SELECT * FROM taikhoan WHERE MaNV = ?";

	private static final String CHECK_TAIKHOAN_SQL = "SELECT * FROM taikhoan WHERE TenTaiKhoan = ? and MatKhau = ?"
			+ " (?, ?);";

	private static final String DOIMATKHAU_TAIKHOAN_SQL = "UPDATE taikhoan SET MatKhau = ? WHERE TenTaiKhoan = ?";
	
	private static final String CHECK_DOIMATKHAU_TAIKHOAN_SQL = "SELECT * FROM taikhoan WHERE TenTaiKhoan = ? AND MatKhau = ?";


	
	public boolean doiMatKhau(String tenTK, String matKhauMoi) throws SQLException {
	    try (Connection connection = JDBCUtils.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(DOIMATKHAU_TAIKHOAN_SQL)) {

	        preparedStatement.setString(1, matKhauMoi);
	        preparedStatement.setString(2, tenTK);

	        int rowsAffected = preparedStatement.executeUpdate();

	        return rowsAffected > 0;
	    } catch (SQLException exception) {
	        HandleException.printSQLException(exception);
	        return false;
	    }
	}
	
	public boolean kiemTraMatKhau(String tenTK, String matKhau) throws SQLException {
	    try (Connection connection = JDBCUtils.getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(CHECK_DOIMATKHAU_TAIKHOAN_SQL)) {

	        preparedStatement.setString(1, tenTK);
	        preparedStatement.setString(2, matKhau);

	        
	        try (ResultSet resultSet = preparedStatement.executeQuery()) {
	            return resultSet.next() && tenTK.equals(resultSet.getString("TenTaiKhoan"))
	                    && matKhau.equals(resultSet.getString("MatKhau"));
	        }
	    } catch (SQLException exception) {
	        HandleException.printSQLException(exception);
	        return false;
	    }
	}

	public void insertTK(TaiKhoan tk) throws SQLException {
		System.out.println(INSERT_TAIKHOAN_SQL);
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_TAIKHOAN_SQL)) {

			preparedStatement.setString(1, tk.getTenTaiKhoan());
			preparedStatement.setString(2, tk.getMatKhau());
			preparedStatement.setString(3, tk.getMaNV());

			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {
			HandleException.printSQLException(exception);
		}
	}

	public TaiKhoan selectTaiKhoanByMaNV(String maNV) {
		TaiKhoan tk = null;
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_TAIKHOAN_MaNV_SQL);) {
			preparedStatement.setString(1, maNV);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String tenTk = rs.getString("TenTaiKhoan");
				String mk = rs.getString("MatKhau");
				String maVN = rs.getString("MaNV");
				tk = new TaiKhoan(tenTk, mk, maVN);
			}
		} catch (SQLException exception) {
			HandleException.printSQLException(exception);
		}

		System.out.println(tk.getMaNV());
		return tk;
	}

	public static TaiKhoan validate(TaiKhoan tk) throws ClassNotFoundException {
		TaiKhoan acc = null;
		System.out.println(CHECK_TAIKHOAN_SQL);
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(CHECK_TAIKHOAN_SQL)) {
			preparedStatement.setString(1, tk.getMaNV());
			preparedStatement.setString(2, tk.getMatKhau());

			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				acc = new TaiKhoan();
				acc.setTenTaiKhoan(rs.getString(1));
				acc.setMatKhau(rs.getString(2));
			}
		} catch (SQLException e) {
			HandleException.printSQLException(e);
		}
		return acc;
	}
}
