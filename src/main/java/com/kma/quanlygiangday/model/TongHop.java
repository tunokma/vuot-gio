/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kma.quanlygiangday.model;

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
@Table(name = "tong_hop", schema = "gio_day_them")
public class TongHop implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "object_id")
    private Long objectId;
    
    @Column(name = "id_khoa")
    private Long idKhoa;
    
    @Column(name = "id_bo_mon")
    private Long idBoMon;
    
    @Column(name = "nam_hoc")
    private String namHoc;

    @Column(name = "tong_so_tiet")
    private Long tongSoTiet;

    @Column(name = "tiet_phai_giang")
    private Long soTietPhaiGiang;

    @Column(name = "gio_chua_ht_nckh")
    private Long soTietChuaHTNCKH;

    @Column(name = "giam_tru")
    private Long soTietGiamTru;

    @Column(name = "ly_do_giam_tru")
    private String lyDoGiamTru;

    @Column(name = "so_vuot_gio")
    private Long soTietThanhToan;

    public TongHop() {
    }

    public TongHop(Long id, Long objectId, Long idKhoa, Long idBoMon, String namHoc, Long tongSoTiet, Long soTietPhaiGiang, Long soTietChuaHTNCKH, Long soTietGiamTru, String lyDoGiamTru, Long soTietThanhToan) {
        this.id = id;
        this.objectId = objectId;
        this.idKhoa = idKhoa;
        this.idBoMon = idBoMon;
        this.namHoc = namHoc;
        this.tongSoTiet = tongSoTiet;
        this.soTietPhaiGiang = soTietPhaiGiang;
        this.soTietChuaHTNCKH = soTietChuaHTNCKH;
        this.soTietGiamTru = soTietGiamTru;
        this.lyDoGiamTru = lyDoGiamTru;
        this.soTietThanhToan = soTietThanhToan;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getObjectId() {
        return objectId;
    }

    public void setObjectId(Long objectId) {
        this.objectId = objectId;
    }

    public Long getIdKhoa() {
        return idKhoa;
    }

    public void setIdKhoa(Long idKhoa) {
        this.idKhoa = idKhoa;
    }

    public Long getIdBoMon() {
        return idBoMon;
    }

    public void setIdBoMon(Long idBoMon) {
        this.idBoMon = idBoMon;
    }

    public String getNamHoc() {
        return namHoc;
    }

    public void setNamHoc(String namHoc) {
        this.namHoc = namHoc;
    }

    public Long getTongSoTiet() {
        return tongSoTiet;
    }

    public void setTongSoTiet(Long tongSoTiet) {
        this.tongSoTiet = tongSoTiet;
    }

    public Long getSoTietPhaiGiang() {
        return soTietPhaiGiang;
    }

    public void setSoTietPhaiGiang(Long soTietPhaiGiang) {
        this.soTietPhaiGiang = soTietPhaiGiang;
    }

    public Long getSoTietChuaHTNCKH() {
        return soTietChuaHTNCKH;
    }

    public void setSoTietChuaHTNCKH(Long soTietChuaHTNCKH) {
        this.soTietChuaHTNCKH = soTietChuaHTNCKH;
    }

    public Long getSoTietGiamTru() {
        return soTietGiamTru;
    }

    public void setSoTietGiamTru(Long soTietGiamTru) {
        this.soTietGiamTru = soTietGiamTru;
    }

    public String getLyDoGiamTru() {
        return lyDoGiamTru;
    }

    public void setLyDoGiamTru(String lyDoGiamTru) {
        this.lyDoGiamTru = lyDoGiamTru;
    }

    public Long getSoTietThanhToan() {
        return soTietThanhToan;
    }

    public void setSoTietThanhToan(Long soTietThanhToan) {
        this.soTietThanhToan = soTietThanhToan;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.id);
        hash = 79 * hash + Objects.hashCode(this.objectId);
        hash = 79 * hash + Objects.hashCode(this.idKhoa);
        hash = 79 * hash + Objects.hashCode(this.idBoMon);
        hash = 79 * hash + Objects.hashCode(this.namHoc);
        hash = 79 * hash + Objects.hashCode(this.tongSoTiet);
        hash = 79 * hash + Objects.hashCode(this.soTietPhaiGiang);
        hash = 79 * hash + Objects.hashCode(this.soTietChuaHTNCKH);
        hash = 79 * hash + Objects.hashCode(this.soTietGiamTru);
        hash = 79 * hash + Objects.hashCode(this.lyDoGiamTru);
        hash = 79 * hash + Objects.hashCode(this.soTietThanhToan);
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
        final TongHop other = (TongHop) obj;
        if (!Objects.equals(this.namHoc, other.namHoc)) {
            return false;
        }
        if (!Objects.equals(this.lyDoGiamTru, other.lyDoGiamTru)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.objectId, other.objectId)) {
            return false;
        }
        if (!Objects.equals(this.idKhoa, other.idKhoa)) {
            return false;
        }
        if (!Objects.equals(this.idBoMon, other.idBoMon)) {
            return false;
        }
        if (!Objects.equals(this.tongSoTiet, other.tongSoTiet)) {
            return false;
        }
        if (!Objects.equals(this.soTietPhaiGiang, other.soTietPhaiGiang)) {
            return false;
        }
        if (!Objects.equals(this.soTietChuaHTNCKH, other.soTietChuaHTNCKH)) {
            return false;
        }
        if (!Objects.equals(this.soTietGiamTru, other.soTietGiamTru)) {
            return false;
        }
        if (!Objects.equals(this.soTietThanhToan, other.soTietThanhToan)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TongHop{" + "id=" + id + ", objectId=" + objectId + ", idKhoa=" + idKhoa + ", idBoMon=" + idBoMon + ", namHoc=" + namHoc + ", tongSoTiet=" + tongSoTiet + ", soTietPhaiGiang=" + soTietPhaiGiang + ", soTietChuaHTNCKH=" + soTietChuaHTNCKH + ", soTietGiamTru=" + soTietGiamTru + ", lyDoGiamTru=" + lyDoGiamTru + ", soTietThanhToan=" + soTietThanhToan + '}';
    }

    
    

   
}
