package fikra.store.adapters.presentation.dto;

import jakarta.validation.constraints.Min;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CartDto {
    @Min(1)
    private Long userId;

    @Min(1)
    private Long productId;
}
