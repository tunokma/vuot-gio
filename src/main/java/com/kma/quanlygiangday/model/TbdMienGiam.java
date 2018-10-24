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
@Table(name = "tbd_mien_giam", schema = "gio_day_them")
public class TbdMienGiam implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;
    @Size(max = 250)
    @Column(name = "doi_tuong")
    private String doiTuong;
    @Column(name = "ty_le")
    private Long tyLe;
    @Column(name = "so_tiet_giam")
    private Long soTietGiam;
    @Column(name = "is_deleted")
    private Long isDeleted;

    public TbdMienGiam() {
    }

    public TbdMienGiam(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDoiTuong() {
        return doiTuong;
    }

    public void setDoiTuong(String doiTuong) {
        this.doiTuong = doiTuong;
    }

    public Long getTyLe() {
        return tyLe;
    }

    public void setTyLe(Long tyLe) {
        this.tyLe = tyLe;
    }

    public Long getSoTietGiam() {
        return soTietGiam;
    }

    public void setSoTietGiam(Long soTietGiam) {
        this.soTietGiam = soTietGiam;
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
        if (!(object instanceof TbdMienGiam)) {
            return false;
        }
        TbdMienGiam other = (TbdMienGiam) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.kma.quanlygiangday.model.TbdMienGiam[ id=" + id + " ]";
    }

}
