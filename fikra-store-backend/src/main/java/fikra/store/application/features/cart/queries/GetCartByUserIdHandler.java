package fikra.store.application.features.cart.queries;

import java.util.List;
import java.util.Objects;

import fikra.store.application.repositories.CartRepository;
import fikra.store.domain.Cart;

public class GetCartByUserIdHandler implements GetCartByUserIdQuery {

    private final CartRepository cartRepository;

    public GetCartByUserIdHandler(CartRepository cartRepository) {
        this.cartRepository = Objects.requireNonNull(cartRepository);
    }

    @Override
    public List<Cart> execute(Long userId) {
        return cartRepository.findByUserId(userId);
    }
}