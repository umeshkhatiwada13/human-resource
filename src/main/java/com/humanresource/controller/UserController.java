package com.humanresource.controller;

import com.humanresource.auth.User;
import com.humanresource.service.UserService;
import com.humanresource.utils.StringUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("user")
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserService userService;

    @GetMapping("list")
    public String getEmployeeList(Model model) {
        log.info("UserController : getEmployeeList");
        model.addAttribute("baseUrl", StringUtils.baseUrl);
        model.addAttribute("title", "User List");
        model.addAttribute("users", userService.findAll());
        return "user/list";
    }

    @GetMapping("add")
    public String getAddPage(Model model) {
        log.info("UserController : get Add page");
        model.addAttribute("baseUrl", StringUtils.baseUrl);
        model.addAttribute("title", "Add User");
        model.addAttribute("buttonTitle", "Add");
        return "user/add";
    }

    @PostMapping("add")
    public String saveUser(@ModelAttribute User user, Model model) {
        log.info("UserController : Save Employee");
        userService.save(user);
        model.addAttribute(user.getId() != null ? "User edited Successfully" : "User added Successfully");
        return "redirect:/user/list";
    }
}
