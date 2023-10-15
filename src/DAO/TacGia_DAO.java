package DAO;

import Model.TacGia;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class TacGia_DAO implements DAO_Interface<TacGia> {

    public static TacGia_DAO getInstance() {
        return new TacGia_DAO();
    }

    @Override
    public int add(TacGia tacGia) {
        int rowsAffected = 0;
        String sql = "INSERT INTO dbo.[TacGia] (maTacGia, tenTacGia, soSach) VALUES (?, ?, ?)";
        try (Connection conn = KetNoiSQL.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, tacGia.getMaTacGia());
            pst.setString(2, tacGia.getTenTacGia());
            pst.setInt(3, tacGia.getSoSach());
            rowsAffected = pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowsAffected;
    }

    @Override
    public int update(TacGia tacGia) {
        int rowsAffected = 0;
        String sql = "UPDATE dbo.[TacGia] SET tenTacGia = ?, soSach = ? WHERE maTacGia = ?";
        try (Connection conn = KetNoiSQL.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, tacGia.getTenTacGia());
            pst.setInt(2, tacGia.getSoSach());
            pst.setString(3, tacGia.getMaTacGia());
            rowsAffected = pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowsAffected;
    }

    @Override
    public int delete(String maTacGia) {
        int rowsAffected = 0;
        String sql = "DELETE FROM dbo.[TacGia] WHERE maTacGia = ?";
        try (Connection conn = KetNoiSQL.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, maTacGia);
            rowsAffected = pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowsAffected;
    }

    @Override
    public List<TacGia> selectAll() {
        List<TacGia> rowSelected = new ArrayList<TacGia>();
        try (Connection conn = KetNoiSQL.getConnection();
             PreparedStatement pst = conn.prepareStatement("SELECT * FROM dbo.[TacGia]");
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                String maTacGia = rs.getString("maTacGia");
                String tenTacGia = rs.getString("tenTacGia");
                int soSach = rs.getInt("soSach");
                TacGia tacGia = new TacGia(maTacGia, tenTacGia, soSach);
                rowSelected.add(tacGia);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowSelected;
    }

    @Override
    public TacGia selectById(String maTacGia) {
        TacGia result = new TacGia();
        String sql = "SELECT * FROM dbo.[TacGia] WHERE maTacGia = ?";
        try (Connection conn = KetNoiSQL.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, maTacGia);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                result.setMaTacGia(rs.getString("maTacGia"));
                result.setTenTacGia(rs.getString("tenTacGia"));
                result.setSoSach(rs.getInt("soSach"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
