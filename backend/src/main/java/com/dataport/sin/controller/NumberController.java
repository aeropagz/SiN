package com.dataport.sin.controller;

import com.dataport.sin.model.number.NumbersDto;
import com.dataport.sin.model.number.SingleNumberDto;
import com.dataport.sin.service.DbService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
@RequestMapping("/api/number")
public class NumberController {

    private final DbService userService;

    public NumberController(DbService userService) {
        this.userService = userService;
    }


    @PostMapping()
    ResponseEntity<?> saveNumber(@RequestBody SingleNumberDto number) {
        try {
            NumbersDto numberDto = userService.saveNumber(number);
            return ResponseEntity.ok(numberDto);
        } catch (SQLException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

