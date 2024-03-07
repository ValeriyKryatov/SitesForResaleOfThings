package ru.skypro.sitesforresaleofthings.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.skypro.sitesforresaleofthings.entity.UserEntity;

import java.util.Optional;

/**
 * Создаем репозиторий для сущности "Пользователь"
 */

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Integer> {

    /**
     * Поиск пользователя по логину
     * @param username логин пользователя
     * @return найденный пользователь
     */
    UserEntity findByUsername(String username);
}