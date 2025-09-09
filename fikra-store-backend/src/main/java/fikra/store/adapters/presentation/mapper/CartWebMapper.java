package fikra.store.adapters.presentation.mapper;

import org.springframework.stereotype.Component;
import fikra.store.adapters.presentation.dto.CartDto;
import fikra.store.domain.Cart;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CartWebMapper {

    public Cart toDomain(CartDto dto) {
        if (dto == null) return null;
        return new Cart(dto.getUserId(), dto.getProductId());
    }

    public CartDto toDto(Cart domain) {
        if (domain == null) return null;
        CartDto dto = new CartDto();
        dto.setUserId(domain.getUserId());
        dto.setProductId(domain.getProductId());
        return dto;
    }

    public List<CartDto> toDtoList(List<Cart> list) {
        return list.stream().map(this::toDto).collect(Collectors.toList());
    }
}
