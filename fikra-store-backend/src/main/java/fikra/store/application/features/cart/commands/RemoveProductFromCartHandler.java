package fikra.store.application.features.cart.commands;

import java.util.Objects;

import fikra.store.application.repositories.CartRepository;

public class RemoveProductFromCartHandler implements RemoveProductFromCartCommand {

    private final CartRepository cartRepository;

    public RemoveProductFromCartHandler(CartRepository cartRepository) {
        this.cartRepository = Objects.requireNonNull(cartRepository);
    }

    @Override
    public void execute(Long userId, Long productId) {
        cartRepository.deleteByUserIdAndProductId(userId, productId);
    }
}