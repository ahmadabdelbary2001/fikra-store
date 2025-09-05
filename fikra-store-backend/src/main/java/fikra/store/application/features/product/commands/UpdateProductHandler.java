package fikra.store.application.features.product.commands;

import java.util.Objects;

import fikra.store.application.exception.InvalidRequestException;
import fikra.store.application.exception.ResourceNotFoundException;
import fikra.store.application.repositories.ProductRepository;
import fikra.store.domain.Product;
import fikra.store.domain.Rating;

public class UpdateProductHandler implements UpdateProductCommand {

    private final ProductRepository productRepository;

    public UpdateProductHandler(ProductRepository productRepository) {
        this.productRepository = Objects.requireNonNull(productRepository);
    }

    @Override
    public Product execute(Long id, Product product) {
        Objects.requireNonNull(product, "product must not be null");
        Product existing = productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found: " + id));

        validate(product);

        existing.setTitle(product.getTitle());
        existing.setPrice(product.getPrice());
        existing.setDescription(product.getDescription());
        existing.setCategory(product.getCategory());
        existing.setImage(product.getImage());
        existing.setRating(product.getRating());

        return productRepository.save(existing);
    }

    private void validate(Product product) {
        if (product.getTitle() == null || product.getTitle().isBlank()) {
            throw new InvalidRequestException("Product title must be provided");
        }
        if (product.getPrice() < 0.0) {
            throw new InvalidRequestException("Product price must be >= 0");
        }
        Rating r = product.getRating();
        if (r != null) {
            if (r.getRate() < 0.0 || r.getRate() > 5.0) {
                throw new InvalidRequestException("Rating.rate must be between 0 and 5");
            }
            if (r.getCount() < 0) {
                throw new InvalidRequestException("Rating.count must be >= 0");
            }
        }
    }
}
