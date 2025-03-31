package com.example.spring.boot.controller;

import com.example.spring.boot.entity.User;
import com.example.spring.boot.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin")
@RequiredArgsConstructor
//@PreAuthorize("hasAuthority('ADMIN')")
public class RegisterController {

    private final UserService userService;

    @GetMapping("/add-user")
    public String addForm() {
        return "addUser";
    }

    @PostMapping("/add-user")
    @ResponseBody
    public String register(@RequestBody User user) {
        if (userService.getUser(user).isPresent()) {
            return "User already exists!";
        }
        userService.addUser(user);
        return "User registered!";
    }
}
