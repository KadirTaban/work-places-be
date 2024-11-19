package dev.pinecone.webapp.model.response;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@Builder
public class ErrorResponse {
    private LocalDateTime timestamp;
    private Integer code;
    private String errorMessage;
    private HttpStatus httpStatus;
}
