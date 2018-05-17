package DTO;

public class LauDTO {
    private int MaLau;
    private String TenLau;
    private int SoPhong;

    public void setMaLau(int maLau) {
        MaLau = maLau;
    }

    public void setSoPhong(int soPhong) {
        SoPhong = soPhong;
    }

    public void setTenLau(String tenLau) {
        TenLau = tenLau;
    }

    public int getMaLau() {
        return MaLau;
    }

    public String getTenLau() {
        return TenLau;
    }

    public int getSoPhong() {
        return SoPhong;
    }
}
