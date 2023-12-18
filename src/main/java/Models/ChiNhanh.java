package Models;

import java.util.List;

public class ChiNhanh {
	private String maCN;
	private String maGiamDoc;
	private String tenChiNhanh;
	private String trangThai;
	private List<PhongBan> phongBanList;
    
	public ChiNhanh(String maCN, String maGiamDoc, String tenChiNhanh, List<PhongBan> phongBanList,String trangThai) {
		super();
		this.maCN = maCN;
		this.maGiamDoc = maGiamDoc;
		this.tenChiNhanh = tenChiNhanh;
		this.phongBanList = phongBanList;
		this.trangThai =trangThai;
	}
	
	public ChiNhanh(String maCN, String maGiamDoc, String tenChiNhanh, List<PhongBan> phongBanList) {
		super();
		this.maCN = maCN;
		this.maGiamDoc = maGiamDoc;
		this.tenChiNhanh = tenChiNhanh;
		this.phongBanList = phongBanList;
	}
	
	public String getTrangThai() {
		return trangThai;
	}
	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}
	public String getMaCN() {
		return maCN;
	}
	public void setMaCN(String maCN) {
		this.maCN = maCN;
	}
	public String getMaGiamDoc() {
		return maGiamDoc;
	}
	public void setMaGiamDoc(String maGiamDoc) {
		this.maGiamDoc = maGiamDoc;
	}
	public String getTenChiNhanh() {
		return tenChiNhanh;
	}
	public void setTenChiNhanh(String tenChiNhanh) {
		this.tenChiNhanh = tenChiNhanh;
	}
	public List<PhongBan> getPhongBanList() {
		return phongBanList;
	}
	public void setPhongBanList(List<PhongBan> phongBanList) {
		this.phongBanList = phongBanList;
	}

	
}
