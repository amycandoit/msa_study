package com.example.auth.service;

import com.example.auth.client.api.CustomerClient;
import com.example.auth.client.api.OwnerClient;
import com.example.auth.client.request.OwnerRequest;
import com.example.auth.config.JwtService;
import com.example.auth.domain.entity.Role;
import com.example.auth.domain.entity.User;
import com.example.auth.domain.request.LoginRequest;
import com.example.auth.domain.request.SignupRequest;
import com.example.auth.domain.response.LoginResponse;
import com.example.auth.kafka.CustomerProducer;
import com.example.auth.kafka.OwnerProducer;
import com.example.auth.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final RestTemplate restTemplate;
    //    private final OwnerClient ownerClient;
    private final OwnerProducer ownerProducer;
    //    private final CustomerClient customerClient;
    private final CustomerProducer customerProducer;

    @Transactional
    public void signUp(SignupRequest signupRequest) {
        User build = User.builder()
                .name(signupRequest.getName())
                .number(signupRequest.getNumber())
                .email(signupRequest.getEmail())
                .password(passwordEncoder.encode(signupRequest.getPassword()))
                .role(Role.valueOf(signupRequest.getRole()))
                .build();
        User save = userRepository.save(build);
        OwnerRequest ownerRequest = new OwnerRequest(save.getId(), save.getName(), save.getNumber());
        if (save.getRole() == Role.OWNER) {
            ownerProducer.send(ownerRequest);
//            ResponseEntity<Void> response = ownerClient.saveOwner(ownerRequest);
//            ResponseEntity<Void> response = restTemplate.postForEntity("http://localhost:9001/api/v1/owner",
//                    new OwnerRequest(save.getId(), save.getName(), save.getNumber()), Void.class);
//            if (response.getStatusCode() != HttpStatus.CREATED) {
//                String err = save.getRole().name() + "-SERVICE DEAD";
//                throw new RuntimeException(err);
//            }
        } else {
            customerProducer.send(ownerRequest);
//            ResponseEntity<Void> response = customerClient.saveCustomer(ownerRequest);
//            if (response.getStatusCode() != HttpStatus.CREATED) {
//                String err = save.getRole().name() + "-SERVICE DEAD";
//                throw new RuntimeException(err);
//            }
        }
    }

    public LoginResponse login(LoginRequest loginRequest){
        Optional<User> byEmail = userRepository.findByEmail(loginRequest.getEmail());
        User user = byEmail.orElseThrow(() -> new IllegalArgumentException("USER NOT FOUND"));
        if (!passwordEncoder.matches(loginRequest.getPassword(), user.getPassword())) {
            throw new IllegalArgumentException("USER NOT FOUND"); //password 같은지 확인
        }
        String token = jwtService.makeToken(user);
        return new LoginResponse(token, user.getRole().name());
    }
}
