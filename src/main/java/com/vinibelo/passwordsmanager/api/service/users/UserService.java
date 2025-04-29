package com.vinibelo.passwordsmanager.api.service.users;

import com.vinibelo.passwordsmanager.api.controller.user.dto.CreateUserDto;
import com.vinibelo.passwordsmanager.user.entity.User;
import com.vinibelo.passwordsmanager.user.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = new BCryptPasswordEncoder();
    }

    public void createUser(CreateUserDto createUserDto) {
        if (userRepository.findByUsername(createUserDto.username()).isPresent()) {
            throw new RuntimeException("User already exists");
        }

        String passwordEncoded = bCryptPasswordEncoder.encode(createUserDto.password());
        User newUser = new User();
        newUser.setUsername(createUserDto.username());
        newUser.setPassword(passwordEncoded);
        userRepository.save(newUser);
    }
}
