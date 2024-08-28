package com.example.product_service.dto;

import java.math.BigDecimal;

public record ProductRequest(String id, String name, String skuCode, String slug, String description,
                              BigDecimal price) {
}