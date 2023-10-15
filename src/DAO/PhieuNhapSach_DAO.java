package DAO;
import Model.PhieuNhapSach;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class PhieuNhapSach_DAO implements DAO_Interface<PhieuNhapSach> {
    public static PhieuNhapSach_DAO getInstance() {
        return new PhieuNhapSach_DAO();
    }

    @Override
    public int add(PhieuNhapSach phieuNhapSach) {
        int rowsAffected = 0;
        String sql = "INSERT INTO dbo.[PhieuNhapSach] (maPhieuNhap, ngayNhap, maNhaCungCap) VALUES (?, ?, ?)";
        try (Connection conn = KetNoiSQL.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, phieuNhapSach.getMaPhieuNhap());
            pst.setDate(2, java.sql.Date.valueOf(phieuNhapSach.getNgayNhap()));
            pst.setString(3, phieuNhapSach.getMaNhaCungCap());
            rowsAffected = pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowsAffected;
    }

    @Override
    public int update(PhieuNhapSach phieuNhapSach) {
        int rowsAffected = 0;
        String sql = "UPDATE dbo.[PhieuNhapSach] SET ngayNhap = ?, maNhaCungCap = ? WHERE maPhieuNhap = ?";
        try (Connection conn = KetNoiSQL.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setDate(1, java.sql.Date.valueOf(phieuNhapSach.getNgayNhap()));
            pst.setString(2, phieuNhapSach.getMaNhaCungCap());
            pst.setString(3, phieuNhapSach.getMaPhieuNhap());
            rowsAffected = pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowsAffected;
    }

    @Override
    public int delete(String maPhieuNhap) {
        int rowsAffected = 0;
        String sql = "DELETE FROM dbo.[PhieuNhapSach] WHERE maPhieuNhap = ?";
        try (Connection conn = KetNoiSQL.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, maPhieuNhap);
            rowsAffected = pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowsAffected;
    }

    @Override
    public List<PhieuNhapSach> selectAll() {
        List<PhieuNhapSach> rowSelected = new ArrayList<PhieuNhapSach>();
        try (Connection conn = KetNoiSQL.getConnection();
             PreparedStatement pst = conn.prepareStatement("SELECT * FROM dbo.[PhieuNhapSach]");
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                String maPhieuNhap = rs.getString("maPhieuNhap");
                LocalDate ngayNhap = rs.getDate("ngayNhap").toLocalDate();
                String maNhaCungCap = rs.getString("maNhaCungCap");
                PhieuNhapSach phieuNhapSach = new PhieuNhapSach(maPhieuNhap, ngayNhap, maNhaCungCap);
                rowSelected.add(phieuNhapSach);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowSelected;
    }

    @Override
    public PhieuNhapSach selectById(String maPhieuNhap) {
        PhieuNhapSach result = new PhieuNhapSach();
        String sql = "SELECT * FROM dbo.[PhieuNhapSach] WHERE maPhieuNhap = ?";
        try (Connection conn = KetNoiSQL.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, maPhieuNhap);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                result.setMaPhieuNhap(rs.getString("maPhieuNhap"));
                result.setNgayNhap(rs.getDate("ngayNhap").toLocalDate());
                result.setMaNhaCungCap(rs.getString("maNhaCungCap"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
