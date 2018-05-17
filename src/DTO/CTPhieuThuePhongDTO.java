package DTO;

public class CTPhieuThuePhongDTO {
    private int MaCTPhieuThuePhong;
    private int MaPhieuThuePhong;
    private int MaKhachHang;
    private int DaXoa;

    public void setMaPhieuThuePhong(int maPhieuThuePhong) {
        MaPhieuThuePhong = maPhieuThuePhong;
    }

    public void setDaXoa(int daXoa) {
        DaXoa = daXoa;
    }

    public void setMaKhachHang(int maKhachHang) {
        MaKhachHang = maKhachHang;
    }

    public void setMaCTPhieuThuePhong(int maCTPhieuThuePhong) {
        MaCTPhieuThuePhong = maCTPhieuThuePhong;
    }

    public int getMaPhieuThuePhong() {
        return MaPhieuThuePhong;
    }

    public int getDaXoa() {
        return DaXoa;
    }

    public int getMaKhachHang() {
        return MaKhachHang;
    }

    public int getMaCTPhieuThuePhong() {
        return MaCTPhieuThuePhong;
    }
}
