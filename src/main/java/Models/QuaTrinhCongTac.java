package Models;

import java.time.LocalDate;

public class QuaTrinhCongTac {
	private static final long serialVersionUID = 1L;
	private String maQTCT;
	private String maHS;
	private LocalDate thoiGianBatDau;
	private LocalDate thoiGianKetThuc;
	private String donViCT;
	
	public QuaTrinhCongTac(String maQTCT, String maHS, LocalDate thoiGianBatDau, LocalDate thoiGianKetThuc,
			String donViCT) {
		super();
		this.maQTCT = maQTCT;
		this.maHS = maHS;
		this.thoiGianBatDau = thoiGianBatDau;
		this.thoiGianKetThuc = thoiGianKetThuc;
		this.donViCT = donViCT;
	}

	public String getMaQTCT() {
		return maQTCT;
	}

	public void setMaQTCT(String maQTCT) {
		this.maQTCT = maQTCT;
	}

	public String getMaHS() {
		return maHS;
	}

	public void setMaHS(String maHS) {
		this.maHS = maHS;
	}

	public LocalDate getThoiGianBatDau() {
		return thoiGianBatDau;
	}

	public void setThoiGianBatDau(LocalDate thoiGianBatDau) {
		this.thoiGianBatDau = thoiGianBatDau;
	}

	public LocalDate getThoiGianKetThuc() {
		return thoiGianKetThuc;
	}

	public void setThoiGianKetThuc(LocalDate thoiGianKetThuc) {
		this.thoiGianKetThuc = thoiGianKetThuc;
	}

	public String getDonViCT() {
		return donViCT;
	}

	public void setDonViCT(String donViCT) {
		this.donViCT = donViCT;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public QuaTrinhCongTac() {

	}

}
