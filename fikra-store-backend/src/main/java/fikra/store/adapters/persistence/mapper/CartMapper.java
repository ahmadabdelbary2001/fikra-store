package fikra.store.adapters.persistence.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import fikra.store.adapters.persistence.jpa.entities.CartEntity;
import fikra.store.domain.Cart;

@Mapper(componentModel = "spring")
public interface CartMapper {
    CartMapper INSTANCE = Mappers.getMapper(CartMapper.class);

    Cart toDomain(CartEntity e);
    CartEntity toEntity(Cart d);
}
