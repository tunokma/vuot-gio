/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kma.quanlygiangday.controller;

import com.kma.quanlygiangday.model.BoMon;
import com.kma.quanlygiangday.model.Khoa;
import com.kma.quanlygiangday.service.BoMonService;
import com.kma.quanlygiangday.service.KhoaService;
import java.util.ArrayList;
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
public class BoMonController {

    private static final Logger logger = LoggerFactory.getLogger(BoMonController.class);

    @Autowired
    BoMonService boMonService;

    @Autowired
    KhoaService khoaService;

    @RequestMapping("/boMon")
    public String boMon(Model model) {
        logger.info("/boMon");
        model.addAttribute("boMons", boMonService.findAll());
        return "boMon";
    }

    @PostMapping("/boMon/save")
    public String save(@Valid BoMon boMon, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return "boMon-form";
        }
        boMonService.save(boMon);
        redirect.addFlashAttribute("success", "Thành công!");
        return "redirect:/boMon";
    }

    @GetMapping("/boMon/create")
    public String create(Model model) {
        model.addAttribute("boMon", new BoMon());
        Map<Long, String> mapTenKhoa = boMonService.getTenKhoaBy();
        model.addAttribute("mapTenKhoa", mapTenKhoa);
        return "boMon-form";
    }

    @GetMapping("/boMon/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        model.addAttribute("boMon", boMonService.findById(id));
        Map<Long, String> mapTenKhoa = boMonService.getTenKhoaBy();
        model.addAttribute("mapTenKhoa", mapTenKhoa);
        return "boMon-form";
    }

    @GetMapping("/boMon/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirect) {
        boMonService.delete(id);
        redirect.addFlashAttribute("success", "Thành công!");
        return "redirect:/boMon";
    }

    @GetMapping("/boMon/search")
    public String search(@RequestParam("search") String s, Model model) {
        if (s.equals("")) {
            return "redirect:/boMon";
        }

        model.addAttribute("boMons", boMonService.search(s));
        return "boMon";
    }
}
