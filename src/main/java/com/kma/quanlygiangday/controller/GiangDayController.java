/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kma.quanlygiangday.controller;

import com.kma.quanlygiangday.model.MonHoc;
import com.kma.quanlygiangday.model.TbdGiangDay;
import com.kma.quanlygiangday.service.BoMonService;
import com.kma.quanlygiangday.service.GiangDayService;
import com.kma.quanlygiangday.service.GiangVienService;
import com.kma.quanlygiangday.service.MonHocService;
import com.kma.quanlygiangday.utils.Constants;
import com.kma.quanlygiangday.utils.DateUtil;
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
public class GiangDayController {

    private static final Logger logger = LoggerFactory.getLogger(GiangDayController.class);

    @Autowired
    GiangDayService giangDayService;
    @Autowired
    GiangVienService giangVienService;
    @Autowired
    BoMonService boMonService;
    @Autowired
    MonHocService monHocService;

    Map<String, String> hkCnMap = new HashMap<>();
    Map<String, String> namHocMap = new HashMap<>();
    Map<String, String> monHocMap = new HashMap<>();
    String namHocNay;

    public void loadNamHoc() {
        namHocMap.clear();
        namHocMap.put(DateUtil.getNamHoc(DateUtil.now(), 4), DateUtil.getNamHoc(DateUtil.now(), 4));
        namHocMap.put(DateUtil.getNamHoc(DateUtil.now(), 3), DateUtil.getNamHoc(DateUtil.now(), 3));
        namHocMap.put(DateUtil.getNamHoc(DateUtil.now(), 2), DateUtil.getNamHoc(DateUtil.now(), 2));
        namHocMap.put(DateUtil.getNamHoc(DateUtil.now(), 1), DateUtil.getNamHoc(DateUtil.now(), 1));
        namHocMap.put(namHocNay, namHocNay);
    }

    public void loadMap() {
        hkCnMap.put(Constants.HocKy_ChuyenNganh.HOC_KY_1_AT, Constants.HocKy_ChuyenNganh.HOC_KY_1_AT_TEXT);
        hkCnMap.put(Constants.HocKy_ChuyenNganh.HOC_KY_1_MM, Constants.HocKy_ChuyenNganh.HOC_KY_1_MM_TEXT);
        hkCnMap.put(Constants.HocKy_ChuyenNganh.HOC_KY_2_AT, Constants.HocKy_ChuyenNganh.HOC_KY_2_AT_TEXT);
        hkCnMap.put(Constants.HocKy_ChuyenNganh.HOC_KY_2_MM, Constants.HocKy_ChuyenNganh.HOC_KY_2_MM_TEXT);
    }

    public void loadMonHoc(List<MonHoc> lstMon) {
        monHocMap.clear();
        lstMon.forEach((monHoc) -> {
            monHocMap.put(monHoc.getTenMon(), monHoc.getTenMon());
        });
    }

    @RequestMapping("/giangVien/{objectId}/statistic/giangDay")
    public String statistic(@PathVariable Long objectId, Model model) {
        namHocNay = DateUtil.getNamHoc(DateUtil.now(), 0);
        loadMap();
        loadNamHoc();
        model.addAttribute("giangDays", giangDayService.findByObjectId(objectId, namHocNay));
        model.addAttribute("objectId", objectId);
        model.addAttribute("namHocMap", namHocMap);
        model.addAttribute("namHocNay", namHocNay);
        return "giangDay";
    }

    @GetMapping("/giangVien/{objectId}/statistic/giangDay/search")
    public String search(@RequestParam("namHoc") String namHoc,@PathVariable Long objectId, Model model) {
        namHocNay = namHoc;
        model.addAttribute("giangDays", giangDayService.findByObjectId(objectId, namHocNay));
        model.addAttribute("objectId", objectId);
        model.addAttribute("namHocMap", namHocMap);
        model.addAttribute("namHocNay", namHocNay);
        return "giangDay";
    }
    
    @PostMapping("/giangVien/{objectId}/statistic/giangDay/save")
    public String save(@Valid TbdGiangDay giangDay, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return "giangDay-form";
        }
        giangDay.setIsDeleted(Constants.SetDelete.NOT_DELETE);
        if (giangDay.getTenMon() != null) {
            giangDay.setDvhtTc(monHocService.findByTenMon(giangDay.getTenMon()).getDvhtTc());
        }
        giangDayService.save(giangDay);
        redirect.addFlashAttribute("success", "Thành công!!!");
        return "redirect:/giangVien/{objectId}/statistic/giangDay";
    }

    @GetMapping("/giangVien/{objectId}/statistic/giangDay/create")
    public String create(@PathVariable Long objectId, Model model) {
        TbdGiangDay tbdGiangDay = new TbdGiangDay();
        tbdGiangDay.setObjectId(objectId);
        tbdGiangDay.setIsDeleted(Constants.SetDelete.NOT_DELETE);
        tbdGiangDay.setNamHoc(namHocNay);
        loadNamHoc();

        Long idBoMon = giangVienService.findById(objectId).getIdBoMon();
        Long idKhoa = boMonService.findById(idBoMon).getIdKhoa();
        List<MonHoc> lstMonHoc = monHocService.findByIdKhoa(idKhoa);
        loadMonHoc(lstMonHoc);
        model.addAttribute("monHocMap", monHocMap);
        model.addAttribute("namHocMap", namHocMap);
        model.addAttribute("namHocNay", namHocNay);
        model.addAttribute("giangDay", tbdGiangDay);
        model.addAttribute("objectId", objectId);
        model.addAttribute("hkCnMap", hkCnMap);
        model.addAttribute("msg", "create");
        return "giangDay-form";
    }

    @GetMapping("/giangVien/{objectId}/statistic/giangDay/{id}/edit")
    public String edit(@PathVariable Long id, @PathVariable Long objectId, Model model) {
        TbdGiangDay giangDay = giangDayService.findById(id);
        model.addAttribute("giangDay", giangDay);
        model.addAttribute("objectId", objectId);
        model.addAttribute("hkCnMap", hkCnMap);
        loadNamHoc();

        Long idBoMon = giangVienService.findById(objectId).getIdBoMon();
        Long idKhoa = boMonService.findById(idBoMon).getIdKhoa();
        List<MonHoc> lstMonHoc = monHocService.findByIdKhoa(idKhoa);
        loadMonHoc(lstMonHoc);
        model.addAttribute("monHocMap", monHocMap);
        model.addAttribute("namHocMap", namHocMap);
        model.addAttribute("namHocNay", giangDay.getNamHoc());
        return "giangDay-form";
    }

    @GetMapping("/giangVien/{objectId}/statistic/giangDay/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirect) {
        giangDayService.delete(id);
        redirect.addFlashAttribute("success", "Thành công!!!");
        return "redirect:/giangVien/{objectId}/statistic/giangDay";
    }
}
