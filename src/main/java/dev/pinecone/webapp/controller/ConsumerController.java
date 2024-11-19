package dev.pinecone.webapp.controller;

import dev.pinecone.webapp.model.request.UserLoginRequest;
import dev.pinecone.webapp.model.request.UserRegisterRequest;
import dev.pinecone.webapp.model.response.AuthResponse;
import dev.pinecone.webapp.model.response.BaseResponse;
import dev.pinecone.webapp.service.ConsumerService;
import dev.pinecone.webapp.utils.constants.Apis;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static dev.pinecone.webapp.utils.constants.HeaderConstants.X_CONSUMER_ID;

@RestController
@RequestMapping(Apis.User.BASE_URL)
@RequiredArgsConstructor
public class ConsumerController {

    private final ConsumerService consumerService;

    @GetMapping
    public String getInfo() {
        return consumerService.getInfo();
    }

    @PostMapping(Apis.User.LOGIN)
    public ResponseEntity<BaseResponse<AuthResponse>> login(@Valid @RequestBody UserLoginRequest request) {
        BaseResponse<AuthResponse> response = consumerService.login(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(Apis.User.REGISTER)
    public ResponseEntity<BaseResponse<AuthResponse>> register(@Valid @RequestBody UserRegisterRequest request) {
        BaseResponse<AuthResponse> response = consumerService.register(request);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
