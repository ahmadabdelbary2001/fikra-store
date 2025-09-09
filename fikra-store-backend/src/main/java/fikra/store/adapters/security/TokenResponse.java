package fikra.store.adapters.security;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TokenResponse {
    private String token;
    private String tokenType = "Bearer";

    public TokenResponse() {}

    public TokenResponse(String token) {
        this.token = token;
    }
}
