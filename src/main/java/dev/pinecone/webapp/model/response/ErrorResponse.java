package dev.pinecone.webapp.model.response;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@Data
@Builder
public class ErrorResponse {
    private LocalDateTime timestamp;
    private Integer code;
    private String errorMessage;
    private HttpStatus httpStatus;
}
