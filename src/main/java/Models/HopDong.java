package Models;

import java.time.LocalDate;

public class HopDong {
	private static final long serialVersionUID = 1L;
	private String maHD;
	private String maHS;
	private String loaiHD;
	private String soQD;
	private LocalDate ngayKyHD;
	private LocalDate hanHD;
	private String trangThai;
	private String noiDung;

	public HopDong(String maHD,String maHS ,String loaiHD, String soQD, LocalDate ngayKyHD, LocalDate hanHD, String trangThai,
			String noiDung) {
		super();
		this.maHD = maHD;
		this.maHS = maHS;
		this.loaiHD = loaiHD;
		this.soQD = soQD;
		this.ngayKyHD = ngayKyHD;
		this.hanHD = hanHD;
		this.trangThai = trangThai;
		this.noiDung = noiDung;
	}

	public String getMaHS() {
		return maHS;
	}

	public void setMaHS(String maHS) {
		this.maHS = maHS;
	}

	public String getMaHD() {
		return maHD;
	}

	public void setMaHD(String maHD) {
		this.maHD = maHD;
	}

	public String getLoaiHD() {
		return loaiHD;
	}

	public void setLoaiHD(String loaiHD) {
		this.loaiHD = loaiHD;
	}

	public String getSoQD() {
		return soQD;
	}

	public void setSoQD(String soQD) {
		this.soQD = soQD;
	}

	public LocalDate getNgayKyHD() {
		return ngayKyHD;
	}

	public void setNgayKyHD(LocalDate ngayKyHD) {
		this.ngayKyHD = ngayKyHD;
	}

	public LocalDate getHanHD() {
		return hanHD;
	}

	public void setHanHD(LocalDate hanHD) {
		this.hanHD = hanHD;
	}

	public String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}

	public String getNoiDung() {
		return noiDung;
	}

	public void setNoiDung(String noiDung) {
		this.noiDung = noiDung;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public HopDong() {

	}

}
