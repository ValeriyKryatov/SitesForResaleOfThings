package ru.skypro.sitesforresaleofthings.service;

import ru.skypro.sitesforresaleofthings.constant.Role;
import ru.skypro.sitesforresaleofthings.dto.Register;

/**
 * Создаем интерфейс-сервис по работе с аутентификацией
 */
public interface AuthService {
    boolean login(String userName, String password);

    boolean register(Register register, Role role);
}