package fikra.store.adapters.presentation.dto;

import jakarta.validation.constraints.*;
import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductDto {
    @NotBlank
    private String title;

    @DecimalMin("0.0")
    private double price;

    @NotBlank
    @Size(max = 1000)
    private String description;

    @NotBlank
    private String category;

    @NotBlank
    private String image;

    private RatingDto rating;

    @Override
    public boolean equals(Object o) { return Objects.equals(this, o); }
    @Override
    public int hashCode() { return Objects.hash(title, price, description, category, image, rating); }
}
