package com.code_tamabayan.e_commerce.services;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.code_tamabayan.e_commerce.entities.Product;
import com.code_tamabayan.e_commerce.repositories.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product addNewProduct(Product product) {
        return productRepository.save(product);
    }

    public Product getProductById(Long id) {
        return productRepository.findById(id).orElse(null);
    }

    public void subtractOrderQuantityToProduct(Long productId, Integer quantity) {
        Product product = productRepository.findById(productId).orElse(null);

        if (product == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product not found");
        }

        product.setQuantity(product.getQuantity() - quantity);
        productRepository.save(product);
    }
}
