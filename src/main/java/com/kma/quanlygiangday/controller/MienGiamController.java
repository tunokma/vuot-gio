/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kma.quanlygiangday.controller;

import com.kma.quanlygiangday.model.TbdMienGiam;
import com.kma.quanlygiangday.service.MienGiamService;
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
public class MienGiamController {
    
    @Autowired
    MienGiamService mienGiamService;
    
    private static final Logger logger = LoggerFactory.getLogger(MienGiamController.class);
    
    @RequestMapping("/mienGiam")
    public String mienGiam(Model model) {
        logger.info("/mienGiam");
        model.addAttribute("mienGiams", mienGiamService.findAll());
        return "mienGiam";
    }

    @PostMapping("/mienGiam/save")
    public String save(@Valid TbdMienGiam mienGiam, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return "mienGiam-form";
        }
        mienGiamService.save(mienGiam);
        redirect.addFlashAttribute("success", "Thành công!");
        return "redirect:/mienGiam";
    }

    @GetMapping("/mienGiam/create")
    public String create(Model model) {
        model.addAttribute("mienGiam", new TbdMienGiam());
        return "mienGiam-form";
    }

    @GetMapping("/mienGiam/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("mienGiam", mienGiamService.findById(id));
        return "mienGiam-form";
    }

    @GetMapping("/mienGiam/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirect) {
        mienGiamService.delete(id);
        redirect.addFlashAttribute("success", "Xóa thành công!");
        return "redirect:/mienGiam";
    }

    @GetMapping("/mienGiam/search")
    public String search(@RequestParam("search") String s, Model model) {
        if (s.equals("")) {
            return "redirect:/mienGiam";
        }

        model.addAttribute("mienGiams", mienGiamService.search(s));
        return "mienGiam";
    }
}
