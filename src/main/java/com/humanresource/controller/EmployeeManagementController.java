package com.humanresource.controller;

import com.humanresource.auth.User;
import com.humanresource.model.Employee;
import com.humanresource.service.EmployeeService;
import com.humanresource.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("employee")
@RequiredArgsConstructor
public class EmployeeManagementController {
    private final EmployeeService employeeService;

    @GetMapping("list")
    public String getCourses(Model model) {
        model.addAttribute("baseUrl", StringUtils.baseUrl);
        model.addAttribute("title", "User List");
        model.addAttribute("employees", employeeService.findAll());
        return "employee/list";
    }

    @GetMapping("add")
    public String getAddPage(Model model) {
        model.addAttribute("baseUrl", StringUtils.baseUrl);
        model.addAttribute("title", "Add User");
        return "employee/add";
    }

    @PostMapping("add")
    public String saveUser(@ModelAttribute Employee employee) {
        employeeService.save(employee);
        return "redirect:/employee/list";
    }
}
