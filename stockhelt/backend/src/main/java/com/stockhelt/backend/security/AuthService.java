package com.stockhelt.backend.security;

import com.stockhelt.backend.security.dto.SignupRequest;
import com.stockhelt.backend.user.UserRepository;
import com.stockhelt.backend.user.model.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;

    private final PasswordEncoder encoder;


    public boolean existsByUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    public boolean existsByEmail(String email) {
        return userRepository.existsByEmail(email);
    }

    public void register(SignupRequest signUpRequest) {
        Admin user = Admin.builder()
                .username(signUpRequest.getUsername())
                .password(encoder.encode(signUpRequest.getPassword()))
                .email(signUpRequest.getEmail())
                .build();

        userRepository.save(user);
    }

    public void registerDoctor(SignupRequest signUpRequest) {
        Doctor user = Doctor.builder()
                .username(signUpRequest.getUsername())
                .password(encoder.encode(signUpRequest.getPassword()))
                .email(signUpRequest.getEmail())
                .appointments(null)
                .build();

        userRepository.save(user);
    }

    public void registerSecretary(SignupRequest signUpRequest) {
        Secretary user = Secretary.builder()
                .username(signUpRequest.getUsername())
                .password(encoder.encode(signUpRequest.getPassword()))
                .email(signUpRequest.getEmail())
                .build();

        userRepository.save(user);
    }

    public void registerPatient(SignupRequest signUpRequest) {
        Patient user = Patient.builder()
                .username(signUpRequest.getUsername())
                .password(encoder.encode(signUpRequest.getPassword()))
                .email(signUpRequest.getEmail())
                .CNP("BLDSADLSADAS")
                .lastName("Dummy")
                .firstName("Patient")
                .address("testAddress nr.8")
                .dateOfBirth(new java.util.Date())
                .build();

        userRepository.save(user);
    }
}
