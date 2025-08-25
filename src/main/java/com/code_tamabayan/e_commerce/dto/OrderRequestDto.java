package com.code_tamabayan.e_commerce.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class OrderRequestDto {
    List<Long> productIdList;
}
