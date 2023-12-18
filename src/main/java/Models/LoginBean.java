package Models;

import java.io.Serializable;

public class LoginBean implements Serializable {
    private static final long serialVersionUID = 1L;
    private String username;
    private String password;
    private String maNhanvien;

    public String getMaNhanvien() {
		return maNhanvien;
	}

	public void setMaNhanvien(String maNhanvien) {
		this.maNhanvien = maNhanvien;
	}

	public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}