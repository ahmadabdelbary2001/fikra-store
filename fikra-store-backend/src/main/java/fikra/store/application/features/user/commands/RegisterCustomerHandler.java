package fikra.store.application.features.user.commands;

import java.util.Objects;

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
        customer.setRole(Role.CUSTOMER);
        return userRepository.save(customer);
    }
}