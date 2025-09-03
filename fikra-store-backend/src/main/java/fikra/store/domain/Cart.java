package fikra.store.domain;

import java.util.Objects;

import lombok.Getter;
import lombok.Setter;

/**
 * Domain model for Cart. This is intentionally simple:
 * real business rules (e.g., quantities, ownership checks) should be
 * implemented in domain services / use cases.
 */

@Getter
@Setter
public class Cart {
    private Long id;
    private Long userId;
    private Long productId;

    public Cart() {}

    public Cart(Long id, Long userId, Long productId) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
    }

    public Cart(Long userId, Long productId) {
        this(null, userId, productId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Cart cart = (Cart) o;
        return Objects.equals(id, cart.id) &&
               Objects.equals(userId, cart.userId) &&
               Objects.equals(productId, cart.productId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userId, productId);
    }

    @Override
    public String toString() {
        return "Cart{" + "id=" + id + ", userId=" + userId + ", productId=" + productId + '}';
    }
}
