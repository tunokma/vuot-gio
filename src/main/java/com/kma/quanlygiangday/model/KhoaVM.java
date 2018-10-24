/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kma.quanlygiangday.model;

import java.util.List;

/**
 *
 * @author Tuno
 */
public class KhoaVM {

    private String tenKhoaVM;
    private List<BoMonVM> lstBoMon;
    private Long tong;
    private Long thue;
    private Long thucLinh;

    public KhoaVM() {
        
    }

    public KhoaVM(String tenKhoaVM, List<BoMonVM> lstBoMon) {
        this.tenKhoaVM = tenKhoaVM;
        this.lstBoMon = lstBoMon;
    }

    public void tinhToan() {
        this.tong = 0l;
        this.lstBoMon.forEach((boMonVM) -> {
            this.tong += boMonVM.getTong();
        });
        this.thue = this.tong * 10 / 100;
        this.thucLinh = this.tong - this.thue;
    }

    public Long getTong() {
        return tong;
    }

    public void setTong(Long tong) {
        this.tong = tong;
    }

    public Long getThue() {
        return thue;
    }

    public void setThue(Long thue) {
        this.thue = thue;
    }

    public Long getThucLinh() {
        return thucLinh;
    }

    public void setThucLinh(Long thucLinh) {
        this.thucLinh = thucLinh;
    }

    public String getTenKhoaVM() {
        return tenKhoaVM;
    }

    public void setTenKhoaVM(String tenKhoaVM) {
        this.tenKhoaVM = tenKhoaVM;
    }

    public List<BoMonVM> getLstBoMon() {
        return lstBoMon;
    }

    public void setLstBoMon(List<BoMonVM> lstBoMon) {
        this.lstBoMon = lstBoMon;
    }

}
