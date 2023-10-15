package Model;

import java.time.LocalDate;

public class PhieuNhapSach {
    private String maPhieuNhap;
    private LocalDate ngayNhap;
    private String maNhaCungCap;

    public PhieuNhapSach() {
    }
    public PhieuNhapSach(String maPhieuNhap) {
        this.maPhieuNhap = maPhieuNhap;
    }
    public PhieuNhapSach(String maPhieuNhap, LocalDate ngayNhap) {
        this.maPhieuNhap = maPhieuNhap;
        this.ngayNhap = ngayNhap;
    }
    public PhieuNhapSach(String maPhieuNhap, LocalDate ngayNhap, String maNhaCungCap) {
        this.maPhieuNhap = maPhieuNhap;
        this.ngayNhap = ngayNhap;
        this.maNhaCungCap = maNhaCungCap;
    }

    public String getMaPhieuNhap() {
        return maPhieuNhap;
    }

    public void setMaPhieuNhap(String maPhieuNhap) {
        this.maPhieuNhap = maPhieuNhap;
    }

    public LocalDate getNgayNhap() {
        return ngayNhap;
    }

    public void setNgayNhap(LocalDate ngayNhap) {
        this.ngayNhap = ngayNhap;
    }

    public String getMaNhaCungCap() {
        return maNhaCungCap;
    }

    public void setMaNhaCungCap(String maNhaCungCap) {
        this.maNhaCungCap = maNhaCungCap;
    }
}
