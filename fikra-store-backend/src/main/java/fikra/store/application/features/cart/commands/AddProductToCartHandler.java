package fikra.store.application.features.cart.commands;

import java.util.Objects;

import fikra.store.application.repositories.CartRepository;
import fikra.store.domain.Cart;

public class AddProductToCartHandler implements AddProductToCartCommand {

    private final CartRepository cartRepository;

    public AddProductToCartHandler(CartRepository cartRepository) {
        this.cartRepository = Objects.requireNonNull(cartRepository);
    }

    @Override
    public Cart execute(Cart cart) {
        return cartRepository.save(cart);
    }
}