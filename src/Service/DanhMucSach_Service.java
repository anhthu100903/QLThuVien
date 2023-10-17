/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Service;

import DAO.DanhMucSach_DAO;
import Model.DanhMucSach;
import java.util.List;
import javax.swing.JOptionPane;

public class DanhMucSach_Service {
     private DanhMucSach_DAO dmsDAO;
    public DanhMucSach_Service(){
        dmsDAO = new DanhMucSach_DAO();
    }

    public List<DanhMucSach> getDSDanhMucSach(){
        return dmsDAO.selectAll();
    }

    public boolean checkMaDM(String maDM){
        return dmsDAO.checkMaDM(maDM);
    }

    public void addDanhMucSach(DanhMucSach danhmuc, String maDM){
        if(!dmsDAO.checkMaDM(maDM)){
            if (dmsDAO.add(danhmuc)>0){
                JOptionPane.showMessageDialog(null, "Đã thêm thành công!");
            }else{
                JOptionPane.showMessageDialog(null, "Thêm thất bại!");
            }

        }else{
            JOptionPane.showMessageDialog(null, "Không thể thêm vì trùng mã danh mục!");
        }
    }

    public void updateDanhMucSach(DanhMucSach danhmuc){
        dmsDAO.update(danhmuc);
//        JOptionPane.showMessageDialog(null, "Đã sửa thành công!");
    }

}
