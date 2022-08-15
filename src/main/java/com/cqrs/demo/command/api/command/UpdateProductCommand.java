package com.cqrs.demo.command.api.command;

import lombok.Data;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

import java.math.BigDecimal;

@Data
public class UpdateProductCommand {

    @TargetAggregateIdentifier
    private String productId;
    private BigDecimal price;
    private int quantity;
}
