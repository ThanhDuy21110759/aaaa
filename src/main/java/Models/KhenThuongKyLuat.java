package Models;

import java.io.Serializable;
import java.time.LocalDate;

public class KhenThuongKyLuat implements Serializable {
	private static final long serialVersionUID = 1L;
	private String maDG;	
	private String maNgGui;
	private String maNgNhan;
	private String maNgDuyet;	
	private String soQD;
	private String noiDung;
	private LocalDate ngayGui;
	private String trangThai;
	private Float soTienThuong;
	private String loaiDG;
	
	public KhenThuongKyLuat(){
		
	}
	public KhenThuongKyLuat(String madg,String soQD,String noidung, Float sotien, LocalDate ngayGui){
			this.maDG = madg;
			this.noiDung= noidung;
			this.soTienThuong = sotien;
			this.ngayGui = ngayGui;
			this.soQD = soQD;
	}
	public KhenThuongKyLuat(String madg,String soQD ,String noidung, LocalDate ngayGui){
			this.maDG = madg;
			this.noiDung= noidung;
			this.ngayGui = ngayGui;
			this.soQD = soQD;
	}
	public KhenThuongKyLuat(String madg,String noidung, LocalDate ngayGui, String trangThai){
		this.maDG = madg;
		this.noiDung= noidung;
		this.ngayGui = ngayGui;
		this.trangThai= trangThai;
	}
	public KhenThuongKyLuat(String madg,String manggui, String mangnhan,String noidung, LocalDate ngayGui, String trangThai, String loai){
		this.maDG = madg;
		this.maNgGui= manggui;
		this.maNgNhan= mangnhan;
		this.noiDung= noidung;
		this.ngayGui = ngayGui;
		this.trangThai= trangThai;
		this.loaiDG =loai;
	}
	
	public String getMaNgDuyet() {
		return maNgDuyet;
	}
	public void setMaNgDuyet(String maNgDuyet) {
		this.maNgDuyet = maNgDuyet;
	}
	public String getMaNgGui() {
		return maNgGui;
	}
	public void setMaNgGui(String maNgGui) {
		this.maNgGui = maNgGui;
	}
	public String getMaNgNhan() {
		return maNgNhan;
	}
	public void setMaNgNhan(String maNgNhan) {
		this.maNgNhan = maNgNhan;
	}
	public String getMaDG() {
		return maDG;
	}
	public void setMaDG(String maDG) {
		this.maDG = maDG;
	}
	
	public String getSoQD() {
		return soQD;
	}
	public void setSoQD(String soQD) {
		this.soQD = soQD;
	}
	public String getNoiDung() {
		return noiDung;
	}
	public void setNoiDung(String noiDung) {
		this.noiDung = noiDung;
	}
	public LocalDate getNgayGui() {
		return ngayGui;
	}
	public void setNgayGui(LocalDate ngayGui) {
		this.ngayGui = ngayGui;
	}
	public String getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}
	public Float getSoTienThuong() {
		return soTienThuong;
	}
	public void setSoTienThuong(Float soTienThuong) {
		this.soTienThuong = soTienThuong;
	}
	public String getLoaiDG() {
		return loaiDG;
	}
	public void setLoaiDG(String loaiDG) {
		this.loaiDG = loaiDG;
	}
	
}
