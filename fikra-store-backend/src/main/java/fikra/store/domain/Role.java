package fikra.store.domain;

/**
 * Simple enum for user roles in the domain.
 * Using an enum avoids scattering magic strings across the business logic.
 */
public enum Role {
    ADMIN,
    CUSTOMER,
    // add more roles in the future (e.g., SUPPORT)
}