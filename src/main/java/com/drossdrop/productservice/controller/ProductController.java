package com.drossdrop.productservice.controller;

import com.drossdrop.productservice.dto.ProductRequest;
import com.drossdrop.productservice.dto.ProductResponse;
import com.drossdrop.productservice.service.ProductService;
import com.drossdrop.productservice.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HttpStatus createProduct(@RequestBody ProductRequest productRequest, @RequestHeader("Authorization") String authorizationHeader) {
        String jwtToken = authorizationHeader.substring(7);
        // function to get claims from jwt token
        String role = jwtUtil.getRolesFromJWT(jwtToken);

        if (role.contains("Admin")) {
            productService.createProduct(productRequest);
        } else {
            return HttpStatus.FORBIDDEN;
        }
        return null;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductResponse> getAllProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductResponse getProductById(@PathVariable String id) {
        return productService.getProductById(id);
    }
}

