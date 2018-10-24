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
@Table(name = "tbd_nckh", schema = "gio_day_them")
public class TbdNckh implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @Column(name = "object_id")
    private Long objectId;
    @Size(max = 500)
    @Column(name = "noi_dung")
    private String noiDung;
    @Column(name = "so_tiet_thuc_hien")
    private Long soTietThucHien;
    @Column(name = "so_tiet_chua_thuc_hien")
    private Long soTietChuaThucHien;
    @Column(name = "is_deleted")
    private Long isDeleted;
    @Column(name = "nam_hoc")
    private String namHoc;

    public TbdNckh() {
    }

    public TbdNckh(Long id) {
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

    public String getNoiDung() {
        return noiDung;
    }

    public void setNoiDung(String noiDung) {
        this.noiDung = noiDung;
    }

    public String getNamHoc() {
        return namHoc;
    }

    public void setNamHoc(String namHoc) {
        this.namHoc = namHoc;
    }

    public Long getSoTietThucHien() {
        return soTietThucHien;
    }

    public void setSoTietThucHien(Long soTietThucHien) {
        this.soTietThucHien = soTietThucHien;
    }

    public Long getSoTietChuaThucHien() {
        return soTietChuaThucHien;
    }

    public void setSoTietChuaThucHien(Long soTietChuaThucHien) {
        this.soTietChuaThucHien = soTietChuaThucHien;
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
        if (!(object instanceof TbdNckh)) {
            return false;
        }
        TbdNckh other = (TbdNckh) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kma.quanlygiangday.model.TbdNckh[ id=" + id + " ]";
    }

}
