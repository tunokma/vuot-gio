/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kma.quanlygiangday.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;    
import javax.persistence.Table;
import javax.validation.constraints.Size;

/**
 *
 * @author Tuno
 */
@Entity
@Table(name = "tbd_giang_day", schema = "gio_day_them")
public class TbdGiangDay implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @Column(name = "object_id")
    private Long objectId;
    @Size(max = 5)
    @Column(name = "hk_cn")
    private String hkCn;
    @Size(max = 250)
    @Column(name = "ten_mon")
    private String tenMon;
    @Column(name = "dvht_tc")
    private Long dvhtTc;
    @Size(max = 45)
    @Column(name = "lop")
    private String lop;
    @Size(max = 45)
    @Column(name = "khoa_dt")
    private String khoaDt;
    @Column(name = "so_tiet_qc")
    private Long soTietQc;
    @Column(name = "tiet_tg")
    private Long tietTg;
    @Size(max = 45)
    @Column(name = "nam_hoc")
    private String namHoc;
    @Column(name = "is_deleted")
    private Long isDeleted;

    public TbdGiangDay() {
    }

    public TbdGiangDay(Long id) {
        this.id = id;
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

    public String getHkCn() {
        return hkCn;
    }

    public void setHkCn(String hkCn) {
        this.hkCn = hkCn;
    }

    public String getTenMon() {
        return tenMon;
    }

    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
    }

    public Long getDvhtTc() {
        return dvhtTc;
    }

    public void setDvhtTc(Long dvhtTc) {
        this.dvhtTc = dvhtTc;
    }

    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }

    public String getKhoaDt() {
        return khoaDt;
    }

    public void setKhoaDt(String khoaDt) {
        this.khoaDt = khoaDt;
    }

    public Long getSoTietQc() {
        return soTietQc;
    }

    public void setSoTietQc(Long soTietQc) {
        this.soTietQc = soTietQc;
    }

    public Long getTietTg() {
        return tietTg;
    }

    public void setTietTg(Long tietTg) {
        this.tietTg = tietTg;
    }

    public String getNamHoc() {
        return namHoc;
    }

    public void setNamHoc(String namHoc) {
        this.namHoc = namHoc;
    }

    public Long getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Long isDeleted) {
        this.isDeleted = isDeleted;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TbdGiangDay)) {
            return false;
        }
        TbdGiangDay other = (TbdGiangDay) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kma.quanlygiangday.model.TbdGiangDay[ id=" + id + " ]";
    }
    
}
