/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kma.quanlygiangday.controller;

import com.kma.quanlygiangday.model.TbdNckh;
import com.kma.quanlygiangday.service.NCKHService;
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
public class NCKHController {
    
    private static final Logger logger = LoggerFactory.getLogger(NCKHController.class);
    @Autowired
    NCKHService nckhService;
    
    @RequestMapping("/giangVien/{objectId}/statistic/NCKH")
    public String statistic(@PathVariable Long objectId, Model model){
        model.addAttribute("nckhs", nckhService.findByObjectId(objectId,DateUtil.getNamHoc(DateUtil.now(),0)));
        model.addAttribute("objectId",objectId);
        return "NCKH";
    }
    
    @PostMapping("/giangVien/{objectId}/statistic/NCKH/save")
    public String save(@Valid TbdNckh nckh, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return "nckh-form";
        }
        nckh.setIsDeleted(Constants.SetDelete.NOT_DELETE);
        nckhService.save(nckh);
        redirect.addFlashAttribute("success", "Thành công!!!");
        return "redirect:/giangVien/{objectId}/statistic/NCKH";
    }

    @GetMapping("/giangVien/{objectId}/statistic/NCKH/create")
    public String create(@PathVariable Long objectId, Model model) {
        TbdNckh tbdNckh = new TbdNckh();
        tbdNckh.setObjectId(objectId);
        tbdNckh.setIsDeleted(Constants.SetDelete.NOT_DELETE);
        model.addAttribute("nckh", tbdNckh);
        model.addAttribute("objectId", objectId);
        model.addAttribute("msg", "create");
        return "nckh-form";
    }

    @GetMapping("/giangVien/{objectId}/statistic/NCKH/{id}/edit")
    public String edit(@PathVariable Long id, @PathVariable Long objectId, Model model) {
        model.addAttribute("nckh", nckhService.findById(id));
        model.addAttribute("objectId", objectId);
        return "nckh-form";
    }

    @GetMapping("/giangVien/{objectId}/statistic/NCKH/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirect) {
        nckhService.delete(id);
        redirect.addFlashAttribute("success", "Thành công!!!");
        return "redirect:/giangVien/{objectId}/statistic/NCKH";
    }
}
