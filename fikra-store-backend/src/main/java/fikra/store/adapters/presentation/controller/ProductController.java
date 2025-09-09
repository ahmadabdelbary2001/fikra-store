package fikra.store.adapters.presentation.controller;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import fikra.store.adapters.presentation.dto.ProductDto;
import fikra.store.adapters.presentation.mapper.ProductWebMapper;
import fikra.store.application.features.product.commands.CreateProductCommand;
import fikra.store.application.features.product.commands.UpdateProductCommand;
import fikra.store.application.features.product.commands.DeleteProductCommand;
import fikra.store.application.features.product.queries.GetAllProductsQuery;
import fikra.store.application.features.product.queries.GetProductDetailsQuery;
import fikra.store.domain.Product;

@RestController
@RequestMapping("/api/v1/products")
@CrossOrigin(origins = "http://localhost:5500")
public class ProductController {

    private static final Logger log = LoggerFactory.getLogger(ProductController.class);

    private final CreateProductCommand createCmd;
    private final UpdateProductCommand updateCmd;
    private final DeleteProductCommand deleteCmd;
    private final GetAllProductsQuery getAllQuery;
    private final GetProductDetailsQuery getDetailsQuery;
    private final ProductWebMapper mapper;

    public ProductController(CreateProductCommand createCmd,
                             UpdateProductCommand updateCmd,
                             DeleteProductCommand deleteCmd,
                             GetAllProductsQuery getAllQuery,
                             GetProductDetailsQuery getDetailsQuery,
                             ProductWebMapper mapper) {
        this.createCmd = createCmd;
        this.updateCmd = updateCmd;
        this.deleteCmd = deleteCmd;
        this.getAllQuery = getAllQuery;
        this.getDetailsQuery = getDetailsQuery;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<ProductDto>> getAllProducts() {
        log.debug("GET /api/v1/products");
        List<Product> list = getAllQuery.execute();
        var dtos = list.stream().map(mapper::toDto).collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id) {
        log.debug("GET /api/v1/products/{}", id);
        Product p = getDetailsQuery.execute(id);
        return ResponseEntity.ok(mapper.toDto(p));
    }

    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@Valid @RequestBody ProductDto dto) {
        log.info("POST /api/v1/products title={}", dto.getTitle());
        Product created = createCmd.execute(mapper.toDomain(dto));
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(created));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id, @Valid @RequestBody ProductDto dto) {
        log.info("PUT /api/v1/products/{} title={}", id, dto.getTitle());
        Product updated = updateCmd.execute(id, mapper.toDomain(dto));
        return ResponseEntity.ok(mapper.toDto(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        log.info("DELETE /api/v1/products/{}", id);
        deleteCmd.execute(id);
        return ResponseEntity.noContent().build();
    }
}
