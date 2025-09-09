package fikra.store.adapters.security;

import fikra.store.application.repositories.UserRepository;
import fikra.store.domain.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Loads user details from the application UserRepository port and adapts to Spring Security's UserDetails.
 * Keeps dependency on the application port so this class remains an adapter (infrastructure).
 */
@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Load a user by username and convert to Spring Security UserDetails.
     *
     * @param username the username to lookup
     * @return UserDetails for Spring Security authentication/authorization
     * @throws UsernameNotFoundException if no user exists with the given username
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User u = userRepository.findByUsername(username)
                .orElseThrow(() -> {
                    log.warn("User not found: {}", username);
                    return new UsernameNotFoundException("No such user: " + username);
                });

        // Map domain Role to Spring authorities
        String roleName = u.getRole() == null ? "CUSTOMER" : u.getRole().name();
        List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_" + roleName));

        log.debug("Loaded user '{}' with role {} and authorities {}", u.getUsername(), roleName, authorities);

        // Return Spring Security User (username, password, authorities). 
        // Account flags (enabled, accountNonExpired, credentialsNonExpired, accountNonLocked) set to true by default.
        return new org.springframework.security.core.userdetails.User(
                u.getUsername(),
                u.getPassword(),
                /* enabled */ true,
                /* accountNonExpired */ true,
                /* credentialsNonExpired */ true,
                /* accountNonLocked */ true,
                authorities
        );
    }
}
