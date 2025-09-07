package fikra.store.adapters.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

import fikra.store.adapters.persistence.jpa.entities.ProductEntity;
import fikra.store.domain.Product;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    Product toDomain(ProductEntity entity);
    ProductEntity toEntity(Product domain);
    List<Product> toDomainList(List<ProductEntity> entities);
}
