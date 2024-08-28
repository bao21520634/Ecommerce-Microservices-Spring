package com.example.cart_service.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.ReactiveRedisTemplate;
import org.springframework.data.redis.core.ReactiveValueOperations;
import org.springframework.web.bind.annotation.*;
import com.example.cart_service.model.Cart;
import com.example.cart_service.model.CartItem;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;

@RestController
@CrossOrigin
@RequestMapping("/api/carts")
public class CartController {

    private static final Logger LOG = LoggerFactory.getLogger(CartController.class);

    private final ReactiveRedisTemplate<String, Cart> redisTemplate;
    private final ReactiveValueOperations<String, Cart> cartOps;

    public CartController(ReactiveRedisTemplate<String, Cart> redisTemplate) {
        this.redisTemplate = redisTemplate;
        this.cartOps = this.redisTemplate.opsForValue();
    }

    @GetMapping
    public Flux<Cart> list() {
        return redisTemplate.keys("*")
                .flatMap(cartOps::get);
    }

    @PostMapping
    public Mono<Void> create(@RequestBody Mono<Cart> cart) {
        return cart.doOnNext(c -> {
            LOG.info("Adding cart to Redis: {}", c);
            BigDecimal total = BigDecimal.ZERO;
            for (CartItem item : c.getItems()) {
                total = total.add(item.getPrice().multiply(new BigDecimal(item.getQuantity())));
            }
            c.setTotal(total);
            cartOps.set(c.getUserId(), c).subscribe();
        }).then();
    }

    @GetMapping("/{customerId}")
    public Mono<Cart> findById(@PathVariable String customerId) {
        return cartOps.get(customerId);
    }
}
