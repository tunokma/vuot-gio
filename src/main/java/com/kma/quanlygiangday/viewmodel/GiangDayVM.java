/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kma.quanlygiangday.viewmodel;

import com.kma.quanlygiangday.model.TbdGiangDay;
import com.kma.quanlygiangday.utils.Constants;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author Tuno
 */
public class GiangDayVM {
    private Integer stt;
    private Long id;
    private Long objectId;
    private String hkCn;
    private String tenMon;
    private Long dvhtTc;
    private String lop;
    private String khoaDt;
    private Long soTietQc;
    private Long tietTg;
    private String namHoc;
    private Long isDeleted;
    private String hkCnString;
    Map<String, String> hkCnMap = new HashMap<>();

    public void loadMap() {
        hkCnMap.put(Constants.HocKy_ChuyenNganh.HOC_KY_1_AT, Constants.HocKy_ChuyenNganh.HOC_KY_1_AT_TEXT);
        hkCnMap.put(Constants.HocKy_ChuyenNganh.HOC_KY_1_MM, Constants.HocKy_ChuyenNganh.HOC_KY_1_MM_TEXT);
        hkCnMap.put(Constants.HocKy_ChuyenNganh.HOC_KY_2_AT, Constants.HocKy_ChuyenNganh.HOC_KY_2_AT_TEXT);
        hkCnMap.put(Constants.HocKy_ChuyenNganh.HOC_KY_2_MM, Constants.HocKy_ChuyenNganh.HOC_KY_2_MM_TEXT);
    }

    public GiangDayVM() {
    }

    public GiangDayVM(Long id, Long objectId, String hkCn, String tenMon, Long dvhtTc, String lop, String khoaDt, Long soTietQc, Long tietTg, String namHoc, Long isDeleted, String hkCnString) {
        this.id = id;
        this.objectId = objectId;
        this.hkCn = hkCn;
        this.tenMon = tenMon;
        this.dvhtTc = dvhtTc;
        this.lop = lop;
        this.khoaDt = khoaDt;
        this.soTietQc = soTietQc;
        this.tietTg = tietTg;
        this.namHoc = namHoc;
        this.isDeleted = isDeleted;
        this.hkCnString = hkCnString;
    }

    public GiangDayVM(TbdGiangDay giangDay) {
        if (null != giangDay.getId()) {
            this.id = giangDay.getId();
        }
        if (null != giangDay.getObjectId()) {
            this.objectId = giangDay.getObjectId();
        }

        if (null != giangDay.getHkCn()) {
            this.hkCn = giangDay.getHkCn();
            this.hkCnString = hkCnMap.get(giangDay.getHkCn());
        }

        if (null != giangDay.getTenMon()) {
            this.tenMon = giangDay.getTenMon();
        }

        if (null != giangDay.getDvhtTc()) {
            this.dvhtTc = giangDay.getDvhtTc();
        }

        if (null != giangDay.getLop()) {
            this.lop = giangDay.getLop();
        }

        if (null != giangDay.getKhoaDt()) {
            this.khoaDt = giangDay.getKhoaDt();
        }

        if (null != giangDay.getSoTietQc()) {
            this.soTietQc = giangDay.getSoTietQc();
        }

        if (null != giangDay.getTietTg()) {
            this.tietTg = giangDay.getTietTg();
        }

        if (null != giangDay.getNamHoc()) {
            this.namHoc = giangDay.getNamHoc();
        }

        if (null != giangDay.getIsDeleted()) {
            this.isDeleted = giangDay.getIsDeleted();
        }

    }

    public Integer getStt() {
        return stt;
    }

    public void setStt(Integer stt) {
        this.stt = stt;
    }

    public Map<String, String> getHkCnMap() {
        return hkCnMap;
    }

    public void setHkCnMap(Map<String, String> hkCnMap) {
        this.hkCnMap = hkCnMap;
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

    public String getHkCn() {
        return hkCn;
    }

    public void setHkCn(String hkCn) {
        this.hkCn = hkCn;
    }

    public String getTenMon() {
        return tenMon;
    }

    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
    }

    public Long getDvhtTc() {
        return dvhtTc;
    }

    public void setDvhtTc(Long dvhtTc) {
        this.dvhtTc = dvhtTc;
    }

    public String getLop() {
        return lop;
    }

    public void setLop(String lop) {
        this.lop = lop;
    }

    public String getKhoaDt() {
        return khoaDt;
    }

    public void setKhoaDt(String khoaDt) {
        this.khoaDt = khoaDt;
    }

    public Long getSoTietQc() {
        return soTietQc;
    }

    public void setSoTietQc(Long soTietQc) {
        this.soTietQc = soTietQc;
    }

    public Long getTietTg() {
        return tietTg;
    }

    public void setTietTg(Long tietTg) {
        this.tietTg = tietTg;
    }

    public String getNamHoc() {
        return namHoc;
    }

    public void setNamHoc(String namHoc) {
        this.namHoc = namHoc;
    }

    public Long getIsDeleted() {
        return isDeleted;
    }

    public void setIsDeleted(Long isDeleted) {
        this.isDeleted = isDeleted;
    }

    public String getHkCnString() {
        return hkCnString;
    }

    public void setHkCnString(String hkCnString) {
        this.hkCnString = hkCnString;
    }

}
