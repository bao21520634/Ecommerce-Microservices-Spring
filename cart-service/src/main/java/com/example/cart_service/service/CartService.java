package com.example.cart_service.service;

import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import com.example.cart_service.model.Cart;
import com.example.cart_service.controller.CartController;

@Service
public class CartService {

    private final CartController cartController;

    public CartService(CartController cartController) {
        this.cartController = cartController;
    }

    public Mono<Cart> getCartById(String customerId) {
        return cartController.findById(customerId);
    }

    public Mono<Void> saveCart(Mono<Cart> cart) {
        return cartController.create(cart);
    }
}
