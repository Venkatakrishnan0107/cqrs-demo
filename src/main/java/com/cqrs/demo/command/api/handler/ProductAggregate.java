package com.cqrs.demo.command.api.handler;

import com.cqrs.demo.command.api.command.CreateProductCommand;
import com.cqrs.demo.command.api.command.UpdateProductCommand;
import com.cqrs.demo.command.api.events.ProductCreatedEvent;
import com.cqrs.demo.command.api.events.ProductUpdatedEvent;
import lombok.Data;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.modelling.command.AggregateLifecycle;
import org.axonframework.spring.stereotype.Aggregate;
import org.springframework.beans.BeanUtils;

import java.math.BigDecimal;

@Aggregate
@Data
public class ProductAggregate {

    @AggregateIdentifier
    private String productId;
    private String productName;
    private BigDecimal price;
    private int quantity;

    public ProductAggregate() {
    }

    @CommandHandler
    public ProductAggregate(CreateProductCommand createProductCommand) {
        this.productId = createProductCommand.getProductId();
        ProductCreatedEvent productCreatedEvent = new ProductCreatedEvent();
        BeanUtils.copyProperties(createProductCommand,productCreatedEvent);
        AggregateLifecycle.apply(productCreatedEvent); //publish
    }

    @CommandHandler
    public void handle(UpdateProductCommand updateProductCommand){
        AggregateLifecycle.apply(new ProductUpdatedEvent(updateProductCommand.getProductId(),
                updateProductCommand.getPrice(),updateProductCommand.getQuantity()));
    }

    @EventSourcingHandler
    public void on(ProductCreatedEvent productCreatedEvent){
        this.productName = productCreatedEvent.getProductName();
        this.price = productCreatedEvent.getPrice();
        this.quantity = productCreatedEvent.getQuantity();
        this.productId = productCreatedEvent.getProductId();
    }

    @EventSourcingHandler
    public void on(ProductUpdatedEvent productUpdatedEvent){
        this.price = productUpdatedEvent.getPrice();
        this.quantity = productUpdatedEvent.getQuantity();
        this.productId = productUpdatedEvent.getProductId();
    }
}
