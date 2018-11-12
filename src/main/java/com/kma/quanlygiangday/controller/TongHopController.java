/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kma.quanlygiangday.controller;

import com.kma.quanlygiangday.model.BoMon;
import com.kma.quanlygiangday.model.GiangVien;
import com.kma.quanlygiangday.model.Khoa;
import com.kma.quanlygiangday.model.TbdGiangDay;
import com.kma.quanlygiangday.model.TbdHdTotNghiep;
import com.kma.quanlygiangday.model.TbdMienGiam;
import com.kma.quanlygiangday.model.TbdNckh;
import com.kma.quanlygiangday.model.TongHop;
import com.kma.quanlygiangday.service.BoMonService;
import com.kma.quanlygiangday.service.GiangDayService;
import com.kma.quanlygiangday.service.GiangVienService;
import com.kma.quanlygiangday.service.HdTotNghiepService;
import com.kma.quanlygiangday.service.KhoaService;
import com.kma.quanlygiangday.service.MienGiamService;
import com.kma.quanlygiangday.service.NCKHService;
import com.kma.quanlygiangday.service.TongHopService;
import com.kma.quanlygiangday.utils.Constants;
import com.kma.quanlygiangday.utils.DateUtil;
import com.kma.quanlygiangday.viewmodel.GiangDayVM;
import com.kma.quanlygiangday.viewmodel.GiangVienVM;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.jsolve.templ4docx.core.Docx;
import pl.jsolve.templ4docx.variable.TableVariable;
import pl.jsolve.templ4docx.variable.TextVariable;
import pl.jsolve.templ4docx.variable.Variable;
import pl.jsolve.templ4docx.variable.Variables;

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
    ServletContext c;

    @Autowired
    GiangVienService giangVienService;

    @Autowired
    BoMonService boMonService;

    @Autowired
    KhoaService khoaService;

    @Autowired
    HdTotNghiepService hdTotNghiepService;

    @Autowired
    NCKHService nckhService;

    @Autowired
    TongHopService tongHopService;

    @Autowired
    MienGiamService mienGiamService;

    Map<String, Long> soTietDMMap = new HashMap<>();
    List<TbdGiangDay> listGiangDay = new ArrayList<>();
    List<TbdNckh> listNckh = new ArrayList<>();
    List<TbdHdTotNghiep> listHdTotNghiep = new ArrayList<>();

    //List phuc vu xuat word
    List<Variable> sttCol = new ArrayList<>();
    List<Variable> tenMonCol = new ArrayList<>();
    List<Variable> tcCol = new ArrayList<>();
    List<Variable> lopCol = new ArrayList<>();
    List<Variable> khoaCol = new ArrayList<>();
    List<Variable> tietQCCol = new ArrayList<>();
    List<Variable> tietTGCol = new ArrayList<>();

    List<GiangDayVM> lstGiangDayMM1 = new ArrayList<>();
    List<GiangDayVM> lstGiangDayMM2 = new ArrayList<>();
    List<GiangDayVM> lstGiangDayAT1 = new ArrayList<>();
    List<GiangDayVM> lstGiangDayAT2 = new ArrayList<>();
    Long giangVienId;
    String namHocNay;
    Map<String, String> namHocMap = new HashMap<>();

    public void loadMap() {
        soTietDMMap.put(Constants.DINH_MUC_GIANG_DAY.CHUYEN_MON, Constants.SO_TIET.CHUYEN_MON);
        soTietDMMap.put(Constants.DINH_MUC_GIANG_DAY.CO_BAN, Constants.SO_TIET.CO_BAN);
        soTietDMMap.put(Constants.DINH_MUC_GIANG_DAY.CHUYEN_MON_THAI_SAN, Constants.SO_TIET.CHUYEN_MON_THAI_SAN);
        soTietDMMap.put(Constants.DINH_MUC_GIANG_DAY.CO_BAN_THAI_SAN, Constants.SO_TIET.CO_BAN_THAI_SAN);
    }

    public void loadNamHoc() {
        namHocMap.clear();
        namHocMap.put(DateUtil.getNamHoc(DateUtil.now(), 4), DateUtil.getNamHoc(DateUtil.now(), 4));
        namHocMap.put(DateUtil.getNamHoc(DateUtil.now(), 3), DateUtil.getNamHoc(DateUtil.now(), 3));
        namHocMap.put(DateUtil.getNamHoc(DateUtil.now(), 2), DateUtil.getNamHoc(DateUtil.now(), 2));
        namHocMap.put(DateUtil.getNamHoc(DateUtil.now(), 1), DateUtil.getNamHoc(DateUtil.now(), 1));
        namHocMap.put(namHocNay, namHocNay);
    }

    public Boolean checkTongHop(TongHop tongHop) {

        return true;
    }

    @RequestMapping("/giangVien/{objectId}/statistic")
    public String statistic(@PathVariable Long objectId, Model model) {
        namHocNay = DateUtil.getNamHoc(DateUtil.now(), 0);
        loadMap();
        loadNamHoc();
        //Lay danh sach cac thuoc tinh cua giang vien
        listGiangDay = giangDayService.findByObjectId(objectId, namHocNay);
        listNckh = nckhService.findByObjectId(objectId, namHocNay);
        listHdTotNghiep = hdTotNghiepService.findByObjectId(objectId, namHocNay);
        giangVienId = objectId;
        TongHop tongHop = tongHopService.findByObjectId(objectId, namHocNay);

        GiangVien gv = giangVienService.findById(objectId);

        tongHop.setObjectId(gv.getId());
        tongHop.setIdBoMon(gv.getIdBoMon());
        tongHop.setIdKhoa(boMonService.findById(gv.getIdBoMon()).getIdKhoa());
        tongHop.setNamHoc(namHocNay);

        //Số tiết giảm trừ
        Long soTietGiamTru = 0l;
        String giamTruString = gv.getGiamTru();
        if (giamTruString != null) {
            String[] lstGiamTru = giamTruString.split(",");
            Long dinhMucGG = getDinhMucGioGiang(objectId);
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
        }
        tongHop.setSoTietGiamTru(soTietGiamTru);

        //Số tiết đã thực hiện
        Long soTietA = giangDayService.getSoTietDaDay(objectId, namHocNay) != null ? giangDayService.getSoTietDaDay(objectId, namHocNay) : 0l;
        Long soTietB = hdTotNghiepService.getSoTietByObjectId(objectId, namHocNay) != null ? hdTotNghiepService.getSoTietByObjectId(objectId, namHocNay) : 0l;
        tongHop.setTongSoTiet(soTietA + soTietB);

        //Số tiết phải giảng
        Long soTietPhaiGiang = soTietDMMap.get(gv.getDinhMucGG()) != null ? soTietDMMap.get(gv.getDinhMucGG()) : 0l;
        tongHop.setSoTietPhaiGiang(soTietPhaiGiang);

        //Số giờ chưa hoàn thành NCKH (Tính tiết)
        Long soTietChuaNCKH = Constants.SO_TIET.SO_TIET_NCKH;
        List<TbdNckh> lstNckhs = nckhService.findByObjectId(objectId, namHocNay);
        if (null != lstNckhs && !lstNckhs.isEmpty()) {
            TbdNckh nckh = lstNckhs.get(0);
            if (nckh != null) {
                soTietChuaNCKH = nckh.getSoTietChuaThucHien();
            }
        }

        tongHop.setSoTietChuaHTNCKH(soTietChuaNCKH);

        //Tổng số tiết vượt giờ
        Long soTietVuotGio = soTietA + soTietB - soTietPhaiGiang - soTietChuaNCKH + soTietGiamTru;
        tongHop.setSoTietThanhToan(soTietVuotGio >= 0 ? soTietVuotGio : 0);

        tongHopService.save(tongHop);
        model.addAttribute("namHocMap", namHocMap);
        model.addAttribute("tongHops", tongHop);
        model.addAttribute("objectId", objectId);
        model.addAttribute("namHocNay", namHocNay);
        return "tongHop";
    }

    @GetMapping("/giangVien/{objectId}/statistic/search")
    public String search(@RequestParam("namHoc") String namHoc,@PathVariable Long objectId, Model model) {
        if ("".equals(namHoc)) {
            return "redirect:/tongHop";
        }
        namHocNay = namHoc;
        giangVienId = objectId;
        TongHop tongHop = tongHopService.findByObjectId(objectId, namHocNay);
        GiangVien gv = giangVienService.findById(objectId);
        //Lay danh sach cac thuoc tinh cua giang vien
        listGiangDay = giangDayService.findByObjectId(objectId, namHocNay);
        listNckh = nckhService.findByObjectId(objectId, namHocNay);
        listHdTotNghiep = hdTotNghiepService.findByObjectId(objectId, namHocNay);
        
        tongHop.setObjectId(gv.getId());
        tongHop.setIdBoMon(gv.getIdBoMon());
        tongHop.setIdKhoa(boMonService.findById(gv.getIdBoMon()).getIdKhoa());
        tongHop.setNamHoc(namHocNay);
        //Số tiết giảm trừ
        Long soTietGiamTru = 0l;
        String giamTruString = gv.getGiamTru();
        if (giamTruString != null) {
            String[] lstGiamTru = giamTruString.split(",");
            Long dinhMucGG = getDinhMucGioGiang(objectId);
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
        }
        tongHop.setSoTietGiamTru(soTietGiamTru);

        //Số tiết đã thực hiện
        Long soTietA = giangDayService.getSoTietDaDay(objectId, namHocNay) != null ? giangDayService.getSoTietDaDay(objectId, namHocNay) : 0l;
        Long soTietB = hdTotNghiepService.getSoTietByObjectId(objectId, namHocNay) != null ? hdTotNghiepService.getSoTietByObjectId(objectId, namHocNay) : 0l;
        tongHop.setTongSoTiet(soTietA + soTietB);

        //Số tiết phải giảng
        Long soTietPhaiGiang = soTietDMMap.get(gv.getDinhMucGG()) != null ? soTietDMMap.get(gv.getDinhMucGG()) : 0l;
        tongHop.setSoTietPhaiGiang(soTietPhaiGiang);

        //Số giờ chưa hoàn thành NCKH (Tính tiết)
        Long soTietChuaNCKH = Constants.SO_TIET.SO_TIET_NCKH;
        List<TbdNckh> lstNckhs = nckhService.findByObjectId(objectId, namHocNay);
        if (null != lstNckhs && !lstNckhs.isEmpty()) {
            TbdNckh nckh = lstNckhs.get(0);
            if (nckh != null) {
                soTietChuaNCKH = nckh.getSoTietChuaThucHien();
            }
        }

        tongHop.setSoTietChuaHTNCKH(soTietChuaNCKH);

        //Tổng số tiết vượt giờ
        Long soTietVuotGio = soTietA + soTietB - soTietPhaiGiang - soTietChuaNCKH + soTietGiamTru;
        tongHop.setSoTietThanhToan(soTietVuotGio >= 0 ? soTietVuotGio : 0);

        tongHopService.save(tongHop);
        model.addAttribute("namHocMap", namHocMap);
        model.addAttribute("tongHops", tongHop);
        model.addAttribute("objectId", objectId);
        model.addAttribute("namHocNay", namHoc);
        return "tongHop";
    }

    @RequestMapping(value = "/giangVien/{objectId}/statistic/exportWord", method = RequestMethod.GET)
    public void baoCao(HttpServletResponse response) {
        try {

            if (null != listGiangDay && !listGiangDay.isEmpty()) {
                lstGiangDayMM1.clear();
                lstGiangDayAT1.clear();
                lstGiangDayMM2.clear();
                lstGiangDayAT2.clear();
                for (TbdGiangDay tbdGiangDay : listGiangDay) {
                    GiangDayVM temp = new GiangDayVM(tbdGiangDay);
                    if (temp.getHkCn().equals(Constants.HocKy_ChuyenNganh.HOC_KY_1_MM)) {
                        temp.setStt(lstGiangDayMM1.size() + 1);
                        lstGiangDayMM1.add(temp);
                    }
                    if (temp.getHkCn().equals(Constants.HocKy_ChuyenNganh.HOC_KY_1_AT)) {
                        temp.setStt(lstGiangDayAT1.size() + 1);
                        lstGiangDayAT1.add(temp);
                    }
                    if (temp.getHkCn().equals(Constants.HocKy_ChuyenNganh.HOC_KY_2_MM)) {
                        temp.setStt(lstGiangDayMM2.size() + 1);
                        lstGiangDayMM2.add(temp);
                    }
                    if (temp.getHkCn().equals(Constants.HocKy_ChuyenNganh.HOC_KY_2_AT)) {
                        temp.setStt(lstGiangDayAT2.size() + 1);
                        lstGiangDayAT2.add(temp);
                    }
                }
            }
            onExportWord(giangVienId, response);
        } catch (Exception ex) {

        }
    }

    public void onExportWord(Long objectId, HttpServletResponse response) {
        Docx docx = new Docx(c.getRealPath("/temp/baoCao1.docx"));
        Variables var = new Variables();
        GiangVien gv = giangVienService.findById(objectId);

        //Add cac thuoc tinh cua tieu de
        BoMon boMon = boMonService.findById(gv.getIdBoMon());
        Khoa khoa = khoaService.findById(boMon.getIdKhoa());
        var.addTextVariable(new TextVariable("${khoa}", khoa.getTenKhoa()));
        var.addTextVariable(new TextVariable("${boMon}", boMon.getTenBoMon()));
        String ngayHomNay = "ngày " + DateUtil.getDay(DateUtil.now()) + " tháng " + DateUtil.getMonth(DateUtil.now()) + " năm " + DateUtil.getYear(DateUtil.now());
        var.addTextVariable(new TextVariable("${ngay}", ngayHomNay));
        var.addTextVariable(new TextVariable("${chuNhiemKhoa}", findChuNhiemKhoa(khoa.getId())));
        var.addTextVariable(new TextVariable("${chuNhiemBoMon}", findChuNhiemBoMon(boMon.getId())));
        var.addTextVariable(new TextVariable("${namHoc}", namHocNay));

        //Add cac thuoc tinh cua giang vien
        Map<Long, String> hocHamMap = new HashMap<>();
        hocHamMap.clear();
        hocHamMap.put(Constants.HocHam.CU_NHAN, Constants.HocHam.CU_NHAN_TEXT);
        hocHamMap.put(Constants.HocHam.KY_SU, Constants.HocHam.KY_SU_TEXT);
        hocHamMap.put(Constants.HocHam.THAC_SY, Constants.HocHam.THAC_SY_TEXT);
        hocHamMap.put(Constants.HocHam.TIEN_SI, Constants.HocHam.TIEN_SI_TEXT);
        hocHamMap.put(Constants.HocHam.PGS_TIEN_SI, Constants.HocHam.PGS_TIEN_SI_TEXT);
        hocHamMap.put(Constants.HocHam.GS_TIEN_SI, Constants.HocHam.GS_TIEN_SI_TEXT);
        var.addTextVariable(new TextVariable("${hoTen}", gv.getHoTen()));
        var.addTextVariable(new TextVariable("${namSinh}", String.valueOf(gv.getNamSinh())));
        var.addTextVariable(new TextVariable("${chucVu}", gv.getChucVu()));
        var.addTextVariable(new TextVariable("${luongThucNhan}", String.valueOf(gv.getLuongThucNhan())));
        var.addTextVariable(new TextVariable("${hocHam}", hocHamMap.get(gv.getHocHam())));

        //Add cac thuoc tinh cua bang giang day
        int tong1 = 0;
        int tong2 = 0;
        int tong3 = 0;
        int tong4 = 0;
        int tongA = 0;
        int tongB = 0;
        int tong = 0;
        int tongTT = 0;
        TableVariable tableVariable = new TableVariable();
        //<editor-fold defaultstate="collapsed" desc="add danh sách giảng dạy theo học kỳ, chuyên ngành">
        setNewColumn();
        if (null != lstGiangDayMM1 && !lstGiangDayMM1.isEmpty()) {
            for (GiangDayVM giangDayVM : lstGiangDayMM1) {
                setColumnForExportWord("MM1", giangDayVM);
                if (giangDayVM.getTietTg() != null) {
                    tong1 += giangDayVM.getTietTg().intValue();
                }
            }
        } else {
            setColumnForExportWord("MM1", new GiangDayVM());
        }
        addColumn(var, tableVariable);

        setNewColumn();
        if (null != lstGiangDayAT1 && !lstGiangDayAT1.isEmpty()) {
            for (GiangDayVM giangDayVM : lstGiangDayAT1) {
                setColumnForExportWord("AT1", giangDayVM);
                if (giangDayVM.getTietTg() != null) {
                    tong2 += giangDayVM.getTietTg().intValue();
                }
            }
        } else {
            setColumnForExportWord("AT1", new GiangDayVM());
        }
        addColumn(var, tableVariable);

        setNewColumn();
        if (null != lstGiangDayMM2 && !lstGiangDayMM2.isEmpty()) {
            for (GiangDayVM giangDayVM : lstGiangDayMM2) {
                setColumnForExportWord("MM2", giangDayVM);
                if (giangDayVM.getTietTg() != null) {
                    tong3 += giangDayVM.getTietTg().intValue();
                }
            }
        } else {
            setColumnForExportWord("MM2", new GiangDayVM());
        }
        addColumn(var, tableVariable);

        setNewColumn();
        if (null != lstGiangDayAT2 && !lstGiangDayAT2.isEmpty()) {
            for (GiangDayVM giangDayVM : lstGiangDayAT2) {
                setColumnForExportWord("AT2", giangDayVM);
                if (giangDayVM.getTietTg() != null) {
                    tong4 += giangDayVM.getTietTg().intValue();
                }
            }
        } else {
            setColumnForExportWord("AT2", new GiangDayVM());
        }
        addColumn(var, tableVariable);
        //</editor-fold>
        tongA = tong1 + tong2 + tong3 + tong4;

        //<editor-fold defaultstate="collapsed" desc="add các thuộc tính hướng dẫn đồ án tốt nghiệp">
        TableVariable tableVariable2 = new TableVariable();
        List<Variable> ttTN = new ArrayList<>();
        List<Variable> tenSv = new ArrayList<>();
        List<Variable> khoaDT = new ArrayList<>();
        List<Variable> soNgHD = new ArrayList<>();
        List<Variable> tietQD = new ArrayList<>();
        if (null != listHdTotNghiep && !listHdTotNghiep.isEmpty()) {

            int i = 1;
            for (TbdHdTotNghiep tbdHdTotNghiep : listHdTotNghiep) {
                ttTN.add(new TextVariable("${ttTN}", String.valueOf(i++)));
                tenSv.add(new TextVariable("${tenSV}", null != tbdHdTotNghiep.getTenSv() ? tbdHdTotNghiep.getTenSv() : " "));
                khoaDT.add(new TextVariable("${khoaDT}", null != tbdHdTotNghiep.getKhoaDt() ? tbdHdTotNghiep.getKhoaDt() : " "));
                soNgHD.add(new TextVariable("${soNgHD}", null != tbdHdTotNghiep.getSoNguoitHd() ? String.valueOf(tbdHdTotNghiep.getSoNguoitHd()) : " "));
                tietQD.add(new TextVariable("${tietQD}", null != tbdHdTotNghiep.getSoTietQd() ? String.valueOf(tbdHdTotNghiep.getSoTietQd()) : " "));
                if (null != tbdHdTotNghiep.getSoTietQd()) {
                    tongB += tbdHdTotNghiep.getSoTietQd().intValue();
                }
            }
        } else {
            ttTN.add(new TextVariable("${ttTN}", " "));
            tenSv.add(new TextVariable("${tenSV}", " "));
            khoaDT.add(new TextVariable("${khoaDT}", " "));
            soNgHD.add(new TextVariable("${soNgHD}", " "));
            tietQD.add(new TextVariable("${tietQD}", " "));
        }
        tableVariable2.addVariable(ttTN);
        tableVariable2.addVariable(tenSv);
        tableVariable2.addVariable(khoaDT);
        tableVariable2.addVariable(soNgHD);
        tableVariable2.addVariable(tietQD);

        var.addTableVariable(tableVariable2);
        //</editor-fold>

        tong = tongA + tongB;
        int soTietChuaHT = 0;
        //<editor-fold defaultstate="collapsed" desc="add nghiên cứu khoa học">
        TableVariable tableVariable3 = new TableVariable();
        List<Variable> ndKH = new ArrayList<>();
        List<Variable> soTietTH = new ArrayList<>();
        List<Variable> soTietChua = new ArrayList<>();
        if (null != listNckh && !listNckh.isEmpty()) {

            for (TbdNckh tbdNckh : listNckh) {
                ndKH.add(new TextVariable("${ndKH}", null != tbdNckh.getNoiDung() ? tbdNckh.getNoiDung() : " "));
                soTietTH.add(new TextVariable("${soTietTH}", null != tbdNckh.getSoTietChuaThucHien() ? String.valueOf(tbdNckh.getSoTietThucHien()) : " "));
                soTietChua.add(new TextVariable("${soTietChuaTH}", null != tbdNckh.getSoTietChuaThucHien() ? String.valueOf(tbdNckh.getSoTietChuaThucHien()) : " "));
                if (null != tbdNckh.getSoTietChuaThucHien()) {
                    soTietChuaHT += tbdNckh.getSoTietChuaThucHien().intValue();
                }
            }
        } else {
            ndKH.add(new TextVariable("${ndKH}", " "));
            soTietTH.add(new TextVariable("${soTietTH}", " "));
            soTietChua.add(new TextVariable("${soTietChuaTH}", " "));
        }
        tableVariable3.addVariable(ndKH);
        tableVariable3.addVariable(soTietTH);
        tableVariable3.addVariable(soTietChua);

        var.addTableVariable(tableVariable3);
        //</editor-fold>

        Long soTietPhaiGiang = soTietDMMap.get(gv.getDinhMucGG());
        Long soTietGiamTru = 0l;
        String giamTruString = gv.getGiamTru();
        if (giamTruString != null) {
            String[] lstGiamTru = giamTruString.split(",");
            Long dinhMucGG = getDinhMucGioGiang(objectId);
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
        }
        tongTT = tong - soTietPhaiGiang.intValue() - soTietChuaHT + soTietGiamTru.intValue();
        var.addTextVariable(new TextVariable("${tong1}", String.valueOf(tong1)));
        var.addTextVariable(new TextVariable("${tong2}", String.valueOf(tong2)));
        var.addTextVariable(new TextVariable("${tong3}", String.valueOf(tong3)));
        var.addTextVariable(new TextVariable("${tong4}", String.valueOf(tong4)));
        var.addTextVariable(new TextVariable("${tongA}", String.valueOf(tongA)));
        var.addTextVariable(new TextVariable("${tongB}", String.valueOf(tongB)));
        var.addTextVariable(new TextVariable("${tong}", String.valueOf(tong)));
        var.addTextVariable(new TextVariable("${soTietPhaiGiang}", String.valueOf(soTietPhaiGiang)));
        var.addTextVariable(new TextVariable("${soTietChua}", String.valueOf(soTietChuaHT)));
        var.addTextVariable(new TextVariable("${soTietGiamTru}", String.valueOf(soTietGiamTru)));
        var.addTextVariable(new TextVariable("${tongTT}", String.valueOf(tongTT)));

        docx.fillTemplate(var);
        docx.save("Bao_Cao.docx");
        try {
            File dosfile = new File("Bao_Cao.docx");
            if (dosfile.exists()) {
                String mimeType = URLConnection.guessContentTypeFromName(dosfile.getName());//fileNameNews.xls
                if (mimeType == null) {
                    mimeType = "application/octet-stream";
                }

                response.setContentType(mimeType);
                response.setHeader("Content-Disposition", String.format("inline; filename=\"" + dosfile.getName() + "\""));
                response.setContentLength((int) dosfile.length());

                InputStream inputStream = new BufferedInputStream(new FileInputStream(dosfile));

                FileCopyUtils.copy(inputStream, response.getOutputStream());
            }
        } catch (Exception en) {
        }
    }

    public Long getDinhMucGioGiang(Long id) {
        loadMap();
        return soTietDMMap.get(giangVienService.findById(id).getDinhMucGG());
    }

    public void setColumnForExportWord(String kiHoc, GiangDayVM giangDayVM) {
        sttCol.add(new TextVariable("${stt" + kiHoc + "}", null != giangDayVM.getStt() ? String.valueOf(giangDayVM.getStt()) : " "));
        tenMonCol.add(new TextVariable("${tenMon" + kiHoc + "}", null != giangDayVM.getTenMon() ? giangDayVM.getTenMon() : " "));
        tcCol.add(new TextVariable("${tc" + kiHoc + "}", null != giangDayVM.getDvhtTc() ? String.valueOf(giangDayVM.getDvhtTc()) : " "));
        lopCol.add(new TextVariable("${lop" + kiHoc + "}", null != giangDayVM.getLop() ? giangDayVM.getLop() : " "));
        khoaCol.add(new TextVariable("${khoa" + kiHoc + "}", null != giangDayVM.getKhoaDt() ? giangDayVM.getKhoaDt() : " "));
        tietQCCol.add(new TextVariable("${tietQC" + kiHoc + "}", null != giangDayVM.getSoTietQc() ? String.valueOf(giangDayVM.getSoTietQc()) : " "));
        tietTGCol.add(new TextVariable("${tietTG" + kiHoc + "}", null != giangDayVM.getTietTg() ? String.valueOf(giangDayVM.getTietTg()) : " "));
    }

    private String findChuNhiemBoMon(Long idBoMon) {
        List<GiangVien> lstGiangViens = giangVienService.findByIdBoMon(idBoMon);
        if (null != lstGiangViens && !lstGiangViens.isEmpty()) {
            for (GiangVien giangVien : lstGiangViens) {
                if (giangVienService.isChuNhiemBoMon(giangVien.getId())) {
                    return giangVien.getHoTen();
                }
            }
        }
        return "";
    }

    private String findChuNhiemKhoa(Long idKhoa) {
        List<BoMon> lstBoMons = boMonService.getListByIdKhoa(idKhoa);
        if (null != lstBoMons && !lstBoMons.isEmpty()) {
            for (BoMon boMon : lstBoMons) {
                List<GiangVien> lstGiangViens = giangVienService.findByIdBoMon(boMon.getId());
                if (null != lstGiangViens && !lstGiangViens.isEmpty()) {
                    for (GiangVien giangVien : lstGiangViens) {
                        if (giangVienService.isChuNhiemKhoa(giangVien.getId())) {
                            return giangVien.getHoTen();
                        }
                    }
                }
            }
        }
        return "";
    }

    private void setNewColumn() {
        sttCol = new ArrayList<>();
        tenMonCol = new ArrayList<>();
        tcCol = new ArrayList<>();
        lopCol = new ArrayList<>();
        khoaCol = new ArrayList<>();
        tietQCCol = new ArrayList<>();
        tietTGCol = new ArrayList<>();
    }

    private void addColumn(Variables var, TableVariable tableVariable) {
        tableVariable = new TableVariable();
        tableVariable.addVariable(sttCol);
        tableVariable.addVariable(tenMonCol);
        tableVariable.addVariable(tcCol);
        tableVariable.addVariable(lopCol);
        tableVariable.addVariable(khoaCol);
        tableVariable.addVariable(tietQCCol);
        tableVariable.addVariable(tietTGCol);

        var.addTableVariable(tableVariable);
    }
}
