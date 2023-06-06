package com.drossdrop.productservice.service;

import com.drossdrop.productservice.dto.ProductRequest;
import com.drossdrop.productservice.dto.ProductResponse;
import com.drossdrop.productservice.model.Product;
import com.drossdrop.productservice.rabbitmq.RabbitMQProducer;
import com.drossdrop.productservice.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {

    @Autowired
    private RabbitMQProducer rabbitMQProducer;

    private final ProductRepository productRepository;

    public void createProduct(ProductRequest productRequest) {
        Product product = Product.builder()
                .name(productRequest.getName())
                .description(productRequest.getDescription())
                .price(productRequest.getPrice())
                .stock(productRequest.getStock())
                .build();

        productRepository.save(product);
        log.info("Product is {} created", product.getId());
//        rabbitMQProducer.sendMessage("myQueue", product.toString());
        rabbitMQProducer.sendProduct(product);
    }

    public List<ProductResponse> getAllProducts() {
        List<Product> products = productRepository.findAll();

        return products.stream().map(this::mapToProductResponse).toList();
    }

    // create function to get product by id and return product response object
    public ProductResponse getProductById(String id) {
        Product product = productRepository.findById(id).orElseThrow();
        return mapToProductResponse(product);
    }

    private ProductResponse mapToProductResponse(Product product) {
        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .stock(product.getStock())
                .build();
    }
}
