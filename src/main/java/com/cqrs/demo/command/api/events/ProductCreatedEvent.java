package com.cqrs.demo.command.api.events;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.axonframework.spring.stereotype.Aggregate;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ProductCreatedEvent {

    private String productId;
    private String productName;
    private BigDecimal price;
    private int quantity;
}
