package com.humanresource.controller;

import com.humanresource.auth.CustomUserDetails;
import com.humanresource.auth.User;
import com.humanresource.model.Employee;
import com.humanresource.service.EmployeeService;
import com.humanresource.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
@RequiredArgsConstructor
public class HomeController {

    private final EmployeeService employeeService;

    @GetMapping("login")
    public String getLoginView() {
        return "login";
    }

    @GetMapping("home")
    public String getHomePage(Model model) {
        model.addAttribute("baseUrl", StringUtils.baseUrl);
        model.addAttribute("title", "Home page");
        CustomUserDetails customUser = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = customUser.getUser();
        String role = user.getRole();
        model.addAttribute("user", user);
        if (role.equals("EMPLOYEE") || role.equals("HR")) {
            Employee employee = employeeService.findByUserId(user.getId());
            model.addAttribute("employee", employee);
        }

        return "home";
    }
}
