package DAO;

import DTO.PhongDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PhongDAO {

    public List<PhongDTO> get(int MaLau) {
        String query = "SELECT * FROM `PHONG` where MaLau = ? and DaXoa = 0";
        try (Connection conn = DbConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, MaLau);
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.isBeforeFirst()) {
                List<PhongDTO> phong = new ArrayList<PhongDTO>();
                int i = 0;
                while (resultSet.next()) {
                    PhongDTO newPhong = new PhongDTO();
                    newPhong.setMaPhong(resultSet.getInt("MaPhong"));
                    newPhong.setTenPhong(resultSet.getString("TenPhong"));
                    newPhong.setMaLau(resultSet.getInt("MaLau"));
                    newPhong.setMaLoaiPhong(resultSet.getInt("MaLoaiPhong"));
                    newPhong.setTinhTrangPhong(resultSet.getString("TinhTrangPhong"));
                    newPhong.setGhiChu(resultSet.getString("GhiChu"));
                    newPhong.setDaXoa(resultSet.getInt("DaXoa"));
                    phong.add(newPhong);
                    i++;
                }
                return phong;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public boolean insert(PhongDTO phongDTO) {
        //createMonthTable(month, year);
        String query = "INSERT INTO `PHONG`(TenPhong, MaLau, MaLoaiPhong, GhiChu, TinhTrangPhong) VALUES ("
                + phongDTO.getTenPhong()
                + "', " + phongDTO.getMaLau()
                + ", " + phongDTO.getMaLoaiPhong()
                + ", " + phongDTO.getGhiChu()
                + ", '" + phongDTO.getTinhTrangPhong()
                + "')";
        try (Connection conn = DbConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
}
