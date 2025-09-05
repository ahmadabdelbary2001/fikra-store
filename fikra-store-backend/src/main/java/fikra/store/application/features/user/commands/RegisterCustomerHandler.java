package fikra.store.application.features.user.commands;

import java.util.Objects;

import fikra.store.application.exception.DuplicateResourceException;
import fikra.store.application.repositories.UserRepository;
import fikra.store.domain.Role;
import fikra.store.domain.User;

/**
 * Public registration for customers. Outer layer must perform password encoding.
 */
public class RegisterCustomerHandler implements RegisterCustomerCommand {

    private final UserRepository userRepository;

    public RegisterCustomerHandler(UserRepository userRepository) {
        this.userRepository = Objects.requireNonNull(userRepository);
    }

    @Override
    public User execute(User customer) {
        Objects.requireNonNull(customer, "customer must not be null");
        if (customer.getUsername() == null || customer.getUsername().isBlank()) {
            throw new IllegalArgumentException("username must be provided");
        }

        if (userRepository.existsByUsername(customer.getUsername())) {
            throw new DuplicateResourceException("Username already taken: " + customer.getUsername());
        }

        customer.setRole(Role.CUSTOMER);
        return userRepository.save(customer);
    }
}
