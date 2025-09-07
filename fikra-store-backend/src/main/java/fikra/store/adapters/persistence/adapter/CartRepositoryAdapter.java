package fikra.store.adapters.persistence.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import fikra.store.application.repositories.CartRepository;
import fikra.store.adapters.persistence.jpa.repositories.JpaCartRepository;
import fikra.store.adapters.persistence.mapper.CartMapper;
import fikra.store.domain.Cart;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class CartRepositoryAdapter implements CartRepository {

    private final JpaCartRepository jpa;
    private final CartMapper mapper;

    @Override
    public Optional<Cart> findById(Long id) {
        return jpa.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Cart> findByUserId(Long userId) {
        return jpa.findByUserId(userId).stream().map(mapper::toDomain).collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Cart save(Cart cart) {
        var entity = mapper.toEntity(cart);
        var saved = jpa.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    @Transactional
    public void deleteByUserIdAndProductId(Long userId, Long productId) {
        jpa.deleteByUserIdAndProductId(userId, productId);
    }
}
