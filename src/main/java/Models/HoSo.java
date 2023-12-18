package Models;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

public class HoSo implements Serializable {

    private static final long serialVersionUID = 1L;
    private String maHS;
    private String maNV;
    private String cCCD;
    private String noiCapCCCD;
    private LocalDate ngayCapCCCD;
    private String maSoThue;
    private LocalDate ngayCapMST;
    private String soDienThoai;
    private Boolean gioiTinh;
    private String quocTich;
    private String danToc;
    private String tonGiao;
    private LocalDate ngaySinh;
    private String noiSinh;
    private String diaChi;
    private String tinhThanh;
    private String quanHuyen;
    private String phuongXa;
    private String emailCaNhan;
    private String tinhTrangHN;
    private String trinhDoVanHoa;
    private String trinhDoHocVan;
    
	public HoSo(String maHS, String maNV, String cCCD, String noiCapCCCD, LocalDate ngayCapCCCD, String maSoThue,
			LocalDate ngayCapMST, String soDienThoai, Boolean gioiTinh, String quocTich, String danToc, String tonGiao,
			LocalDate ngaySinh, String noiSinh, String diaChi, String tinhThanh, String quanHuyen, String phuongXa,
			String emailCaNhan, String tinhTrangHN, String trinhDoVanHoa, String trinhDoHocVan) {
		super();
		this.maHS = maHS;
		this.maNV = maNV;
		this.cCCD = cCCD;
		this.noiCapCCCD = noiCapCCCD;
		this.ngayCapCCCD = ngayCapCCCD;
		this.maSoThue = maSoThue;
		this.ngayCapMST = ngayCapMST;
		this.soDienThoai = soDienThoai;
		this.gioiTinh = gioiTinh;
		this.quocTich = quocTich;
		this.danToc = danToc;
		this.tonGiao = tonGiao;
		this.ngaySinh = ngaySinh;
		this.noiSinh = noiSinh;
		this.diaChi = diaChi;
		this.tinhThanh = tinhThanh;
		this.quanHuyen = quanHuyen;
		this.phuongXa = phuongXa;
		this.emailCaNhan = emailCaNhan;
		this.tinhTrangHN = tinhTrangHN;
		this.trinhDoVanHoa = trinhDoVanHoa;
		this.trinhDoHocVan = trinhDoHocVan;
	}

	public HoSo() {}
	
	public String getMaHS() {
		return maHS;
	}

	public void setMaHS(String maHS) {
		this.maHS = maHS;
	}

	public String getMaNV() {
		return maNV;
	}

	public void setMaNV(String maNV) {
		this.maNV = maNV;
	}

	public String getcCCD() {
		return cCCD;
	}

	public void setcCCD(String cCCD) {
		this.cCCD = cCCD;
	}

	public String getNoiCapCCCD() {
		return noiCapCCCD;
	}

	public void setNoiCapCCCD(String noiCapCCCD) {
		this.noiCapCCCD = noiCapCCCD;
	}

	public LocalDate getNgayCapCCCD() {
		return ngayCapCCCD;
	}

	public void setNgayCapCCCD(LocalDate ngayCapCCCD) {
		this.ngayCapCCCD = ngayCapCCCD;
	}

	public String getMaSoThue() {
		return maSoThue;
	}

	public void setMaSoThue(String maSoThue) {
		this.maSoThue = maSoThue;
	}

	public LocalDate getNgayCapMST() {
		return ngayCapMST;
	}

	public void setNgayCapMST(LocalDate ngayCapMST) {
		this.ngayCapMST = ngayCapMST;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	public Boolean getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(Boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public String getQuocTich() {
		return quocTich;
	}

	public void setQuocTich(String quocTich) {
		this.quocTich = quocTich;
	}

	public String getDanToc() {
		return danToc;
	}

	public void setDanToc(String danToc) {
		this.danToc = danToc;
	}

	public String getTonGiao() {
		return tonGiao;
	}

	public void setTonGiao(String tonGiao) {
		this.tonGiao = tonGiao;
	}

	public LocalDate getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(LocalDate ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public String getNoiSinh() {
		return noiSinh;
	}

	public void setNoiSinh(String noiSinh) {
		this.noiSinh = noiSinh;
	}

	public String getDiaChi() {
		return diaChi;
	}

	public void setDiaChi(String diaChi) {
		this.diaChi = diaChi;
	}

	public String getTinhThanh() {
		return tinhThanh;
	}

	public void setTinhThanh(String tinhThanh) {
		this.tinhThanh = tinhThanh;
	}

	public String getQuanHuyen() {
		return quanHuyen;
	}

	public void setQuanHuyen(String quanHuyen) {
		this.quanHuyen = quanHuyen;
	}

	public String getPhuongXa() {
		return phuongXa;
	}

	public void setPhuongXa(String phuongXa) {
		this.phuongXa = phuongXa;
	}

	public String getEmailCaNhan() {
		return emailCaNhan;
	}

	public void setEmailCaNhan(String emailCaNhan) {
		this.emailCaNhan = emailCaNhan;
	}

	public String getTinhTrangHN() {
		return tinhTrangHN;
	}

	public void setTinhTrangHN(String tinhTrangHN) {
		this.tinhTrangHN = tinhTrangHN;
	}

	public String getTrinhDoVanHoa() {
		return trinhDoVanHoa;
	}

	public void setTrinhDoVanHoa(String trinhDoVanHoa) {
		this.trinhDoVanHoa = trinhDoVanHoa;
	}

	public String getTrinhDoHocVan() {
		return trinhDoHocVan;
	}

	public void setTrinhDoHocVan(String trinhDoHocVan) {
		this.trinhDoHocVan = trinhDoHocVan;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}

