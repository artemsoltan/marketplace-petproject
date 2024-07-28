package com.petproject.marketplace.service;

import com.petproject.marketplace.config.jwt.JwtUtil;
import com.petproject.marketplace.exception.UserDoesNotExistsException;
import com.petproject.marketplace.model.dto.LoginDTO;
import com.petproject.marketplace.model.dto.UserDTO;
import com.petproject.marketplace.exception.UserExistsException;
import com.petproject.marketplace.model.entity.User;
import com.petproject.marketplace.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    public void registerUser(UserDTO userDTO) {
        if (userRepository.findByUsername(userDTO.getUsername()).isPresent())
            throw new UserExistsException();
        else {
            User user = new User(
                    userDTO.getName(),
                    userDTO.getSurname(),
                    userDTO.getUsername(),
                    passwordEncoder.encode(userDTO.getPassword())
            );
            userRepository.save(user);
        }
    }

    public boolean isAccountAvailable(LoginDTO loginDTO) {
        User user = userRepository.findByUsername(loginDTO.getUsername()).orElse(null);

        return user != null && passwordEncoder.matches(loginDTO.getPassword(), user.getPassword());
    }

    public String generateToken(LoginDTO loginDTO) {
        if (isAccountAvailable(loginDTO)) {
            throw new UserDoesNotExistsException();
        }

        User user = userRepository.findByUsername(loginDTO.getUsername()).orElse(null);

        return jwtUtil.generateToken(user);
    }
}