package com.dataport.sin.controller;

import com.dataport.sin.model.LoginDto;
import com.dataport.sin.model.UserDto;
import com.dataport.sin.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/api/user")  //http://localhost:8080/api/user/login
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
        this.userService.initDb();
    }

    @GetMapping()
    public String hi() {
        return "hi";
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody LoginDto loginDto) throws SQLException {
        UserDto user = userService.login(loginDto);
        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }
}
