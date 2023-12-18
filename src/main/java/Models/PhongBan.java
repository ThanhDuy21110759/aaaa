package Models;

public class PhongBan {
	private String maPB;
	private String maCN;
	private String maTrgPhg;
	private String tenPhongBan;
	private String trangThai;

	
	public PhongBan(String maPB, String maCN, String maTrgPhg, String tenPhongBan,String trangThai) {
		super();
		this.maPB = maPB;
		this.maCN = maCN;
		this.maTrgPhg = maTrgPhg;
		this.tenPhongBan = tenPhongBan;
		this.trangThai =trangThai;
	}
	public PhongBan(String maPB, String maCN, String maTrgPhg, String tenPhongBan) {
		super();
		this.maPB = maPB;
		this.maCN = maCN;
		this.maTrgPhg = maTrgPhg;
		this.tenPhongBan = tenPhongBan;
	}
	public String getTrangThai() {
		return trangThai;
	}

	public void setTrangThai(String trangThai) {
		this.trangThai = trangThai;
	}

	public String getMaPB() {
		return maPB;
	}
	
	public void setMaPB(String maPB) {
		this.maPB = maPB;
	}

	public String getMaCN() {
		return maCN;
	}

	public void setMaCN(String maCN) {
		this.maCN = maCN;
	}

	public String getMaTrgPhg() {
		return maTrgPhg;
	}

	public void setMaTrgPhg(String maTrgPhg) {
		this.maTrgPhg = maTrgPhg;
	}

	public String getTenPhongBan() {
		return tenPhongBan;
	}

	public void setTenPhongBan(String tenPhongBan) {
		this.tenPhongBan = tenPhongBan;
	}

	public PhongBan() {
		
	}
}
