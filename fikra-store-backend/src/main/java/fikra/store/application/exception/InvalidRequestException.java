package fikra.store.application.exception;

/**
 * Thrown when an input fails business validation/invariants.
 */
public class InvalidRequestException extends RuntimeException {
    public InvalidRequestException(String message) {
        super(message);
    }
}
