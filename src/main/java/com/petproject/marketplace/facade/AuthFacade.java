package com.petproject.marketplace.facade;

import com.petproject.marketplace.model.dto.LoginDTO;
import com.petproject.marketplace.model.dto.UserDTO;
import com.petproject.marketplace.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
public class AuthFacade {
    private final AuthService authService;

    @Autowired
    public AuthFacade(AuthService authService) {
        this.authService = authService;
    }

    public String generateToken(LoginDTO loginDTO) {
        return authService.generateToken(loginDTO);
    }

    public void register(UserDTO userDTO) {
        authService.registerUser(userDTO);
    }
}