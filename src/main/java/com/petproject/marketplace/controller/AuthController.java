package com.petproject.marketplace.controller;

import com.petproject.marketplace.config.jwt.JwtUtil;
import com.petproject.marketplace.dto.LoginDTO;
import com.petproject.marketplace.dto.UserDTO;
import com.petproject.marketplace.exception.UserDoesNotExistsException;
import com.petproject.marketplace.exception.UserExistsException;
import com.petproject.marketplace.model.User;
import com.petproject.marketplace.repository.UserRepository;
import com.petproject.marketplace.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthController(AuthService authService, UserRepository userRepository, JwtUtil jwtUtil) {
        this.authService = authService;
        this.userRepository = userRepository;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid UserDTO userDTO) {
        authService.registerUser(userDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginDTO loginDTO) {

        if (!authService.isAccountAvailable(loginDTO)) {
            throw new UserDoesNotExistsException();
        }

        User user = userRepository.findByUsername(loginDTO.getUsername()).orElse(null);

        return ResponseEntity.ok().header(
                HttpHeaders.AUTHORIZATION,
                jwtUtil.generateToken(user)
        ).body("");
    }
}