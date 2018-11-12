package com.kma.quanlygiangday.controller;

import com.kma.quanlygiangday.model.GiangVien;
import com.kma.quanlygiangday.service.GiangVienService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


@Component
@Scope(value = "session", proxyMode = ScopedProxyMode.TARGET_CLASS)
public class GlobalController {

    @Autowired
    private GiangVienService userService;

    private GiangVien loginUser;

    public GiangVien getLoginUser() {
        if (loginUser == null) {
            Authentication auth = SecurityContextHolder.getContext().getAuthentication();
            loginUser = userService.findByUsername(auth.getName());
        }
        return loginUser;
    }
}
