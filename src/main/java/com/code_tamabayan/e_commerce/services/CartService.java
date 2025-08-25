package com.code_tamabayan.e_commerce.services;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.code_tamabayan.e_commerce.entities.Cart;
import com.code_tamabayan.e_commerce.entities.Product;
import com.code_tamabayan.e_commerce.repositories.CartRepository;

@Service
public class CartService {

    private final CartRepository cartRepository;
    private final ProductService productService;

    public CartService(CartRepository cartRepository, ProductService productService) {
        this.cartRepository = cartRepository;
        this.productService = productService;
    }

    public Cart addToCart(Long productId) {
        Product product = productService.getProductById(productId);

        Cart cart = new Cart();
        cart.setProduct(product);
        return cartRepository.save(cart);
    }

    public void removeProductInCartByProductId(Long productId) {
        Product product = productService.getProductById(productId);

        if (product == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product not found");
        }

        cartRepository.deleteByProduct(product);
    }
}
