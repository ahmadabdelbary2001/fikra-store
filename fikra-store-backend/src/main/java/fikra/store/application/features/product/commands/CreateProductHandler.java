package fikra.store.application.features.product.commands;

import java.util.Objects;

import fikra.store.application.exception.InvalidRequestException;
import fikra.store.application.repositories.ProductRepository;
import fikra.store.domain.Product;
import fikra.store.domain.Rating;

public class CreateProductHandler implements CreateProductCommand {

    private final ProductRepository productRepository;

    public CreateProductHandler(ProductRepository productRepository) {
        this.productRepository = Objects.requireNonNull(productRepository);
    }

    @Override
    public Product execute(Product product) {
        Objects.requireNonNull(product, "product must not be null");
        validate(product);
        return productRepository.save(product);
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
