package dev.pinecone.webapp.exception;

import dev.pinecone.webapp.model.response.BaseResponse;
import dev.pinecone.webapp.model.response.ErrorResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@RequiredArgsConstructor
public class GlobalExceptionHandler {

    private final MessageSource messageSource;

    @ExceptionHandler(GenericException.class)
    public ResponseEntity<BaseResponse<ErrorResponse>> handleGenericException(GenericException ex) {
        String errorMessage = messageSource.getMessage(ex.getErrorEnum().getMessageTemplate(), null, LocaleContextHolder.getLocale());
        ErrorResponse errorResponse = ErrorResponse.builder()
                .code(ex.getErrorEnum().getCode())
                .errorMessage(ex.getErrorEnum().getMessageTemplate())
                .timestamp(LocalDateTime.now())
                .httpStatus(ex.getErrorEnum().getHttpStatus())
                .build();

        BaseResponse<ErrorResponse> response = BaseResponse
                .<ErrorResponse>builder()
                .success(false)
                .message(errorMessage)
                .data(errorResponse)
                .build();
        return new ResponseEntity<>(response,errorResponse.getHttpStatus());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponse<Map<String, String>>> handleValidationException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });


        BaseResponse<Map<String, String>> response = BaseResponse
                .<Map<String, String>>builder()
                .success(false)
                .message("Hata Meydana Geldi!")
                .data(errors)
                .build();
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


}
