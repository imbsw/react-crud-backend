package com.example.vms.service.impl;

import com.example.vms.config.exception.AuthenticationException;
import com.example.vms.config.security.JwtAuthenticationProvider;
import com.example.vms.dto.request.LoginRequest;
import com.example.vms.dto.response.LoginResponse;
import com.example.vms.model.User;
import com.example.vms.repository.UserRepository;
import com.example.vms.service.AuthService;
import com.example.vms.util.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    private final JwtAuthenticationProvider tokenService;
    private final UserRepository userRepository;

    @Override
    public LoginResponse userAuthenticate(LoginRequest loginRequest) throws AuthenticationException {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUserName(),loginRequest.getPassword()));
        }catch (Exception e){
            throw new AuthenticationException(ResponseCode.FAIL_LOGIN);
        }


        Optional<User> userOp = userRepository.findByUsername(loginRequest.getUserName());

        if(userOp.isPresent()){
            User user = userOp.get();
            return new LoginResponse(user.getUsername(),tokenService.generateToken(user.getUsername()));
        }
        else {
            throw new AuthenticationException(ResponseCode.FAIL_USER_NOT_FOUND);
        }

    }
}
