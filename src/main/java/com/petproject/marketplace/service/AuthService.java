package com.petproject.marketplace.service;

import com.petproject.marketplace.dto.LoginDTO;
import com.petproject.marketplace.dto.UserDTO;
import com.petproject.marketplace.exception.UserExistsException;
import com.petproject.marketplace.model.User;
import com.petproject.marketplace.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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
}