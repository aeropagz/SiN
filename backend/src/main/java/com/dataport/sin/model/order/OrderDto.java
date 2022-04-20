package com.dataport.sin.model.order;

import com.dataport.sin.model.user.UserDto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderDto {
    private int did;
    private int amount;
    private String good;
    private int customer;
}
