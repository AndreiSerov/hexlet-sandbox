package org.example.exception;

import org.springframework.http.HttpStatus;

/**
 * @author andreiserov
 */
public final class ServiceException extends RuntimeException {

    private final HttpStatus status;
    private ServiceException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }

    public static ServiceException unauthorized(String message) {
        return new ServiceException(message, HttpStatus.UNAUTHORIZED);
    }

    public static ServiceException forbidden(String message) {
        return new ServiceException(message, HttpStatus.FORBIDDEN);
    }

    public static ServiceException forbidden() {
        return forbidden("User doesn't have enough permissions for this operation");
    }

    public static ServiceException conflict(String message) {
        return new ServiceException(message, HttpStatus.CONFLICT);
    }

    public static ServiceException internal(String message) {
        return new ServiceException(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static ServiceException notFound() {
        return new ServiceException("Not Found", HttpStatus.NOT_FOUND);
    }

    public HttpStatus getStatus() {
        return status;
    }
}
