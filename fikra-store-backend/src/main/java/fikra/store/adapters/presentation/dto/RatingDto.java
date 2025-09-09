package fikra.store.adapters.presentation.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RatingDto {
    @DecimalMin("0.0") @DecimalMax("5.0")
    private double rate;

    @Min(0)
    private int count;
}
