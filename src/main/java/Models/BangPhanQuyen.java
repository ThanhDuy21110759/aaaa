package Models;

public class BangPhanQuyen {
	private static final long serialVersionUID = 1L;
	private String maPQ;
	private String maNV;
	private String maQH;
	private String maPB;
	private String maCN;
	private String viTri;

	public BangPhanQuyen(String maPQ, String maNV, String maQH, String maPB, String maCN, String viTri) {
		super();
		this.maPQ = maPQ;
		this.maNV = maNV;
		this.maQH = maQH;
		this.maPB = maPB;
		this.maCN = maCN;
		this.viTri = viTri;
	}

	public String getMaPQ() {
		return maPQ;
	}

	public void setMaPQ(String maPQ) {
		this.maPQ = maPQ;
	}

	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}

	public String getMaQH() {
		return maQH;
	}

	public void setMaQH(String maQH) {
		this.maQH = maQH;
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

	public String getViTri() {
		return viTri;
	}

	public void setViTri(String viTri) {
		this.viTri = viTri;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public BangPhanQuyen() {
	}

}
