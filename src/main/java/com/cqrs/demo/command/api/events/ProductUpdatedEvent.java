package com.cqrs.demo.command.api.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductUpdatedEvent {

    private String productId;
    private BigDecimal price;
    private int quantity;
}
