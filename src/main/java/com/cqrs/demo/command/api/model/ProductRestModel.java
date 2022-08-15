package com.cqrs.demo.command.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.math.BigDecimal;

@Data
@AllArgsConstructor
public class ProductRestModel {

    private String productName;
    private BigDecimal price;
    private int quantity;
}
