package com.cqrs.demo;

import com.cqrs.demo.command.api.exception.ProductServiceErrorHandler;
import org.axonframework.config.EventProcessingConfigurer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProductServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProductServiceApplication.class, args);
	}

	@Autowired
	public void configure(EventProcessingConfigurer eventProcessingConfigurer){
		eventProcessingConfigurer.registerListenerInvocationErrorHandler("product",e->new ProductServiceErrorHandler());
	}
}
