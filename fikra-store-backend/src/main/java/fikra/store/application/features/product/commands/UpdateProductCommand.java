package fikra.store.application.features.product.commands;

import fikra.store.domain.Product;

public interface UpdateProductCommand {
    Product execute(Long id, Product product);
}