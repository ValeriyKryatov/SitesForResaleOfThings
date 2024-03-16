package ru.skypro.sitesforresaleofthings.service;

import org.springframework.web.multipart.MultipartFile;
import ru.skypro.sitesforresaleofthings.dto.NewPasswordDTO;
import ru.skypro.sitesforresaleofthings.dto.UpdateUserDTO;
import ru.skypro.sitesforresaleofthings.dto.UserDTO;

/**
 * Создаем интерфейс-сервис по работе с пользователями
 */
public interface UserService {
    /**
     * Обновление пароля
     *
     * @param password новый пароль
     * @param username информация о пользователе
     * @return true or false
     */
    boolean setPassword(NewPasswordDTO password, String username);

    /**
     * Получить информацию об авторизованном пользователе
     *
     * @param username информация о пользователе
     * @return пользователь
     */
    UserDTO getUser(String username);

    /**
     * Обновить информацию об авторизованном пользователе
     *
     * @param user     пользователь
     * @param username информация о пользователе
     * @return измененный пользователей
     */
    UpdateUserDTO updateUser(UpdateUserDTO user, String username);

    /**
     * Обновить аватар авторизованного пользователя
     *
     * @param username информация о пользователе
     * @param image    новый аватар
     * @return true or false
     */
    boolean updateUserImage(String username, MultipartFile image);
}