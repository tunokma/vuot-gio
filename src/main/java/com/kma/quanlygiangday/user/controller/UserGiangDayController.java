/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kma.quanlygiangday.user.controller;

import com.kma.quanlygiangday.model.GiangVien;
import com.kma.quanlygiangday.model.TbdGiangDay;
import com.kma.quanlygiangday.service.GiangDayService;
import com.kma.quanlygiangday.service.GiangVienService;
import com.kma.quanlygiangday.utils.Constants;
import com.kma.quanlygiangday.utils.DateUtil;
import java.util.HashMap;
import java.util.Map;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.context.SecurityContextHolder;
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
public class UserGiangDayController {

    //<editor-fold defaultstate="collapsed" desc="bean">
    private static final Logger logger = LoggerFactory.getLogger(UserGiangDayController.class);
    @Autowired
    GiangVienService giangVienService;

    @Autowired
    GiangDayService giangDayService;
    GiangVien giangVien;

    Map<String, String> hkCnMap = new HashMap<>();
    Map<String, String> namHocMap = new HashMap<>();
    String namHocNay;
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="loadComponent">
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
//</editor-fold>

    //<editor-fold defaultstate="collapsed" desc="mapping">
    @RequestMapping("/user-giangDay")
    public String statistic(Model model) {
        namHocNay = DateUtil.getNamHoc(DateUtil.now(), 0);
        loadMap();
        loadNamHoc();
        giangVien = giangVienService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("giangDays", giangDayService.findByObjectId(giangVien.getId(), namHocNay));
        model.addAttribute("namHocMap", namHocMap);
        model.addAttribute("namHocNay", namHocNay);
        return "user-giangDay";
    }

     @GetMapping("/user-giangDay/search")
    public String search(@RequestParam("namHoc") String namHoc, Model model) {
        namHocNay = namHoc;
        model.addAttribute("giangDays", giangDayService.findByObjectId(giangVien.getId(), namHocNay));
        model.addAttribute("namHocMap", namHocMap);
        model.addAttribute("namHocNay", namHocNay);
        return "user-giangDay";
    }
    
    @PostMapping("/user-giangDay/save")
    public String save(@Valid TbdGiangDay giangDay, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return "user-giangDay-form";
        }
        giangDay.setIsDeleted(Constants.SetDelete.NOT_DELETE);
        giangDayService.save(giangDay);
        redirect.addFlashAttribute("success", "Thành công!!!");
        return "redirect:/user-giangDay";
    }

    @GetMapping("/user-giangDay/create")
    public String create(Model model) {
        TbdGiangDay tbdGiangDay = new TbdGiangDay();
        tbdGiangDay.setObjectId(giangVien.getId());
        tbdGiangDay.setIsDeleted(Constants.SetDelete.NOT_DELETE);
        tbdGiangDay.setNamHoc(namHocNay);
        loadNamHoc();
        model.addAttribute("namHocMap", namHocMap);
        model.addAttribute("namHocNay", namHocNay);
        model.addAttribute("giangDay", tbdGiangDay);
        model.addAttribute("hkCnMap", hkCnMap);
        model.addAttribute("msg", "create");
        return "user-giangDay-form";
    }

    @GetMapping("/user-giangDay/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        TbdGiangDay giangDay = giangDayService.findById(id);
        model.addAttribute("giangDay", giangDay);
//        model.addAttribute("objectId", gv.getId());
        model.addAttribute("hkCnMap", hkCnMap);
        loadNamHoc();
        model.addAttribute("namHocMap", namHocMap);
        model.addAttribute("namHocNay", giangDay.getNamHoc());
        return "user-giangDay-form";
    }

    @GetMapping("/user-giangDay/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirect) {
        giangDayService.delete(id);
        redirect.addFlashAttribute("success", "Thành công!!!");
        return "redirect:/user-giangDay";
    }
//</editor-fold>
}
