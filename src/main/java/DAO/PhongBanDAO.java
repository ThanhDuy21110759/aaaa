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

public class PhongBanDAO {
	private static final String SELECT_PB_BY_MAPB = "SELECT * FROM PhongBan WHERE MaPB= ?";
	private static final String SELECT_PB_BY_MACN = "SELECT * FROM PhongBan WHERE MaCN = ?";
	private static final String SELECT_PB = "SELECT * FROM PhongBan";
	private static final String UPDATE_PB = "update phongban set MaTrgPhg=?, TenPhongBan=?, TrangThai=? where MaPB=? ;";
	private static final String CREATE_PB = "insert into phongban(MaPB,MaCN,TenPhongBan,TrangThai) values(?,?,?,?);";
	private static final String DELETE_PB = "update phongban set TrangThai=? where MaPB=?";
	private static final String UpdateQuyenCN_bangphanquyen = "UPDATE bangphanquyen SET MaNV = ? WHERE MaPB = ? AND MaQH = 'truongphong';";
	private static final String UpdateQuyenCN_bangphongban = "UPDATE phongban set MaTrgPhg= ? where MaPB = ?";
	public PhongBanDAO() {
	}
	
    public List<PhongBan> SelectAllPBByMaCN(String maCN) {

        List<PhongBan> phongbans = new ArrayList<>();
        try (Connection connection = JDBCUtils.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PB_BY_MACN);) {
            preparedStatement.setString(1, maCN);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                String maPB = rs.getString("MaPB");
                System.out.println(maPB);
                String maTrgPhg = rs.getString("MaTrgPhg");
                String tenPhongBan = rs.getString("TenPhongBan");
                String trangthai = rs.getString("TrangThai");

                phongbans.add(new PhongBan(maPB, maCN, maTrgPhg, tenPhongBan,trangthai));
            }
        } catch (SQLException exception) {
            HandleException.printSQLException(exception);
        }
        return phongbans;
    }
	
    public PhongBan SelectPBByMaPB(String maPB)
	{
		PhongBan pb = null;
	    try (Connection connection = JDBCUtils.getConnection();
	        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PB_BY_MAPB);) {
	        preparedStatement.setString(1, maPB);
	        System.out.println(preparedStatement);
	        ResultSet rs = preparedStatement.executeQuery();

	        while (rs.next()) {
	            String maCN = rs.getString("MaCN");
	            String maTrgPhg = rs.getString("MaTrgPhg");
	            String tenPhongBan = rs.getString("TenPhongBan");
	            String trangthai = rs.getString("TrangThai");

	            pb = new PhongBan(maPB, maCN, maTrgPhg, tenPhongBan,trangthai);
	        }
	    } catch (SQLException exception) {
	        HandleException.printSQLException(exception);
	    }
	    return pb;
	}
    public PhongBan SelectPBByMaCN(String maCN)
	{
		PhongBan pb = null;
	    try (Connection connection = JDBCUtils.getConnection();
	        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PB_BY_MACN);) {
	        preparedStatement.setString(1, maCN);
	        System.out.println(preparedStatement);
	        ResultSet rs = preparedStatement.executeQuery();

	        while (rs.next()) {
	            String maPB = rs.getString("MaPB");
	            String maTrgPhg = rs.getString("MaTrgPhg");
	            String tenPhongBan = rs.getString("TenPhongBan");
	            String trangthai = rs.getString("TrangThai");

	            pb = new PhongBan(maPB, maCN, maTrgPhg, tenPhongBan,trangthai);
	        }
	    } catch (SQLException exception) {
	        HandleException.printSQLException(exception);
	    }
	    return pb;
	}
    
	public List<PhongBan> SelectPB()
	{
		List<PhongBan> phongbans = new ArrayList<>();
	    try (Connection connection = JDBCUtils.getConnection();
	        PreparedStatement preparedStatement = connection.prepareStatement(SELECT_PB);) {
	        System.out.println(preparedStatement);
	        ResultSet rs = preparedStatement.executeQuery();

	        while (rs.next()) {
	            String maPB = rs.getString("MaPB");
	            String maTrgPhg = rs.getString("MaTrgPhg");
	            String maCN = rs.getString("MaCN");
	            String tenPhongBan = rs.getString("TenPhongBan");
	            String trangthai = rs.getString("TrangThai");
	            
	            phongbans.add(new PhongBan(maPB, maCN, maTrgPhg, tenPhongBan,trangthai));     
	        }
	    } catch (SQLException exception) {
	        HandleException.printSQLException(exception);
	    }
	    return phongbans;
	}
	
	
	public boolean taoPB(String maPB,String tenPB,String maCN) throws SQLException {
        boolean rowUpdated;
      
        try (Connection connection = JDBCUtils.getConnection(); PreparedStatement statement = connection.prepareStatement(CREATE_PB);) {
            statement.setString(1, maPB);
            statement.setString(2, maCN);
            statement.setString(3, tenPB);
            statement.setString(4, "danghoatdong");
            
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
	
	public boolean suaPB(String maPB,String tenPB,String maGD,String trangthai) throws SQLException {
        boolean rowUpdated;
      
        try (Connection connection = JDBCUtils.getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATE_PB);) {
            statement.setString(1, maGD);
            statement.setString(2, tenPB);
            statement.setString(3, trangthai);
            statement.setString(4, maPB);
 
            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
	
	public boolean updateQuyenPB(String maNV,String maPB) throws SQLException {
        boolean rowUpdated;
      
        try (Connection connection = JDBCUtils.getConnection(); 
        	PreparedStatement statement1 = connection.prepareStatement(UpdateQuyenCN_bangphanquyen);
        	PreparedStatement statement2 = connection.prepareStatement(UpdateQuyenCN_bangphongban);
        		) {
        	statement1.setString(1, maNV);
            statement1.setString(2, maPB);
            statement2.setString(1, maNV);
            statement2.setString(2, maPB);
 


            rowUpdated = statement1.executeUpdate() > 0;
            rowUpdated = statement2.executeUpdate() > 0;
            

        }
        return rowUpdated;
    }
	
	public boolean xoaPB(String maPB) throws SQLException {
        boolean rowUpdated;
      
        try (Connection connection = JDBCUtils.getConnection(); PreparedStatement statement = connection.prepareStatement(DELETE_PB);) {
            statement.setString(1, "ngunghoatdong");
            statement.setString(2, maPB);

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }
	
	
	
}
