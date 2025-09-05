package fikra.store.application.features.product.commands;

import java.util.Objects;

import fikra.store.application.exception.ResourceNotFoundException;
import fikra.store.application.repositories.ProductRepository;
import fikra.store.domain.Product;

public class UpdateProductHandler implements UpdateProductCommand {

    private final ProductRepository productRepository;

    public UpdateProductHandler(ProductRepository productRepository) {
        this.productRepository = Objects.requireNonNull(productRepository);
    }

    @Override
    public Product execute(Long id, Product product) {
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found: " + id));

        existing.setTitle(product.getTitle());
        existing.setPrice(product.getPrice());
        existing.setDescription(product.getDescription());
        existing.setCategory(product.getCategory());
        existing.setImage(product.getImage());
        existing.setRating(product.getRating());

        return productRepository.save(existing);
    }
}