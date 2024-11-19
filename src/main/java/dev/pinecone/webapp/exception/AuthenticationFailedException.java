package dev.pinecone.webapp.exception;

import dev.pinecone.webapp.model.response.ErrorResponse;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class AuthenticationFailedException extends RuntimeException {

    private final HttpStatus httpStatus;
    private final ErrorResponse errorResponse;

    public AuthenticationFailedException(ErrorResponse errorResponse) {
        this.httpStatus = HttpStatus.UNAUTHORIZED;
        this.errorResponse = errorResponse;
    }
}