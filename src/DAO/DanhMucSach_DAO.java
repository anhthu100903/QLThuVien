package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import Model.DanhMucSach;

public class DanhMucSach_DAO implements DAO_Interface<DanhMucSach> {
    public static DanhMucSach_DAO getInstance() {
        return new DanhMucSach_DAO();
    }

    @Override
    public int add(DanhMucSach danhMucSach) {
        int rowsAffected = 0;
        String sql = "INSERT INTO dbo.[DanhMucSach] (maDMSach, tenDMSach) VALUES (?, ?)";
        try (Connection conn = KetNoiSQL.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, danhMucSach.getMaDM());
            pst.setString(2, danhMucSach.getTenDM());
            rowsAffected = pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowsAffected;
    }

    @Override
    public int update(DanhMucSach danhMucSach) {
        int rowsAffected = 0;
        String sql = "UPDATE dbo.[DanhMucSach] SET tenDMSach = ? WHERE maDMSach = ?";
        try (Connection conn = KetNoiSQL.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, danhMucSach.getTenDM());
            pst.setString(2, danhMucSach.getMaDM());
            rowsAffected = pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowsAffected;
    }

    @Override
    public int delete(String maDM) {
        int rowsAffected = 0;
        String sql = "DELETE FROM dbo.[DanhMucSach] WHERE maDMSach = ?";
        try (Connection conn = KetNoiSQL.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, maDM);
            rowsAffected = pst.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return rowsAffected;
    }

    @Override
    public List<DanhMucSach> selectAll() {
        List<DanhMucSach> rowSelected = new ArrayList<DanhMucSach>();
        try (Connection conn = KetNoiSQL.getConnection();
             PreparedStatement pst = conn.prepareStatement("SELECT * FROM dbo.[DanhMucSach]");
             ResultSet rs = pst.executeQuery()) {
            while (rs.next()) {
                String maDM = rs.getString("maDMSach");
                String tenDM = rs.getString("tenDMSach");
                DanhMucSach danhMucSach = new DanhMucSach(maDM, tenDM);
                rowSelected.add(danhMucSach);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowSelected;
    }

    @Override
    public DanhMucSach selectById(String maDM) {
        DanhMucSach result = new DanhMucSach();
        String sql = "SELECT * FROM dbo.[DanhMucSach] WHERE maDMSach = ?";
        try (Connection conn = KetNoiSQL.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, maDM);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                result.setMaDM(rs.getString("maDM"));
                result.setTenDM(rs.getString("tenDM"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
    public boolean checkMaDM(String maDM) {
        Connection connection = KetNoiSQL.getConnection();
        String sql = "SELECT * FROM DanhMucSach WHERE maDMSach like '%" + maDM + "%'";
        try{
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            if(rs.isBeforeFirst() == false){
                return false;
            } else
                return true;
        } catch(Exception e){

        }
        return false;
    }
}
