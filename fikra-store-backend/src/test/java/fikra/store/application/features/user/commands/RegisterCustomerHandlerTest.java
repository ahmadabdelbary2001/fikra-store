package fikra.store.application.features.user.commands;

import fikra.store.application.exception.DuplicateResourceException;
import fikra.store.application.repositories.UserRepository;
import fikra.store.domain.User;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

class RegisterCustomerHandlerTest {

    static class InMemoryUserRepo implements UserRepository {
        final List<User> users = new ArrayList<>();

        @Override
        public Optional<User> findById(Long id) {
            return users.stream().filter(u -> u.getId() != null && u.getId().equals(id)).findFirst();
        }

        @Override
        public Optional<User> findByUsername(String username) {
            return users.stream().filter(u -> username.equals(u.getUsername())).findFirst();
        }

        @Override
        public User save(User user) {
            user.setId((long)(users.size() + 1));
            users.add(user);
            return user;
        }

        @Override
        public void deleteById(Long id) {
            users.removeIf(u -> u.getId() != null && u.getId().equals(id));
        }

        @Override
        public boolean existsByUsername(String username) {
            return users.stream().anyMatch(u -> username.equals(u.getUsername()));
        }

        @Override
        public List<User> findByRole(fikra.store.domain.Role role) {
            List<User> r = new ArrayList<>();
            for (User u : users) if (u.getRole() == role) r.add(u);
            return r;
        }
    }

    @Test
    void registersCustomer() {
        var repo = new InMemoryUserRepo();
        var handler = new RegisterCustomerHandler(repo);
        var input = new User("alice", "pass", null);
        var saved = handler.execute(input);
        assertNotNull(saved.getId());
        assertEquals(fikra.store.domain.Role.CUSTOMER, saved.getRole());
    }

    @Test
    void duplicateUsernameThrows() {
        var repo = new InMemoryUserRepo();
        var handler = new RegisterCustomerHandler(repo);
        var input1 = new User("bob", "p1", null);
        handler.execute(input1);
        var input2 = new User("bob", "p2", null);
        assertThrows(DuplicateResourceException.class, () -> handler.execute(input2));
    }
}
