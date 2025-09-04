package fikra.store.application.repositories;

import fikra.store.domain.Cart;
import java.util.List;
import java.util.Optional;

public interface CartRepository {
    Optional<Cart> findById(Long id);
    List<Cart> findByUserId(Long userId);
    Cart save(Cart cart);
    void deleteByUserIdAndProductId(Long userId, Long productId);
}