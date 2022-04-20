package com.dataport.sin.controller;


import com.dataport.sin.model.order.OrderDto;
import com.dataport.sin.service.DbService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/api/order")
public class OrderController {

    private final DbService dbService;

    public OrderController(DbService dbService) {
        this.dbService = dbService;
    }

    @PostMapping
    public ResponseEntity<?> createOrder(@RequestBody OrderDto orderDto) {
        try {
            OrderDto savedOrder = dbService.saveOrder(orderDto);
            return ResponseEntity.ok(savedOrder);
        } catch (SQLException e) {
            return new ResponseEntity<String>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/all/{session}")
    public ResponseEntity<?> getOrders(@PathVariable("session") String session) throws SQLException {
        if (session != null) {
            List<OrderDto> orders = dbService.getOrdersByUserId(session);
            return ResponseEntity.ok(orders);
        } else {
            return new ResponseEntity<String>("No Session id provied", HttpStatus.BAD_REQUEST);
        }
    }
}
