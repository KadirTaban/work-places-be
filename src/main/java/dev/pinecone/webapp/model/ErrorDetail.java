package dev.pinecone.webapp.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ErrorDetail implements Serializable {

    @Serial
    private static final long serialVersionUID = 4359762518076830332L;
    
    private int code;
    private String message;
}