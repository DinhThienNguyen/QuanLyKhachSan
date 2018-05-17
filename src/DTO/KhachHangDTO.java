package DTO;

public class KhachHangDTO {
    private int MaKhachHang;
    private String TenKhachHang;
    private int MaLoaiKhachHang;
    private int CMND;
    private int SoDienThoai;
    private String DiaChi;
    private int DaXoa;

    public void setDaXoa(int daXoa) {
        DaXoa = daXoa;
    }

    public void setCMND(int CMND) {
        this.CMND = CMND;
    }

    public void setDiaChi(String diaChi) {
        DiaChi = diaChi;
    }

    public void setMaKhachHang(int maKhachHang) {
        MaKhachHang = maKhachHang;
    }

    public void setMaLoaiKhachHang(int maLoaiKhachHang) {
        MaLoaiKhachHang = maLoaiKhachHang;
    }

    public void setSoDienThoai(int soDienThoai) {
        SoDienThoai = soDienThoai;
    }

    public void setTenKhachHang(String tenKhachHang) {
        TenKhachHang = tenKhachHang;
    }

    public int getDaXoa() {
        return DaXoa;
    }

    public int getCMND() {
        return CMND;
    }

    public int getMaKhachHang() {
        return MaKhachHang;
    }

    public int getMaLoaiKhachHang() {
        return MaLoaiKhachHang;
    }

    public int getSoDienThoai() {
        return SoDienThoai;
    }

    public String getDiaChi() {
        return DiaChi;
    }

    public String getTenKhachHang() {
        return TenKhachHang;
    }
}
