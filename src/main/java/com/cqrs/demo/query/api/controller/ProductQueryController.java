package com.cqrs.demo.query.api.controller;

import com.cqrs.demo.command.api.model.ProductRestModel;
import com.cqrs.demo.query.api.queries.GetProductsQuery;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductQueryController {

    QueryGateway queryGateway;

    public ProductQueryController(QueryGateway queryGateway) {
        this.queryGateway = queryGateway;
    }

    @GetMapping
    public List<ProductRestModel> getAllProducts(){
        GetProductsQuery query = new GetProductsQuery();
        List<ProductRestModel> productRestModelList = queryGateway.query(query, ResponseTypes.multipleInstancesOf(ProductRestModel.class
        )).join();
        return productRestModelList;
    }
}
