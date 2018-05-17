package DTO;

public class PhongDTO {
    private int MaPhong;
    private String TenPhong;
    private int MaLau;
    private int MaLoaiPhong;
    private String GhiChu;
    private String TinhTrangPhong;
    private int DaXoa;

    public PhongDTO() {
    }

    public PhongDTO(int maPhong, String tenPhong, int maLoaiPhong, String ghiChu, String tinhTrangPhong, int daXoa) {
        MaPhong = maPhong;
        TenPhong = tenPhong;
        MaLoaiPhong = maLoaiPhong;
        GhiChu = ghiChu;
        TinhTrangPhong = tinhTrangPhong;
        DaXoa = daXoa;
    }

    public void setMaLau(int maLau) {
        MaLau = maLau;
    }

    public void setDaXoa(int daXoa) {
        DaXoa = daXoa;
    }

    public void setGhiChu(String ghiChu) {
        GhiChu = ghiChu;
    }

    public void setMaLoaiPhong(int maLoaiPhong) {
        MaLoaiPhong = maLoaiPhong;
    }

    public void setMaPhong(int maPhong) {
        MaPhong = maPhong;
    }

    public void setTenPhong(String tenPhong) {
        TenPhong = tenPhong;
    }

    public void setTinhTrangPhong(String tinhTrangPhong) {
        TinhTrangPhong = tinhTrangPhong;
    }

    public int getMaLau() {
        return MaLau;
    }

    public int getDaXoa() {
        return DaXoa;
    }

    public int getMaLoaiPhong() {
        return MaLoaiPhong;
    }

    public int getMaPhong() {
        return MaPhong;
    }

    public String getGhiChu() {
        return GhiChu;
    }

    public String getTenPhong() {
        return TenPhong;
    }

    public String getTinhTrangPhong() {
        return TinhTrangPhong;
    }
}