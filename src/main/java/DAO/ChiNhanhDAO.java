package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import Models.ChiNhanh;
import Models.PhongBan;
import Util.HandleException;
import Util.JDBCUtils;

public class ChiNhanhDAO {
	private static final String SELECT_CN = "SELECT * FROM chinhanh";
	private static final String UPDATE_CN = "update chinhanh set MaGiamDoc=?, TenChiNhanh=?, TrangThai=? where MaCN=? ;";
	private static final String CREATE_CN = "insert into chinhanh(MaCN,TenChiNhanh,TrangThai) values(?,?,?);";
	private static final String DELETE_CN = "update chinhanh set TrangThai=? where MaCN=?";
	private static final String UpdateQuyenCN_bangphanquyen = "UPDATE bangphanquyen SET MaNV = ? WHERE MaCN = ? AND MaQH = 'giamdoc';";
	private static final String UpdateQuyenCN_bangchinhanh = "UPDATE chinhanh set MaGiamDoc= ? where MaCN = ?";
	
	PhongBanDAO pbDAO = new PhongBanDAO();
	
	public List<ChiNhanh> SelectAllChiNhanh()
	{
		List<ChiNhanh> chinhanhs = new ArrayList<>();

		try (Connection connection = JDBCUtils.getConnection();
			PreparedStatement preparedStatement = connection.prepareStatement(SELECT_CN);) {
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
	            String maCN = rs.getString("MaCN");
	            String maGiamDoc = rs.getString("MaGiamDoc");
	            String tenChiNhanh = rs.getString("TenChiNhanh");
	            String trangThai = rs.getString("TrangThai");
	            List<PhongBan> listHD = pbDAO.SelectAllPBByMaCN(maCN);
	           
	            chinhanhs.add(new ChiNhanh(maCN, maGiamDoc, tenChiNhanh, listHD,trangThai ));
			}
		} catch (SQLException exception) {
			HandleException.printSQLException(exception);
		}
		return chinhanhs;
	}
	
	public boolean taoCN(String maCN,String tenCN) throws SQLException {
        boolean rowUpdated;
      
        try (Connection connection = JDBCUtils.getConnection(); PreparedStatement statement = connection.prepareStatement(CREATE_CN);) {
            statement.setString(1, maCN);
            statement.setString(2, tenCN);
            statement.setString(3, "danghoatdong");
            
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
	
	public boolean suaCN(String maCN,String tenCN,String maGD,String trangthai) throws SQLException {
        boolean rowUpdated;
      
        try (Connection connection = JDBCUtils.getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_CN);) {
            statement.setString(1, maGD);
            statement.setString(2, tenCN);
            statement.setString(3, trangthai);
            statement.setString(4, maCN);
 
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
	
	public boolean updateQuyenCN(String maNV,String maCN) throws SQLException {
        boolean rowUpdated;
      
        try (Connection connection = JDBCUtils.getConnection(); 
        	PreparedStatement statement1 = connection.prepareStatement(UpdateQuyenCN_bangphanquyen);
        	PreparedStatement statement2 = connection.prepareStatement(UpdateQuyenCN_bangchinhanh);
        		) {
        	statement1.setString(1, maNV);
            statement1.setString(2, maCN);
            statement2.setString(1, maNV);
            statement2.setString(2, maCN);
      
            rowUpdated = statement1.executeUpdate() > 0;
            rowUpdated = statement2.executeUpdate() > 0;
        }
        return rowUpdated;
    }
	
	public boolean xoaCN(String maCN) throws SQLException {
        boolean rowUpdated;
      
        try (Connection connection = JDBCUtils.getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_CN);) {
            statement.setString(1, "ngunghoatdong");
            statement.setString(2, maCN);

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
	
	
	
	
}
