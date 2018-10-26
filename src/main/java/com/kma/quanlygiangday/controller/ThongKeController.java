/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kma.quanlygiangday.controller;

import com.kma.quanlygiangday.model.BoMonVM;
import com.kma.quanlygiangday.model.Khoa;
import com.kma.quanlygiangday.model.KhoaVM;
import com.kma.quanlygiangday.model.TbdGiangDay;
import com.kma.quanlygiangday.model.TbdHdTotNghiep;
import com.kma.quanlygiangday.model.ThongKeVM;
import com.kma.quanlygiangday.model.TongHop;
import com.kma.quanlygiangday.service.BoMonService;
import com.kma.quanlygiangday.service.GiangDayService;
import com.kma.quanlygiangday.service.GiangVienService;
import com.kma.quanlygiangday.service.HdTotNghiepService;
import com.kma.quanlygiangday.service.KhoaService;
import com.kma.quanlygiangday.service.TongHopService;
import com.kma.quanlygiangday.utils.Constants;
import com.kma.quanlygiangday.utils.DateUtil;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;
import net.sf.jxls.transformer.XLSTransformer;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Tuno
 */
@Controller
@ComponentScan
public class ThongKeController {

    private static final Logger logger = LoggerFactory.getLogger(ThongKeController.class);

    @Autowired
    ServletContext c;

    @Autowired
    BoMonService boMonService;

    @Autowired
    KhoaService khoaService;

    @Autowired
    GiangVienService giangVienService;

    @Autowired
    GiangDayService giangDayService;

    @Autowired
    HdTotNghiepService hdTotNghiepService;

    @Autowired
    TongHopService tongHopService;

    Map<String, Long> soTietDMMap = new HashMap<>();
    Map<Long, String> khoaMap = new HashMap<>();
    Map<String, String> namHocMap = new HashMap<>();
    List<ThongKeVM> lstThongKe;
    List<TongHop> lstTongHop;
    String namHocNay;
    
//<editor-fold defaultstate="collapsed" desc="mapping">
    
    public void loadNamHoc() {
        namHocNay = DateUtil.getNamHoc(DateUtil.now(), 0);
        namHocMap.clear();
        namHocMap.put(DateUtil.getNamHoc(DateUtil.now(), 4), DateUtil.getNamHoc(DateUtil.now(), 4));
        namHocMap.put(DateUtil.getNamHoc(DateUtil.now(), 3), DateUtil.getNamHoc(DateUtil.now(), 3));
        namHocMap.put(DateUtil.getNamHoc(DateUtil.now(), 2), DateUtil.getNamHoc(DateUtil.now(), 2));
        namHocMap.put(DateUtil.getNamHoc(DateUtil.now(), 1), DateUtil.getNamHoc(DateUtil.now(), 1));
        namHocMap.put(namHocNay, namHocNay);
    }
    
    public void loadMap() {
        soTietDMMap.clear();
        soTietDMMap.put(Constants.DINH_MUC_GIANG_DAY.CHUYEN_MON, Constants.SO_TIET.CHUYEN_MON);
        soTietDMMap.put(Constants.DINH_MUC_GIANG_DAY.CO_BAN, Constants.SO_TIET.CO_BAN);
        soTietDMMap.put(Constants.DINH_MUC_GIANG_DAY.CHUYEN_MON_THAI_SAN, Constants.SO_TIET.CHUYEN_MON_THAI_SAN);
        soTietDMMap.put(Constants.DINH_MUC_GIANG_DAY.CO_BAN_THAI_SAN, Constants.SO_TIET.CO_BAN_THAI_SAN);
    }
    
    public void loadKhoaMap() {
        khoaMap.clear();
        List<Khoa> lstKhoa = khoaService.findAll();
        if (lstKhoa != null && !lstKhoa.isEmpty()) {
            khoaMap = new HashMap<>();
            for (Khoa k : lstKhoa) {
                khoaMap.put(k.getId(), k.getTenKhoa());
            }
        }
    }
//</editor-fold>

    @RequestMapping("/thongKe")
    public String thongKe(Model model) {
        logger.info("/thongKe");
        lstThongKe = new ArrayList<>();
        lstTongHop = tongHopService.findAll();
        getListThongKe(lstTongHop);
        loadKhoaMap();
        loadNamHoc();
        model.addAttribute("khoaMap", khoaMap);
        model.addAttribute("namHocMap", namHocMap);
        model.addAttribute("thongKes", lstThongKe);
        model.addAttribute("namHocNay", namHocNay);
        return "thongKe";
    }

    @GetMapping("/thongKe/search")
    public String search(@RequestParam("namHoc") String namHoc, @RequestParam("khoa") Long khoa, Model model) {
        if ("".equals(namHoc) && (khoa == null)) {
            return "redirect:/thongKe";
        }
        TongHop tongHop = new TongHop();
        tongHop.setNamHoc(namHoc);
        tongHop.setIdKhoa(khoa);
        lstThongKe = new ArrayList<>();
        lstTongHop = tongHopService.search(tongHop);
        getListThongKe(lstTongHop);
        loadKhoaMap();
        model.addAttribute("namHocMap", namHocMap);
        model.addAttribute("khoaMap", khoaMap);
        model.addAttribute("thongKes", lstThongKe);
        namHocNay = namHoc;
        model.addAttribute("namHocNay", namHocNay);
        return "thongKe";
    }

    @RequestMapping(value = "/thongKe/baoCao", method = RequestMethod.GET)
    public void baoCao(HttpServletResponse response) {
        try {

            List<BoMonVM> lstBoMonVM = new ArrayList<>();

            if (!lstThongKe.isEmpty()) {

                List<String> lstTenBoMon = new ArrayList<>();
                //Tạo danh sách tên bộ môn đã có giảng viên giảng dạy
                for (ThongKeVM thongKeVM : lstThongKe) {
                    if (!existTenBoMon(lstTenBoMon, thongKeVM.getTenBoMon())) {
                        lstTenBoMon.add(thongKeVM.getTenBoMon());
                    }
                }
                for (String tenBoMonTemp : lstTenBoMon) {
                    String tenKhoa = "";
                    List<ThongKeVM> lstTemp = new ArrayList<>();
                    for (ThongKeVM thongKeVM : lstThongKe) {
                        if (thongKeVM.getTenBoMon().equals(tenBoMonTemp)) {
                            lstTemp.add(thongKeVM);
                            tenKhoa = thongKeVM.getTenKhoa();
                        }
                    }
                    BoMonVM boMonVM = new BoMonVM(tenKhoa, tenBoMonTemp, lstTemp);
                    boMonVM.tinhToan();
                    lstBoMonVM.add(boMonVM);
                }

            }
            List<KhoaVM> lstKhoaVM = new ArrayList<>();
            if (!lstBoMonVM.isEmpty()) {

                List<String> lstTenKhoa = new ArrayList<>();

                //tạo danh sách tên khoa
                for (BoMonVM boMonVM : lstBoMonVM) {
                    if (!existTenBoMon(lstTenKhoa, boMonVM.getTenKhoaVM())) {
                        lstTenKhoa.add(boMonVM.getTenKhoaVM());
                    }
                }

                for (String tenKhoa : lstTenKhoa) {
                    List<BoMonVM> lstTemp = new ArrayList<>();
                    for (BoMonVM boMonVM : lstBoMonVM) {
                        if (boMonVM.getTenKhoaVM().equals(tenKhoa)) {
                            lstTemp.add(boMonVM);
                        }
                    }
                    KhoaVM khoaVM = new KhoaVM(tenKhoa, lstTemp);
                    khoaVM.tinhToan();
                    lstKhoaVM.add(khoaVM);
                }

            }

            Long tong = 0l;
            Long thue = 0l;
            Long thucLinh = 0l;
            for (KhoaVM khoaVM : lstKhoaVM) {
                tong += khoaVM.getTong();
                thue += khoaVM.getThue();
                thucLinh += khoaVM.getThucLinh();
            }

            Map<String, Object> beans = new HashMap<>();
            beans.put("data", lstKhoaVM);
            beans.put("tong", tong);
            beans.put("thucLinh", thucLinh);
            beans.put("thue", thue);
            beans.put("tieuChi", "HỌC VIỆN KỸ THUẬT MẬT MÃ");
            beans.put("namHoc", lstThongKe.get(0).getNamHoc());
            String fileString = "vuot_gio_" + lstThongKe.get(0).getNamHoc();
            //Lưu file vào bộ nhớ trên server
            exportByJXLS("bao_cao.xlsx", fileString, beans);

            //Cho download file
            File file = new File(c.getRealPath("/dir/" + fileString + ".xls"));
            if (file.exists()) {
                String mimeType = URLConnection.guessContentTypeFromName(file.getName());//fileNameNews.xls
                if (mimeType == null) {
                    mimeType = "application/octet-stream";
                }

                response.setContentType(mimeType);
                response.setHeader("Content-Disposition", String.format("inline; filename=\"" + file.getName() + "\""));
                response.setContentLength((int) file.length());

                InputStream inputStream = new BufferedInputStream(new FileInputStream(file));

                FileCopyUtils.copy(inputStream, response.getOutputStream());

            }
        } catch (IOException e) {
        }
    }

    private Boolean existTenBoMon(List<String> lst, String ten) {
        if (lst.isEmpty()) {
            return false;
        }
        for (String string : lst) {
            if (ten.equals(string)) {
                return true; //Đã có trong list
            }
        }
        return false;
    }

    private void exportByJXLS(String fileName, String fileNameNews, Map<String, Object> beans) {
        try {
            String folder = c.getRealPath("/temp/");
            String absoluteTemplateFilePath = (folder + fileName).replace('/', File.separatorChar);
            String exportedFileName = c.getRealPath("/dir/") + fileNameNews + ".xls";
            String absoluteExportedFilePath = (exportedFileName).replace('/', File.separatorChar);
            InputStream inputStream = new BufferedInputStream(new FileInputStream(absoluteTemplateFilePath));
            XLSTransformer transformer = new XLSTransformer();
            Workbook resultWorkbook = transformer.transformXLS(inputStream, beans);
            OutputStream os = new BufferedOutputStream(new FileOutputStream(absoluteExportedFilePath));
            resultWorkbook.write(os);
            os.flush();

            FileCopyUtils.copy(inputStream, os);

        } catch (Exception ex) {

        }
    }

    public void getListThongKe(List<TongHop> lstTongHop) {
        lstThongKe.clear();
        long i = 1l;
        if (!lstTongHop.isEmpty()) {
            for (TongHop tongHop : lstTongHop) {
                ThongKeVM thongKeVM = new ThongKeVM();

                //Lấy danh sách thực tế giảng dạy
                List<TbdGiangDay> lstGiangDay = giangDayService.findByObjectId(tongHop.getObjectId(), namHocNay);
                thongKeVM.fromTongHop(tongHop);
                for (TbdGiangDay tbdGiangDay : lstGiangDay) {
                    if (tbdGiangDay.getHkCn().equals(Constants.HocKy_ChuyenNganh.HOC_KY_1_AT)) {
                        thongKeVM.sethK1_AT(tbdGiangDay.getTietTg());
                    }
                    if (tbdGiangDay.getHkCn().equals(Constants.HocKy_ChuyenNganh.HOC_KY_1_MM)) {
                        thongKeVM.sethK1_MM(tbdGiangDay.getTietTg());
                    }
                    if (tbdGiangDay.getHkCn().equals(Constants.HocKy_ChuyenNganh.HOC_KY_2_AT)) {
                        thongKeVM.sethK2_AT(tbdGiangDay.getTietTg());
                    }
                    if (tbdGiangDay.getHkCn().equals(Constants.HocKy_ChuyenNganh.HOC_KY_2_MM)) {
                        thongKeVM.sethK2_MM(tbdGiangDay.getTietTg());
                    }
                }

                //Lấy số tiết hdtn
                Long soTietHd = 0l;
                List<TbdHdTotNghiep> listHdTN = hdTotNghiepService.findByObjectId(tongHop.getObjectId(), namHocNay);
                for (TbdHdTotNghiep tbdHdTotNghiep : listHdTN) {
                    soTietHd += tbdHdTotNghiep.getSoTietQd();
                }
                thongKeVM.setDinhMucGioGiang(getDinhMucGioGiang(tongHop.getObjectId()));
                thongKeVM.setTenKhoa(getTenKhoa(tongHop.getIdKhoa()));
                thongKeVM.setTenBoMon(getTenBoMon(tongHop.getIdBoMon()));
                thongKeVM.setTenGV(getTenGV(tongHop.getObjectId()));
                thongKeVM.setMucTTChuan(getMucTTChuan(tongHop.getObjectId()));
                //thongKeVM.setTongTien();//Lưu ý ?????????
                thongKeVM.setLaiThuocTinh();
                thongKeVM.sethK2_AT(thongKeVM.gethK2_AT() + soTietHd);
                thongKeVM.setStt(i++);
                lstThongKe.add(thongKeVM);
            }
        }
    }

    public Long getDinhMucGioGiang(Long id) {
        loadMap();
        return soTietDMMap.get(giangVienService.findById(id).getDinhMucGG());
    }

    public String getTenKhoa(Long id) {
        return khoaService.findById(id).getTenKhoa();
    }

    public String getTenBoMon(Long id) {
        return boMonService.findById(id).getTenBoMon();
    }

    public String getTenGV(Long id) {
        return giangVienService.findById(id).getHoTen();
    }

    public Long getMucTTChuan(Long id) {
        return giangVienService.findById(id).getMucTtChuan();
    }
}
