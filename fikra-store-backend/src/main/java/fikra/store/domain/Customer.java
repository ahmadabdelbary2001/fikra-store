package fikra.store.domain;

/**
 * Domain representation of a Customer user. Extends User for role semantics.
 * Add customer-specific behaviour (e.g., loyalty points) here later.
 */
public class Customer extends User {

    public Customer() {
        super();
        setRole(Role.CUSTOMER);
    }

    public Customer(Long id, String username, String password) {
        super(id, username, password, Role.CUSTOMER);
    }

    public Customer(String username, String password) {
        super(username, password, Role.CUSTOMER);
    }
}