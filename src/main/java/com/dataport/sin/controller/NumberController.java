package com.dataport.sin.controller;

import com.dataport.sin.model.NumbersDto;
import com.dataport.sin.model.SingleNumberDto;
import com.dataport.sin.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/api/number")
public class NumberController {

    private final UserService userService;

    public NumberController(UserService userService) {
        this.userService = userService;
    }


    @PostMapping()
    ResponseEntity<NumbersDto> saveNumber(@RequestBody SingleNumberDto number) throws SQLException {
        NumbersDto numberDto = userService.saveNumber(number);
        return ResponseEntity.ok(numberDto);
    }
}

