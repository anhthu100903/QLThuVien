package Model;

import com.sun.nio.sctp.AbstractNotificationHandler;

public class KhoSach {
    private String maSach;
    private int tongSoLuong;
    private int soLuongCon;
    private int soLuongSachHong;

    public KhoSach() {
        maSach = "";
    }

    public KhoSach(String maSach, int tongSoLuong, int soLuongCon, int soLuongSachHong) {
        this.maSach = maSach;
        this.tongSoLuong = tongSoLuong;
        this.soLuongCon = soLuongCon;
        this.soLuongSachHong = soLuongSachHong;
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public int getTongSoLuong() {
        return tongSoLuong;
    }

    public void setTongSoLuong(int tongSoLuong) {
        this.tongSoLuong = tongSoLuong;
    }

    public int getSoLuongCon() {
        return soLuongCon;
    }

    public void setSoLuongCon(int soLuongCon) {
        this.soLuongCon = soLuongCon;
    }

    public int getSoLuongSachHong() {
        return soLuongSachHong;
    }

    public void setSoLuongSachHong(int soLuongSachHong) {
        this.soLuongSachHong = soLuongSachHong;
    }
}
