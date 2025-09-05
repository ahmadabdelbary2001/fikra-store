package fikra.store.application.features.product.queries;

import java.util.Objects;

import fikra.store.application.exception.ResourceNotFoundException;
import fikra.store.application.repositories.ProductRepository;
import fikra.store.domain.Product;

public class GetProductDetailsHandler implements GetProductDetailsQuery {

    private final ProductRepository productRepository;

    public GetProductDetailsHandler(ProductRepository productRepository) {
        this.productRepository = Objects.requireNonNull(productRepository);
    }

    @Override
    public Product execute(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Product not found: " + id));
    }
}
