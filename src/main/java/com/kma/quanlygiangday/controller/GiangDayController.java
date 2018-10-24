/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kma.quanlygiangday.controller;

import com.kma.quanlygiangday.model.TbdGiangDay;
import com.kma.quanlygiangday.service.GiangDayService;
import com.kma.quanlygiangday.utils.Constants;
import com.kma.quanlygiangday.utils.DateUtil;
import java.util.HashMap;
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

    Map<String, String> hkCnMap = new HashMap<>();
    Map<String, String> namHocMap = new HashMap<>();
    String namHocNay;

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
        hkCnMap.put(Constants.HocKy_ChuyenNganh.HOC_KY_1_AT, Constants.HocKy_ChuyenNganh.HOC_KY_1_AT_TEXT);
        hkCnMap.put(Constants.HocKy_ChuyenNganh.HOC_KY_1_MM, Constants.HocKy_ChuyenNganh.HOC_KY_1_MM_TEXT);
        hkCnMap.put(Constants.HocKy_ChuyenNganh.HOC_KY_2_AT, Constants.HocKy_ChuyenNganh.HOC_KY_2_AT_TEXT);
        hkCnMap.put(Constants.HocKy_ChuyenNganh.HOC_KY_2_MM, Constants.HocKy_ChuyenNganh.HOC_KY_2_MM_TEXT);
    }

    @RequestMapping("/giangVien/{objectId}/statistic/giangDay")
    public String statistic(@PathVariable Long objectId, Model model) {
        loadMap();
        model.addAttribute("giangDays", giangDayService.findByObjectId(objectId, DateUtil.getNamHoc(DateUtil.now(), 0)));
        model.addAttribute("objectId", objectId);
        return "giangDay";
    }

    @PostMapping("/giangVien/{objectId}/statistic/giangDay/save")
    public String save(@Valid TbdGiangDay giangDay, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return "giangDay-form";
        }
        giangDay.setIsDeleted(Constants.SetDelete.NOT_DELETE);
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
