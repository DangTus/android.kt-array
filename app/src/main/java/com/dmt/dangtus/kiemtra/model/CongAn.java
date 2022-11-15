package com.dmt.dangtus.kiemtra.model;

public class CongAn {
    private String ten;
    private String hinhAnh;
    private String capBac;
    private String noiCongTac;
    private String quocGia;
    private int soSao;

    public CongAn() {
    }

    public CongAn(String ten, String hinhAnh, String capBac, String noiCongTac, String quocGia, int soSao) {
        this.ten = ten;
        this.hinhAnh = hinhAnh;
        this.capBac = capBac;
        this.noiCongTac = noiCongTac;
        this.quocGia = quocGia;
        this.soSao = soSao;
    }

    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    public String getHinhAnh() {
        return hinhAnh;
    }

    public void setHinhAnh(String hinhAnh) {
        this.hinhAnh = hinhAnh;
    }

    public String getCapBac() {
        return capBac;
    }

    public void setCapBac(String capBac) {
        this.capBac = capBac;
    }

    public String getNoiCongTac() {
        return noiCongTac;
    }

    public void setNoiCongTac(String noiCongTac) {
        this.noiCongTac = noiCongTac;
    }

    public String getQuocGia() {
        return quocGia;
    }

    public void setQuocGia(String quocGia) {
        this.quocGia = quocGia;
    }

    public int getSoSao() {
        return soSao;
    }

    public void setSoSao(int soSao) {
        this.soSao = soSao;
    }
}
