package com.dataport.sin.model.number;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;


@Data
public class NumbersDto {
    private List<Integer> numbers = new ArrayList<>();

    public void addNumber(Integer number){
        numbers.add(number);
    }
}
