package com.example.spring.boot.controller;

import com.example.spring.boot.entity.User;
import com.example.spring.boot.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
@Slf4j
public class RegisterController {

    private final UserService userService;

    @GetMapping("/add-user")
    public String addForm(Model model) {
        model.addAttribute("user", new User());
        return "addUser";
    }

    @PostMapping("/add-user")
    @ResponseBody
    public String register(@ModelAttribute("user") User user) {
        log.info("Registering user: {}", user.getUsername());
        if (userService.getUser(user).isPresent()) {
            return "User already exists!";
        }
        userService.addUser(user);
        return "User registered!";
    }
}
