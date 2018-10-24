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
@Table(name = "tbd_bo_mon", schema = "gio_day_them")
public class BoMon implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "id_khoa")
    private Long idKhoa;

    @Column(name = "ten_bo_mon")
    private String tenBoMon;

    @Column(name = "mo_ta")
    private String moTa;

    private String tenKhoa;

    public BoMon() {
    }

       
    public BoMon(BoMon boMon, String tenKhoa) {
        this.id = boMon.getId();
        this.idKhoa = boMon.getIdKhoa();
        this.moTa = boMon.getMoTa();
        this.tenKhoa = tenKhoa;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdKhoa() {
        return idKhoa;
    }

    public void setIdKhoa(Long idKhoa) {
        this.idKhoa = idKhoa;
    }

    public String getTenKhoa() {
        return tenKhoa;
    }

    public void setTenKhoa(String tenKhoa) {
        this.tenKhoa = tenKhoa;
    }

    public String getTenBoMon() {
        return tenBoMon;
    }

    public void setTenBoMon(String tenBoMon) {
        this.tenBoMon = tenBoMon;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, idKhoa, tenBoMon, moTa);
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
        final BoMon other = (BoMon) obj;
        if (!Objects.equals(this.tenBoMon, other.tenBoMon)) {
            return false;
        }
        if (!Objects.equals(this.moTa, other.moTa)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.idKhoa, other.idKhoa)) {
            return false;
        }
        return true;
    }

}
