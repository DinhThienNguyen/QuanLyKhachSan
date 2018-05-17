package DAO;

import DTO.LoaiPhongDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoaiPhongDAO {
    public List<LoaiPhongDTO> getList() {
        String query = "SELECT * FROM `LOAIPHONG` where DaXoa = 0";
        try (Connection conn = DbConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.isBeforeFirst()) {
                List<LoaiPhongDTO> loaiPhongDTOList = new ArrayList<LoaiPhongDTO>();
                int i = 0;
                while (resultSet.next()) {
                    LoaiPhongDTO loaiPhongDTO = new LoaiPhongDTO();
                    loaiPhongDTO.setMaLoaiPhong(resultSet.getInt("MaLoaiPhong"));
                    loaiPhongDTO.setTenLoaiPhong(resultSet.getString("TenLoaiPhong"));
                    loaiPhongDTO.setDonGia(resultSet.getDouble("DonGia"));
                    loaiPhongDTO.setSoLuongGiuong(resultSet.getInt("SoLuongGiuong"));
                    loaiPhongDTO.setDaXoa(resultSet.getInt("DaXoa"));
                    loaiPhongDTOList.add(loaiPhongDTO);
                    i++;
                }
                return loaiPhongDTOList;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
}
