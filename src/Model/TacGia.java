package Model;

public class TacGia {
    private String maTacGia;
    private String tenTacGia;
    private int soSach;

    public TacGia(String maTacGia, String tenTacGia, int soSach) {
        this.maTacGia = maTacGia;
        this.tenTacGia = tenTacGia;
        this.soSach = soSach;
    }
    public TacGia(String maTacGia) {
        this.maTacGia = maTacGia;
    }

    public TacGia() {
        maTacGia="";
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

    public int getSoSach() {
        return soSach;
    }

    public void setSoSach(int soSach) {
        this.soSach = soSach;
    }
}
