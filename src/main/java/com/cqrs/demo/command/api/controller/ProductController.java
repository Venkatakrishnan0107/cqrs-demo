package com.cqrs.demo.command.api.controller;

import com.cqrs.demo.command.api.command.CreateProductCommand;
import com.cqrs.demo.command.api.command.UpdateProductCommand;
import com.cqrs.demo.command.api.model.ProductRestModel;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/products")
public class ProductController {

    private CommandGateway commandGateway;

    public ProductController(CommandGateway commandGateway){
        this.commandGateway = commandGateway;
    }

    @PostMapping
    public String addProduct(@RequestBody ProductRestModel product){

        CreateProductCommand createProductCommand = new CreateProductCommand();
        createProductCommand.setProductId(UUID.randomUUID().toString());
        createProductCommand.setProductName(product.getProductName());
        createProductCommand.setPrice(product.getPrice());
        createProductCommand.setQuantity(product.getQuantity());
        String result = commandGateway.sendAndWait(createProductCommand);
        return result;
    }

    @PutMapping("/update/{id}")
    public String updateProduct(@RequestBody ProductRestModel productRestModel, @PathVariable String id){

        UpdateProductCommand updateProductCommand = new UpdateProductCommand();
        updateProductCommand.setProductId(id);
        updateProductCommand.setPrice(productRestModel.getPrice());
        updateProductCommand.setQuantity(productRestModel.getQuantity());
        String result = commandGateway.sendAndWait(updateProductCommand);
        return result;
    }
}
