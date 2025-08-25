package com.code_tamabayan.e_commerce.services;

import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.code_tamabayan.e_commerce.dto.CartRequestDto;
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

    public ResponseDto addToCart(CartRequestDto cartAndOrderRequestDto) {
        ResponseDto response = new ResponseDto();
        try {
            Product product = productService.getProductById(cartAndOrderRequestDto.getProductId());

            if (Objects.isNull(product)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product not found");
            }

            Cart cartItem = cartRepository.findByProduct(product); // dapat findByProductandUserIdOrUserEmail

            Double totalPriceAdded = product.getPrice() * cartAndOrderRequestDto.getQuantity();

            if (Objects.nonNull(cartItem)) {
                cartItem.setPricePerProduct(cartItem.getPricePerProduct() + totalPriceAdded);
                cartItem.setQuantity(cartItem.getQuantity() + cartAndOrderRequestDto.getQuantity());
                cartItem.setProduct(product);
                cartRepository.save(cartItem);
                response.setMessage("Successfully updated the product in the cart.");
            } else {
                Cart cart = new Cart();
                cart.setProduct(product);
                cart.setQuantity(cartAndOrderRequestDto.getQuantity());
                cart.setPricePerProduct(totalPriceAdded);
                cartRepository.save(cart);
                response.setMessage("Successfully added new product in the cart.");
            }
        } catch (Exception e) {
            response.setMessage("Error happen while adding product in the cart: " + e.getMessage());
        }

        return response;
    }

    // dapat productId and userId para di masama ung product ng ibang user
    public ResponseDto deleteProductsInCartByProductId(Long productId) {
        ResponseDto response = new ResponseDto();
        try {

            Product product = productService.getProductById(productId);

            if (Objects.isNull(product)) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product not found");
            }

            Cart cartProducts = cartRepository.findByProduct(product);

            cartRepository.deleteById(cartProducts.getId());

            response.setMessage("Successfully deleted the products in the cart.");
        } catch (Exception e) {
            response.setMessage("Error happen during deleting of products in cart: " + e.getMessage());
        }

        return response;
    }

    public Cart getCartItemByProduct(Product product) {
        return cartRepository.findByProduct(product);
    }
}
