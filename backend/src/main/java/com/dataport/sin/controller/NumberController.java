package com.dataport.sin.controller;

import com.dataport.sin.model.number.NumbersDto;
import com.dataport.sin.model.number.SingleNumberDto;
import com.dataport.sin.service.DbService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;

@RestController
@RequestMapping("/api/number")
public class NumberController {

    private final DbService userService;

    public NumberController(DbService userService) {
        this.userService = userService;
    }


    @PostMapping()
    ResponseEntity<NumbersDto> saveNumber(@RequestBody SingleNumberDto number) throws SQLException {
        NumbersDto numberDto = userService.saveNumber(number);
        return ResponseEntity.ok(numberDto);
    }
}

