package DAO;

import Model.KhoSach;
import Model.Sach;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Sach_DAO implements DAO_Interface <Sach>{
    public static Sach_DAO getInstance(){
        return new Sach_DAO();
    }
    @Override
    public int add(Sach sach) {
        int rowsAffected=0;
        String sql = "INSERT INTO dbo.[ThongTinSach] VALUES (?,?,?,?,?,?,?,?,?,?,?)";
        try (Connection conn = KetNoiSQL.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, sach.getMaSach());
            pst.setString(2, sach.getTenSach());
            pst.setString(3, sach.getMaDMSach());
            pst.setString(4, sach.getMaTheLoai());
            pst.setString(5, sach.getMaTacGia());
            pst.setString(6, sach.getTenTacGia());
            pst.setString(7, sach.getNXB());
            pst.setInt(8, sach.getNamXuatBan());
            pst.setDouble(9, sach.getGiaTienSach());
            pst.setString(10, sach.getTinhTrangSach());
            pst.setString(11, sach.getTomTatND());
            rowsAffected = pst.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
        return rowsAffected;
    }

    @Override
    public int update(Sach sach) {
        int rowsAffected=0;
        String sql = "UPDATE dbo.[ThongTinSach] SET tenSach = ?, maDMSach = ?, maTheLoai = ?, maTacGia = ?, " +
                "tenTacGia = ?,NXB = ?, namXuatBan = ?, giaTienSach = ?, tinhTrangSach = ?, tomTatND = ? WHERE maSach = ?";
        try (Connection conn = KetNoiSQL.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            pst.setString(1, sach.getTenSach());
            pst.setString(2, sach.getMaDMSach());
            pst.setString(3, sach.getMaTheLoai());
            pst.setString(4, sach.getMaTacGia());
            pst.setString(5, sach.getTenTacGia());
            pst.setString(6, sach.getNXB());
            pst.setInt(7,sach.getNamXuatBan());
            pst.setDouble(8, sach.getGiaTienSach());
            pst.setString(9, sach.getTinhTrangSach());
            pst.setString(10, sach.getTomTatND());
            pst.setString(11, sach.getMaSach());
            rowsAffected = pst.executeUpdate();
        }catch(Exception e){
            e.printStackTrace();
        }
        return rowsAffected;
    }

    // Lưu ý: Việc xóa thông tin sách đồng nghĩa xóa nó trong kho sách.
    @Override
    public int delete(String maSach) {
        int rowsAffected =0;
        String sql = "DELETE FROM dbo.[ThongTinSach] WHERE maSach ='"+maSach+"'";
        try (Connection conn = KetNoiSQL.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql)) {
            rowsAffected = pst.executeUpdate();
            if (rowsAffected > 1){
                KhoSach_DAO.getInstance().delete(maSach);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return rowsAffected;
    }


    @Override
    public List selectAll() {
        List<Sach> rowSelected = new ArrayList<Sach>();
        try (Connection conn = KetNoiSQL.getConnection();
             PreparedStatement pst = conn.prepareStatement("SELECT * FROM dbo.[ThongTinSach]");
             ResultSet rs = pst.executeQuery()) {
            if (rs.next()) {
                do {
                    String maSach = rs.getString("maSach");
                    String tenSach = rs.getString("tenSach");
                    String maDMSach = rs.getString("maDMSach");
                    String maTheLoai = rs.getString("maTheLoai");
                    String maTacGia = rs.getString("maTacGia");
                    String tenTacGia = rs.getString("tenTacGia");
                    String NXB = rs.getString("NXB");
                    int namXuatBan = rs.getInt("namXuatBan");
                    double giaTienSach = rs.getDouble("giaTienSach");
                    String tinhTrangSach = rs.getString("tinhTrangSach");
                    String tomTatND = rs.getString("tomTatND");
                    Sach book = new Sach(maSach, tenSach, maDMSach, maTheLoai, maTacGia, tenTacGia, NXB, namXuatBan, giaTienSach, tinhTrangSach, tomTatND);
                    rowSelected.add(book);
                } while (rs.next());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowSelected;
    }

    @Override
    public Sach selectById(String maSach) {
        Sach result = new Sach();
        String sql = "SELECT * FROM dbo.[ThongTinSach] WHERE maSach ='"+maSach+"'";
        try (Connection conn = KetNoiSQL.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery())  {
            if (rs.next()) {
                do {
                    result.setMaSach(rs.getString("maSach"));
                    result.setTenSach(rs.getString("tenSach"));
                    result.setMaDMSach(rs.getString("maDMSach"));
                    result.setMaTheLoai(rs.getString("maTheLoai"));
                    result.setMaTacGia(rs.getString("maTacGia"));
                    result.setTacGia(rs.getString("tenTacGia"));
                    result.setNXB(rs.getString("NXB"));
                    result.setNamXuatBan(rs.getInt("namXuatBan"));
                    result.setGiaTienSach(rs.getDouble("giaTienSach"));
                    result.setTinhTrangSach(rs.getString("tinhTrangSach"));
                    result.setTomTatND(rs.getString("tomTatND"));
                } while (rs.next());
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return result;
    }

    //Lọc theo danh mục
    public List<Sach> selectByCategory(String maDMSach) {
        List<Sach> rowSelected = new ArrayList<Sach>();
        String sql = "SELECT * FROM dbo.[ThongTinSach] WHERE maSach ='"+maDMSach+"'";
        try (Connection conn = KetNoiSQL.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            if (rs.next()) {
                do {
                    String maSach = rs.getString("maSach");
                    String tenSach = rs.getString("tenSach");
                    String maTheLoai = rs.getString("maTheLoai");
                    String maTacGia = rs.getString("maTacGia");
                    String tenTacGia = rs.getString("tenTacGia");
                    String NXB = rs.getString("NXB");
                    int namXuatBan = rs.getInt("namXuatBan");
                    double giaTienSach = rs.getDouble("giaTienSach");
                    String tinhTrangSach = rs.getString("tinhTrangSach");
                    String tomTatND = rs.getString("tomTatND");
                    Sach book = new Sach(maSach, tenSach, maDMSach, maTheLoai, maTacGia, tenTacGia, NXB, namXuatBan, giaTienSach, tinhTrangSach, tomTatND);
                    rowSelected.add(book);
                } while (rs.next());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowSelected;
    }

    //Lọc theo thể loại
    public List<Sach> selectByGenre(String maTheLoai) {
        List<Sach> rowSelected = new ArrayList<Sach>();
        String sql = "SELECT * FROM dbo.[ThongTinSach] WHERE maSach ='"+maTheLoai+"'";
        try (Connection conn = KetNoiSQL.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            if (rs.next()) {
                do {
                    String maSach = rs.getString("maSach");
                    String tenSach = rs.getString("tenSach");
                    String maDMSach = rs.getString("maDMSach");
                    String maTacGia = rs.getString("maTacGia");
                    String tenTacGia = rs.getString("tenTacGia");
                    String NXB = rs.getString("NXB");
                    int namXuatBan = rs.getInt("namXuatBan");
                    double giaTienSach = rs.getDouble("giaTienSach");
                    String tinhTrangSach = rs.getString("tinhTrangSach");
                    String tomTatND = rs.getString("tomTatND");
                    Sach book = new Sach(maSach, tenSach, maDMSach, maTheLoai, maTacGia, tenTacGia, NXB, namXuatBan, giaTienSach, tinhTrangSach, tomTatND);
                    rowSelected.add(book);
                } while (rs.next());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowSelected;
    }

    //Loc theo danh mục và thể loại
    public List<Sach> selectByCategoryAndGenre(String maDMSach, String maTheLoai) {
        List<Sach> rowSelected = new ArrayList<Sach>();
        String sql = "SELECT * FROM dbo.[ThongTinSach] WHERE maSach ='"+maDMSach+"' AND maTheLoai ='"+maTheLoai+"' ";
        try (Connection conn = KetNoiSQL.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            if (rs.next()) {
                do {
                    String maSach = rs.getString("maSach");
                    String tenSach = rs.getString("tenSach");
                    String maTacGia = rs.getString("maTacGia");
                    String tenTacGia = rs.getString("tenTacGia");
                    String NXB = rs.getString("NXB");
                    int namXuatBan = rs.getInt("namXuatBan");
                    double giaTienSach = rs.getDouble("giaTienSach");
                    String tinhTrangSach = rs.getString("tinhTrangSach");
                    String tomTatND = rs.getString("tomTatND");
                    Sach book = new Sach(maSach, tenSach, maDMSach, maTheLoai, maTacGia, tenTacGia, NXB, namXuatBan, giaTienSach, tinhTrangSach, tomTatND);
                    rowSelected.add(book);
                } while (rs.next());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowSelected;
    }

    //Lọc theo tên (gần giống nhất)
    public List<Sach> selectByMostSimilarName (String tenSach) {
        List<Sach> rowSelected = new ArrayList<Sach>();
        String sql = "SELECT * FROM dbo.[ThongTinSach] WHERE TenCot LIKE '% '"+tenSach+"' %' ";
        try (Connection conn = KetNoiSQL.getConnection();
             PreparedStatement pst = conn.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {
            if (rs.next()) {
                do {
                    String maSach = rs.getString("maSach");
                    String maDMSach = rs.getString("maDMSach");
                    String maTheLoai = rs.getString("maTheLoai");
                    String maTacGia = rs.getString("maTacGia");
                    String tenTacGia = rs.getString("tenTacGia");
                    String NXB = rs.getString("NXB");
                    int namXuatBan = rs.getInt("namXuatBan");
                    double giaTienSach = rs.getDouble("giaTienSach");
                    String tinhTrangSach = rs.getString("tinhTrangSach");
                    String tomTatND = rs.getString("tomTatND");
                    Sach book = new Sach(maSach, tenSach, maDMSach, maTheLoai, maTacGia, tenTacGia, NXB, namXuatBan, giaTienSach, tinhTrangSach, tomTatND);
                    rowSelected.add(book);
                } while (rs.next());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rowSelected;
    }
}
