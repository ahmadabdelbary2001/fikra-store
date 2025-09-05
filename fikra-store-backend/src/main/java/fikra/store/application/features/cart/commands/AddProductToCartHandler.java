package fikra.store.application.features.cart.commands;

import java.util.Objects;

import fikra.store.application.exception.InvalidRequestException;
import fikra.store.application.repositories.CartRepository;
import fikra.store.domain.Cart;

public class AddProductToCartHandler implements AddProductToCartCommand {

    private final CartRepository cartRepository;

    public AddProductToCartHandler(CartRepository cartRepository) {
        this.cartRepository = Objects.requireNonNull(cartRepository);
    }

    @Override
    public Cart execute(Cart cart) {
        Objects.requireNonNull(cart, "cart must not be null");
        if (cart.getUserId() == null) throw new InvalidRequestException("userId is required");
        if (cart.getProductId() == null) throw new InvalidRequestException("productId is required");
        return cartRepository.save(cart);
    }
}
