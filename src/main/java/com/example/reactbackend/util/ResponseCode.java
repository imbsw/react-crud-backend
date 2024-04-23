package com.example.reactbackend.util;

import lombok.Getter;

@Getter
public enum ResponseCode {
    SUCCESS_LOGIN(2000, "Authentication successful"),
    SUCCESS_USER_CREATED(2003, "User account has been created successfully"),



    FAIL_LOGIN(1000, "Authentication Failed"),
    FAIL_USER_DISABLED(1001, "User not active."),
    FAIL_USER_NOT_FOUND(1003, "User not found"),;

    private final int code;
    private final String description;

    ResponseCode(int code, String description) {
        this.code = code;
        this.description = description;
    }

}
