package fikra.store.adapters.persistence.adapter;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import fikra.store.application.repositories.UserRepository;
import fikra.store.adapters.persistence.jpa.repositories.JpaUserRepository;
import fikra.store.adapters.persistence.mapper.UserMapper;
import fikra.store.domain.User;
import fikra.store.domain.Role;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserRepositoryAdapter implements UserRepository {

    private final JpaUserRepository jpa;
    private final UserMapper mapper;

    @Override
    public Optional<User> findById(Long id) {
        return jpa.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return jpa.findByUsername(username).map(mapper::toDomain);
    }

    @Override
    @Transactional
    public User save(User user) {
        var entity = mapper.toEntity(user);
        var saved = jpa.save(entity);
        return mapper.toDomain(saved);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        jpa.deleteById(id);
    }

    @Override
    public boolean existsByUsername(String username) {
        return jpa.existsByUsername(username);
    }

    @Override
    public List<User> findByRole(Role role) {
        // return mapper.toDomainList(jpa.findByRole(role));
        return jpa.findByRole(role).stream().map(mapper::toDomain).collect(Collectors.toList());
    }
}
