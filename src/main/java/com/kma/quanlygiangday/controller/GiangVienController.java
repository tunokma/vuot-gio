/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kma.quanlygiangday.controller;

import com.kma.quanlygiangday.model.BoMon;
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
import com.kma.quanlygiangday.utils.PassEncoding;
import com.kma.quanlygiangday.utils.Roles;
import com.kma.quanlygiangday.viewmodel.GiangVienVM;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Tuno
 */
@Controller
@ComponentScan
public class GiangVienController {

    private static final Logger logger = LoggerFactory.getLogger(GiangVienController.class);

    @Autowired
    GiangVienService giangVienService;

    @Autowired
    GiangDayService giangDayService;

    @Autowired
    HdTotNghiepService hdTotNghiepService;

    @Autowired
    NCKHService nckhService;

    @Autowired
    TongHopService tongHopService;

    @Autowired
    MienGiamService mienGiamService;

    @Autowired
    BoMonService boMonService;
    Map<Long, String> hocHamMap = new HashMap<>();

    Map<String, String> soTietDMMap = new HashMap<>();
    Map<Long, String> giamTruMap = new HashMap<>();
    Map<Long, String> chuNhiemMap = new HashMap<>();
    Map<String, Long> soTietDMMap2 = new HashMap<>();

//<editor-fold defaultstate="collapsed" desc="load">
    public void loadMap() {
        soTietDMMap.clear();
        soTietDMMap.put(Constants.DINH_MUC_GIANG_DAY.CHUYEN_MON, Constants.DINH_MUC_GIANG_DAY.CHUYEN_MON_TEXT);
        soTietDMMap.put(Constants.DINH_MUC_GIANG_DAY.CO_BAN, Constants.DINH_MUC_GIANG_DAY.CO_BAN_TEXT);
        soTietDMMap.put(Constants.DINH_MUC_GIANG_DAY.CHUYEN_MON_THAI_SAN, Constants.DINH_MUC_GIANG_DAY.CHUYEN_MON_THAI_SAN_TEXT);
        soTietDMMap.put(Constants.DINH_MUC_GIANG_DAY.CO_BAN_THAI_SAN, Constants.DINH_MUC_GIANG_DAY.CO_BAN_THAI_SAN_TEXT);
    }

    public void loadMap2() {
        soTietDMMap2.put(Constants.DINH_MUC_GIANG_DAY.CHUYEN_MON, Constants.SO_TIET.CHUYEN_MON);
        soTietDMMap2.put(Constants.DINH_MUC_GIANG_DAY.CO_BAN, Constants.SO_TIET.CO_BAN);
        soTietDMMap2.put(Constants.DINH_MUC_GIANG_DAY.CHUYEN_MON_THAI_SAN, Constants.SO_TIET.CHUYEN_MON_THAI_SAN);
        soTietDMMap2.put(Constants.DINH_MUC_GIANG_DAY.CO_BAN_THAI_SAN, Constants.SO_TIET.CO_BAN_THAI_SAN);
    }

    public void loadData() {
        hocHamMap.clear();
        hocHamMap.put(Constants.HocHam.CU_NHAN, Constants.HocHam.CU_NHAN_TEXT);
        hocHamMap.put(Constants.HocHam.KY_SU, Constants.HocHam.KY_SU_TEXT);
        hocHamMap.put(Constants.HocHam.THAC_SY, Constants.HocHam.THAC_SY_TEXT);
        hocHamMap.put(Constants.HocHam.TIEN_SI, Constants.HocHam.TIEN_SI_TEXT);
        hocHamMap.put(Constants.HocHam.PGS_TIEN_SI, Constants.HocHam.PGS_TIEN_SI_TEXT);
        hocHamMap.put(Constants.HocHam.GS_TIEN_SI, Constants.HocHam.GS_TIEN_SI_TEXT);
    }

    public void loadGiamTru() {
        giamTruMap.clear();
        List<TbdMienGiam> lstMienGiam = mienGiamService.findAll();
        if (lstMienGiam != null && !lstMienGiam.isEmpty()) {
            for (TbdMienGiam tbdMienGiam : lstMienGiam) {
                giamTruMap.put(tbdMienGiam.getId(), tbdMienGiam.getDoiTuong());
            }
        }
    }

    public void loadChuNhiem() {
        chuNhiemMap.clear();
        chuNhiemMap.put(Constants.CHU_NHIEM.KHONG, Constants.CHU_NHIEM.KHONG_TEXT);
        chuNhiemMap.put(Constants.CHU_NHIEM.KHOA, Constants.CHU_NHIEM.KHOA_TEXT);
        chuNhiemMap.put(Constants.CHU_NHIEM.BO_MON, Constants.CHU_NHIEM.BO_MON_TEXT);
    }
//</editor-fold>

    @RequestMapping("/giangVien")
    public String giangVien(Model model) {
        logger.info("/giangVien");
        loadData();
        loadMap();
        List<GiangVienVM> lst = giangVienService.findAll();
        if (!lst.isEmpty()) {
            for (GiangVienVM giangVien : lst) {
                if (giangVien.getHocHam() != null) {
                    giangVien.setHocHamText(hocHamMap.get(giangVien.getHocHam()));
                }
            }
        }
        model.addAttribute("giangViens", lst);
        return "giangVien";
    }

    @PostMapping("/giangVien/save")
    public String save(@Valid GiangVienVM giangVien, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return "giangVien-form";
        }
        if (giangVien.getId() == null) { //Create
            GiangVien gv = giangVienService.findByUsername(giangVien.getUsername());
            if (gv != null) {
                redirect.addFlashAttribute("saveUser", "exist-name");
                return "redirect:/giangVien-form";
            }

            if (!giangVien.getPassword().equals(giangVien.getPassword_2())) {
                redirect.addFlashAttribute("saveUser", "valid-pass");
                return "redirect:/giangVien-form";
            }

            giangVien.setPassword(PassEncoding.getInstance().passwordEncoder.encode(giangVien.getPassword()));
            giangVien.setRole(Roles.ROLE_USER.getValue());
        } else {//Edit
            GiangVien gv = giangVienService.findById(giangVien.getId());
            giangVien.setUsername(gv.getUsername());
            giangVien.setPassword(gv.getPassword());
            giangVien.setRole(gv.getRole());
        }
        giangVien.setMucTtChuan(getMucTTChuan(giangVien.getLuongThucNhan()));
        GiangVien gv = new GiangVien(giangVien);
        giangVienService.save(gv);
        if (giangVien.getId() == null) { //Them moi giang vien phai them moi TongHop
            createTongHop(gv);
        }
        redirect.addFlashAttribute("success", "Thành công!!!");
        return "redirect:/giangVien";
    }

    @GetMapping("/giangVien/create")
    public String create(Model model) {
        model.addAttribute("giangVienVM", new GiangVienVM());
        loadGiamTru();
        loadChuNhiem();
        Map<Long, String> mapTenBoMon = giangVienService.getBoMon();
        model.addAttribute("mapTenBoMon", mapTenBoMon);
        model.addAttribute("hocHamMap", hocHamMap);
        model.addAttribute("soTietDMMap", soTietDMMap);
        model.addAttribute("giamTruMap", giamTruMap);
        model.addAttribute("chuNhiemMap", chuNhiemMap);
        model.addAttribute("msg", "create");
        return "giangVien-form";
    }

    @GetMapping("/giangVien/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        GiangVienVM gv = new GiangVienVM(giangVienService.findById(id));
        model.addAttribute("giangVienVM", gv);
        loadGiamTru();
        loadChuNhiem();
        Map<Long, String> mapTenBoMon = giangVienService.getBoMon();
        model.addAttribute("mapTenBoMon", mapTenBoMon);
        model.addAttribute("hocHamMap", hocHamMap);
        model.addAttribute("soTietDMMap", soTietDMMap);

//        String giamTruString = gv.getGiamTru();
//        String[] chosenGiamTru = giamTruString.split(",");
        model.addAttribute("giamTruMap", giamTruMap);
        model.addAttribute("chuNhiemMap", chuNhiemMap);
//        model.addAttribute("chosenGiamTru", chosenGiamTru);
        return "giangVien-form";
    }

    @GetMapping("/giangVien/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirect) {
        giangVienService.delete(id);
        redirect.addFlashAttribute("success", "Thành công!!!");
        return "redirect:/giangVien";
    }

    @GetMapping("/giangVien/search")
    public String search(@RequestParam("search") String s, Model model) {
        if (s.equals("")) {
            return "redirect:/giangVien";
        }
        loadData();
        List<GiangVienVM> lst = giangVienService.search(s);
        if (!lst.isEmpty()) {
            for (GiangVienVM giangVien : lst) {
                if (giangVien.getHocHam() != null) {
                    giangVien.setHocHamText(hocHamMap.get(giangVien.getHocHam()));
                }
            }
        }
        model.addAttribute("giangViens", lst);
        return "giangVien";
    }

    private Long getMucTTChuan(Long luong) {
        return luong / 176;
    }

    private void createTongHop(GiangVien gv) {
        TongHop tongHop = new TongHop();
        loadMap2();
        String namHocNay = DateUtil.getNamHoc(DateUtil.now(), 0);
        tongHop.setObjectId(gv.getId());
        tongHop.setIdBoMon(gv.getIdBoMon());
        tongHop.setIdKhoa(boMonService.findById(gv.getIdBoMon()).getIdKhoa());
        tongHop.setNamHoc(namHocNay);

        //Số tiết giảm trừ
        Long soTietGiamTru = 0l;
        String giamTruString = gv.getGiamTru();
        if (giamTruString != null) {
            String[] lstGiamTru = giamTruString.split(",");
            Long dinhMucGG = soTietDMMap2.get(giangVienService.findById(gv.getId()).getDinhMucGG());
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
        Long soTietA = giangDayService.getSoTietDaDay(gv.getId(), namHocNay) != null ? giangDayService.getSoTietDaDay(gv.getId(), namHocNay) : 0l;
        Long soTietB = hdTotNghiepService.getSoTietByObjectId(gv.getId(), namHocNay) != null ? hdTotNghiepService.getSoTietByObjectId(gv.getId(), namHocNay) : 0l;
        tongHop.setTongSoTiet(soTietA + soTietB);

        //Số tiết phải giảng
        Long soTietPhaiGiang = soTietDMMap2.get(gv.getDinhMucGG());
        tongHop.setSoTietPhaiGiang(soTietPhaiGiang);

        //Số giờ chưa hoàn thành NCKH (Tính tiết)
        Long soTietChuaNCKH = Constants.SO_TIET.SO_TIET_NCKH;
        List<TbdNckh> lstNckhs = nckhService.findByObjectId(gv.getId(), namHocNay);
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
    }
}
