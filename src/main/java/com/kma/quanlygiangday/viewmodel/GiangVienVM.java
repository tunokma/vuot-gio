/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kma.quanlygiangday.viewmodel;

import com.kma.quanlygiangday.model.GiangVien;

/**
 *
 * @author Tuno
 */
public class GiangVienVM {

    private Long id;
    private String hoTen;
    private Long namSinh;
    private String chucVu;
    private Long luongThucNhan;
    private Long hocHam;
    private Long idBoMon;
    private String giamTru;
    private String username;
    private String password;
    private String password_2;
    private String email;
    private Long role;
    private String dinhMucGG;
    private Long mucTtChuan;

    private String tenBoMon;
    private String hocHamText;

    public GiangVienVM() {
    }

    public GiangVienVM(GiangVien giangVien) {
        this.id = giangVien.getId();
        this.hoTen = giangVien.getHoTen();
        this.namSinh = giangVien.getNamSinh();
        this.chucVu = giangVien.getChucVu();
        this.luongThucNhan = giangVien.getLuongThucNhan();
        this.hocHam = giangVien.getHocHam();
        this.idBoMon = giangVien.getIdBoMon();
        this.giamTru = giangVien.getGiamTru();
        this.username = giangVien.getUsername();
        this.password = giangVien.getPassword();
        this.role = giangVien.getRole();
        this.password_2 = giangVien.getPassword_2();
        this.email = giangVien.getEmail();
        this.dinhMucGG = giangVien.getDinhMucGG();
        this.mucTtChuan = giangVien.getMucTtChuan();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHoTen() {
        return hoTen;
    }

    public void setHoTen(String hoTen) {
        this.hoTen = hoTen;
    }

    public Long getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(Long namSinh) {
        this.namSinh = namSinh;
    }

    public String getChucVu() {
        return chucVu;
    }

    public void setChucVu(String chucVu) {
        this.chucVu = chucVu;
    }

    public Long getLuongThucNhan() {
        return luongThucNhan;
    }

    public void setLuongThucNhan(Long luongThucNhan) {
        this.luongThucNhan = luongThucNhan;
    }

    public Long getHocHam() {
        return hocHam;
    }

    public void setHocHam(Long hocHam) {
        this.hocHam = hocHam;
    }

    public Long getIdBoMon() {
        return idBoMon;
    }

    public void setIdBoMon(Long idBoMon) {
        this.idBoMon = idBoMon;
    }

    public String getGiamTru() {
        return giamTru;
    }

    public void setGiamTru(String giamTru) {
        this.giamTru = giamTru;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword_2() {
        return password_2;
    }

    public void setPassword_2(String password_2) {
        this.password_2 = password_2;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getRole() {
        return role;
    }

    public void setRole(Long role) {
        this.role = role;
    }

    public String getDinhMucGG() {
        return dinhMucGG;
    }

    public void setDinhMucGG(String dinhMucGG) {
        this.dinhMucGG = dinhMucGG;
    }

    public Long getMucTtChuan() {
        return mucTtChuan;
    }

    public void setMucTtChuan(Long mucTtChuan) {
        this.mucTtChuan = mucTtChuan;
    }

    public String getTenBoMon() {
        return tenBoMon;
    }

    public void setTenBoMon(String tenBoMon) {
        this.tenBoMon = tenBoMon;
    }

    public String getHocHamText() {
        return hocHamText;
    }

    public void setHocHamText(String hocHamText) {
        this.hocHamText = hocHamText;
    }

}
