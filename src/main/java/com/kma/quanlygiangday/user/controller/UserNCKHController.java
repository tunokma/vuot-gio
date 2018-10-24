/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kma.quanlygiangday.user.controller;

import com.kma.quanlygiangday.model.GiangVien;
import com.kma.quanlygiangday.model.TbdNckh;
import com.kma.quanlygiangday.service.GiangVienService;
import com.kma.quanlygiangday.service.NCKHService;
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
public class UserNCKHController {
    
    private static final Logger logger = LoggerFactory.getLogger(UserNCKHController.class);
    @Autowired
    NCKHService nckhService;
    
    @Autowired
    GiangVienService giangVienService;
    GiangVien giangVien;
    
    @RequestMapping("/user-NCKH")
    public String statistic(Model model){
        giangVien = giangVienService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        model.addAttribute("nckhs", nckhService.findByObjectId(giangVien.getId(),DateUtil.getNamHoc(DateUtil.now(),0)));
        return "user-NCKH";
    }
    
    @PostMapping("/user-NCKH/save")
    public String save(@Valid TbdNckh nckh, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return "user-NCKH-form";
        }
        nckh.setIsDeleted(Constants.SetDelete.NOT_DELETE);
        nckhService.save(nckh);
        redirect.addFlashAttribute("success", "Thành công!!!");
        return "redirect:/user-NCKH";
    }

    @GetMapping("/user-NCKH/create")
    public String create(Model model) {
        TbdNckh tbdNckh = new TbdNckh();
        tbdNckh.setObjectId(giangVien.getId());
        tbdNckh.setIsDeleted(Constants.SetDelete.NOT_DELETE);
        model.addAttribute("nckh", tbdNckh);
        model.addAttribute("msg", "create");
        return "user-NCKH-form";
    }

    @GetMapping("/user-NCKH/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("nckh", nckhService.findById(id));
        return "user-NCKH-form";
    }

    @GetMapping("/user-NCKH/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirect) {
        nckhService.delete(id);
        redirect.addFlashAttribute("success", "Thành công!!!");
        return "redirect:/user-NCKH";
    }
}
