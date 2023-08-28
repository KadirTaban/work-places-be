package dev.pinecone.webapp.controller;

import dev.pinecone.webapp.model.response.AuthResponse;
import dev.pinecone.webapp.model.request.UserLoginRequest;
import dev.pinecone.webapp.model.request.UserRegisterRequest;
import dev.pinecone.webapp.model.response.BaseResponse;
import dev.pinecone.webapp.service.UserService;
import dev.pinecone.webapp.utils.constants.Apis;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(Apis.User.BASE_URL)
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final MessageSource messageSource;

    @GetMapping
    public String test(){
        return messageSource.getMessage("user.register.request.email.notBlank",null,LocaleContextHolder.getLocale());
    }

    @PostMapping(Apis.User.LOGIN)
    public ResponseEntity<BaseResponse<AuthResponse>> login(@Valid @RequestBody UserLoginRequest request) {
        BaseResponse<AuthResponse> response = userService.login(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(Apis.User.REGISTER)
    public ResponseEntity<BaseResponse<AuthResponse>> register(@Valid @RequestBody UserRegisterRequest request) {
        BaseResponse<AuthResponse> response = userService.register(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
