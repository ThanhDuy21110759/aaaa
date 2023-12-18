package Models;

import java.io.Serializable;
import java.time.LocalDate;


public class KhieuNai implements Serializable {
	private static final long serialVersionUID = 1L;
	private String maKN;
	private String maNggui;
	private String maNgnhan;
	private String noidungKN;
	private String noidungPH;
	private LocalDate ngayDuyet;
	private LocalDate ngayGui;
	
	public KhieuNai(String makn, String manggui, String mangnhan, String noidungKN,String noidungPH, LocalDate ngayDuyet, LocalDate ngayGui) {
		this.maKN = makn;
		this.maNggui = manggui;
		this.maNgnhan = mangnhan;
		this.noidungKN = noidungKN;
		this.noidungPH = noidungPH;
		this.ngayDuyet = ngayDuyet;
		this.ngayGui = ngayGui;
	}
	public KhieuNai(String makn, String noidungKN, LocalDate ngayGui) {
		this.maKN = makn;
		this.noidungKN = noidungKN;
		this.ngayGui = ngayGui;
	}
	public KhieuNai(String makn, String noidungPH, LocalDate ngayGui, LocalDate ngayDuyet, String mangnhan) {
		this.maKN = makn;
		this.noidungPH = noidungPH;
		this.ngayDuyet= ngayDuyet;			
		this.ngayGui = ngayGui;
		this.maNgnhan= mangnhan;
	}
	public KhieuNai(String makn, String manggui, String noidungKN, LocalDate ngayGui) {
		this.maKN = makn;
		this.maNggui = manggui;
		this.noidungKN = noidungKN;
		this.ngayGui = ngayGui;
	}
	
	public KhieuNai() {
		
	}
	
	public String getMaKN() {
		return maKN;
	}
	public void setMaKN(String maKN) {
		this.maKN = maKN;
	}
	public String getMaNggui() {
		return maNggui;
	}
	public void setMaNggui(String maNggui) {
		this.maNggui = maNggui;
	}
	public String getMaNgnhan() {
		return maNgnhan;
	}
	public void setMaNgnhan(String maNgnhan) {
		this.maNgnhan = maNgnhan;
	}
	public String getNoidungKN() {
		return noidungKN;
	}
	public void setNoidungKN(String noidungKN) {
		this.noidungKN = noidungKN;
	}
	public String getNoidungPH() {
		return noidungPH;
	}
	public void setNoidungPH(String noidungPH) {
		this.noidungPH = noidungPH;
	}
	public LocalDate getNgayDuyet() {
		return ngayDuyet;
	}
	public void setNgayDuyet(LocalDate ngayDuyet) {
		this.ngayDuyet = ngayDuyet;
	}
	public LocalDate getNgayGui() {
		return ngayGui;
	}
	public void setNgayGui(LocalDate ngayGui) {
		this.ngayGui = ngayGui;
	}
		
}
