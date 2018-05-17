package DAO;

import DTO.KhachHangDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class KhachHangDAO {
    public KhachHangDTO get(int MaKhachHang) {
        String query = "SELECT * FROM `KHACHHANG` where MaKhachHang = ? and DaXoa = 0";
        try (Connection conn = DbConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, MaKhachHang);
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.isBeforeFirst()) {
                KhachHangDTO khachHangDTO = new KhachHangDTO();
                while (resultSet.next()) {
                    KhachHangDTO khachHangDTO1 = new KhachHangDTO();
                    khachHangDTO1.setMaKhachHang(resultSet.getInt("MaKhachHang"));
                    khachHangDTO1.setTenKhachHang(resultSet.getString("TenKhachHang"));
                    khachHangDTO1.setMaLoaiKhachHang(resultSet.getInt("MaLoaiKhachHang"));
                    khachHangDTO1.setCMND(resultSet.getInt("CMND"));
                    khachHangDTO1.setDiaChi(resultSet.getString("DiaChi"));
                    khachHangDTO1.setSoDienThoai(resultSet.getInt("SoDienThoai"));
                    khachHangDTO1.setDaXoa(resultSet.getInt("DaXoa"));
                    return khachHangDTO1;
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
}
