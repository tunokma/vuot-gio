package com.kma.quanlygiangday.controller;

import com.kma.quanlygiangday.model.GiangVien;
import com.kma.quanlygiangday.model.Khoa;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.kma.quanlygiangday.model.User;
import com.kma.quanlygiangday.service.GiangVienService;
import com.kma.quanlygiangday.service.KhoaService;
import com.kma.quanlygiangday.service.TaskService;
import com.kma.quanlygiangday.service.UserService;
import com.kma.quanlygiangday.utils.PassEncoding;
import com.kma.quanlygiangday.utils.Roles;

/**
 *
 * @author Tuno
 */
@Controller
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    GlobalController globalController;

    @Autowired
    KhoaService khoaService;

    @Autowired
    TaskService taskService;
    
    @Autowired
    GiangVienService giangVienService;

    @Autowired
    UserService userService;

    @RequestMapping("/")
    public String root(Model model) {
        model.addAttribute("reqUser", new GiangVien());
        logger.info("root");
        return "login";
    }

    @RequestMapping("/login")
    public String login(Model model) {
        model.addAttribute("reqUser", new GiangVien());
        logger.info("login");
        return "login";
    }


    @RequestMapping("/khoa")
    public String helloAdmin(Model model) {
        logger.info("khoa");
        model.addAttribute("khoas", khoaService.findAll());
        return "khoa";
    }

    @RequestMapping("/register")
    public String register(Model model) {
        model.addAttribute("reqUser", new User());
        logger.info("register");
        return "register";
    }

    @RequestMapping(value = {"/user/register"}, method = RequestMethod.POST)
    public String register(@ModelAttribute("reqUser") User reqUser,
            final RedirectAttributes redirectAttributes) {

        logger.info("/user/register");
        User user = userService.findByUserName(reqUser.getUsername());
        if (user != null) {
            redirectAttributes.addFlashAttribute("saveUser", "exist-name");
            return "redirect:/register";
        }
        user = userService.findByEmail(reqUser.getEmail());
        if (user != null) {
            redirectAttributes.addFlashAttribute("saveUser", "exist-email");
            return "redirect:/register";
        }

        reqUser.setPassword(PassEncoding.getInstance().passwordEncoder.encode(reqUser.getPassword()));
        reqUser.setRole(Roles.ROLE_USER.getValue());
        if (userService.save(reqUser) != null) {
            redirectAttributes.addFlashAttribute("saveUser", "success");
        } else {
            redirectAttributes.addFlashAttribute("saveUser", "fail");
        }

        return "redirect:/register";
    }

}
