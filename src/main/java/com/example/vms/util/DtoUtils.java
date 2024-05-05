package com.example.vms.util;

import com.example.vms.dto.request.SignUpRequest;
import com.example.vms.model.Gender;
import com.example.vms.model.User;
import com.example.vms.model.UserType;

import java.util.function.Function;

public class DtoUtils {

    public static Function<SignUpRequest, User> SIGNUP_TO_USER = signUpRequest -> {
        User user = new User();
        user.setUsername(signUpRequest.getUserName());
        user.setPassword(signUpRequest.getPassword());
        user.setType(UserType.valueOf(signUpRequest.getUserType()));
        user.setFirstName(signUpRequest.getFirstName());
        user.setLastName(signUpRequest.getLastName());
        user.setMiddlName(signUpRequest.getMiddleName());
        user.setGender(Gender.valueOf(signUpRequest.getGender()));
        user.setEmail(signUpRequest.getEmail());
        user.setPhoneNumber(signUpRequest.getPhoneNumber());
        user.setNic(signUpRequest.getNic());
        user.setAddress(signUpRequest.getAddress());
        return user;
    };
}
