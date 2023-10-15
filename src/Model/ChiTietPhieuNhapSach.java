package Model;

public class ChiTietPhieuNhapSach {
    private String maPhieuNhap;
    private String maSach;
    private String tenSach;
    private String maTacGia;
    private String maTheLoai;
    private String NXB;
    private int namXuatBan;
    private int soLuongNhap;
    private double giaNhap;

    public ChiTietPhieuNhapSach() {
    }

    public ChiTietPhieuNhapSach(String maPhieuNhap, String maSach, String tenSach, String maTacGia, String maTheLoai, String NXB, int namXuatBan, int soLuongNhap, double giaNhap) {
        this.maPhieuNhap = maPhieuNhap;
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.maTacGia = maTacGia;
        this.maTheLoai = maTheLoai;
        this.NXB = NXB;
        this.namXuatBan = namXuatBan;
        this.soLuongNhap = soLuongNhap;
        this.giaNhap = giaNhap;
    }

    public String getMaPhieuNhap() {
        return maPhieuNhap;
    }

    public void setMaPhieuNhap(String maPhieuNhap) {
        this.maPhieuNhap = maPhieuNhap;
    }

    public String getMaSach() {
        return maSach;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public String getMaTacGia() {
        return maTacGia;
    }

    public void setMaTacGia(String maTacGia) {
        this.maTacGia = maTacGia;
    }

    public String getMaTheLoai() {
        return maTheLoai;
    }

    public void setMaTheLoai(String maTheLoai) {
        this.maTheLoai = maTheLoai;
    }

    public String getNXB() {
        return NXB;
    }

    public void setNXB(String NXB) {
        this.NXB = NXB;
    }

    public int getNamXuatBan() {
        return namXuatBan;
    }

    public void setNamXuatBan(int namXuatBan) {
        this.namXuatBan = namXuatBan;
    }

    public int getSoLuongNhap() {
        return soLuongNhap;
    }

    public void setSoLuongNhap(int soLuongNhap) {
        this.soLuongNhap = soLuongNhap;
    }

    public double getGiaNhap() {
        return giaNhap;
    }

    public void setGiaNhap(double giaNhap) {
        this.giaNhap = giaNhap;
    }

    @Override
    public String toString() {
        return "ChiTietPhieuNhapSach{" +
                "maPhieuNhap='" + maPhieuNhap + '\'' +
                ", maSach='" + maSach + '\'' +
                ", tenSach='" + tenSach + '\'' +
                ", maTacGia='" + maTacGia + '\'' +
                ", maTheLoai='" + maTheLoai + '\'' +
                ", NXB='" + NXB + '\'' +
                ", namXuatBan='" + namXuatBan + '\'' +
                ", soLuongNhap=" + soLuongNhap +
                ", giaNhap=" + giaNhap +
                '}';
    }
}
