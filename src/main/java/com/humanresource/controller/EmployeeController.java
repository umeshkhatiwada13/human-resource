package com.humanresource.controller;

import com.humanresource.model.Employee;
import com.humanresource.service.EmployeeService;
import com.humanresource.service.UserService;
import com.humanresource.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("employee")
@RequiredArgsConstructor
@Slf4j
public class EmployeeController {
    private final EmployeeService employeeService;
    private final UserService userService;

    @GetMapping("list")
    public String getEmployeeList(Model model) {
        log.info("Employee Controller : getEmployeeList");
        model.addAttribute("baseUrl", StringUtils.baseUrl);
        model.addAttribute("title", "User List");
        model.addAttribute("employees", employeeService.findAll());
        return "employee/list";
    }

    @GetMapping("add")
    public String getAddPage(Model model) {
        log.info("Employee Controller : get Add page");
        model.addAttribute("baseUrl", StringUtils.baseUrl);
        model.addAttribute("title", "Add Employee");
        model.addAttribute("buttonTitle", "Add");
        model.addAttribute("users", userService.findAllUnassigned());
        return "employee/add";
    }

    @PostMapping("add")
    public String saveUser(@ModelAttribute Employee employee, Model model) {
        log.info("Employee Controller : Save Employee");
        employeeService.save(employee);
        model.addAttribute(employee.getId() != null ? "User edited Successfully" : "User added Successfully");
        return "redirect:/employee/list";
    }

    @GetMapping("edit/{id}")
    public String getAddPage(@PathVariable Integer id, Model model) throws Exception {
        log.info("Employee Controller : Edit Employee with id {}", id);
        model.addAttribute("baseUrl", StringUtils.baseUrl);
        model.addAttribute("title", "Edit User");
        model.addAttribute("buttonTitle", "Edit");
        model.addAttribute("employee", employeeService.findById(id));
        return "employee/add";
    }

    @GetMapping("delete/{id}")
    public String saveUser(@PathVariable Integer id, Model model) {
        log.info("Employee Controller : Delete Employee with id {}", id);
        employeeService.delete(id);
        return "redirect:/employee/list";
    }
}
