package com.code_tamabayan.e_commerce.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartAndOrderRequestDto {
    private Long productId;
    private Integer quantity;
}
