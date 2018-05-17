package DTO;

public class LoaiPhongDTO {
    private int MaLoaiPhong;
    private String TenLoaiPhong;
    private double DonGia;
    private int SoLuongGiuong;
    private int DaXoa;

    public void setDaXoa(int daXoa) {
        DaXoa = daXoa;
    }

    public void setSoLuongGiuong(int soLuongGiuong) {
        SoLuongGiuong = soLuongGiuong;
    }

    public void setMaLoaiPhong(int maLoaiPhong) {
        MaLoaiPhong = maLoaiPhong;
    }

    public void setDonGia(double donGia) {
        DonGia = donGia;
    }

    public void setTenLoaiPhong(String tenLoaiPhong) {
        TenLoaiPhong = tenLoaiPhong;
    }

    public int getDaXoa() {
        return DaXoa;
    }

    public int getSoLuongGiuong() {
        return SoLuongGiuong;
    }

    public int getMaLoaiPhong() {
        return MaLoaiPhong;
    }

    public double getDonGia() {
        return DonGia;
    }

    public String getTenLoaiPhong() {
        return TenLoaiPhong;
    }
}
