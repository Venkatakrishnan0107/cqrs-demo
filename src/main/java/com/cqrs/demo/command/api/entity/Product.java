package com.cqrs.demo.command.api.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Data
@Entity
public class Product {

    @Id
    private String productId;
    private String productName;
    private BigDecimal price;
    private int quantity;
}
