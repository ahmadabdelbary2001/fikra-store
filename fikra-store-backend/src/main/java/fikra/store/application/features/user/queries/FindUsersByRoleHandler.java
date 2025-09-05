package fikra.store.application.features.user.queries;

import java.util.List;
import java.util.Objects;

import fikra.store.application.repositories.UserRepository;
import fikra.store.domain.Role;
import fikra.store.domain.User;

public class FindUsersByRoleHandler implements FindUsersByRoleQuery {

    private final UserRepository userRepository;

    public FindUsersByRoleHandler(UserRepository userRepository) {
        this.userRepository = Objects.requireNonNull(userRepository);
    }

    @Override
    public List<User> execute(Role role) {
        return userRepository.findByRole(role);
    }
}