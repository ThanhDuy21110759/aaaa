package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import Models.LoginBean;
import Util.HandleException;
import Util.JDBCUtils;


public class LoginDao {

	public static LoginBean validate(LoginBean loginBean) throws ClassNotFoundException {
		LoginBean acc = null;

		Class.forName("com.mysql.jdbc.Driver");

		try (Connection connection = JDBCUtils.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement("select * from taikhoan where TenTaiKhoan = ? and MatKhau = ? ")) {
			preparedStatement.setString(1, loginBean.getUsername());
			preparedStatement.setString(2, loginBean.getPassword());

			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next())
			{
				acc = new LoginBean();
				acc.setUsername(rs.getString(1));
				acc.setPassword(rs.getString(2));
				acc.setMaNhanvien(rs.getString("MaNV"));
				System.out.println(rs.getString("MaNV"));
			}

		} catch (SQLException e) {
			HandleException.printSQLException(e);
		}
		return acc;
	}
}
