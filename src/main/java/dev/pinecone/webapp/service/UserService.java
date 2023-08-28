package dev.pinecone.webapp.service;

import dev.pinecone.webapp.entity.User;
import dev.pinecone.webapp.entity.enums.Role;
import dev.pinecone.webapp.exception.GenericException;
import dev.pinecone.webapp.model.error.ErrorEnum;
import dev.pinecone.webapp.model.request.UserLoginRequest;
import dev.pinecone.webapp.model.request.UserRegisterRequest;
import dev.pinecone.webapp.model.response.AuthResponse;
import dev.pinecone.webapp.model.response.BaseResponse;
import dev.pinecone.webapp.repository.UserRepository;
import dev.pinecone.webapp.security.JwtService;
import dev.pinecone.webapp.security.JwtUserDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public BaseResponse<AuthResponse> register(UserRegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            throw new GenericException(ErrorEnum.USER_HAS_ALREADY_EXISTS);
        }
        User newUser = User
                .builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();
        this.userRepository.save(newUser);
        var jwt = jwtService.generateToken(JwtUserDetails.create(newUser));

        AuthResponse build = AuthResponse
                .builder()
                .token(jwt)
                .build();
        return BaseResponse
                .<AuthResponse>builder()
                .data(build)
                .success(true)
                .message("Kullanıcı Başarıyla Kayıt Oldu!")
                .build();
    }


    public BaseResponse<AuthResponse> login(UserLoginRequest request){

        try{
            Authentication auth = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getEmail(),
                            request.getPassword()
                    ));
            SecurityContextHolder.getContext().setAuthentication(auth);
            var user= userRepository.findByEmail(request.getEmail())
                    .orElseThrow(()-> new UsernameNotFoundException("Kullanıcıya ait email bulunamadı."));

            var jwt = jwtService.generateToken(JwtUserDetails.create(user));

            AuthResponse build = AuthResponse
                    .builder()
                    .token(jwt)
                    .build();
            return BaseResponse
                    .<AuthResponse>builder()
                    .data(build)
                    .success(true)
                    .message("Kullanıcı Başarıyla Giriş Yaptı!")
                    .build();
        }catch (Exception e){
            throw new GenericException(ErrorEnum.INVALID_CREDENTIALS);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return JwtUserDetails.create(this.userRepository
                                             .findByEmail(username).orElseThrow());
    }
}
