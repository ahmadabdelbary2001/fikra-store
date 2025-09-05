package fikra.store.application.features.product.commands;


import java.util.Objects;

import fikra.store.application.repositories.ProductRepository;

public class DeleteProductHandler implements DeleteProductCommand {

    private final ProductRepository productRepository;

    public DeleteProductHandler(ProductRepository productRepository) {
        this.productRepository = Objects.requireNonNull(productRepository);
    }

    @Override
    public void execute(Long id) {
        productRepository.deleteById(id);
    }
}