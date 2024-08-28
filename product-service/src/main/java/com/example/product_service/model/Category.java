package com.example.product_service.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Set;

@Document(value = "categories")
public class Category {
    @Id
    private String id;
    private String name;

    @DBRef
    private Set<Product> products;

    @DBRef
    private Category parentCategory;
}
