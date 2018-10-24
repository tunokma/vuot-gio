/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kma.quanlygiangday.viewmodel;

import com.kma.quanlygiangday.model.MonHoc;

/**
 *
 * @author Tuno
 */
public class MonHocVM {

    private Long id;
    private Long idBoMon;
    private String tenMon;
    private String moTa;
    private String tenBoMon;

    public MonHocVM() {
    }

    public MonHocVM(MonHoc monHoc) {
        this.id = monHoc.getId();
        this.idBoMon = monHoc.getIdBoMon();
        this.tenMon = monHoc.getTenMon();
        this.moTa = monHoc.getMoTa();
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

    public String getTenBoMon() {
        return tenBoMon;
    }

    public void setTenBoMon(String tenBoMon) {
        this.tenBoMon = tenBoMon;
    }

}
