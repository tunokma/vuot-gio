/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kma.quanlygiangday.model;

import com.kma.quanlygiangday.viewmodel.GiangVienVM;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Tuno
 */
@Entity
@Table(name = "tbd_giang_vien", schema = "gio_day_them")
public class GiangVien implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "ho_ten")
    private String hoTen;

    @Column(name = "nam_sinh")
    private Long namSinh;

    @Column(name = "chuc_vu")
    private String chucVu;

    @Column(name = "luong_thuc_nhan")
    private Long luongThucNhan;

    @Column(name = "hoc_ham")
    private Long hocHam;

    @Column(name = "id_bo_mon")
    private Long idBoMon;

    @Column(name = "giam_tru")
    private String giamTru;

    @Column(name = "user_name")
    private String username;

    @Column(name = "password")
    private String password;

    @Column(name = "password_2")
    private String password_2;

    @Column(name = "email")
    private String email;

    @Column(name = "role")
    private Long role;

    @Column(name = "chu_nhiem")
    private Long chuNhiem;

    @Column(name = "dinh_muc_gg")
    private String dinhMucGG;

    @Column(name = "muc_tt_chuan")
    private Long mucTtChuan;

    public GiangVien() {
    }

    public GiangVien(GiangVienVM giangVien) {
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
        this.chuNhiem = giangVien.getChuNhiem();
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

    public Long getChuNhiem() {
        return chuNhiem;
    }

    public void setChuNhiem(Long chuNhiem) {
        this.chuNhiem = chuNhiem;
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.id);
        hash = 53 * hash + Objects.hashCode(this.hoTen);
        hash = 53 * hash + Objects.hashCode(this.namSinh);
        hash = 53 * hash + Objects.hashCode(this.chucVu);
        hash = 53 * hash + Objects.hashCode(this.luongThucNhan);
        hash = 53 * hash + Objects.hashCode(this.hocHam);
        hash = 53 * hash + Objects.hashCode(this.idBoMon);
        hash = 53 * hash + Objects.hashCode(this.giamTru);
        hash = 53 * hash + Objects.hashCode(this.username);
        hash = 53 * hash + Objects.hashCode(this.password);
        hash = 53 * hash + Objects.hashCode(this.password_2);
        hash = 53 * hash + Objects.hashCode(this.email);
        hash = 53 * hash + Objects.hashCode(this.role);
        hash = 53 * hash + Objects.hashCode(this.dinhMucGG);
        hash = 53 * hash + Objects.hashCode(this.mucTtChuan);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final GiangVien other = (GiangVien) obj;
        if (!Objects.equals(this.hoTen, other.hoTen)) {
            return false;
        }
        if (!Objects.equals(this.chucVu, other.chucVu)) {
            return false;
        }
        if (!Objects.equals(this.username, other.username)) {
            return false;
        }
        if (!Objects.equals(this.password, other.password)) {
            return false;
        }
        if (!Objects.equals(this.password_2, other.password_2)) {
            return false;
        }
        if (!Objects.equals(this.email, other.email)) {
            return false;
        }
        if (!Objects.equals(this.dinhMucGG, other.dinhMucGG)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.namSinh, other.namSinh)) {
            return false;
        }
        if (!Objects.equals(this.luongThucNhan, other.luongThucNhan)) {
            return false;
        }
        if (!Objects.equals(this.hocHam, other.hocHam)) {
            return false;
        }
        if (!Objects.equals(this.idBoMon, other.idBoMon)) {
            return false;
        }
        if (!Objects.equals(this.giamTru, other.giamTru)) {
            return false;
        }
        if (!Objects.equals(this.role, other.role)) {
            return false;
        }
        if (!Objects.equals(this.mucTtChuan, other.mucTtChuan)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kma.quanlygiangday.model.GiangVien{" + "id=" + id + '}';
    }

}
