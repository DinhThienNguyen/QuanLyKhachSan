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

    public LoaiPhongDTO getByID(int MaLoaiPhong) {
        String query = "SELECT * FROM `LOAIPHONG` where MaLoaiPhong = ? and DaXoa = 0";
        try (Connection conn = DbConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, MaLoaiPhong);
            ResultSet resultSet = pstmt.executeQuery();
            if (resultSet.isBeforeFirst()) {
                LoaiPhongDTO loaiPhongDTO = new LoaiPhongDTO();
                int i = 0;
                while (resultSet.next()) {
                    loaiPhongDTO.setMaLoaiPhong(resultSet.getInt("MaLoaiPhong"));
                    loaiPhongDTO.setTenLoaiPhong(resultSet.getString("TenLoaiPhong"));
                    loaiPhongDTO.setDonGia(resultSet.getDouble("DonGia"));
                    loaiPhongDTO.setSoLuongGiuong(resultSet.getInt("SoLuongGiuong"));
                    loaiPhongDTO.setDaXoa(resultSet.getInt("DaXoa"));
                    i++;
                }
                return loaiPhongDTO;
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return null;
    }

    public boolean insert(LoaiPhongDTO loaiPhongDTO) {
        //createMonthTable(month, year);
        String query = "INSERT INTO `LOAIPHONG`(TenLoaiPhong, DonGia, SoLuongGiuong) VALUES (?,?,?)";
        try (Connection conn = DbConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, loaiPhongDTO.getTenLoaiPhong());
            pstmt.setDouble(2, loaiPhongDTO.getDonGia());
            pstmt.setInt(3, loaiPhongDTO.getSoLuongGiuong());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean delete(int MaLoaiPhong) {
        //createMonthTable(month, year);
        String query = "UPDATE `LOAIPHONG` SET DaXoa = 1 WHERE MaLoaiPhong = ?";
        try (Connection conn = DbConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, MaLoaiPhong);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }

    public boolean update(LoaiPhongDTO loaiPhongDTO) {
        //createMonthTable(month, year);
        String query = "UPDATE `LOAIPHONG` SET TenLoaiPhong = ?, DonGia = ?, SoLuongGiuong = ? WHERE MaLoaiPhong = ?";
        try (Connection conn = DbConnection.connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, loaiPhongDTO.getTenLoaiPhong());
            pstmt.setDouble(2, loaiPhongDTO.getDonGia());
            pstmt.setInt(3, loaiPhongDTO.getSoLuongGiuong());
            pstmt.setInt(4, loaiPhongDTO.getMaLoaiPhong());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
        return true;
    }
}
