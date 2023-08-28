package dev.pinecone.webapp.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UserLoginRequest {

    @NotBlank(message = "{user.login.request.email.notBlank}")
    private String email;

    @NotBlank(message = "{user.login.request.password.notBlank}")
    private String password;
}
