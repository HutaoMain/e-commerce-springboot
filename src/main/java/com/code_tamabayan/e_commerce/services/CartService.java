package com.code_tamabayan.e_commerce.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.code_tamabayan.e_commerce.dto.CartAndOrderRequestDto;
import com.code_tamabayan.e_commerce.dto.ResponseDto;
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

    public Cart addToCart(CartAndOrderRequestDto cartAndOrderRequestDto) {
        Product product = productService.getProductById(cartAndOrderRequestDto.getProductId());

        Double totalPricePerProduct = product.getPrice() * cartAndOrderRequestDto.getQuantity();

        Cart cart = new Cart();
        cart.setProduct(product);
        cart.setQuantity(cartAndOrderRequestDto.getQuantity());
        cart.setPricePerProduct(totalPricePerProduct);
        return cartRepository.save(cart);
    }

    // dapat productId and userId para di masama ung product ng ibang user
    public ResponseDto deleteProductsInCartByProductId(Long productId) {
        ResponseDto response = new ResponseDto();
        try {

            Product product = productService.getProductById(productId);

            if (product == null) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product not found");
            }

            List<Cart> cartProducts = cartRepository.findByProduct(product);

            for (Cart cartItems : cartProducts) {
                cartRepository.deleteById(cartItems.getId());
            }

            response.setMessage("Successfully deleted the items in the cart.");
        } catch (Exception e) {
            response.setMessage("Error happen during deleting of products in cart: " + e.getMessage());
        }

        return response;
    }
}
