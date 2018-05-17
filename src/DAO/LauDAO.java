package DAO;

import DTO.LauDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LauDAO {
    public List<LauDTO> getList() {
        String query = "SELECT * FROM `LAU` where DaXoa = 0";
        try (Connection conn = DbConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.isBeforeFirst()) {
                List<LauDTO> laus = new ArrayList<LauDTO>();
                int i = 0;
                while (resultSet.next()) {
                    LauDTO lau = new LauDTO();
                    lau.setMaLau(resultSet.getInt("MaLau"));
                    lau.setTenLau(resultSet.getString("TenLau"));
                    lau.setSoPhong(resultSet.getInt("SoPhong"));
                    laus.add(lau);
                    i++;
                }
                return laus;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public boolean update(LauDTO lauDTO) {
        //createMonthTable(month, year);
        String query = "UPDATE `LAU` SET SoPhong = ? where MaLau = ?";
        try (Connection conn = DbConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, lauDTO.getSoPhong());
            pstmt.setInt(2, lauDTO.getMaLau());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public void refresh(LauDTO lauDTO) {
        //createMonthTable(month, year);
        String query = "SELECT count(*) FROM `PHONG` where MaLau = ? and DaXoa = 0";
        try (Connection conn = DbConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, lauDTO.getMaLau());
            ResultSet resultSet = pstmt.executeQuery();
            if(resultSet.isBeforeFirst()){
                lauDTO.setSoPhong(resultSet.getInt(1));
                update(lauDTO);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
