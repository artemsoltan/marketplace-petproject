package com.petproject.marketplace.controller;

import com.petproject.marketplace.config.jwt.JwtUtil;
import com.petproject.marketplace.facade.AuthFacade;
import com.petproject.marketplace.model.dto.LoginDTO;
import com.petproject.marketplace.model.dto.UserDTO;
import com.petproject.marketplace.exception.UserDoesNotExistsException;
import com.petproject.marketplace.model.entity.User;
import com.petproject.marketplace.repository.UserRepository;
import com.petproject.marketplace.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthFacade authFacade;

    @Autowired
    public AuthController(AuthFacade authFacade) {
        this.authFacade = authFacade;
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody @Valid UserDTO userDTO) {
        authFacade.register(userDTO);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginDTO loginDTO) {
        return ResponseEntity.ok().header(
                HttpHeaders.AUTHORIZATION,
                authFacade.generateToken(loginDTO)
        ).body(null);
    }
}