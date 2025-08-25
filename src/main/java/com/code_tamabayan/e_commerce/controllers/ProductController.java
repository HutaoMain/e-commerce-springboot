package com.code_tamabayan.e_commerce.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.code_tamabayan.e_commerce.entities.Product;
import com.code_tamabayan.e_commerce.services.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @PostMapping("/add")
    ResponseEntity<Product> addNewProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productService.addNewProduct(product));
    }
}
