package fikra.store.adapters.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fikra.store.adapters.persistence.jpa.entities.CartEntity;

import java.util.List;

@Repository
public interface JpaCartRepository extends JpaRepository<CartEntity, Long> {
    void deleteByUserIdAndProductId(Long userId, Long productId);
    List<CartEntity> findByUserId(Long userId);
}
