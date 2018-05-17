package DAO;

import DTO.PhieuThuePhongDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class PhieuThuePhongDAO {
    public PhieuThuePhongDTO get(int MaPhong) {
        String query = "SELECT * FROM `PHIEUTHUEPHONG` where MaPhong = ? and DaXoa = 0";
        try (Connection conn = DbConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, MaPhong);
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.isBeforeFirst()) {
                PhieuThuePhongDTO phieuThuePhongDTO = new PhieuThuePhongDTO();
                while (resultSet.next()) {
                    PhieuThuePhongDTO phieuThuePhongDTO1 = new PhieuThuePhongDTO();
                    phieuThuePhongDTO1.setMaPhieuThuePhong(resultSet.getInt("MaPhieuThuePhong"));
                    phieuThuePhongDTO1.setNgayBatDauThue(resultSet.getLong("NgayBatDauThue"));
                    phieuThuePhongDTO1.setNgayTraPhongDuKien(resultSet.getInt("NgayTraPhongDuKien"));
                    phieuThuePhongDTO1.setMaPhong(resultSet.getInt("MaPhong"));
                    phieuThuePhongDTO1.setDaXoa(resultSet.getInt("DaXoa"));
                    return phieuThuePhongDTO1;
                }
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
}
