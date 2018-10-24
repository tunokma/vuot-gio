/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kma.quanlygiangday.user.controller;

import com.kma.quanlygiangday.model.GiangVien;
import com.kma.quanlygiangday.model.TbdHdTotNghiep;
import com.kma.quanlygiangday.service.GiangDayService;
import com.kma.quanlygiangday.service.GiangVienService;
import com.kma.quanlygiangday.service.HdTotNghiepService;
import com.kma.quanlygiangday.utils.Constants;
import com.kma.quanlygiangday.utils.DateUtil;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Tuno
 */
@Controller
@ComponentScan
public class UserHdTotNghiepController {
    
    private static final Logger logger = LoggerFactory.getLogger(UserHdTotNghiepController.class);
    @Autowired
    HdTotNghiepService hdTotNghiepService;
    
    @Autowired
    GiangVienService giangVienService;
    GiangVien giangVien;

    
    @RequestMapping("/user-hdTotNghiep")
    public String statistic(Model model){
        giangVien = giangVienService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("hdTotNghieps", hdTotNghiepService.findByObjectId(giangVien.getId(),DateUtil.getNamHoc(DateUtil.now(),0)));
        return "user-hdTotNghiep";
    }
    
    @PostMapping("/user-hdTotNghiep/save")
    public String save(@Valid TbdHdTotNghiep hdTotNghiep, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return "user-hdTotNghiep-form";
        }
        hdTotNghiep.setIsDeleted(Constants.SetDelete.NOT_DELETE);
        hdTotNghiepService.save(hdTotNghiep);
        redirect.addFlashAttribute("success", "Thành công!!!");
        return "redirect:/user-hdTotNghiep";
    }

    @GetMapping("/user-hdTotNghiep/create")
    public String create(Model model) {
        TbdHdTotNghiep tbdHdTotNghiep = new TbdHdTotNghiep();
        tbdHdTotNghiep.setObjectId(giangVien.getId());
        tbdHdTotNghiep.setIsDeleted(Constants.SetDelete.NOT_DELETE);
        model.addAttribute("hdTotNghiep", tbdHdTotNghiep);
        model.addAttribute("msg", "create");
        return "user-hdTotNghiep-form";
    }

    @GetMapping("/user-hdTotNghiep/{id}/edit")
    public String edit(@PathVariable Long id,Model model) {
        model.addAttribute("hdTotNghiep", hdTotNghiepService.findById(id));
        return "user-hdTotNghiep-form";
    }

    @GetMapping("/user-hdTotNghiep/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirect) {
        hdTotNghiepService.delete(id);
        redirect.addFlashAttribute("success", "Thành công!!!");
        return "redirect:/user-hdTotNghiep";
    }
    
}
