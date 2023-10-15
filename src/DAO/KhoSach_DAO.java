package DAO;

import Model.KhoSach;
import Model.Sach;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class KhoSach_DAO implements DAO_Interface <KhoSach> {
    //Cap nhat (cộng) tổng so luong dua tren Id co san;
    //Cap nhat tổng so luong dua tren id mới --> so luong con = tong so luong;
    //Tru so luong con ( 2 hàm: - hàm 1: trừ 1 sách dựa trên id, -hàm 2: trừ n sách dựa trên id);
    //Cập nhật số lượng bị hỏng (cần nhập n số lượng)


    //xuất:
    // Xuất tất cả, chọn theo id, chọn theo điều kiện;
    //
    public static KhoSach_DAO getInstance(){
        return new KhoSach_DAO();
    }

    @Override
    public int add(KhoSach khoSach) {
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

}
