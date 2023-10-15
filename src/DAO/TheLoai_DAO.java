package DAO;
import Model.TheLoai;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class TheLoai_DAO implements DAO_Interface<TheLoai> {
    public static TheLoai_DAO getInstance() {
        return new TheLoai_DAO();
    }

    @Override
    public int add(TheLoai theLoai) {
        int rowsAffected = 0;
        String sql = "INSERT INTO dbo.[TheLoai] (maTheLoai, tenTheLoai) VALUES (?, ?)";
        try (Connection conn = KetNoiSQL.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, theLoai.getMaTheLoai());
            pst.setString(2, theLoai.getTenTheLoai());
            rowsAffected = pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowsAffected;
    }

    @Override
    public int update(TheLoai theLoai) {
        int rowsAffected = 0;
        String sql = "UPDATE dbo.[TheLoai] SET tenTheLoai = ? WHERE maTheLoai = ?";
        try (Connection conn = KetNoiSQL.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, theLoai.getTenTheLoai());
            pst.setString(2, theLoai.getMaTheLoai());
            rowsAffected = pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowsAffected;
    }

    @Override
    public int delete(String maTheLoai) {
        int rowsAffected = 0;
        String sql = "DELETE FROM dbo.[TheLoai] WHERE maTheLoai = ?";
        try (Connection conn = KetNoiSQL.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, maTheLoai);
            rowsAffected = pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowsAffected;
    }

    @Override
    public List<TheLoai> selectAll() {
        List<TheLoai> rowSelected = new ArrayList<TheLoai>();
        try (Connection conn = KetNoiSQL.getConnection();
             PreparedStatement pst = conn.prepareStatement("SELECT * FROM dbo.[TheLoai]");
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                String maTheLoai = rs.getString("maTheLoai");
                String tenTheLoai = rs.getString("tenTheLoai");
                TheLoai theLoai = new TheLoai(maTheLoai, tenTheLoai);
                rowSelected.add(theLoai);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowSelected;
    }

    @Override
    public TheLoai selectById(String maTheLoai) {
        TheLoai result = new TheLoai();
        String sql = "SELECT * FROM dbo.[TheLoai] WHERE maTheLoai = ?";
        try (Connection conn = KetNoiSQL.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, maTheLoai);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                result.setMaTheLoai(rs.getString("maTheLoai"));
                result.setTenTheLoai(rs.getString("tenTheLoai"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}
