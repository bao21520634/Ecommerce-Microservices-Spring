package com.example.cart_service.model;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Cart {
    private String userId;
    private List<CartItem> items;
    private BigDecimal total;
}
