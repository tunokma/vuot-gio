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
    private Long idKhoa;
    private String tenMon;
    private Long dvhtTc;
    private String moTa;
    private String tenKhoa;

    public MonHocVM() {
    }

    public MonHocVM(MonHoc monHoc) {
        this.id = monHoc.getId();
        this.idKhoa = monHoc.getIdKhoa();
        this.tenMon = monHoc.getTenMon();
        this.moTa = monHoc.getMoTa();
        this.dvhtTc = monHoc.getDvhtTc();
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

    public String getTenKhoa() {
        return tenKhoa;
    }

    public void setTenKhoa(String tenBoMon) {
        this.tenKhoa = tenBoMon;
    }

    public Long getDvhtTc() {
        return dvhtTc;
    }

    public void setDvhtTc(Long dvhtTc) {
        this.dvhtTc = dvhtTc;
    }

}
