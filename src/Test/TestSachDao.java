package Test;

import DAO.ChiTietPhieuNhap_DAO;
import DAO.KhoSach_DAO;
import DAO.PhieuNhapSach_DAO;
import DAO.Sach_DAO;
import Model.ChiTietPhieuNhapSach;
import Model.KhoSach;
import Model.PhieuNhapSach;
import Model.Sach;

import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.util.List;

public class TestSachDao {
    public static void main(String[] args) {

     //   KhoSach khoSach1 = new KhoSach("N0001", 99, 99, 1);
     //   KhoSach_DAO.getInstance().add(khoSach1);
    //    KhoSach khoSach2 = new KhoSach("N0008", 10, 10, 0);
     //   System.out.println(KhoSach_DAO.getInstance().add(khoSach2));

//        Sach sach1 = new Sach("N0001", "Harry Potter", "MD03", "FG001",
//                "TG100","J. K. Rowling", "NXB Tre", 2007,
//                150000.9999, "Hong", "");
//        System.out.println(Sach_DAO.getInstance().add(sach1));
//        LocalDate date1 = LocalDate.of(2023, 4, 15);
//        PhieuNhapSach phieuNhapSach1 = new PhieuNhapSach("PN125", date1);
//        PhieuNhapSach_DAO.getInstance().add(phieuNhapSach1);
//        ChiTietPhieuNhapSach chiTietPhieuNhapSach1 = new ChiTietPhieuNhapSach("PN125", "N0031", "Hoang Tu Be", "TG0020", "TL019", "NXB Tre", 2011, 10, 101200.9);
//        ChiTietPhieuNhap_DAO.getInstance().add(chiTietPhieuNhapSach1);
        List<Sach> sachByCate = Sach_DAO.getInstance().selectByCategory("DM002");
        int i = 0;
        for (Sach s : sachByCate) {
            i++;
            KhoSach khoSach = KhoSach_DAO.getInstance().selectById(s.getMaSach());
            System.out.println(i);
            System.out.println(s.getTenSach() + " "+ s.getTenTacGia() +" " + s.getNXB() + " " + khoSach.getSoLuongCon());
        }

       // Sach s = Sach_DAO.getInstance().selectById("S0001");
       // KhoSach khoSach = KhoSach_DAO.getInstance().selectById(s.getMaSach());
       // System.out.println(s.getTenSach() + " "+ s.getTenTacGia() +" " + s.getNXB() + " " + khoSach.getSoLuongCon());

    }
}
