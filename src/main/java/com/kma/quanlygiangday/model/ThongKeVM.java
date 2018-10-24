/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kma.quanlygiangday.model;

import com.kma.quanlygiangday.utils.Constants;

/**
 *
 * @author Tuno
 */
public class ThongKeVM {

    private String tenKhoa;
    private String tenBoMon;
    private String tenGV;
    private String namHoc;
    private Long dinhMucGioGiang;
    private Long gioNCKH;
    private Long duocGiam;
    private Long dinhMucPhaiGiang;

    //thực tế giảng dạy
    private Long hK1_MM;
    private Long hK1_AT;
    private Long hK2_MM;
    private Long hK2_AT;
    private Long caNam_MM;
    private Long caNam_AT;
    private Long caNam_Sum;

    //vượt định mức
    private Long vuotDM_MM;
    private Long vuotDM_AT;
    private Long vuotDM_CaNam;

    private Long mucTTChuan;

    //thành tiền
    private Long thanhTien_MM;
    private Long thanhTien_AT;
    private Long thanhTien_Sum;
    private Long thue;
    private Long thucLinh;

    private Long stt;

    public ThongKeVM() {
    }

    public void fromTongHop(TongHop tongHop) {
        if (tongHop != null) {
            if (tongHop.getTongSoTiet() != null) {
                this.setCaNam_Sum(tongHop.getTongSoTiet());
            }
            if (tongHop.getSoTietPhaiGiang() != null) {
                this.setDinhMucPhaiGiang(tongHop.getSoTietPhaiGiang());
            }
            if (tongHop.getSoTietChuaHTNCKH() != null) {
                this.setGioNCKH(Constants.SO_TIET.SO_TIET_NCKH - tongHop.getSoTietChuaHTNCKH());
            }
            if (tongHop.getSoTietGiamTru() != null) {
                this.setDuocGiam(tongHop.getSoTietGiamTru());
            }
            if (tongHop.getSoTietThanhToan() != null) {
                this.setVuotDM_CaNam(tongHop.getSoTietThanhToan());
            }

            if (tongHop.getNamHoc() != null) {
                this.setNamHoc(tongHop.getNamHoc());
            }

        }

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

    public String getTenGV() {
        return tenGV;
    }

    public void setTenGV(String tenGV) {
        this.tenGV = tenGV;
    }

    public String getNamHoc() {
        return namHoc;
    }

    public void setNamHoc(String namHoc) {
        this.namHoc = namHoc;
    }

    public Long getDinhMucGioGiang() {
        return dinhMucGioGiang;
    }

    public void setDinhMucGioGiang(Long dinhMucGioGiang) {
        this.dinhMucGioGiang = dinhMucGioGiang;
    }

    public Long getGioNCKH() {
        return gioNCKH;
    }

    public void setGioNCKH(Long gioNCKH) {
        this.gioNCKH = gioNCKH;
    }

    public Long getDuocGiam() {
        return duocGiam;
    }

    public void setDuocGiam(Long duocGiam) {
        this.duocGiam = duocGiam;
    }

    public Long getDinhMucPhaiGiang() {
        return dinhMucPhaiGiang;
    }

    public void setDinhMucPhaiGiang(Long dinhMucPhaiGiang) {
        this.dinhMucPhaiGiang = dinhMucPhaiGiang;
    }

    public Long gethK1_MM() {
        return hK1_MM;
    }

    public void sethK1_MM(Long hK1_MM) {
        this.hK1_MM = hK1_MM;
    }

    public Long gethK1_AT() {
        return hK1_AT;
    }

    public void sethK1_AT(Long hK1_AT) {
        this.hK1_AT = hK1_AT;
    }

    public Long gethK2_MM() {
        return hK2_MM;
    }

    public void sethK2_MM(Long hK2_MM) {
        this.hK2_MM = hK2_MM;
    }

    public Long gethK2_AT() {
        return hK2_AT;
    }

    public void sethK2_AT(Long hK2_AT) {
        this.hK2_AT = hK2_AT;
    }

    public Long getCaNam_MM() {
        return caNam_MM;
    }

    public void setCaNam_MM(Long caNam_MM) {
        this.caNam_MM = caNam_MM;
    }

    public Long getCaNam_AT() {
        return caNam_AT;
    }

    public void setCaNam_AT(Long caNam_AT) {
        this.caNam_AT = caNam_AT;
    }

    public Long getCaNam_Sum() {
        return caNam_Sum;
    }

    public void setCaNam_Sum(Long caNam_Sum) {
        this.caNam_Sum = caNam_Sum;
    }

    public Long getVuotDM_MM() {
        return vuotDM_MM;
    }

    public void setVuotDM_MM(Long vuotDM_MM) {
        this.vuotDM_MM = vuotDM_MM;
    }

    public Long getVuotDM_AT() {
        return vuotDM_AT;
    }

    public void setVuotDM_AT(Long vuotDM_AT) {
        this.vuotDM_AT = vuotDM_AT;
    }

    public Long getVuotDM_CaNam() {
        return vuotDM_CaNam;
    }

    public void setVuotDM_CaNam(Long vuotDM_CaNam) {
        this.vuotDM_CaNam = vuotDM_CaNam;
    }

    public Long getMucTTChuan() {
        return mucTTChuan;
    }

    public void setMucTTChuan(Long mucTTChuan) {
        this.mucTTChuan = mucTTChuan;
    }

    public Long getThanhTien_MM() {
        return thanhTien_MM;
    }

    public void setThanhTien_MM(Long thanhTien_MM) {
        this.thanhTien_MM = thanhTien_MM;
    }

    public Long getThanhTien_AT() {
        return thanhTien_AT;
    }

    public void setThanhTien_AT(Long thanhTien_AT) {
        this.thanhTien_AT = thanhTien_AT;
    }

    public Long getThanhTien_Sum() {
        return thanhTien_Sum;
    }

    public void setThanhTien_Sum(Long thanhTien_Sum) {
        this.thanhTien_Sum = thanhTien_Sum;
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

    public Long getStt() {
        return stt;
    }

    public void setStt(Long stt) {
        this.stt = stt;
    }

    private void setLaiDinhMucPhaiGiang() {
        this.dinhMucPhaiGiang = this.dinhMucGioGiang - this.duocGiam;
    }

    private void setThucTeGiangDay() {
        this.caNam_AT = this.hK1_AT + this.hK2_AT;
        this.caNam_MM = this.hK1_MM + this.hK2_MM;
        this.caNam_Sum = this.caNam_AT + this.caNam_MM;
    }

    private void setVuotDinhMuc() {
        this.vuotDM_MM = (this.caNam_MM - this.dinhMucPhaiGiang) > 0 ? (this.caNam_MM - this.dinhMucPhaiGiang) : 0l;
        this.vuotDM_AT = (this.caNam_Sum - this.dinhMucPhaiGiang - this.vuotDM_MM) > 0 ? (this.caNam_Sum - this.dinhMucPhaiGiang - this.vuotDM_MM) : 0l;
        this.vuotDM_CaNam = this.vuotDM_AT + this.vuotDM_MM;
    }

    public void setThanhTien() {
        this.thanhTien_MM = this.vuotDM_MM * this.mucTTChuan;
        this.thanhTien_AT = this.vuotDM_AT * this.mucTTChuan;
        this.thanhTien_Sum = this.thanhTien_MM + this.thanhTien_AT;
        this.thue = Math.round(this.thanhTien_Sum * 0.1);
        this.thucLinh = this.thanhTien_Sum - this.thue;
    }

    public void setTongTien() {
        this.thanhTien_Sum = this.vuotDM_CaNam * this.mucTTChuan;
        this.thue = Math.round(this.thanhTien_Sum * 0.1);
        this.thucLinh = this.thanhTien_Sum - this.thue;
    }

    public void setLaiThuocTinh() {
        if (this.hK1_AT == null) {
            hK1_AT = 0l;
        }
        if (this.hK1_MM == null) {
            hK1_MM = 0l;
        }
        if (this.hK2_AT == null) {
            hK2_AT = 0l;
        }
        if (this.hK2_MM == null) {
            hK2_MM = 0l;
        }
        this.setLaiDinhMucPhaiGiang();
        this.setThucTeGiangDay();
        this.setVuotDinhMuc();
        this.setThanhTien();
    }
}
