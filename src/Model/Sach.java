/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author KHP2T
 */
public class Sach {
    private String maSach = "";
    private String tenSach;
    private String maDMSach;
    private String maTheLoai;
    private String maTacGia;
    private String tenTacGia;
    private String NXB;
    private int namXuatBan;
    private double giaTienSach;
    private String tinhTrangSach;
    private String tomTatND ;

    public Sach() {
    }
    public Sach(String maSach){
        this.maSach = maSach;
    }
    public Sach(String maSach, String tenSach, String maDMSach, String maTheLoai, String maTacGia, String tenTacGia, String NXB, int namXuatBan, double giaTienSach, String tinhTrangSach, String tomTatND) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.maDMSach = maDMSach;
        this.maTheLoai = maTheLoai;
        this.maTacGia = maTacGia;
        this.tenTacGia = tenTacGia;
        this.NXB = NXB;
        this.namXuatBan = namXuatBan;
        this.giaTienSach = giaTienSach;
        this.tinhTrangSach = tinhTrangSach;
        this.tomTatND = tomTatND;
    }
    public Sach (String maSach, String tenSach, String maTacGia, String maTheLoai, String nxb, int namXuatBan) {
        this.maSach = maSach;
        this.tenSach = tenSach;
        this.maTheLoai = maTheLoai;
        this.maTacGia = maTacGia;
        this.NXB = nxb;
        this.namXuatBan = namXuatBan;
    }
    public String getMaTacGia() {
        return maTacGia;
    }

    public void setMaTacGia(String maTacGia) {
        this.maTacGia = maTacGia;
    }

    public String getTenTacGia() {
        return tenTacGia;
    }

    public void setTenTacGia(String tenTacGia) {
        this.tenTacGia = tenTacGia;
    }

    public double getGiaTienSach() {
        return giaTienSach;
    }

    public void setGiaTienSach(double giaTienSach) {
        this.giaTienSach = giaTienSach;
    }

    public String getTinhTrangSach() {
        return tinhTrangSach;
    }

    public void setTinhTrangSach(String tinhTrangSach) {
        this.tinhTrangSach = tinhTrangSach;
    }

    public String getMaSach() {
        return maSach;
    }

    public String getTenSach() {
        return tenSach;
    }

    public String getMaDMSach() {
        return maDMSach;
    }

    public String getMaTheLoai() {
        return maTheLoai;
    }

    public String getTacGia() {
        return tenTacGia;
    }

    public String getNXB() {
        return NXB;
    }

    public int getNamXuatBan() {
        return namXuatBan;
    }

    public String getTomTatND() {
        return tomTatND;
    }

    public void setMaSach(String maSach) {
        this.maSach = maSach;
    }

    public void setTenSach(String tenSach) {
        this.tenSach = tenSach;
    }

    public void setMaDMSach(String maDMSach) {
        this.maDMSach = maDMSach;
    }

    public void setMaTheLoai(String maTheLoai) {
        this.maTheLoai = maTheLoai;
    }

    public void setTacGia(String tacGia) {
        this.tenTacGia = tacGia;
    }

    public void setNXB(String NXB) {
        this.NXB = NXB;
    }

    public void setNamXuatBan(int namXuatBan) {
        this.namXuatBan = namXuatBan;
    }

    public void setTomTatND(String tomTatND) {
        this.tomTatND = tomTatND;
    }
    
    
}
