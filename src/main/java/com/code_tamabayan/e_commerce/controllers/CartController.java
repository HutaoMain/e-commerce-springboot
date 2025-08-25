package com.code_tamabayan.e_commerce.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.code_tamabayan.e_commerce.dto.CartRequestDto;
import com.code_tamabayan.e_commerce.dto.ResponseDto;
import com.code_tamabayan.e_commerce.services.CartService;

@RestController
@RequestMapping("/cart")
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @PostMapping("/add")
    public ResponseEntity<ResponseDto> addToCart(@RequestBody CartRequestDto cartAndOrderRequestDto) {
        return ResponseEntity.ok(cartService.addToCart(cartAndOrderRequestDto));
    }

    @DeleteMapping("/delete/{productId}")
    public ResponseEntity<ResponseDto> deleteProductsInCartByProductId(@PathVariable Long productId) {
        return ResponseEntity.ok(cartService.deleteProductsInCartByProductId(productId));
    }
}
