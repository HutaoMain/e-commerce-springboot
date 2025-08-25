package com.code_tamabayan.e_commerce.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.code_tamabayan.e_commerce.entities.Cart;
import com.code_tamabayan.e_commerce.entities.Product;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {
    List<Cart> findByProduct(Product product);
}
