package com.humanresource.controller;

import com.humanresource.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author umeshkhatiwada13@infodev
 * @project human-resource
 * @created 31/08/2022 - 18:03
 */

@Controller
@RequestMapping("email")
@Slf4j
@RequiredArgsConstructor
public class EmailController {

    private final EmployeeService employeeService;

    @GetMapping("resignation/{empId}")
    public String sendRegnisationMail(@PathVariable Integer empId) {
        log.info("Email Controller : Regination Letter by Employee with Id {}", empId);
        employeeService.sendTerminationMail(empId);
        return "redirect:/home";
    }
}
