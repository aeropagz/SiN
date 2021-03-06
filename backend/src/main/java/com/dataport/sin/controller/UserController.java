package com.dataport.sin.controller;

import com.dataport.sin.model.user.LoginDto;
import com.dataport.sin.model.user.RegisterDto;
import com.dataport.sin.model.user.UserDto;
import com.dataport.sin.service.DbService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.Arrays;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final DbService userService;

    public UserController(DbService userService) {
        this.userService = userService;
        this.userService.initDb();
    }


    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterDto registerDto) {
        try {
            userService.register(registerDto);
            return ResponseEntity.ok("Register succesfull!");
        } catch (SQLException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.FORBIDDEN);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<UserDto> login(@RequestBody LoginDto loginDto) throws SQLException {
        UserDto user = userService.login(loginDto);
        if (user != null) {
            // set user id as session in custom header
            HttpHeaders httpHeaders = new HttpHeaders();
            // expose header to cors request
            httpHeaders.setAccessControlExposeHeaders(Arrays.asList("session"));
            httpHeaders.set("session", user.getId().toString());
            return ResponseEntity
                    .ok()
                    .headers(httpHeaders)
                    .body(user);
        } else {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }
}
