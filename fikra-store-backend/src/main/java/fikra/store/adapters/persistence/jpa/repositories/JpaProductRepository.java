package fikra.store.adapters.persistence.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fikra.store.adapters.persistence.jpa.entities.ProductEntity;

@Repository
public interface JpaProductRepository extends JpaRepository<ProductEntity, Long> {
    // JpaRepository already provides findById, findAll, save, deleteById, etc.
}
