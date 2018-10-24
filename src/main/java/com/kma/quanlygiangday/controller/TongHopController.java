/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kma.quanlygiangday.controller;

import com.kma.quanlygiangday.model.GiangVien;
import com.kma.quanlygiangday.model.TbdMienGiam;
import com.kma.quanlygiangday.model.TbdNckh;
import com.kma.quanlygiangday.model.TongHop;
import com.kma.quanlygiangday.service.BoMonService;
import com.kma.quanlygiangday.service.GiangDayService;
import com.kma.quanlygiangday.service.GiangVienService;
import com.kma.quanlygiangday.service.HdTotNghiepService;
import com.kma.quanlygiangday.service.MienGiamService;
import com.kma.quanlygiangday.service.NCKHService;
import com.kma.quanlygiangday.service.TongHopService;
import com.kma.quanlygiangday.utils.Constants;
import com.kma.quanlygiangday.utils.DateUtil;
import java.util.HashMap;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Tuno
 */
@Controller
@ComponentScan
public class TongHopController {

    private static final Logger logger = LoggerFactory.getLogger(TongHopController.class);
    @Autowired
    GiangDayService giangDayService;

    @Autowired
    GiangVienService giangVienService;

    @Autowired
    BoMonService boMonService;

    @Autowired
    HdTotNghiepService hdTotNghiepService;

    @Autowired
    NCKHService nckhService;

    @Autowired
    TongHopService tongHopService;

    @Autowired
    MienGiamService mienGiamService;

    Map<String, Long> soTietDMMap = new HashMap<>();

    public void loadMap() {
        soTietDMMap.put(Constants.DINH_MUC_GIANG_DAY.CHUYEN_MON, Constants.SO_TIET.CHUYEN_MON);
        soTietDMMap.put(Constants.DINH_MUC_GIANG_DAY.CO_BAN, Constants.SO_TIET.CO_BAN);
        soTietDMMap.put(Constants.DINH_MUC_GIANG_DAY.CHUYEN_MON_THAI_SAN, Constants.SO_TIET.CHUYEN_MON_THAI_SAN);
        soTietDMMap.put(Constants.DINH_MUC_GIANG_DAY.CO_BAN_THAI_SAN, Constants.SO_TIET.CO_BAN_THAI_SAN);
    }

    public Boolean checkTongHop(TongHop tongHop) {

        return true;
    }

    @RequestMapping("/giangVien/{objectId}/statistic")
    public String statistic(@PathVariable Long objectId, Model model) {
        String namHocNay = DateUtil.getNamHoc(DateUtil.now(), 0);
        loadMap();
        TongHop tongHop = tongHopService.findByObjectId(objectId, namHocNay);

        GiangVien gv = giangVienService.findById(objectId);

        tongHop.setObjectId(gv.getId());
        tongHop.setIdBoMon(gv.getIdBoMon());
        tongHop.setIdKhoa(boMonService.findById(gv.getIdBoMon()).getIdKhoa());
        tongHop.setNamHoc(namHocNay);

        //Số tiết giảm trừ
        Long soTietGiamTru = 0l;
        String giamTruString = gv.getGiamTru();
        Long dinhMucGG = getDinhMucGioGiang(objectId);
        String[] lstGiamTru = giamTruString.split(",");
        for (int i = 0; i < lstGiamTru.length; i++) {
            TbdMienGiam mienGiam = mienGiamService.findById(Long.parseLong(lstGiamTru[i]));
            if (mienGiam.getSoTietGiam() != null) {
                soTietGiamTru += mienGiam.getSoTietGiam();
            }
            if (mienGiam.getTyLe() != null) {
                soTietGiamTru += Math.round(dinhMucGG * mienGiam.getTyLe() / 100);
            }
            if (soTietGiamTru >= Math.round(dinhMucGG * 0.5)) {
                soTietGiamTru = Math.round(dinhMucGG * 0.5);
            }
        }
        tongHop.setSoTietGiamTru(soTietGiamTru);

        //Số tiết đã thực hiện
        Long soTietA = giangDayService.getSoTietDaDay(objectId, namHocNay);
        Long soTietB = hdTotNghiepService.getSoTietByObjectId(objectId, namHocNay);
        tongHop.setTongSoTiet(soTietA + soTietB);

        //Số tiết phải giảng
        Long soTietPhaiGiang = soTietDMMap.get(gv.getDinhMucGG());
        tongHop.setSoTietPhaiGiang(soTietPhaiGiang);

        //Số giờ chưa hoàn thành NCKH (Tính tiết)
        Long soTietChuaNCKH = Constants.SO_TIET.SO_TIET_NCKH;
        TbdNckh nckh = (nckhService.findByObjectId(objectId, namHocNay) != null ? nckhService.findByObjectId(objectId, namHocNay).get(0) : null);
        if (nckh != null) {
            soTietChuaNCKH = nckh.getSoTietChuaThucHien();
        }

        tongHop.setSoTietChuaHTNCKH(soTietChuaNCKH);

        //Tổng số tiết vượt giờ
        Long soTietVuotGio = soTietA + soTietB - soTietPhaiGiang - soTietChuaNCKH + soTietGiamTru;
        tongHop.setSoTietThanhToan(soTietVuotGio >= 0 ? soTietVuotGio : 0);

        tongHopService.save(tongHop);

        model.addAttribute("tongHops", tongHop);
        model.addAttribute("objectId", objectId);
        return "tongHop";
    }

    public Long getDinhMucGioGiang(Long id) {
        loadMap();
        return soTietDMMap.get(giangVienService.findById(id).getDinhMucGG());
    }
}
