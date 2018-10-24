/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kma.quanlygiangday.controller;

import com.kma.quanlygiangday.model.TbdHdTotNghiep;
import com.kma.quanlygiangday.service.HdTotNghiepService;
import com.kma.quanlygiangday.utils.Constants;
import com.kma.quanlygiangday.utils.DateUtil;
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
public class HdTotNghiepController {
    
    private static final Logger logger = LoggerFactory.getLogger(HdTotNghiepController.class);
    @Autowired
    HdTotNghiepService hdTotNghiepService;
    
    @RequestMapping("/giangVien/{objectId}/statistic/hdTotNghiep")
    public String statistic(@PathVariable Long objectId, Model model){
        model.addAttribute("hdTotNghieps", hdTotNghiepService.findByObjectId(objectId,DateUtil.getNamHoc(DateUtil.now(),0)));
        model.addAttribute("objectId",objectId);
        return "hdTotNghiep";
    }
    
    @PostMapping("/giangVien/{objectId}/statistic/hdTotNghiep/save")
    public String save(@Valid TbdHdTotNghiep hdTotNghiep, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return "hdTotNghiep-form";
        }
        hdTotNghiep.setIsDeleted(Constants.SetDelete.NOT_DELETE);
        hdTotNghiepService.save(hdTotNghiep);
        redirect.addFlashAttribute("success", "Thành công!!!");
        return "redirect:/giangVien/{objectId}/statistic/hdTotNghiep";
    }

    @GetMapping("/giangVien/{objectId}/statistic/hdTotNghiep/create")
    public String create(@PathVariable Long objectId, Model model) {
        TbdHdTotNghiep tbdHdTotNghiep = new TbdHdTotNghiep();
        tbdHdTotNghiep.setObjectId(objectId);
        tbdHdTotNghiep.setIsDeleted(Constants.SetDelete.NOT_DELETE);
        model.addAttribute("hdTotNghiep", tbdHdTotNghiep);
        model.addAttribute("objectId", objectId);
        model.addAttribute("msg", "create");
        return "hdTotNghiep-form";
    }

    @GetMapping("/giangVien/{objectId}/statistic/hdTotNghiep/{id}/edit")
    public String edit(@PathVariable Long id, @PathVariable Long objectId, Model model) {
        model.addAttribute("hdTotNghiep", hdTotNghiepService.findById(id));
        model.addAttribute("objectId", objectId);
        return "hdTotNghiep-form";
    }

    @GetMapping("/giangVien/{objectId}/statistic/hdTotNghiep/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirect) {
        hdTotNghiepService.delete(id);
        redirect.addFlashAttribute("success", "Thành công!!!");
        return "redirect:/giangVien/{objectId}/statistic/hdTotNghiep";
    }
    
}
