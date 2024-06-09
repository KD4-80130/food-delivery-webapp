package com.swaad.request;

import lombok.Data;

import java.util.List;


@Data
public class AddCartItemRequest {
    private Long foodId;

    private Integer quantity;

    private List<String>  ingredient;
}
