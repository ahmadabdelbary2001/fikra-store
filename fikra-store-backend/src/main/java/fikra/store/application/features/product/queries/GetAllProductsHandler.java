package fikra.store.application.features.product.queries;

import java.util.List;
import java.util.Objects;

import fikra.store.application.repositories.ProductRepository;
import fikra.store.domain.Product;

public class GetAllProductsHandler implements GetAllProductsQuery {

    private final ProductRepository productRepository;

    public GetAllProductsHandler(ProductRepository productRepository) {
        this.productRepository = Objects.requireNonNull(productRepository);
    }

    @Override
    public List<Product> execute() {
        return productRepository.findAll();
    }
}