package fikra.store.adapters.presentation.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRegistrationDto {
    @NotBlank @Size(min=3, max=20)
    private String username;

    @NotBlank @Size(min=6, max=100)
    private String password;
}
