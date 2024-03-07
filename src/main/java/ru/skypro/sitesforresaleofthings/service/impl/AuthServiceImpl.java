package ru.skypro.sitesforresaleofthings.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import ru.skypro.sitesforresaleofthings.constant.Role;
import ru.skypro.sitesforresaleofthings.dto.Register;
import ru.skypro.sitesforresaleofthings.entity.UserEntity;
import ru.skypro.sitesforresaleofthings.exception.ValidationException;
import ru.skypro.sitesforresaleofthings.mapper.UserMapper;
import ru.skypro.sitesforresaleofthings.repository.UserRepository;
import ru.skypro.sitesforresaleofthings.service.AuthService;
import ru.skypro.sitesforresaleofthings.service.ValidationService;

/**
 * Реализация логики по работе с аутентификацией
 */
@Slf4j
@Service
@AllArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final PasswordEncoder encoder;
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final ValidationService validationService;
    private final UserDetailsService userDetailsService;

    @Override
    public boolean login(String userName, String password) {
        UserEntity user = userRepository.findByUsername(userName);
        if (user == null
                || !user.getUsername().equals(userName)
                && !user.getPassword().equals(encoder.encode(password))) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        UserDetails userDetails = userDetailsService.loadUserByUsername(userName);
        return encoder.matches(password, userDetails.getPassword());
    }

    @Override
    public boolean register(Register register, Role role) {
        UserEntity user = userRepository.findByUsername(register.getUsername());
        if (user != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        if (!validationService.validate(register)) {
            throw new ValidationException(register.toString());
        }
        try {
            register.setRole(role);
            register.setPassword(encoder.encode(register.getPassword()));
            UserEntity newUser = userMapper.mapToEntity(register);
            userRepository.save(newUser);
            return true;
        } catch (RuntimeException e) {
            e.getStackTrace();
            return false;
        }
    }
}