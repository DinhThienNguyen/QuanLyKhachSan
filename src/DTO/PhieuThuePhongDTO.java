package DTO;

public class PhieuThuePhongDTO {
    private int MaPhieuThuePhong;
    private long NgayBatDauThue;
    private long NgayTraPhongDuKien;
    private long NgayTraPhong;
    private int MaPhong;
    private int DaXoa;

    public void setDaXoa(int daXoa) {
        DaXoa = daXoa;
    }

    public void setNgayTraPhongDuKien(long ngayTraPhongDuKien) {
        NgayTraPhongDuKien = ngayTraPhongDuKien;
    }

    public void setMaPhong(int maPhong) {
        MaPhong = maPhong;
    }

    public void setMaPhieuThuePhong(int maPhieuThuePhong) {
        MaPhieuThuePhong = maPhieuThuePhong;
    }

    public void setNgayBatDauThue(long ngayBatDauThue) {
        NgayBatDauThue = ngayBatDauThue;
    }

    public void setNgayTraPhong(long ngayTraPhong) {
        NgayTraPhong = ngayTraPhong;
    }

    public int getDaXoa() {
        return DaXoa;
    }

    public int getMaPhong() {
        return MaPhong;
    }

    public int getMaPhieuThuePhong() {
        return MaPhieuThuePhong;
    }

    public long getNgayBatDauThue() {
        return NgayBatDauThue;
    }

    public long getNgayTraPhong() {
        return NgayTraPhong;
    }

    public long getNgayTraPhongDuKien() {
        return NgayTraPhongDuKien;
    }
}
