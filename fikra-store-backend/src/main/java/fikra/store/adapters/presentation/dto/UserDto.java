package fikra.store.adapters.presentation.dto;

import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

/**
 * Detailed user representation returned by GET /user/me
 */
@Getter
@Setter
public class UserDto {
    private Long id;
    private String username;
    private String role;

    public UserDto() {}

    public UserDto(Long id, String username, String role) {
        this.id = id;
        this.username = username;
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserDto)) return false;
        UserDto that = (UserDto) o;
        return Objects.equals(id, that.id)
                && Objects.equals(username, that.username)
                && Objects.equals(role, that.role);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, role);
    }
}
