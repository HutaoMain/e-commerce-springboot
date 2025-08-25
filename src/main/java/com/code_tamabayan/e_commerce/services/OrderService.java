package com.code_tamabayan.e_commerce.services;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

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

    public Order addNewOrder(Long productId) {
        Product product = productService.getProductById(productId);

        if (product == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Product not found");
        }

        cartService.removeProductInCartByProductId(productId);

        Order order = new Order();
        order.setProduct(product);

        productService.subtractOrderQuantityToProduct(productId, null);
        return orderRepository.save(null);
    }
}
