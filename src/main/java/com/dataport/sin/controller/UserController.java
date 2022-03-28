package com.dataport.sin.controller;

import com.dataport.sin.model.LoginDto;
import com.dataport.sin.service.UserService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public String hi() {
        return "hi";
    }

    @PostMapping("/login")
    public String login(@RequestBody LoginDto loginDto) {
        return "Hello World";
    }
}
