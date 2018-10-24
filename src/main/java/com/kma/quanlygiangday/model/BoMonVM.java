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
public class BoMonVM {

    private String tenKhoaVM;
    private String tenBoMonVM;
    private List<ThongKeVM> lstThongKe;
    private Long tong;
    private Long thue;
    private Long thucLinh;

    public BoMonVM() {
    }

    public BoMonVM(String tenKhoaVM, String tenBoMonVM, List<ThongKeVM> lstThongKe) {
        this.tenKhoaVM = tenKhoaVM;
        this.tenBoMonVM = tenBoMonVM;
        this.lstThongKe = lstThongKe;
    }

    public void tinhToan() {
        this.tong = 0l;
        this.lstThongKe.forEach((thongKeVM) -> {
            this.tong += thongKeVM.getThanhTien_Sum();
        });
        this.thue = this.tong * 10 / 100;
        this.thucLinh = this.tong - this.thue;
    }

    public String getTenKhoaVM() {
        return tenKhoaVM;
    }

    public void setTenKhoaVM(String tenKhoaVM) {
        this.tenKhoaVM = tenKhoaVM;
    }

    public String getTenBoMonVM() {
        return tenBoMonVM;
    }

    public void setTenBoMonVM(String tenBoMonVM) {
        this.tenBoMonVM = tenBoMonVM;
    }

    public List<ThongKeVM> getLstThongKe() {
        return lstThongKe;
    }

    public void setLstThongKe(List<ThongKeVM> lstThongKe) {
        this.lstThongKe = lstThongKe;
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

}
