package com.example.vms.config.exception;

import com.example.vms.util.ResponseCode;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class AuthenticationException extends CommonException {
    public AuthenticationException(ResponseCode responseCode) {
        super(responseCode);
    }
}
