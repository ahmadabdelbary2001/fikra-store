package fikra.store.application.features.product.commands;

import java.util.Objects;

import fikra.store.application.repositories.ProductRepository;
import fikra.store.domain.Product;

public class CreateProductHandler implements CreateProductCommand {

    private final ProductRepository productRepository;

    public CreateProductHandler(ProductRepository productRepository) {
        this.productRepository = Objects.requireNonNull(productRepository);
    }

    @Override
    public Product execute(Product product) {
        return productRepository.save(product);
    }
}