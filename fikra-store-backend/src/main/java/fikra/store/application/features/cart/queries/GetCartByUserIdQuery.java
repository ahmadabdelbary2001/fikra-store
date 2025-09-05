package fikra.store.application.features.cart.queries;

import java.util.List;

import fikra.store.domain.Cart;

public interface GetCartByUserIdQuery {
    List<Cart> execute(Long userId);
}