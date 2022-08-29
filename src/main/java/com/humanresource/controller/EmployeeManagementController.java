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
        model.addAttribute("buttonTitle", "Add");
        return "employee/add";
    }

    @PostMapping("add")
    public String saveUser(@ModelAttribute Employee employee, Model model) {
        employeeService.save(employee);
        model.addAttribute(employee.getId() != null ? "User edited Successfully" : "User added Successfully");
        return "redirect:/employee/list";
    }

    @GetMapping("edit/{id}")
    public String getAddPage(@PathVariable Integer id, Model model) throws Exception {
        model.addAttribute("baseUrl", StringUtils.baseUrl);
        model.addAttribute("title", "Edit User");
        model.addAttribute("buttonTitle", "Edit");
        model.addAttribute("employee", employeeService.findById(id));
        return "employee/add";
    }

    @DeleteMapping("delete/{id}")
    public String saveUser(@PathVariable Integer id, Model model) {
        employeeService.delete(id);
        return "redirect:/employee/list";
    }
}
