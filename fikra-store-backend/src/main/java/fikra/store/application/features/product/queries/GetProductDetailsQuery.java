package fikra.store.application.features.product.queries;

import fikra.store.domain.Product;

public interface GetProductDetailsQuery {
    Product execute(Long id);
}