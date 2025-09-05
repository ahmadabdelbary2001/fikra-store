package fikra.store.application.features.user.queries;

import java.util.Objects;

import fikra.store.application.exception.ResourceNotFoundException;
import fikra.store.application.repositories.UserRepository;
import fikra.store.domain.User;

public class GetUserByUsernameHandler implements GetUserByUsernameQuery {

    private final UserRepository userRepository;

    public GetUserByUsernameHandler(UserRepository userRepository) {
        this.userRepository = Objects.requireNonNull(userRepository);
    }

    @Override
    public User execute(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found: " + username));
    }
}