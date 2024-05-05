package com.example.vms.service;


import com.example.vms.dto.request.LoginRequest;
import com.example.vms.dto.response.LoginResponse;

import javax.naming.AuthenticationException;

public interface AuthService {
    LoginResponse userAuthenticate(LoginRequest loginRequest) throws AuthenticationException;
}
