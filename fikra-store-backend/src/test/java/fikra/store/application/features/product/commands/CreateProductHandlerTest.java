package fikra.store.application.features.product.commands;

import fikra.store.application.exception.InvalidRequestException;
import fikra.store.application.repositories.ProductRepository;
import fikra.store.domain.Product;
import fikra.store.domain.Rating;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class CreateProductHandlerTest {

    static class InMemoryProductRepo implements ProductRepository {
        final List<Product> list = new ArrayList<>();

        @Override
        public Optional<Product> findById(Long id) {
            return list.stream().filter(p -> p.getId() != null && p.getId().equals(id)).findFirst();
        }

        @Override
        public List<Product> findAll() {
            return list;
        }

        @Override
        public Product save(Product product) {
            product.setId((long)(list.size() + 1));
            list.add(product);
            return product;
        }

        @Override
        public void deleteById(Long id) {
            list.removeIf(p -> p.getId() != null && p.getId().equals(id));
        }
    }

    @Test
    void createProductValid() {
        var repo = new InMemoryProductRepo();
        var handler = new CreateProductHandler(repo);
        var p = new Product("A", 10.0, "d", "cat", "/img", new Rating(4.5, 10));
        var saved = handler.execute(p);
        assertNotNull(saved.getId());
    }

    @Test
    void createProductNegativePriceThrows() {
        var repo = new InMemoryProductRepo();
        var handler = new CreateProductHandler(repo);
        var p = new Product("A", -1.0, "d", "cat", "/img", null);
        assertThrows(InvalidRequestException.class, () -> handler.execute(p));
    }

    @Test
    void createProductInvalidRatingThrows() {
        var repo = new InMemoryProductRepo();
        var handler = new CreateProductHandler(repo);
        var p = new Product("A", 10.0, "d", "cat", "/img", new Rating(6.0, 1));
        assertThrows(InvalidRequestException.class, () -> handler.execute(p));
    }
}
