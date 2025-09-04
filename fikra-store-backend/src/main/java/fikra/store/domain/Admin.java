package fikra.store.domain;

/**
 * Domain representation of an Admin user. Extends User for role semantics.
 * Keep admin-specific domain behaviour here in future (e.g., admin validations).
 */
public class Admin extends User {

    public Admin() {
        super();
        setRole(Role.ADMIN);
    }

    public Admin(Long id, String username, String password) {
        super(id, username, password, Role.ADMIN);
    }

    public Admin(String username, String password) {
        super(username, password, Role.ADMIN);
    }
}