package com.example.vms.service.impl;

import com.example.vms.model.User;
import com.example.vms.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class AccUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;
    
    @Override
    public UserDetails loadUserByUsername(String username) {
        Optional<User> user  = userRepository.findByUsername(username);
        if (user.isPresent()) {
            return new org.springframework.security.core.userdetails.User(user.get().getUsername(), user.get().getPassword(),
                    Stream.of(user.get().getType().toString()).map(SimpleGrantedAuthority::new).toList());
        } else {
            throw new InternalAuthenticationServiceException("Access to your account is temporarily disabled.\n Please contact your administrator");
        }
    }
}

