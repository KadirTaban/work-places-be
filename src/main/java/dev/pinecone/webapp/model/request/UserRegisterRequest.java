package dev.pinecone.webapp.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRegisterRequest {

    @NotBlank(message = "{user.login.request.email.notBlank}")
    private String email;

    @NotBlank(message = "{user.login.request.password.notBlank}")
    private String password;
}
