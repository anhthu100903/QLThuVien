package DAO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import Model.KhoSach;
import Model.ThanhLySach;

public class ThanhLySach_DAO implements DAO_Interface<ThanhLySach> {
    public static ThanhLySach_DAO getInstance() {
        return new ThanhLySach_DAO();
    }

    @Override
    public int add(ThanhLySach thanhLySach) {
        int rowsAffected = 0;
        String sql = "INSERT INTO dbo.[ThanhLySach] (maThanhLySach, maSach, soLuongSachHong, lyDoThanhLy, ngayThanhLy, ghiChu, tongTienThanhLy) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = KetNoiSQL.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, thanhLySach.getMaThanhLySach());
            pst.setString(2, thanhLySach.getMaSach());
            pst.setInt(3, thanhLySach.getSoLuongSachHong());
            pst.setString(4, thanhLySach.getLyDoThanhLy());
            pst.setDate(5, java.sql.Date.valueOf(thanhLySach.getNgayThanhLy()));
            pst.setString(6, thanhLySach.getGhiChu());
            pst.setDouble(7, thanhLySach.getTongTienThanhLy());
            rowsAffected = pst.executeUpdate();
            if (rowsAffected ==1){
                KhoSach khoSach = new KhoSach(thanhLySach.getMaSach(), )
                KhoSach_DAO.getInstance().update();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowsAffected;
    }

    @Override
    public int update(ThanhLySach thanhLySach) {
        int rowsAffected = 0;
        String sql = "UPDATE dbo.[ThanhLySach] SET maSach = ?, soLuongSachHong = ?, lyDoThanhLy = ?, ngayThanhLy = ?, ghiChu = ?, tongTienThanhLy = ? WHERE maThanhLySach = ?";
        try (Connection conn = KetNoiSQL.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, thanhLySach.getMaSach());
            pst.setInt(2, thanhLySach.getSoLuongSachHong());
            pst.setString(3, thanhLySach.getLyDoThanhLy());
            pst.setDate(4, java.sql.Date.valueOf(thanhLySach.getNgayThanhLy()));
            pst.setString(5, thanhLySach.getGhiChu());
            pst.setDouble(6, thanhLySach.getTongTienThanhLy());
            pst.setString(7, thanhLySach.getMaThanhLySach());
            rowsAffected = pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowsAffected;
    }

    @Override
    public int delete(String maThanhLySach) {
        int rowsAffected = 0;
        String sql = "DELETE FROM dbo.[ThanhLySach] WHERE maThanhLySach = ?";
        try (Connection conn = KetNoiSQL.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, maThanhLySach);
            rowsAffected = pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowsAffected;
    }

    @Override
    public List<ThanhLySach> selectAll() {
        List<ThanhLySach> rowSelected = new ArrayList<ThanhLySach>();
        try (Connection conn = KetNoiSQL.getConnection();
             PreparedStatement pst = conn.prepareStatement("SELECT * FROM dbo.[ThanhLySach]");
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                String maThanhLySach = rs.getString("maThanhLySach");
                String maSach = rs.getString("maSach");
                int soLuongSachHong = rs.getInt("soLuongSachHong");
                String lyDoThanhLy = rs.getString("lyDoThanhLy");
                LocalDate ngayThanhLy = rs.getDate("ngayThanhLy").toLocalDate();
                String ghiChu = rs.getString("ghiChu");
                double tongTienThanhLy = rs.getDouble("tongTienThanhLy");
                ThanhLySach thanhLySach = new ThanhLySach(maThanhLySach, maSach, soLuongSachHong, lyDoThanhLy, ngayThanhLy, ghiChu, tongTienThanhLy);
                rowSelected.add(thanhLySach);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowSelected;
    }

    @Override
    public ThanhLySach selectById(String maThanhLySach) {
        ThanhLySach result = new ThanhLySach();
        String sql = "SELECT * FROM dbo.[ThanhLySach] WHERE maThanhLySach = ?";
        try (Connection conn = KetNoiSQL.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, maThanhLySach);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                result.setMaThanhLySach(rs.getString("maThanhLySach"));
                result.setMaSach(rs.getString("maSach"));
                result.setSoLuongSachHong(rs.getInt("soLuongSachHong"));
                result.setLyDoThanhLy(rs.getString("lyDoThanhLy"));
                result.setNgayThanhLy(rs.getDate("ngayThanhLy").toLocalDate());
                result.setGhiChu(rs.getString("ghiChu"));
                result.setTongTienThanhLy(rs.getDouble("tongTienThanhLy"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
