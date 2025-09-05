package fikra.store.application.exception;

/**
 * Thrown when a resource already exists (e.g. username taken).
 */
public class DuplicateResourceException extends RuntimeException {
    public DuplicateResourceException(String message) {
        super(message);
    }
}
