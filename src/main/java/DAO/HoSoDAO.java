package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

import Models.HoSo;
import Models.NhanVien;
import Util.HandleException;
import Util.JDBCUtils;

public class HoSoDAO {
	private static final String INSERT_HOSO_SQL = "INSERT INTO HoSo"
			+ "  (MaHS, MaNV, CCCD, NoiCapCCCD, NgayCapCCCD, MaSoThue, NgayCapMST, SoDienThoai, GioiTinh, QuocTich, DanToc, TonGiao, NgaySinh, NoiSinh, DiaChi, TinhThanh, QuanHuyen, PhuongXa, EmailCaNhan, TinhTrangHN, TrinhDoVanHoa, TrinhDoHocVan) VALUES "
			+ " (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?);";
	public static final String UPDATE_HOSO_BY_MAHS = "UPDATE HoSo SET "
			+ "MaNV = ?, CCCD = ?, NoiCapCCCD = ?, NgayCapCCCD = ?, "
			+ "MaSoThue = ?, NgayCapMST = ?, SoDienThoai = ?, GioiTinh = ?, "
			+ "QuocTich = ?, DanToc = ?, TonGiao = ?, NgaySinh = ?, "
			+ "NoiSinh = ?, DiaChi = ?, TinhThanh = ?, QuanHuyen = ?, "
			+ "PhuongXa = ?, EmailCaNhan = ?, TinhTrangHN = ?, TrinhDoVanHoa = ?, "
			+ "TrinhDoHocVan = ? WHERE MaHS = ?";
	private static final String SELECT_HOSO_BY_MAHS = "SELECT * FROM hoso WHERE MaHS = ?";
	private static final String SELECT_HOSO_BY_MANV = "SELECT * FROM hoso WHERE MaNV = ?";
	
	public boolean updateHoSo(HoSo hs) throws SQLException {
	    boolean rowUpdated;
	    try (Connection connection = JDBCUtils.getConnection();
	         PreparedStatement statement = connection.prepareStatement(UPDATE_HOSO_BY_MAHS);) {
	        statement.setString(1, hs.getMaNV());
	        statement.setString(2, hs.getcCCD());
	        statement.setString(3, hs.getNoiCapCCCD());
	        statement.setDate(4, JDBCUtils.getSQLDate(hs.getNgayCapCCCD()));
	        statement.setString(5, hs.getMaSoThue());
	        statement.setDate(6, JDBCUtils.getSQLDate(hs.getNgayCapMST()));
	        statement.setString(7, hs.getSoDienThoai());
	        statement.setBoolean(8, hs.getGioiTinh());
	        statement.setString(9, hs.getQuocTich());
	        statement.setString(10, hs.getDanToc());
	        statement.setString(11, hs.getTonGiao());
	        statement.setDate(12, JDBCUtils.getSQLDate(hs.getNgaySinh()));
	        statement.setString(13, hs.getNoiSinh());
	        statement.setString(14, hs.getDiaChi());
	        statement.setString(15, hs.getTinhThanh());
	        statement.setString(16, hs.getQuanHuyen());
	        statement.setString(17, hs.getPhuongXa());
	        statement.setString(18, hs.getEmailCaNhan());
	        statement.setString(19, hs.getTinhTrangHN());
	        statement.setString(20, hs.getTrinhDoVanHoa());
	        statement.setString(21, hs.getTrinhDoHocVan());
	        statement.setString(22, hs.getMaHS());

	        rowUpdated = statement.executeUpdate() > 0;
	    }
	    return rowUpdated;
	}
    
	public void insertHoSo(HoSo hs) throws SQLException {
		System.out.println(INSERT_HOSO_SQL);
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(INSERT_HOSO_SQL)) {
			preparedStatement.setString(1, hs.getMaHS());
			preparedStatement.setString(2, hs.getMaNV());
			preparedStatement.setString(3, hs.getcCCD());
			preparedStatement.setString(4, hs.getNoiCapCCCD());
			preparedStatement.setDate(5, JDBCUtils.getSQLDate(hs.getNgayCapCCCD()));
			preparedStatement.setString(6, hs.getMaSoThue());
			preparedStatement.setDate(7, JDBCUtils.getSQLDate(hs.getNgayCapMST()));
			preparedStatement.setString(8, hs.getSoDienThoai());
			preparedStatement.setBoolean(9, hs.getGioiTinh());
			preparedStatement.setString(10, hs.getQuocTich());
			preparedStatement.setString(11, hs.getDanToc());
			preparedStatement.setString(12, hs.getTonGiao());
			preparedStatement.setDate(13, JDBCUtils.getSQLDate(hs.getNgaySinh()));
			preparedStatement.setString(14, hs.getNoiSinh());
			preparedStatement.setString(15, hs.getDiaChi());
			preparedStatement.setString(16, hs.getTinhThanh());
			preparedStatement.setString(17, hs.getQuanHuyen());
			preparedStatement.setString(18, hs.getPhuongXa());
			preparedStatement.setString(19, hs.getEmailCaNhan());
			preparedStatement.setString(20, hs.getTinhTrangHN());
			preparedStatement.setString(21, hs.getTrinhDoVanHoa());
			preparedStatement.setString(22, hs.getTrinhDoHocVan());

			System.out.println(preparedStatement);
			preparedStatement.executeUpdate();
		} catch (SQLException exception) {
			HandleException.printSQLException(exception);
		}
	}

	public HoSo selectHoSo(String MaHS) {
		HoSo hs = null;
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_HOSO_BY_MAHS);) {
			preparedStatement.setString(1, MaHS);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String maHS = rs.getString("MaHS");
				String maNV = rs.getString("MaNV");
				String cccd = rs.getString("CCCD");
				String noiCapCCCD = rs.getString("NoiCapCCCD");
				LocalDate ngayCapCCCD = rs.getDate("NgayCapCCCD").toLocalDate();
				String maSoThue = rs.getString("MaSoThue");
				LocalDate ngayCapMST = rs.getDate("NgayCapMST").toLocalDate();
				String soDienThoai = rs.getString("SoDienThoai");
				Boolean gioiTinh = rs.getBoolean("GioiTinh");
				String quocTich = rs.getString("QuocTich");
				String danToc = rs.getString("DanToc");
				String tonGiao = rs.getString("TonGiao");
				LocalDate ngaySinh = rs.getDate("NgaySinh").toLocalDate();
				String noiSinh = rs.getString("NoiSinh");
				String diaChi = rs.getString("DiaChi");
				String tinhThanh = rs.getString("TinhThanh");
				String quanHuyen = rs.getString("QuanHuyen");
				String phuongXa = rs.getString("PhuongXa");
				String emailCaNhan = rs.getString("EmailCaNhan");
				String tinhTrangHN = rs.getString("TinhTrangHN");
				String trinhDoVanHoa = rs.getString("TrinhDoVanHoa");
				String trinhDoHocVan = rs.getString("TrinhDoHocVan");

				hs = new HoSo(maHS, maNV, cccd, noiCapCCCD, ngayCapCCCD, maSoThue, ngayCapMST, soDienThoai, gioiTinh,
						quocTich, danToc, tonGiao, ngaySinh, noiSinh, diaChi, tinhThanh, quanHuyen, phuongXa,
						emailCaNhan, tinhTrangHN, trinhDoVanHoa, trinhDoHocVan);
			}
		} catch (SQLException exception) {
			HandleException.printSQLException(exception);
		}

		System.out.println(hs.getMaHS());
		return hs;
	}

	public HoSo selectHoSoByMaNV(String MaNV) {
		HoSo hs = null;
		try (Connection connection = JDBCUtils.getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(SELECT_HOSO_BY_MANV);) {
			preparedStatement.setString(1, MaNV);
			System.out.println(preparedStatement);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				String maHS = rs.getString("MaHS");
				String maNV = rs.getString("MaNV");
				String cccd = rs.getString("CCCD");
				String noiCapCCCD = rs.getString("NoiCapCCCD");
				LocalDate ngayCapCCCD = rs.getDate("NgayCapCCCD").toLocalDate();
				String maSoThue = rs.getString("MaSoThue");
				LocalDate ngayCapMST = rs.getDate("NgayCapMST").toLocalDate();
				String soDienThoai = rs.getString("SoDienThoai");
				Boolean gioiTinh = rs.getBoolean("GioiTinh");
				String quocTich = rs.getString("QuocTich");
				String danToc = rs.getString("DanToc");
				String tonGiao = rs.getString("TonGiao");
				LocalDate ngaySinh = rs.getDate("NgaySinh").toLocalDate();
				String noiSinh = rs.getString("NoiSinh");
				String diaChi = rs.getString("DiaChi");
				String tinhThanh = rs.getString("TinhThanh");
				String quanHuyen = rs.getString("QuanHuyen");
				String phuongXa = rs.getString("PhuongXa");
				String emailCaNhan = rs.getString("EmailCaNhan");
				String tinhTrangHN = rs.getString("TinhTrangHN");
				String trinhDoVanHoa = rs.getString("TrinhDoVanHoa");
				String trinhDoHocVan = rs.getString("TrinhDoHocVan");

				hs = new HoSo(maHS, maNV, cccd, noiCapCCCD, ngayCapCCCD, maSoThue, ngayCapMST, soDienThoai, gioiTinh,
						quocTich, danToc, tonGiao, ngaySinh, noiSinh, diaChi, tinhThanh, quanHuyen, phuongXa,
						emailCaNhan, tinhTrangHN, trinhDoVanHoa, trinhDoHocVan);
			}
		} catch (SQLException exception) {
			HandleException.printSQLException(exception);
		}

		System.out.println(hs.getMaHS());
		return hs;
	}
}
