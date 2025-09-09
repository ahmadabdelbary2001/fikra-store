package fikra.store.adapters.presentation.controller;

import java.util.List;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import fikra.store.adapters.presentation.dto.CartDto;
import fikra.store.adapters.presentation.mapper.CartWebMapper;
import fikra.store.application.features.cart.commands.AddProductToCartCommand;
import fikra.store.application.features.cart.commands.RemoveProductFromCartCommand;
import fikra.store.application.features.cart.queries.GetCartByUserIdQuery;
import fikra.store.domain.Cart;

@RestController
@RequestMapping("/api/v1/cart")
@CrossOrigin(origins = "http://localhost:5500")
public class CartController {

    private static final Logger log = LoggerFactory.getLogger(CartController.class);

    private final AddProductToCartCommand addCmd;
    private final RemoveProductFromCartCommand removeCmd;
    private final GetCartByUserIdQuery getByUser;
    private final CartWebMapper mapper;

    public CartController(AddProductToCartCommand addCmd,
                          RemoveProductFromCartCommand removeCmd,
                          GetCartByUserIdQuery getByUser,
                          CartWebMapper mapper) {
        this.addCmd = addCmd;
        this.removeCmd = removeCmd;
        this.getByUser = getByUser;
        this.mapper = mapper;
    }

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<CartDto>> getCartByUserId(@PathVariable Long userId) {
        log.debug("GET /api/v1/cart/user/{}", userId);
        List<Cart> carts = getByUser.execute(userId);
        var dtos = carts.stream().map(mapper::toDto).toList();
        return ResponseEntity.ok(dtos);
    }

    @PostMapping
    public ResponseEntity<String> addProductToCart(@Valid @RequestBody CartDto dto) {
        log.info("POST /api/v1/cart user={} product={}", dto.getUserId(), dto.getProductId());
        addCmd.execute(mapper.toDomain(dto));
        return ResponseEntity.ok("Product added to cart successfully");
    }

    @DeleteMapping("/user/{userId}/product/{productId}")
    public ResponseEntity<Void> removeProductFromCart(@PathVariable Long userId, @PathVariable Long productId) {
        log.info("DELETE /api/v1/cart/user/{}/product/{}", userId, productId);
        removeCmd.execute(userId, productId);
        return ResponseEntity.noContent().build();
    }
}
