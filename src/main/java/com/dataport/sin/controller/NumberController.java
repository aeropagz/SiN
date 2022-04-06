package com.dataport.sin.controller;

import com.dataport.sin.model.NumberDto;
import com.dataport.sin.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("/api/number")
public class NumberController {

    private final UserService userService;

    public NumberController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping("{number}")
    ResponseEntity<NumberDto> saveNumber(@PathVariable("number") Integer number) throws SQLException {
        NumberDto numberDto = userService.saveNumber(number);
        return ResponseEntity.ok(numberDto);
    }
}

