/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kma.quanlygiangday.controller;

import com.kma.quanlygiangday.model.Khoa;
import com.kma.quanlygiangday.service.KhoaService;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Tuno
 */
@Controller
@ComponentScan
public class AdminController {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

    @Autowired
    GlobalController globalController;
    @Autowired
    private KhoaService khoaService;

       
    @PostMapping("/khoa/save")
    public String save(@Valid Khoa khoa, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return "khoa-form";
        }
        khoaService.save(khoa);
        redirect.addFlashAttribute("success", "Saved khoa successfully!");
        return "redirect:/khoa";
    }

    @GetMapping("/khoa/create")
    public String create(Model model) {
        model.addAttribute("khoa", new Khoa());
        return "khoa-form";
    }

    @GetMapping("/khoa/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("khoa", khoaService.findById(id));
        return "khoa-form";
    }
    
    @GetMapping("/khoa/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirect) {
        khoaService.delete(id);
        redirect.addFlashAttribute("success", "Xóa khoa thành công!");
        return "redirect:/khoa";
    }

    @GetMapping("/khoa/search")
    public String search(@RequestParam("s") String s, Model model) {
        if (s.equals("")) {
            return "redirect:/khoa";
        }

        model.addAttribute("khoas", khoaService.search(s));
        return "khoa";
    }
}
