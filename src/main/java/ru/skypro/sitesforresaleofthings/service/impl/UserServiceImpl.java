package ru.skypro.sitesforresaleofthings.service.impl;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import ru.skypro.sitesforresaleofthings.dto.NewPasswordDTO;
import ru.skypro.sitesforresaleofthings.dto.UpdateUserDTO;
import ru.skypro.sitesforresaleofthings.dto.UserDTO;
import ru.skypro.sitesforresaleofthings.entity.UserEntity;
import ru.skypro.sitesforresaleofthings.exception.NotFoundEntityException;
import ru.skypro.sitesforresaleofthings.mapper.UserMapper;
import ru.skypro.sitesforresaleofthings.repository.UserRepository;
import ru.skypro.sitesforresaleofthings.service.ImageService;
import ru.skypro.sitesforresaleofthings.service.UserService;

/**
 * Реализация логики по работе с пользователями
 */
@Slf4j
@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final ImageService imageService;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;

    @Override
    @Transactional
    public boolean setPassword(NewPasswordDTO password, String username) {
        UserEntity user = userRepository.findByUsername(username);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        if (passwordEncoder.matches(password.getCurrentPassword(), user.getPassword())) {
            user.setPassword(passwordEncoder.encode(password.getNewPassword()));
            userRepository.save(user);
            return true;
        } else {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }

    }

    @Override
    public UserDTO getUser(String username) {
        UserEntity user = userRepository.findByUsername(username);
        if (user != null) {
            return userMapper.mapToDTO(user);
        } else {
            throw new NotFoundEntityException("Сущность не найдена!");
        }
    }

    @Override
    @Transactional
    public UpdateUserDTO updateUser(UpdateUserDTO user, String username) {
        UserEntity userDB = userRepository.findByUsername(username);
//        userDB.setId(user.getId());
        userDB.setFirstName(user.getFirstName());
        userDB.setLastName(user.getLastName());
        userDB.setPhone(user.getPhone());
//        userDB.setImage(user.getImage());
//        userDB.setEmail(user.getEmail());
        userRepository.save(userDB);
        return userMapper.updateMapToDTO(userDB);
    }

    @Override
    public boolean updateUserImage(String username, MultipartFile image) {
        String imageId = imageService.addImage(image);
        UserEntity user = userRepository.findByUsername(username);
        if (user == null) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
        user.setImage(imageId);
        userRepository.save(user);
        return true;
    }
}