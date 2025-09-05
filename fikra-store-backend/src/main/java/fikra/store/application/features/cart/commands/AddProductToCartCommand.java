package fikra.store.application.features.cart.commands;

import fikra.store.domain.Cart;

public interface AddProductToCartCommand {
    Cart execute(Cart cart);
}