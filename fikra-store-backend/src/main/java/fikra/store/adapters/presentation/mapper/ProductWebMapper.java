package fikra.store.adapters.presentation.mapper;

import org.springframework.stereotype.Component;
import fikra.store.adapters.presentation.dto.ProductDto;
import fikra.store.adapters.presentation.dto.RatingDto;
import fikra.store.domain.Product;
import fikra.store.domain.Rating;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProductWebMapper {

    public Product toDomain(ProductDto dto) {
        if (dto == null) return null;
        Rating rating = null;
        if (dto.getRating() != null) {
            rating = new Rating(dto.getRating().getRate(), dto.getRating().getCount());
        }
        return new Product(dto.getTitle(), dto.getPrice(), dto.getDescription(), dto.getCategory(), dto.getImage(), rating);
    }

    public ProductDto toDto(Product domain) {
        if (domain == null) return null;
        ProductDto dto = new ProductDto();
        dto.setTitle(domain.getTitle());
        dto.setPrice(domain.getPrice());
        dto.setDescription(domain.getDescription());
        dto.setCategory(domain.getCategory());
        dto.setImage(domain.getImage());
        if (domain.getRating() != null) {
            RatingDto r = new RatingDto();
            r.setRate(domain.getRating().getRate());
            r.setCount(domain.getRating().getCount());
            dto.setRating(r);
        }
        return dto;
    }

    public List<ProductDto> toDtoList(List<Product> list) {
        return list.stream().map(this::toDto).collect(Collectors.toList());
    }
}
