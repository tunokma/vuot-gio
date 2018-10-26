/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kma.quanlygiangday.controller;

import com.kma.quanlygiangday.model.MonHoc;
import com.kma.quanlygiangday.service.BoMonService;
import com.kma.quanlygiangday.service.MonHocService;
import com.kma.quanlygiangday.viewmodel.MonHocVM;
import java.util.Map;
import javax.validation.Valid;
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
public class MonHocController {

    @Autowired
    BoMonService boMonService;

    @Autowired
    MonHocService monHocService;

    @RequestMapping("/monHoc")
    public String monHoc(Model model) {
        model.addAttribute("monHocs", monHocService.findAll());
        return "monHoc";
    }

    @PostMapping("/monHoc/save")
    public String save(@Valid MonHocVM monHocVM, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return "monHoc-form";
        }
        MonHoc monHoc = new MonHoc(monHocVM);
        monHocService.save(monHoc);
        redirect.addFlashAttribute("success", "Thành công!");
        return "redirect:/monHoc";
    }

    @GetMapping("/monHoc/create")
    public String create(Model model) {
        model.addAttribute("monHocVM", new MonHocVM());
        Map<Long, String> mapTenKhoa = monHocService.getTenKhoaBy();
        model.addAttribute("mapTenKhoa", mapTenKhoa);
        return "monHoc-form";
    }

    @GetMapping("/monHoc/{id}/edit")
    public String edit(@PathVariable Long id, Model model) {
        MonHocVM monHocVM = new MonHocVM(monHocService.findById(id));
        model.addAttribute("monHocVM", monHocVM);
        Map<Long, String> mapTenKhoa = monHocService.getTenKhoaBy();
        model.addAttribute("mapTenKhoa", mapTenKhoa);
        return "monHoc-form";
    }

    @GetMapping("/monHoc/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirect) {
        monHocService.delete(id);
        redirect.addFlashAttribute("success", "Thành công!");
        return "redirect:/monHoc";
    }

    @GetMapping("/monHoc/search")
    public String search(@RequestParam("search") String s, Model model) {
        if (s.equals("")) {
            return "redirect:/monHoc";
        }

        model.addAttribute("monHocs", monHocService.search(s));
        return "monHoc";
    }

}
