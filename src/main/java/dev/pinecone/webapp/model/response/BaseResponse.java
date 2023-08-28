package dev.pinecone.webapp.model.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class BaseResponse<T> {

    private Boolean success;
    private String message;
    private T data;
}
