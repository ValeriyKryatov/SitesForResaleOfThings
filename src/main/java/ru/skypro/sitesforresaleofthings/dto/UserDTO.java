package ru.skypro.sitesforresaleofthings.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import ru.skypro.sitesforresaleofthings.constant.Role;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;

/**
 * DTO пользователя
 */

/**
 * Свойства:
 * 1) id - id пользователя,
 * 2) email - электронная почта пользователя,
 * 3) firstName - имя пользователя,
 * 4) lastName - фамилия пользователя,
 * 5) phone - телефон пользователя,
 * 6) role - роль пользователя (USER, ADMIN),
 * 7) image - ссылка на аватар пользователя
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private Integer id;
    private String email;
    private String firstName;
    private String lastName;
    private String phone;
    @Enumerated(EnumType.STRING)
    private Role role;
    private String image;
}