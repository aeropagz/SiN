package com.dataport.sin.model;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class NumberDto {
    private List<Integer> numbers = new ArrayList<>();

    public void addNumber(Integer number){
        numbers.add(number);
    }
}
