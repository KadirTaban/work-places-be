package dev.pinecone.webapp.exception;

import dev.pinecone.webapp.model.error.ErrorEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@Getter
@NoArgsConstructor
public class GenericException extends RuntimeException {
    private ErrorEnum errorEnum;
}