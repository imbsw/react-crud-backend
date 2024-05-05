package com.example.vms.service.impl;

import com.example.vms.dto.request.SignUpRequest;
import com.example.vms.model.User;
import com.example.vms.repository.UserRepository;
import com.example.vms.service.AccountService;
import com.example.vms.util.Constant;
import com.example.vms.util.DtoUtils;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    @Transactional
    public void signup(SignUpRequest signUpRequest) {
        User user = (DtoUtils.SIGNUP_TO_USER.apply(signUpRequest));
        user.setPassword(passwordEncoder.encode(signUpRequest.getPassword()));
        user.setActive(Constant.ACTIVE);
        userRepository.save(user);
    }

}
