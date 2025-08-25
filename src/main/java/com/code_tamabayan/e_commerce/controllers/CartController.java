package com.code_tamabayan.e_commerce.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.code_tamabayan.e_commerce.entities.Cart;
import com.code_tamabayan.e_commerce.services.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add")
    public ResponseEntity<Cart> addToCart(@RequestBody Long productId) {
        return ResponseEntity.ok(cartService.addToCart(productId));
    }

}
