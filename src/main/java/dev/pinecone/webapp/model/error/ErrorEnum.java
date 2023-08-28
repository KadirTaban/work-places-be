package dev.pinecone.webapp.model.error;

import lombok.Getter;
import org.springframework.http.HttpStatus;
@Getter
public enum ErrorEnum {

    USER_HAS_ALREADY_EXISTS(1000, "user.has.already.exists", HttpStatus.BAD_REQUEST),
    INVALID_CREDENTIALS(1001, "invalid.credentials", HttpStatus.BAD_REQUEST),
    CITY_NOT_FOUND(1003, "city.not.found", HttpStatus.NOT_FOUND);
    ;

    private final int code;
    private final String messageTemplate;
    private final HttpStatus httpStatus;

    ErrorEnum(int code, String messageTemplate, HttpStatus httpStatus) {
        this.code = code;
        this.messageTemplate = messageTemplate;
        this.httpStatus = httpStatus;
    }

}