package fikra.store.application.repositories;

import fikra.store.domain.Role;
import fikra.store.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<User> findById(Long id);
    Optional<User> findByUsername(String username);
    User save(User user);
    void deleteById(Long id);
    boolean existsByUsername(String username);
    List<User> findByRole(Role role);
}