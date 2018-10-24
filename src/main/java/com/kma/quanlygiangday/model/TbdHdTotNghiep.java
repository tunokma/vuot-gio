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
@Table(name = "tbd_hd_tot_nghiep", schema = "gio_day_them")
public class TbdHdTotNghiep implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id",unique = true, nullable = false)
    private Long id;
    @Column(name = "object_id")
    private Long objectId;
    @Size(max = 250)
    @Column(name = "ten_sv")
    private String tenSv;
    @Size(max = 45)
    @Column(name = "khoa_dt")
    private String khoaDt;
    @Column(name = "so_nguoit_hd")
    private Long soNguoitHd;
    @Column(name = "so_tiet_qd")
    private Long soTietQd;
    @Column(name = "is_deleted")
    private Long isDeleted;
    @Column(name = "nam_hoc")
    private String namHoc;

    public TbdHdTotNghiep() {
    }

    public TbdHdTotNghiep(Long id) {
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

    public String getNamHoc() {
        return namHoc;
    }

    public void setNamHoc(String namHoc) {
        this.namHoc = namHoc;
    }

    
    
    public String getTenSv() {
        return tenSv;
    }

    public void setTenSv(String tenSv) {
        this.tenSv = tenSv;
    }

    public String getKhoaDt() {
        return khoaDt;
    }

    public void setKhoaDt(String khoaDt) {
        this.khoaDt = khoaDt;
    }

    public Long getSoNguoitHd() {
        return soNguoitHd;
    }

    public void setSoNguoitHd(Long soNguoitHd) {
        this.soNguoitHd = soNguoitHd;
    }

    public Long getSoTietQd() {
        return soTietQd;
    }

    public void setSoTietQd(Long soTietQd) {
        this.soTietQd = soTietQd;
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
        if (!(object instanceof TbdHdTotNghiep)) {
            return false;
        }
        TbdHdTotNghiep other = (TbdHdTotNghiep) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kma.quanlygiangday.model.TbdHdTotNghiep[ id=" + id + " ]";
    }
    
}
