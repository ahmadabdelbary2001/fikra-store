package fikra.store.adapters.presentation.dto;

import java.util.Objects;
import lombok.Getter;
import lombok.Setter;

/**
 * Lightweight user representation for lists (id + username).
 */
@Getter
@Setter
 public class UserSummaryDto {
    private Long id;
    private String username;

    public UserSummaryDto() {}

    public UserSummaryDto(Long id, String username) {
        this.id = id;
        this.username = username;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserSummaryDto)) return false;
        UserSummaryDto that = (UserSummaryDto) o;
        return Objects.equals(id, that.id) && Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username);
    }
}
