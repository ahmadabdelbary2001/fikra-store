package fikra.store.domain;

import java.util.Objects;

import lombok.Getter;
import lombok.Setter;

/**
 * Domain model for user. Note: password handling and security concerns remain
 * for the application layer (e.g., encoding, tokens). Domain model keeps the
 * business attributes only.
 */

@Getter
@Setter
public class User {
    private Long id;
    private String username;
    private String password;
    private Role role;

    public User() {}

    public User(Long id, String username, String password, Role role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.role = role;
    }

    public User(String username, String password, Role role) {
        this(null, username, password, role);
    }

    public boolean isAdmin() {
        return role == Role.ADMIN;
    }

    public boolean isCustomer() {
        return role == Role.CUSTOMER;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;
        return Objects.equals(id, user.id)
                && Objects.equals(username, user.username)
                && Objects.equals(password, user.password)
                && role == user.role;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, username, password, role);
    }

    @Override
    public String toString() {
        return "User{" +
               "id=" + id +
               ", username='" + username + '\'' +
               ", password='[PROTECTED]'" +
               ", role=" + role +
               '}';
    }
}