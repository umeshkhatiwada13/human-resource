package com.humanresource.controller;

import com.humanresource.service.EmployeeService;
import com.humanresource.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeController {

    @GetMapping("login")
    public String getLoginView() {
        return "login";
    }

    @GetMapping("home")
    public String getHomePage(Model model) {
        model.addAttribute("baseUrl", StringUtils.baseUrl);
        model.addAttribute("title", "Home page");
        return "home";
    }
}
