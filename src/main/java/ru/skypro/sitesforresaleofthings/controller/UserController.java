package ru.skypro.sitesforresaleofthings.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.skypro.sitesforresaleofthings.dto.NewPasswordDTO;
import ru.skypro.sitesforresaleofthings.dto.UpdateUserDTO;
import ru.skypro.sitesforresaleofthings.dto.UserDTO;
import ru.skypro.sitesforresaleofthings.service.ImageService;
import ru.skypro.sitesforresaleofthings.service.UserService;

import java.io.IOException;
import java.security.Principal;

/**
 * Крнтроллер для работы с пользователями
 */
@Slf4j
@CrossOrigin(value = "http://localhost:3000")
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@Tag(name = "Пользователи")
public class UserController {

    private final UserService userService;
//    private final ImageService imageService;

    @PostMapping("/set_password")   // http://localhost:8080/users/set_password
    @Operation(
            summary = "Обновление пароля"
    )
    @ApiResponse(
            responseCode = "200",
            description = "ОК"
    )
    @ApiResponse(
            responseCode = "401",
            description = "Unauthorized"
    )
    @ApiResponse(
            responseCode = "403",
            description = "Forbidden"
    )
    public ResponseEntity<?> setPassword(@RequestBody NewPasswordDTO password, Principal principal) {
        if (userService.setPassword(password, principal.getName())) {
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @GetMapping("/me")  // http://localhost:8080:/users/me
    @Operation(
            summary = "Получение информации об авторизованном пользователе"
    )
    @ApiResponse(
            responseCode = "200",
            description = "OK"
    )
    @ApiResponse(
            responseCode = "401",
            description = "Unauthorized"
    )
    public ResponseEntity<UserDTO> getUser(Principal principal) {
        try {
            UserDTO user = userService.getUser(principal.getName());
            return ResponseEntity.ok(user);
        } catch (RuntimeException e) {
            e.getStackTrace();
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @PatchMapping("/me")    // http://localhost:8080:/users/me
    @Operation(
            summary = "Обновление информации об авторизованном пользователе"
    )
    @ApiResponse(
            responseCode = "200",
            description = "OK"
    )
    @ApiResponse(
            responseCode = "401",
            description = "Unauthorized"
    )
    public ResponseEntity<UserDTO> updateUser(@RequestBody UserDTO dto, Principal principal) {
        try {
            UserDTO userDTO = userService.updateUser(dto, principal.getName());
            return ResponseEntity.ok(userDTO);
        } catch (RuntimeException e) {
            e.getStackTrace();
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    // http://localhost:8080:/users/me/image
    @Operation(
            summary = "Обновление аватара авторизованного пользователя"
    )
    @ApiResponse(
            responseCode = "200",
            description = "OK"
    )
    @ApiResponse(
            responseCode = "401",
            description = "Unauthorized"
    )
    public ResponseEntity<?> updateUserImage(@RequestPart(name = "image") MultipartFile image, Principal principal) {
        try {
            return ResponseEntity.ok().body(userService.updateUserImage(principal.getName(), image));
        } catch (RuntimeException e) {
            e.getStackTrace();
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}