package com.example.vms.controller;

import com.example.vms.dto.endpoints.APIResponse;
import com.example.vms.dto.request.LoginRequest;
import com.example.vms.dto.request.SignUpRequest;
import com.example.vms.dto.response.LoginResponse;
import com.example.vms.service.AccountService;
import com.example.vms.service.AuthService;
import com.example.vms.util.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;


@CrossOrigin
@RestController
@RequestMapping("/api/v1/")
@RequiredArgsConstructor
public class AuthController {
    private final AccountService accountService;
    private final AuthService authService;

    @PostMapping("signup")
    public ResponseEntity<APIResponse<?>>  createAccount(@RequestBody SignUpRequest signUpRequest){
        accountService.signup(signUpRequest);
        return ResponseEntity.ok(new APIResponse<>(ResponseCode.SUCCESS_USER_CREATED));
    }

    @PostMapping("authenticate")
    public ResponseEntity<APIResponse<?>> createAccount(@RequestBody LoginRequest loginRequest) throws AuthenticationException {
        LoginResponse loginResponse = authService.userAuthenticate(loginRequest);
        return ResponseEntity.ok(new APIResponse<>(ResponseCode.SUCCESS_LOGIN,loginResponse));
    }
}
