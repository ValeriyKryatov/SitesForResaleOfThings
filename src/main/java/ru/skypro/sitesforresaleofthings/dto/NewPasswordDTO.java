package ru.skypro.sitesforresaleofthings.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * DTO пароля
 */

/**
 * Свойства:
 * 1) currentPassword - текущий пароль,
 * 2) newPassword - новый пароль
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewPasswordDTO {

    @NotBlank
    @Size(min = 8, max = 16)
    private String currentPassword;
    @NotBlank
    @Size(min = 8, max = 16)
    private String newPassword;
}