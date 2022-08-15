package com.cqrs.demo.command.api.events;

import com.cqrs.demo.command.api.ProductRepository;
import com.cqrs.demo.command.api.entity.Product;
import org.axonframework.config.ProcessingGroup;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Optional;

@Component
@ProcessingGroup("product")
public class ProductEventsHandler {

    @Autowired
    ProductRepository productRepository;

    @EventHandler
    public void on(ProductCreatedEvent productCreatedEvent){

        Product product = new Product();
        BeanUtils.copyProperties(productCreatedEvent,product);
        productRepository.save(product);
    }

    @EventHandler
    public void on(ProductUpdatedEvent productUpdatedEvent){

        Optional<Product> productList= productRepository.findById(productUpdatedEvent.getProductId());
        Product product = new Product();
        if(productList.isPresent())
            product = productList.get();
        product.setProductId(productUpdatedEvent.getProductId());
        product.setPrice(productUpdatedEvent.getPrice());
        product.setQuantity(productUpdatedEvent.getQuantity());
        productRepository.save(product);
    }

    @ExceptionHandler
    public void handle(Exception exception) throws Exception {
        throw exception;
    }

}
