package com.cqrs.demo.query.api.projection;

import com.cqrs.demo.command.api.ProductRepository;
import com.cqrs.demo.command.api.entity.Product;
import com.cqrs.demo.command.api.model.ProductRestModel;
import com.cqrs.demo.query.api.queries.GetProductsQuery;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductProjection {

    @Autowired
    ProductRepository productRepository;

    @QueryHandler
    public List<ProductRestModel> handle(GetProductsQuery getProductsQuery){

        List<Product> productList = productRepository.findAll();
        List<ProductRestModel> productRestModelList = productList.stream().map(product -> new ProductRestModel(product.getProductName(),product.getPrice(),product.getQuantity()))
                .collect(Collectors.toList());
        return productRestModelList;
    }
}
