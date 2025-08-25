package com.code_tamabayan.e_commerce.services;

import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.code_tamabayan.e_commerce.dto.OrderRequestDto;
import com.code_tamabayan.e_commerce.dto.ResponseDto;
import com.code_tamabayan.e_commerce.entities.Cart;
import com.code_tamabayan.e_commerce.entities.Order;
import com.code_tamabayan.e_commerce.entities.Product;
import com.code_tamabayan.e_commerce.repositories.OrderRepository;

@Service
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductService productService;
    private final CartService cartService;

    public OrderService(OrderRepository orderRepository, ProductService productService, CartService cartService) {
        this.orderRepository = orderRepository;
        this.productService = productService;
        this.cartService = cartService;
    }

    public ResponseDto addNewOrder(OrderRequestDto orderRequestDto) {
        ResponseDto response = new ResponseDto();

        try {

            for (Long productId : orderRequestDto.getProductIdList()) {
                Product product = productService.getProductById(productId);

                if (Objects.isNull(product)) {
                    throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product not found");
                }

                Cart cart = cartService.getCartItemByProduct(product);

                Order order = new Order();
                order.setProduct(cart.getProduct());
                order.setQuantity(cart.getQuantity());
                order.setPricePerProduct(cart.getPricePerProduct());
                orderRepository.save(order);

                cartService.deleteProductsInCartByProductId(product.getId());

                productService.subtractOrderQuantityToProduct(productId,
                        cart.getQuantity());
            }

            response.setMessage("Successfully ordered the products.");
            return response;
        } catch (Exception e) {
            response.setMessage("Error happen while ordering products: " + e.getMessage());
            return response;
        }
    }
}
