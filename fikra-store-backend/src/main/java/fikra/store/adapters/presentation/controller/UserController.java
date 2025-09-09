package fikra.store.adapters.presentation.controller;

import java.security.Principal;
import java.util.List;
import java.util.stream.Collectors;

import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.password.PasswordEncoder;

import fikra.store.application.features.user.commands.RegisterCustomerCommand;
import fikra.store.application.features.user.commands.RegisterAdminCommand;
import fikra.store.application.features.user.queries.GetUserByUsernameQuery;
import fikra.store.application.features.user.queries.FindUsersByRoleQuery;
import fikra.store.adapters.presentation.dto.UserRegistrationDto;
import fikra.store.adapters.presentation.dto.UserDto;
import fikra.store.adapters.presentation.dto.UserSummaryDto;
import fikra.store.domain.Role;
import fikra.store.domain.User;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "http://localhost:5500")
public class UserController {

    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    private final RegisterCustomerCommand registerCustomer;
    private final RegisterAdminCommand registerAdmin;
    private final GetUserByUsernameQuery getByUsername;
    private final FindUsersByRoleQuery findByRole;
    private final PasswordEncoder passwordEncoder;

    public UserController(RegisterCustomerCommand registerCustomer,
                          RegisterAdminCommand registerAdmin,
                          GetUserByUsernameQuery getByUsername,
                          FindUsersByRoleQuery findByRole,
                          PasswordEncoder passwordEncoder) {
        this.registerCustomer = registerCustomer;
        this.registerAdmin = registerAdmin;
        this.getByUsername = getByUsername;
        this.findByRole = findByRole;
        this.passwordEncoder = passwordEncoder;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody UserRegistrationDto dto) {
        log.info("Register request for username={}", dto.getUsername());
        String encoded = passwordEncoder.encode(dto.getPassword());
        User u = new User(dto.getUsername(), encoded, null);
        User saved = registerCustomer.execute(u);
        return ResponseEntity.ok("User registered successfully: id=" + saved.getId());
    }

    @PostMapping("/admin/register")
    public ResponseEntity<?> registerAdmin(@Valid @RequestBody UserRegistrationDto dto) {
        log.info("Admin register request for username={}", dto.getUsername());
        String encoded = passwordEncoder.encode(dto.getPassword());
        User u = new User(dto.getUsername(), encoded, null);
        User saved = registerAdmin.execute(u);
        return ResponseEntity.status(HttpStatus.CREATED).body("Admin created: id=" + saved.getId());
    }

    @GetMapping("/me")
    public ResponseEntity<UserDto> me(Principal principal) {
        if (principal == null) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
        User u = getByUsername.execute(principal.getName());
        UserDto dto = new UserDto(u.getId(), u.getUsername(), u.getRole() == null ? null : u.getRole().name());
        return ResponseEntity.ok(dto);
    }

    @GetMapping("/role/{role}")
    public ResponseEntity<List<UserSummaryDto>> getUsersByRole(@PathVariable String role) {
        Role r = Role.valueOf(role.toUpperCase());
        List<User> users = findByRole.execute(r);

        List<UserSummaryDto> payload = users.stream()
                .map(u -> new UserSummaryDto(u.getId(), u.getUsername()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(payload);
    }
}
