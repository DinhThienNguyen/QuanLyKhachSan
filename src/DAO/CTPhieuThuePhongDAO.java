package DAO;

import DTO.CTPhieuThuePhongDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CTPhieuThuePhongDAO {
    public List<CTPhieuThuePhongDTO> get(int MaPhieuThuePhong) {
        String query = "SELECT * FROM `CTPHIEUTHUEPHONG` where MaPhieuThuePhong = ? and DaXoa = 0";
        try (Connection conn = DbConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, MaPhieuThuePhong);
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.isBeforeFirst()) {
                List<CTPhieuThuePhongDTO> ctPhieuThuePhongDTOList = new ArrayList<CTPhieuThuePhongDTO>();
                while (resultSet.next()) {
                    CTPhieuThuePhongDTO ctPhieuThuePhongDTO = new CTPhieuThuePhongDTO();
                    ctPhieuThuePhongDTO.setMaCTPhieuThuePhong(resultSet.getInt("MaCTPhieuThuePhong"));
                    ctPhieuThuePhongDTO.setMaPhieuThuePhong(resultSet.getInt("MaPhieuThuePhong"));
                    ctPhieuThuePhongDTO.setMaKhachHang(resultSet.getInt("MaKhachHang"));
                    ctPhieuThuePhongDTO.setDaXoa(resultSet.getInt("DaXoa"));
                    ctPhieuThuePhongDTOList.add(ctPhieuThuePhongDTO);
                }
                return ctPhieuThuePhongDTOList;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }
}
