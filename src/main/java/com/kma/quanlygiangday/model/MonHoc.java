/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kma.quanlygiangday.model;

import com.kma.quanlygiangday.viewmodel.MonHocVM;
import java.io.Serializable;
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
@Table(name = "tbd_mon_hoc", schema = "gio_day_them")
public class MonHoc implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id", unique = true, nullable = false)
    private Long id;

    @Column(name = "id_bo_mon")
    private Long idBoMon;

    @Column(name = "ten_mon")
    private String tenMon;

    @Column(name = "mo_ta")
    private String moTa;

    public MonHoc() {
    }

    public MonHoc(MonHocVM monHocVM) {
        this.id = monHocVM.getId();
        this.idBoMon = monHocVM.getIdBoMon();
        this.tenMon = monHocVM.getTenMon();
        this.moTa = monHocVM.getMoTa();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdBoMon() {
        return idBoMon;
    }

    public void setIdBoMon(Long idBoMon) {
        this.idBoMon = idBoMon;
    }

    public String getTenMon() {
        return tenMon;
    }

    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
    }

    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

}
