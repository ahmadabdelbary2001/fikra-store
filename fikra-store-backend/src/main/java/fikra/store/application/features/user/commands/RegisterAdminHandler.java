package fikra.store.application.features.user.commands;

import java.util.Objects;

import fikra.store.application.exception.DuplicateResourceException;
import fikra.store.application.repositories.UserRepository;
import fikra.store.domain.Role;
import fikra.store.domain.User;

/**
 * Admin registration. Outer layer (controller + security) must enforce authorization.
 */
public class RegisterAdminHandler implements RegisterAdminCommand {

    private final UserRepository userRepository;

    public RegisterAdminHandler(UserRepository userRepository) {
        this.userRepository = Objects.requireNonNull(userRepository);
    }

    @Override
    public User execute(User admin) {
        Objects.requireNonNull(admin, "admin must not be null");
        if (admin.getUsername() == null || admin.getUsername().isBlank()) {
            throw new IllegalArgumentException("username must be provided");
        }

        if (userRepository.existsByUsername(admin.getUsername())) {
            throw new DuplicateResourceException("Username already taken: " + admin.getUsername());
        }

        admin.setRole(Role.ADMIN); // enforce ADMIN
        return userRepository.save(admin);
    }
}
