package com.example.reactbackend.controller;

import com.example.reactbackend.dto.endpoints.APIResponse;
import com.example.reactbackend.dto.request.LoginRequest;
import com.example.reactbackend.service.impl.DataServiceImpl;
import com.example.reactbackend.util.ResponseCode;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AuthController {
    private final DataServiceImpl dataService;
    @PostMapping("/login")
    public ResponseEntity<APIResponse<?>> createAccount(@RequestBody LoginRequest signUpRequest){
        return ResponseEntity.ok(new APIResponse<>(ResponseCode.SUCCESS_LOGIN));
    }

    @GetMapping("/user/list")
    public ResponseEntity<APIResponse<?>> getUserList(){
        return ResponseEntity.ok(new APIResponse<>(ResponseCode.SUCCESS_LOGIN,dataService.getUserList()));
    }
}
