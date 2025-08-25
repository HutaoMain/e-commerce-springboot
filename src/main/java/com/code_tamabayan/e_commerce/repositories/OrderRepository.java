package com.code_tamabayan.e_commerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.code_tamabayan.e_commerce.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {

}
