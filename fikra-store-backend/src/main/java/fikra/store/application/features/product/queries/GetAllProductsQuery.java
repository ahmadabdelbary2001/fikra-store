package fikra.store.application.features.product.queries;

import java.util.List;

import fikra.store.domain.Product;

public interface GetAllProductsQuery {
    List<Product> execute();
}