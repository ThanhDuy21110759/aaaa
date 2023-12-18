package Models;

public class TaiKhoan {
    private static final long serialVersionUID = 1L;
	private String tenTaiKhoan;
	private String matKhau;
	private String maNV;
	
	public String getTenTaiKhoan() {
		return tenTaiKhoan;
	}

	public void setTenTaiKhoan(String tenTaiKhoan) {
		this.tenTaiKhoan = tenTaiKhoan;
	}

	public String getMatKhau() {
		return matKhau;
	}

	public void setMatKhau(String matKhau) {
		this.matKhau = matKhau;
	}

	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}

	public TaiKhoan() {

	}

	public TaiKhoan(String tenTaiKhoan, String matKhau, String maNV) {
		super();
		this.tenTaiKhoan = tenTaiKhoan;
		this.matKhau = matKhau;
		this.maNV = maNV;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
