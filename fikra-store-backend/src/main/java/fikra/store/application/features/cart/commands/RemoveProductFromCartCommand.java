package fikra.store.application.features.cart.commands;

public interface RemoveProductFromCartCommand {
    void execute(Long userId, Long productId);
}