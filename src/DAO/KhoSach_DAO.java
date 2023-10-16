package DAO;

import Model.KhoSach;
import Model.Sach;

import java.awt.image.Kernel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KhoSach_DAO implements DAO_Interface <KhoSach> {

    public static KhoSach_DAO getInstance(){
        return new KhoSach_DAO();
    }

    @Override
    public int add(KhoSach khoSach) {
        if (!kiemTra(khoSach.getTongSoLuong(), khoSach.getSoLuongCon(), khoSach.getSoLuongSachHong())){
            return -1;
        }
        int rowsAffected = 0;
        String sql = "INSERT INTO dbo.[KhoSach] VALUES (?,?,?,?)";
        try (Connection conn = KetNoiSQL.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, khoSach.getMaSach());
            pst.setInt(2, khoSach.getTongSoLuong());
            pst.setInt(3, khoSach.getSoLuongCon());
            pst.setInt(4, khoSach.getSoLuongSachHong());
            rowsAffected = pst.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
        return rowsAffected;
    }

    @Override
    public int update(KhoSach khoSach) {
        if (!kiemTra(khoSach.getTongSoLuong(), khoSach.getSoLuongCon(), khoSach.getSoLuongSachHong())){
            return -1;
        }
        int rowsAffected = 0;
        String sql = "UPDATE dbo.[KhoSach] SET tongSoLuong = ?, soLuongCon = ?, soLuongSachHong = ? WHERE maSach = ?";
        try (Connection conn = KetNoiSQL.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setInt(1, khoSach.getTongSoLuong());
            pst.setInt(2, khoSach.getSoLuongCon());
            pst.setInt(3, khoSach.getSoLuongSachHong());
            pst.setString(4, khoSach.getMaSach());
            rowsAffected = pst.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
        return rowsAffected;
    }
    boolean kiemTra(int tong, int con, int hong){
        if (tong<con+hong) return false;
        if (con>tong || hong>tong) return false;
        return true;
    }
    @Override
    public int delete(String maSach) {
        int rowsAffected = 0;
        String sql = "DELETE FROM dbo.[KhoSach] WHERE maSach ='"+maSach+"'";
        try (Connection conn = KetNoiSQL.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            rowsAffected = pst.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
        return rowsAffected;
    }


    @Override
    public List selectAll() {
        List<KhoSach> rowSelected = new ArrayList<KhoSach>();
        try (Connection conn = KetNoiSQL.getConnection();
             PreparedStatement pst = conn.prepareStatement("SELECT * FROM dbo.[KhoSach]");
             ResultSet rs = pst.executeQuery()) {
            if (rs.next()) {
                do {
                    String maSach = rs.getString("maSach");
                    int tongSoLuong = rs.getInt("tongSoLuong");
                    int soLuongCon = rs.getInt("soLuongCon");
                    int soLuongSachHong = rs.getInt("soLuongSachHong");
                    KhoSach khoSach = new KhoSach(maSach, tongSoLuong, soLuongCon, soLuongSachHong);
                    rowSelected.add(khoSach);
                } while (rs.next());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowSelected;
    }

    @Override
    public KhoSach selectById(String maSach) {
        KhoSach result = new KhoSach();
        String sql = "SELECT * FROM dbo.[KhoSach] WHERE id ='"+maSach+"'";
        try (Connection conn = KetNoiSQL.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery())  {
            if (rs.next()) {
                do {
                    result.setMaSach(rs.getString("maSach"));
                    result.setTongSoLuong(rs.getInt("tongSoLuong"));
                    result.setSoLuongCon(rs.getInt("soLuongCon"));
                    result.setSoLuongSachHong(rs.getInt("soLuongSachHong"));
                } while (rs.next());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

    // Trừ 1 cuốn sách trong số lượng còn (hỗ trợ cho phiếu mượn)
    public int minusOneRemainingBooks (String maSach){
        int rowAffected = 0;
        String sql = "UPDATE dbo.[KhoSach] SET soLuongCon = soLuongCon-1 WHERE soLuongCon>0 AND maSach = '"+maSach+"' ";
        try (Connection conn = KetNoiSQL.getConnection();
            PreparedStatement pst = conn.prepareStatement(sql)){
            rowAffected = pst.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowAffected;
    }

    // Trừ n cuốn sách (hỗ trợ phiếu xuất (phiếu thanh lý))
    public int minusBooks(String maSach, int n){
        KhoSach khoSach1 = KhoSach_DAO.getInstance().selectById(maSach);
        int tongSach = khoSach1.getTongSoLuong();
        int sachCon = khoSach1.getSoLuongCon();
        int sachHong = khoSach1.getSoLuongSachHong();
        if (tongSach>=n){
            tongSach -=n;
            if (sachHong>=n){
                sachHong-=n;
            }else {
                sachCon = sachCon - (n-sachHong);
                sachHong = 0;
            }
        }else {
            return -1;
        }
        khoSach1.setTongSoLuong(tongSach);
        khoSach1.setSoLuongCon(sachCon);
        khoSach1.setSoLuongSachHong(sachHong);
        return KhoSach_DAO.getInstance().update(khoSach1);
    }

    public int updateDamagedBooks(String maSach, int soLuongHong){
        int rowAffected =0;
        String sql = "UPDATE dbo.[KhoSach] SET soLuongSachHong = ? WHERE maSach = '"+maSach+"' ";
        try (Connection conn = KetNoiSQL.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()){
            int soLuongHongHienTai = rs.getInt("soLuongSachHong");
            pst.setInt(1, soLuongHongHienTai+soLuongHong);
            rowAffected = pst.executeUpdate();

        }catch (Exception e){
            e.printStackTrace();
        }
        return rowAffected;
    }

}
