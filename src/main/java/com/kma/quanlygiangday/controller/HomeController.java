/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.kma.quanlygiangday.controller;

import com.kma.quanlygiangday.model.GiangVien;
import com.kma.quanlygiangday.service.GiangVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Tuno
 */
@Controller
@ComponentScan
public class HomeController {

    @Autowired
    GiangVienService giangVienService;
    GiangVien giangVien;

    @RequestMapping("/trangChu")
    public String helloAdmin(Model model) {
        giangVien = giangVienService.findByUsername(SecurityContextHolder.getContext().getAuthentication().getName());
        return "trangChu";
    }
}
