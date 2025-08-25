package com.code_tamabayan.e_commerce.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartRequestDto {
    private Long productId;
    private Integer quantity;
}
