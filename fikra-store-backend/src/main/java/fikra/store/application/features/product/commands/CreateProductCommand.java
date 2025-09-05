package fikra.store.application.features.product.commands;

import fikra.store.domain.Product;

public interface CreateProductCommand {
    Product execute(Product product);
}