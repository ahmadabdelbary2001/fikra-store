package fikra.store.adapters.persistence.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import fikra.store.application.repositories.ProductRepository;
import fikra.store.adapters.persistence.jpa.repositories.JpaProductRepository;
import fikra.store.adapters.persistence.mapper.ProductMapper;
import fikra.store.domain.Product;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProductRepositoryAdapter implements ProductRepository {

    private final JpaProductRepository jpa;
    private final ProductMapper mapper;

    @Override
    public Optional<Product> findById(Long id) {
        return jpa.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Product> findAll() {
        return mapper.toDomainList(jpa.findAll());
    }

    @Override
    @Transactional
    public Product save(Product product) {
        var entity = mapper.toEntity(product);
        var saved = jpa.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        jpa.deleteById(id);
    }
}
